package cn.joey.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.joey.entity.SendOut;
import cn.joey.entity.SendOutDetail;



/**
 * 发货单Dao
 * @author Joey_Fe
 *
 */
@Repository
public interface SendOutDao {
	public int add(SendOut sendOut);
	public int addDetail(SendOutDetail sendOutDetail);
	public int editDetail(SendOutDetail sendOutDetail);
	public int edit(SendOut sendOut);
	public List<SendOut> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
