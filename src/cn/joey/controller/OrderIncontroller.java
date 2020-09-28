package cn.joey.controller;
/**
 * 进货单管理控制器
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

import cn.joey.entity.OrderIn;
import cn.joey.entity.OrderInDetail;
import cn.joey.entity.User;
import cn.joey.page.Page;
import cn.joey.service.OrderInService;
import cn.joey.service.SupplierService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/order_in")

public class OrderIncontroller {


	@Autowired 
	private OrderInService orderInService;
	@Autowired 
	private SupplierService supplierService;
	/**
	 * 进货单管理界面
	 * @param model
	 * @return
 	 */
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		model.addObject("supplierList", supplierService.findList(queryMap));
		model.setViewName("order_in/list");
		return model;
	}
	/**
	 * 搜索进货单表
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
		ret.put("total", orderInService.getTotal(queryMap));
		ret.put("rows", orderInService.findList(queryMap));
		return ret;
	}
	/**
	 * 添加进货单信息
	 * @param orderIn
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(String productList,OrderIn orderIn,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(productList)){
			ret.put("type", "error");
			ret.put("msg", "请至少选择一个商品信息!");
			return ret;
		}
		if (orderIn==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的进货单信息");
			return ret;
		}
		JSONArray productArray = JSONArray.fromObject(productList);
		float money = 0;
		int num = 0;

		for(int i = 0; i < productArray.size(); i++){
			JSONObject jsonObject = productArray.getJSONObject(i);
			OrderInDetail orderInDetail = new OrderInDetail();
			orderInDetail.setProductName(jsonObject.getString("name"));
			orderInDetail.setProductNum(jsonObject.getInt("productNum"));
			orderInDetail.setPrice(Float.valueOf(jsonObject.getString("price")));
			orderInDetail.setMoney(orderInDetail.getPrice() * orderInDetail.getProductNum());
			orderInDetail.setPid(jsonObject.getInt("id"));
			orderIn.getOrderInDetailList().add(orderInDetail);
			money += orderInDetail.getMoney();
			num += orderInDetail.getProductNum();
		}
		
		orderIn.setMoney(money);
		orderIn.setProductNum(num);
		User admin =(User) request.getSession().getAttribute("admin");//获取 登录的操作员姓名
		orderIn.setOperator(admin.getUsername());
		orderIn.setCreateTime(new Date());
		if(orderInService.add(orderIn)<=0)
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
	 * 编辑进货单信息
	 * @param orderIn
	 * @return
	 */
	@RequestMapping(value = "edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(String productList,OrderIn orderIn ,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(productList)){
			ret.put("type", "error");
			ret.put("msg", "修改商品信息不能为空!");
			return ret;
		}
		if (orderIn==null)
		{
			ret.put("type","error");
			ret.put("msg", "请选择正确的进货单信息");
			return ret;
		}
		JSONArray productArray = JSONArray.fromObject(productList);
		float money = 0;
		int num = 0;
		for(int i = 0; i < productArray.size(); i++){
			JSONObject jsonObject = productArray.getJSONObject(i);
			OrderInDetail orderInDetail = new OrderInDetail();
			orderInDetail.setProductName(jsonObject.getString("name"));
			orderInDetail.setProductNum(jsonObject.getInt("productNum"));
			orderInDetail.setPrice(Float.valueOf(jsonObject.getString("price")));
			orderInDetail.setPid(jsonObject.getInt("pid"));
			if(!jsonObject.getString("id").equals("null")) {
			orderInDetail.setId(jsonObject.getLong("id"));	
			}	
			orderInDetail.setMoney(orderInDetail.getPrice() * orderInDetail.getProductNum());
			orderIn.getOrderInDetailList().add(orderInDetail);
			money += orderInDetail.getMoney();
			num += orderInDetail.getProductNum();
		}
		orderIn.setMoney(money);
		orderIn.setProductNum(num);
		User admin =(User) request.getSession().getAttribute("admin");//获取 登录的操作员姓名
		orderIn.setOperator(admin.getUsername());
		if(orderInService.edit(orderIn)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "编辑失败");
		}
		ret.put("type","success");
		ret.put("msg", "编辑成功");
		return ret;
	}

}
