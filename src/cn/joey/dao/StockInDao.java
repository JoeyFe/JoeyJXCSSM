package cn.joey.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.joey.entity.StockIn;
import cn.joey.entity.StockInDetail;



/**
 * 入库单Dao
 * @author Joey_Fe
 *
 */
@Repository
public interface StockInDao {
	public int add(StockIn stockIn);
	public int addDetail(StockInDetail stockInDetail);
	public int editDetail(StockInDetail stockInDetail);
	public int edit(StockIn stockIn);
	public List<StockIn> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
