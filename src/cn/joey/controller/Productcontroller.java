package cn.joey.controller;
/**
 * 商品管理控制器
 * @author Joey_Fe
 *
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.joey.entity.Product;
import cn.joey.page.Page;
import cn.joey.service.ProductService;
import cn.joey.service.SupplierService;

@Controller
@RequestMapping("/product")

public class Productcontroller {
	@Autowired 
	private SupplierService supplierService;
	@Autowired 
	private ProductService productService;
	/**
	 * 商品管理界面
	 * @param model
	 * @return
 	 */
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
	Map<String, Object> queryMap=new HashMap<String, Object>();
	queryMap.put("offset", 0);
	queryMap.put("pageSize", 999);
	model.addObject("supplierList", supplierService.findList(queryMap));
	model.setViewName("product/list");
	return model;
	}
	/**
	 * 搜索商品表
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getlist(
			@RequestParam(name = "supplierId",required = false) Long supplierId,
			@RequestParam(name = "name",defaultValue = "") String name,Page page
			){
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("name", name);
		if(supplierId!=null)
		{
			queryMap.put("supplierId", supplierId);
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("total", productService.getTotal(queryMap));
		ret.put("rows", productService.findList(queryMap));
		return ret;
	}
	/**
	 * 添加商品信息
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Product product){
		Map<String, String> ret = new HashMap<String, String>();
		if (product==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的商品信息");
			return ret;
		}
		if(StringUtils.isEmpty(product.getName())) {
			ret.put("type","error");
			ret.put("msg", "商品名称");
			return ret;
		}
		if(product.getSupplierId()==null)
		{
			ret.put("type","error");
			ret.put("msg", "选择供应商");
			return ret;
		}
		if(productService.add(product)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "添加失败");
			return ret;
		}
		ret.put("type","success");
		ret.put("msg", "添加成功");
		return ret;
	}
	/**
	 * 编辑商品信息
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Product product){
		Map<String, String> ret = new HashMap<String, String>();
		if (product==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的商品信息");
			return ret;
		}
		if(StringUtils.isEmpty(product.getName())) {
			ret.put("type","error");
			ret.put("msg", "商品名称");
			return ret;
		}

		if(productService.edit(product)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "编辑失败");
		}
		ret.put("type","success");
		ret.put("msg", "编辑成功");
		return ret;
	}
	/**
	 * 删除指定id商品
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id==null) {
			ret.put("type","error");
			ret.put("msg", "选择商品");
			return ret;
		}
		try {
			if(productService.delete(id)<=0)
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
