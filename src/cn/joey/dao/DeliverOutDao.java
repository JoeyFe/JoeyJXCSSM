package cn.joey.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.joey.entity.DeliverOut;
import cn.joey.entity.DeliverOutDetail;



/**
 * 采购单Dao
 * @author Joey_Fe
 *
 */
@Repository
public interface DeliverOutDao {
	public int add(DeliverOut deliverOut);
	public int addDetail(DeliverOutDetail deliverOutDetail);
	public int editDetail(DeliverOutDetail deliverOutDetail);
	public int edit(DeliverOut deliverOut);
	public List<DeliverOut> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
