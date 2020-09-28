package cn.joey.controller;
/**
 * 销售单管理控制器
 * @author Joey_Fe
 *
 */

import java.util.Date;
import java.util.HashMap;
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

import cn.joey.entity.SellOut;
import cn.joey.entity.SellOutDetail;
import cn.joey.entity.User;
import cn.joey.page.Page;
import cn.joey.service.SellOutService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/sell_out")

public class SellOutcontroller {

	@Autowired 
	private SellOutService sellOutService;

	/**
	 * 销售单管理界面
	 * @param model
	 * @return
 	 */
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
	
		model.setViewName("sell_out/list");
		return model;
	}
	/**
	 * 搜索销售单表
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
		ret.put("total", sellOutService.getTotal(queryMap));
		ret.put("rows", sellOutService.findList(queryMap));
		return ret;
	}
	/**
	 * 添加销售单信息
	 * @param sellOut
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(String productList,SellOut sellOut,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(productList)){
			ret.put("type", "error");
			ret.put("msg", "请至少选择一个商品信息!");
			return ret;
		}
		if (sellOut==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的销售单信息");
			return ret;
		}
		JSONArray productArray = JSONArray.fromObject(productList);
		float money = 0;
		int num = 0;
		for(int i = 0; i < productArray.size(); i++){
			JSONObject jsonObject = productArray.getJSONObject(i);
			SellOutDetail sellOutDetail = new SellOutDetail();
			sellOutDetail.setProductName(jsonObject.getString("name"));
			sellOutDetail.setProductNum(jsonObject.getInt("productNum"));
			sellOutDetail.setPrice(Float.valueOf(jsonObject.getString("price")));
			sellOutDetail.setMoney(sellOutDetail.getPrice() * sellOutDetail.getProductNum());
			sellOutDetail.setPid(jsonObject.getInt("id"));
			sellOut.getSellOutDetailList().add(sellOutDetail);
			money += sellOutDetail.getMoney();
			num += sellOutDetail.getProductNum();
		}		
		sellOut.setMoney(money);
		sellOut.setProductNum(num);
		User admin =(User) request.getSession().getAttribute("admin");//获取 登录的操作员姓名
		sellOut.setOperator(admin.getUsername());
		sellOut.setCreateTime(new Date());
		if(sellOutService.add(sellOut)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "添加失败");
			return ret;
		}

		ret.put("type", "success");
		ret.put("msg", "添加成功!");
		return ret;
	}
	/**
	 * 编辑销售单信息
	 * @param sellOut
	 * @return
	 */
	@RequestMapping(value = "edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(String productList,SellOut sellOut ,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(productList)){
			ret.put("type", "error");
			ret.put("msg", "修改商品信息不能为空!");
			return ret;
		}
		if (sellOut==null)
		{
			ret.put("type","error");
			ret.put("msg", "请选择正确的销售单信息");
			return ret;
		}
		JSONArray productArray = JSONArray.fromObject(productList);
		float money = 0;
		int num = 0;
		for(int i = 0; i < productArray.size(); i++){
			JSONObject jsonObject = productArray.getJSONObject(i);
			SellOutDetail sellOutDetail = new SellOutDetail();
			sellOutDetail.setProductName(jsonObject.getString("name"));
			sellOutDetail.setProductNum(jsonObject.getInt("productNum"));
			sellOutDetail.setPrice(Float.valueOf(jsonObject.getString("price")));
			sellOutDetail.setPid(jsonObject.getInt("pid"));
			if(!jsonObject.getString("id").equals("null")) {
			sellOutDetail.setId(jsonObject.getLong("id"));	
			}	
			sellOutDetail.setMoney(sellOutDetail.getPrice() * sellOutDetail.getProductNum());
			sellOut.getSellOutDetailList().add(sellOutDetail);
			money += sellOutDetail.getMoney();
			num += sellOutDetail.getProductNum();
		}
		sellOut.setMoney(money);
		sellOut.setProductNum(num);
		User admin =(User) request.getSession().getAttribute("admin");//获取 登录的操作员姓名
		sellOut.setOperator(admin.getUsername());
		if(sellOutService.edit(sellOut)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "编辑失败");
		}
		ret.put("type","success");
		ret.put("msg", "编辑成功");
		return ret;
	}
}
