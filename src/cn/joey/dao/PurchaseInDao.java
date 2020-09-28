package cn.joey.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.joey.entity.PurchaseIn;
import cn.joey.entity.PurchaseInDetail;



/**
 * 采购单Dao
 * @author Joey_Fe
 *
 */
@Repository
public interface PurchaseInDao {
	public int add(PurchaseIn purchaseIn);
	public int addDetail(PurchaseInDetail purchaseInDetail);
	public int editDetail(PurchaseInDetail purchaseInDetail);
	public int edit(PurchaseIn purchaseIn);
	public List<PurchaseIn> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
