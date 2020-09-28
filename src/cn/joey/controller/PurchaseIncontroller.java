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

import cn.joey.entity.PurchaseIn;
import cn.joey.entity.PurchaseInDetail;
import cn.joey.entity.User;
import cn.joey.page.Page;
import cn.joey.service.PurchaseInService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/purchase_in")

public class PurchaseIncontroller {


	@Autowired 
	private PurchaseInService purchaseInService;

	/**
	 * 进货单管理界面
	 * @param model
	 * @return
 	 */
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
	
		model.setViewName("purchase_in/list");
		return model;
	}
	/**
	 * 搜索进货单表
	 * @param payType
	 * @param status
	 * @param minMoney
	 * @param maxMoney
	 * @param operator
	 * @param page
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
		ret.put("total", purchaseInService.getTotal(queryMap));
		ret.put("rows", purchaseInService.findList(queryMap));
		return ret;
	}
	/**
	 * 添加进货单信息
	 * @param productList
	 * @param purchaseIn
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(String productList,PurchaseIn purchaseIn,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(productList)){
			ret.put("type", "error");
			ret.put("msg", "请至少选择一个商品信息!");
			return ret;
		}
		if (purchaseIn==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的进货单信息");
			return ret;
		}
		JSONArray productArray = JSONArray.fromObject(productList);
		float money = 0;
		int num = 0;
		//List<Stock> stockList = new ArrayList<Stock>();
		for(int i = 0; i < productArray.size(); i++){
			JSONObject jsonObject = productArray.getJSONObject(i);
			PurchaseInDetail purchaseInDetail = new PurchaseInDetail();
			purchaseInDetail.setProductName(jsonObject.getString("name"));
			purchaseInDetail.setProductNum(jsonObject.getInt("productNum"));
			purchaseInDetail.setPrice(Float.valueOf(jsonObject.getString("price")));
			purchaseInDetail.setMoney(purchaseInDetail.getPrice() * purchaseInDetail.getProductNum());
			purchaseInDetail.setPid(jsonObject.getInt("id"));
			purchaseIn.getPurchaseInDetailList().add(purchaseInDetail);
			money += purchaseInDetail.getMoney();
			num += purchaseInDetail.getProductNum();
		}
		
		purchaseIn.setMoney(money);
		purchaseIn.setProductNum(num);
		User admin =(User) request.getSession().getAttribute("admin");
		purchaseIn.setOperator(admin.getUsername());
		purchaseIn.setCreateTime(new Date());
		if(purchaseInService.add(purchaseIn)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "添加失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功!");
		return ret;
	}
}
