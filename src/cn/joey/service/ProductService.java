package cn.joey.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.joey.entity.Product;



/**
 * 商品service
 * @author Joey_Fe
 *
 */
@Service
public interface ProductService {
	public int add(Product product);
	public int edit(Product product);
	public List<Product> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(Long id);
}
