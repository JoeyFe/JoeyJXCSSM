package cn.joey.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.joey.entity.SellOut;
import cn.joey.entity.SellOutDetail;



/**
 * 销售单Dao
 * @author Joey_Fe
 *
 */
@Repository
public interface SellOutDao {
	public int addDetail(SellOutDetail sellOutDetail);
	public int editDetail(SellOutDetail sellOutDetail);
	public int add(SellOut sellOut);
	public int edit(SellOut sellOut);
	public List<SellOut> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
