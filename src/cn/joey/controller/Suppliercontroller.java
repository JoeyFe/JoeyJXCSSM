package cn.joey.controller;
/**
 * 供应商管理控制器
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

import cn.joey.entity.Supplier;
import cn.joey.page.Page;
import cn.joey.service.SupplierService;
@Controller
@RequestMapping("/supplier")

public class Suppliercontroller {
	@Autowired 
	private SupplierService supplierService;
	/**
	 * 供应商管理界面
	 * @param model
	 * @return
 	 */
	@RequestMapping(value = "/list", method =RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
	model.setViewName("supplier/list");
	return model;
	}
	/**
	 * 搜索供应商表
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getlist(
			@RequestParam(name = "name",defaultValue = "") String name,Page page
			){
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("total", supplierService.getTotal(queryMap));
		ret.put("rows", supplierService.findList(queryMap));
		return ret;
	}
	/**
	 * 添加供应商信息
	 * @param supplier
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Supplier supplier){
		Map<String, String> ret = new HashMap<String, String>();
		if (supplier==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的供应商信息");
			return ret;
		}
		if(StringUtils.isEmpty(supplier.getName())) {
			ret.put("type","error");
			ret.put("msg", "供应商名称");
		}
		if(supplierService.add(supplier)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "添加失败");
		}
		ret.put("type","success");
		ret.put("msg", "添加成功");
		return ret;
	}
	/**
	 * 编辑供应商信息
	 * @param supplier
	 * @return
	 */
	@RequestMapping(value = "edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Supplier supplier){
		Map<String, String> ret = new HashMap<String, String>();
		if (supplier==null)
		{
			ret.put("type","error");
			ret.put("msg", "请填写正确的供应商信息");
			return ret;
		}
		if(StringUtils.isEmpty(supplier.getName())) {
			ret.put("type","error");
			ret.put("msg", "供应商名称");
		}
		if(supplierService.edit(supplier)<=0)
		{
			ret.put("type","error");
			ret.put("msg", "编辑失败");
		}
		ret.put("type","success");
		ret.put("msg", "编辑成功");
		return ret;
	}
	/**
	 * 删除指定id供应商
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id==null) {
			ret.put("type","error");
			ret.put("msg", "选择供应商");
			return ret;
		}
		try {
			if(supplierService.delete(id)<=0)
			{
				ret.put("type","error");
				ret.put("msg", "删除失败");
				return ret;
			}
		} catch ( Exception e) {
			// TODO: handle exception
			ret.put("type","error");
			ret.put("msg", "先删除商品信息");
			return ret;
		}
		ret.put("type","success");
		ret.put("msg", "删除成功");
		return ret;
		}
}
