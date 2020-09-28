package cn.joey.controller;
/**
 * 退货单管理控制器
 * @author Joey_Fe
 *
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.joey.entity.SellBack;
import cn.joey.entity.SellBackDetail;
import cn.joey.entity.Stock;
import cn.joey.entity.User;
import cn.joey.page.Page;
import cn.joey.service.SellBackService;
import cn.joey.service.StockService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/sell_back")

public class SellBackcontroller {



	@Autowired 
	private StockService stockService ;
	@Autowired 
	private SellBackService sellBackService;
	/**
	 * 退货单管理界面
	 * @param model
	 * @return
 	 */
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
	
		model.setViewName("sell_back/list");
		return model;
	}
	/**
	 * 搜索退货单表
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getlist(
			@RequestParam(name = "payType",required = false) Integer payType,
			@RequestParam(name = "status",required = false) Integer status,
			@RequestParam(name = "minMoney",required = false) Float minMoney,
			@RequestParam(name = "maxMoney",required = false) Float maxMoney,
			@RequestParam(name = "operator",defaultValue ="") String operator,Page page
			){
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("operator", operator);
		if(payType!=null)
		{
			queryMap.put("payType", payType);
		}
		if(status!=null)
		{
			queryMap.put("status", status);
		}
		if(minMoney!=null)
		{
			queryMap.put("minMoney", minMoney);
		}
		if(maxMoney!=null)
		{
			queryMap.put("maxMoney", maxMoney);
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("total", sellBackService.getTotal(queryMap));
		ret.put("rows", sellBackService.findList(queryMap));
		return ret;
	}
	/**
	 * 添加退货单信息
	 * @param sellBack
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(String productList,SellBack sellBack,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(productList)){
			ret.put("type", "error");
			ret.put("msg", "请至少选择一个商品信息!");
			return ret;
		}
		if (sellBack==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的退货单信息");
			return ret;
		}
		JSONArray productArray = JSONArray.fromObject(productList);
		float money = 0;
		int num = 0;
		List<Stock> stockList = new ArrayList<Stock>();
		for(int i = 0; i < productArray.size(); i++){
			JSONObject jsonObject = productArray.getJSONObject(i);
			SellBackDetail sellBackDetail = new SellBackDetail();
			sellBackDetail.setProductName(jsonObject.getString("name"));
			sellBackDetail.setProductNum(jsonObject.getInt("productNum"));
			sellBackDetail.setPrice(Float.valueOf(jsonObject.getString("price")));
			sellBackDetail.setMoney(sellBackDetail.getPrice() * sellBackDetail.getProductNum());
			sellBackDetail.setPid(jsonObject.getInt("id"));
			sellBack.getSellBackDetailList().add(sellBackDetail);
			money += sellBackDetail.getMoney();
			num += sellBackDetail.getProductNum();
			Stock stock = new Stock();
			stock.setProductId(Long.valueOf(jsonObject.getInt("id")+""));
			stock.setProductNum(sellBackDetail.getProductNum());
			stock.setSellNum(0);
			stockList.add(stock);
		}
		
		sellBack.setMoney(money);
		sellBack.setProductNum(num);
		User admin =(User) request.getSession().getAttribute("admin");//获取 登录的操作员姓名
		sellBack.setOperator(admin.getUsername());
		sellBack.setCreateTime(new Date());
		if(sellBackService.add(sellBack)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "添加失败");
			return ret;
		}
		//进行退货操作
				if(sellBack.getStatus()==1) {
					addStock(stockList);
				}
				ret.put("type", "success");
				ret.put("msg", "退货单添加成功!");
				return ret;
	}
	/**
	 * 编辑进货单信息
	 * @param sellDetailList
	 * @param sellBack
	 * @return
	 */
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(String sellDetailList,SellBack sellBack){
		Map<String, String> ret = new HashMap<String, String>();
		if(sellBack == null){
			ret.put("type", "error");
			ret.put("msg", "请选择正确的进货单信息!");
			return ret;
		}
		sellBack.setStatus(1);
		JSONArray sellBackDetailArray = JSONArray.fromObject(sellDetailList);
		List<Stock> stockList = new ArrayList<Stock>();

		for(int i = 0; i < sellBackDetailArray.size(); i++){
			JSONObject jsonObject = sellBackDetailArray.getJSONObject(i);
			Stock stock = new Stock();
			stock.setProductId(Long.valueOf(jsonObject.getInt("pid")+""));
			stock.setProductNum(jsonObject.getInt("productNum"));
			stock.setSellNum(0);
			stockList.add(stock);
		}
		if(sellBackService.edit(sellBack) <= 0){
			ret.put("type", "error");
			ret.put("msg", "编辑失败，请联系管理员!");
			return ret;
		}
		addStock(stockList);
		ret.put("type", "success");
		ret.put("msg", "编辑成功!");
		return ret;
	}
	/**
	 * 商品入库
	 * @param stockList
	 */
	private void addStock(List<Stock> stockList){
		for(Stock stock : stockList){
			Stock existStock = stockService.findByProductId(stock.getProductId());
			existStock.setProductNum(existStock.getProductNum() + stock.getProductNum());
			stockService.edit(existStock);
		}
	}
}
