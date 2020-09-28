package cn.joey.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.joey.entity.Supplier;

/**
 * 供应商dao
 * @author Joey_Fe
 *
 */
@Repository
public interface SupplierDao {
	public int add(Supplier supplier);
	public int edit(Supplier supplier);
	public List<Supplier> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete (Long id);
}
