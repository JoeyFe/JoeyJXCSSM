package cn.joey.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.joey.entity.Stock;
/**
 * 库存Dao接口
 * @author Joey_Fe
 *
 */


@Repository
public interface StockDao {
	public int add(Stock stock);
	public int edit(Stock stock);
	public Stock findByProductId(Long id);
	public List<Stock> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(Long id);
}
