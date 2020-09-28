package cn.joey.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.joey.entity.SellBack;
import cn.joey.entity.SellBackDetail;



/**
 * 退货单Dao
 * @author Joey_Fe
 *
 */
@Repository
public interface SellBackDao {
	public int add(SellBack sellBack);
	public int addDetail(SellBackDetail sellBackDetail);
	public int editDetail(SellBackDetail sellBackDetail);
	public int edit(SellBack sellBack);
	public List<SellBack> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
