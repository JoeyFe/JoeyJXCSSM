package cn.joey.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.joey.entity.Product;



/**
 * productDao
 * @author Joey_Fe
 *
 */
@Repository
public interface ProductDao {
	public int add(Product product);
	public int edit(Product product);
	public List<Product> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(Long id);
}
