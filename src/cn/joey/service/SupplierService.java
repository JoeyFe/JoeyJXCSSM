package cn.joey.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.joey.entity.Supplier;

/**
 * 供应商接口
 * @author Joey_Fe
 *
 */
@Service
public interface SupplierService {
	public int add(Supplier supplier);
	public int edit(Supplier supplier);
	public List<Supplier> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete (Long id);
	
}
