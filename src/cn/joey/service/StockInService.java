package cn.joey.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.joey.entity.StockIn;



/**
 * 入库service
 * @author Joey_Fe
 *
 */
@Service
public interface StockInService {
	public int add(StockIn stockIn);
	public int edit(StockIn stockIn);
	public List<StockIn> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
