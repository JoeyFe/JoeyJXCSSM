package cn.joey.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.joey.entity.OrderIn;
import cn.joey.entity.OrderInDetail;



/**
 * 进货单Dao
 * @author Joey_Fe
 *
 */
@Repository
public interface OrderInDao {
	public int add(OrderIn orderIn);
	public int addDetail(OrderInDetail orderInDetail);
	public int editDetail(OrderInDetail orderInDetail);
	public int edit(OrderIn orderIn);
	public List<OrderIn> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
