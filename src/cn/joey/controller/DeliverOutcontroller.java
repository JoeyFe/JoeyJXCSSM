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

import cn.joey.entity.DeliverOut;
import cn.joey.entity.DeliverOutDetail;
import cn.joey.entity.Stock;
import cn.joey.entity.User;
import cn.joey.page.Page;
import cn.joey.service.DeliverOutService;
import cn.joey.service.StockService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/deliver_out")

public class DeliverOutcontroller {



	@Autowired 
	private StockService stockService ;
	@Autowired 
	private DeliverOutService deliverOutService;
	/**
	 * 入库单管理界面
	 * @param model
	 * @return
 	 */
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
	
		model.setViewName("deliver_out/list");
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
		ret.put("total", deliverOutService.getTotal(queryMap));
		ret.put("rows", deliverOutService.findList(queryMap));
		return ret;
	}
	/**
	 * 添加入库单信息
	 * @param deliverOut
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(String productList,DeliverOut deliverOut,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(productList)){
			ret.put("type", "error");
			ret.put("msg", "请至少选择一个商品信息!");
			return ret;
		}
		if (deliverOut==null)
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
			DeliverOutDetail deliverOutDetail = new DeliverOutDetail();
			deliverOutDetail.setProductName(jsonObject.getString("name"));
			deliverOutDetail.setProductNum(jsonObject.getInt("productNum"));
			deliverOutDetail.setPrice(Float.valueOf(jsonObject.getString("price")));
			deliverOutDetail.setMoney(deliverOutDetail.getPrice() * deliverOutDetail.getProductNum());
			deliverOutDetail.setPid(jsonObject.getInt("id"));
			deliverOut.getDeliverOutDetailList().add(deliverOutDetail);
			money += deliverOutDetail.getMoney();
			num += deliverOutDetail.getProductNum();
			Stock stock = new Stock();
			stock.setProductId(Long.valueOf(jsonObject.getInt("id")+""));
			stock.setProductNum(deliverOutDetail.getProductNum());
			stock.setSellNum(0);
			stockList.add(stock);
		}
		
		deliverOut.setMoney(money);
		deliverOut.setProductNum(num);
		User admin =(User) request.getSession().getAttribute("admin");//获取 登录的操作员姓名
		deliverOut.setOperator(admin.getUsername());
		deliverOut.setCreateTime(new Date());
		if(deliverOutService.add(deliverOut)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "添加失败");
			return ret;
		}
		//进行入库操作
		if(deliverOut.getStatus()==1) {
			for(Stock stock : stockList){
				Stock existStock = stockService.findByProductId(stock.getProductId());
				existStock.setProductNum(existStock.getProductNum() - stock.getProductNum());
				if(existStock.getProductNum()<0) {
					ret.put("type","outstock");
					ret.put("msg", "库存不足");
					deliverOut.setStatus(0);
					deliverOutService.edit(deliverOut);
					
					return ret;
				}
			}
					updateStock(stockList);
					ret.put("type", "success");
					ret.put("msg", "出库成功!");
					return ret;
					}
		ret.put("type", "success");
		ret.put("msg", "添加出库单成功,商品未出库");
		return ret;
	}
	/**
	 * 出库操作
	 * @param deliverDetailList
	 * @param deliverOut
	 * @return
	 */
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(String deliverDetailList,DeliverOut deliverOut){
		Map<String, String> ret = new HashMap<String, String>();
		if(deliverOut == null){
			ret.put("type", "error");
			ret.put("msg", "请选择正确的进货单信息!");
			return ret;
		}
		deliverOut.setStatus(1);
		JSONArray deliverOutDetailArray = JSONArray.fromObject(deliverDetailList);
		List<Stock> stockList = new ArrayList<Stock>();

		for(int i = 0; i < deliverOutDetailArray.size(); i++){
			JSONObject jsonObject = deliverOutDetailArray.getJSONObject(i);
			Stock stock = new Stock();
			stock.setProductId(Long.valueOf(jsonObject.getInt("pid")+""));
			stock.setProductNum(jsonObject.getInt("productNum"));
			stock.setSellNum(0);
			stockList.add(stock);
		}
		if(deliverOut.getStatus()==1) {
			for(Stock stock : stockList){
				Stock existStock = stockService.findByProductId(stock.getProductId());
				existStock.setProductNum(existStock.getProductNum() - stock.getProductNum());
				if(existStock.getProductNum()<0) {
					ret.put("type","outstock");
					ret.put("msg", "库存不足");
					deliverOut.setStatus(0);
					deliverOutService.edit(deliverOut);
					
					return ret;
				}
			}
					updateStock(stockList);
					ret.put("type", "success");
					ret.put("msg", "出库成功!");
					return ret;
					}
		ret.put("type", "success");
		ret.put("msg", "出库成功!");
		return ret;
	}
	/**
	 * 商品出库
	 * @param stockList
	 */
	private void updateStock(List<Stock> stockList){
		for(Stock stock : stockList){
			Stock existStock = stockService.findByProductId(stock.getProductId());
			//走到这里表示数据库中已经存在，则更新商品数量
			existStock.setProductNum(existStock.getProductNum() - stock.getProductNum());
			existStock.setSellNum(existStock.getSellNum() + stock.getProductNum());
			stockService.edit(existStock);
		}
	}
}
