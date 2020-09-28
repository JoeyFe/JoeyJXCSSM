package cn.joey.controller;
/**
 * 入库单管理控制器
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

import cn.joey.entity.Stock;
import cn.joey.entity.StockIn;
import cn.joey.entity.StockInDetail;
import cn.joey.entity.User;
import cn.joey.page.Page;
import cn.joey.service.StockInService;
import cn.joey.service.StockService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/stock_in")

public class StockIncontroller {



	@Autowired 
	private StockInService stockInService;
	@Autowired 
	private StockService stockService ;
	/**
	 * 入库单管理界面
	 * @param model
	 * @return
 	 */
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		
		model.setViewName("stock_in/list");
		return model;
	}
	/**
	 * 搜索入库单表
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
		ret.put("total", stockInService.getTotal(queryMap));
		ret.put("rows", stockInService.findList(queryMap));
		return ret;
	}
	/**
	 * 添加入库单信息
	 * @param stockIn
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(String productList,StockIn stockIn,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(productList)){
			ret.put("type", "error");
			ret.put("msg", "请至少选择一个商品信息!");
			return ret;
		}
		if (stockIn==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的入库单信息");
			return ret;
		}
		JSONArray productArray = JSONArray.fromObject(productList);
		float money = 0;
		int num = 0;
		List<Stock> stockList = new ArrayList<Stock>();
		for(int i = 0; i < productArray.size(); i++){
			JSONObject jsonObject = productArray.getJSONObject(i);
			StockInDetail stockInDetail = new StockInDetail();
			stockInDetail.setProductName(jsonObject.getString("name"));
			stockInDetail.setProductNum(jsonObject.getInt("productNum"));
			stockInDetail.setPrice(Float.valueOf(jsonObject.getString("price")));
			stockInDetail.setMoney(stockInDetail.getPrice() * stockInDetail.getProductNum());
			stockInDetail.setPid(jsonObject.getInt("id"));
			stockIn.getStockInDetailList().add(stockInDetail);
			money += stockInDetail.getMoney();
			num += stockInDetail.getProductNum();
			Stock stock = new Stock();
			stock.setProductId(Long.valueOf(jsonObject.getInt("id")+""));
			stock.setProductNum(stockInDetail.getProductNum());
			stock.setSellNum(0);
			stockList.add(stock);
		}
		
		stockIn.setMoney(money);
		stockIn.setProductNum(num);
		User admin =(User) request.getSession().getAttribute("admin");//获取 登录的操作员姓名
		stockIn.setOperator(admin.getUsername());
		stockIn.setCreateTime(new Date());
		if(stockInService.add(stockIn)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "添加失败");
			return ret;
		}
		//进行入库操作
		if(stockIn.getStatus()==1) {
			addStock(stockList);
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功!");
		return ret;
	}
	/**
	 * 编辑进货单信息
	 * @param product
	 * @return
	 */
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(String stockDetailList,StockIn stockIn){
		Map<String, String> ret = new HashMap<String, String>();
		if(stockIn == null){
			ret.put("type", "error");
			ret.put("msg", "请选择正确的进货单信息!");
			return ret;
		}
		stockIn.setStatus(1);
		JSONArray stockInDetailArray = JSONArray.fromObject(stockDetailList);
		List<Stock> stockList = new ArrayList<Stock>();

		for(int i = 0; i < stockInDetailArray.size(); i++){
			JSONObject jsonObject = stockInDetailArray.getJSONObject(i);
			Stock stock = new Stock();
			stock.setProductId(Long.valueOf(jsonObject.getInt("pid")+""));
			stock.setProductNum(jsonObject.getInt("productNum"));
			stock.setSellNum(0);
			stockList.add(stock);
		}
		if(stockInService.edit(stockIn) <= 0){
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
			if(existStock == null){
				//表示库存里还没有这个商品
				stockService.add(stock);
				continue;
			}
			//走到这里表示数据库中已经存在，则更新商品数量
			existStock.setProductNum(existStock.getProductNum() + stock.getProductNum());
			stockService.edit(existStock);
		}
	}
}
