package cn.joey.controller;
/**
 * 商品库存管理控制器
 * @author Joey_Fe
 *
 */

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.joey.entity.Stock;
import cn.joey.page.Page;
import cn.joey.service.StockService;
import cn.joey.service.SupplierService;

@Controller
@RequestMapping("/stock")

public class Stockcontroller {
	@Autowired 
	private StockService stockService;
	@Autowired
	private SupplierService supplierService;

	/**
	 * 商品库存管理界面
	 * @param model
	 * @return
 	 */
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 9999);
		model.addObject("supplierList", supplierService.findList(queryMap));
	model.setViewName("stock/list");
	return model;
	}
	/**
	 * 搜索商品库存表
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getlist(
			@RequestParam(name = "productNum",required = false) Integer productNum,
			@RequestParam(name="supplierId",required=false) Long supplierId,
			@RequestParam(name = "productName",defaultValue = "") String productName,Page page
			){
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("productName", productName);
		if(supplierId != null){
			queryMap.put("supplierId", supplierId);
		}
		if(productNum!=null)
		{
			queryMap.put("productName", productNum);
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("total", stockService.getTotal(queryMap));
		ret.put("rows", stockService.findList(queryMap));
		return ret;
	}
	
	/**
	 * 编辑商品库存信息
	 * @param stock
	 * @return
	 */
	@RequestMapping(value = "edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Stock stock){
		Map<String, String> ret = new HashMap<String, String>();
		if (stock==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的商品库存信息");
			return ret;
		}
		if(stock.getProductNum()<0) {
			ret.put("type","error");
			ret.put("msg", "商品库存数量不能小于零");
			return ret;
		}

		if(stockService.edit(stock)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "编辑失败");
		}
		ret.put("type","success");
		ret.put("msg", "编辑成功");
		return ret;
	}
	/**
	 * 删除指定id商品库存
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id==null) {
			ret.put("type","error");
			ret.put("msg", "选择商品库存");
			return ret;
		}
		try {
			if(stockService.delete(id)<=0)
			{
				ret.put("type","error");
				ret.put("msg", "删除失败");
				return ret;
			}
		} catch ( Exception e) {
			// TODO: handle exception
			ret.put("type","error");
			ret.put("msg", "先删除入库销售");
			return ret;
		}
		ret.put("type","success");
		ret.put("msg", "删除成功");
		return ret;
		}
}
