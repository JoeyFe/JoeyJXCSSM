package cn.joey.controller;
/**
 * 出库单管理控制器
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
import cn.joey.entity.SendOut;
import cn.joey.entity.SendOutDetail;
import cn.joey.entity.Stock;
import cn.joey.entity.User;
import cn.joey.page.Page;
import cn.joey.service.DeliverOutService;
import cn.joey.service.SendOutService;
import cn.joey.service.StockService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/send_out")

public class SendOutcontroller {



	@Autowired 
	private StockService stockService ;
	@Autowired 
	private SendOutService sendOutService;
	/**
	 * 入库单管理界面
	 * @param model
	 * @return
 	 */
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
	
		model.setViewName("send_out/list");
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
		ret.put("total", sendOutService.getTotal(queryMap));
		ret.put("rows", sendOutService.findList(queryMap));
		return ret;
	}
	/**
	 * 添加发货单信息
	 * @param sendOut
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(String productList,SendOut sendOut,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(productList)){
			ret.put("type", "error");
			ret.put("msg", "请至少选择一个商品信息!");
			return ret;
		}
		if (sendOut==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的入库单信息");
			return ret;
		}
		JSONArray productArray = JSONArray.fromObject(productList);
		float money = 0;
		int num = 0;
		for(int i = 0; i < productArray.size(); i++){
			JSONObject jsonObject = productArray.getJSONObject(i);
			SendOutDetail sendOutDetail = new SendOutDetail();
			sendOutDetail.setProductName(jsonObject.getString("name"));
			sendOutDetail.setProductNum(jsonObject.getInt("productNum"));
			sendOutDetail.setPrice(Float.valueOf(jsonObject.getString("price")));
			sendOutDetail.setMoney(sendOutDetail.getPrice() * sendOutDetail.getProductNum());
			sendOutDetail.setPid(jsonObject.getInt("id"));
			sendOut.getSendOutDetailList().add(sendOutDetail);
			money += sendOutDetail.getMoney();
			num += sendOutDetail.getProductNum();
		}
		sendOut.setMoney(money);
		sendOut.setProductNum(num);
		User admin =(User) request.getSession().getAttribute("admin");//获取 登录的操作员姓名
		sendOut.setOperator(admin.getUsername());
		sendOut.setCreateTime(new Date());
		if(sendOutService.add(sendOut)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "添加失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加出库单成功,商品未出库");
		return ret;
	}
	/**
	 * 出库操作
	 * @param sendDetailList
	 * @param sendOut
	 * @return
	 */
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(String sendDetailList,SendOut sendOut){
		Map<String, String> ret = new HashMap<String, String>();
		if(sendOut == null){
			ret.put("type", "error");
			ret.put("msg", "请选择正确的进货单信息!");
			return ret;
		}
		sendOut.setStatus(1);
		ret.put("type", "success");
		ret.put("msg", "出库成功!");
		return ret;
	}
}
