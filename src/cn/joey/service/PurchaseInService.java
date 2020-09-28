package cn.joey.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.joey.entity.PurchaseIn;



/**
 * 采购service
 * @author Joey_Fe
 *
 */
@Service
public interface PurchaseInService {
	public int add(PurchaseIn purchaseIn);
	public int edit(PurchaseIn purchaseIn);
	public List<PurchaseIn> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
