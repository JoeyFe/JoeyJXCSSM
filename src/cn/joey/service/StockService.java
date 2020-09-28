package cn.joey.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.joey.entity.Stock;

/**
 * 库存service接口
 * @author Joey_Fe
 *
 */
@Service
public interface StockService {
	public int add(Stock stock);
	public int edit(Stock stock);
	public Stock findByProductId(Long id);
	public List<Stock> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(Long id);
}
