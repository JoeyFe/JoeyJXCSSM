package cn.joey.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.joey.entity.OrderIn;



/**
 * 进货单service
 * @author Joey_Fe
 *
 */
@Service
public interface OrderInService {
	public int add(OrderIn orderIn);
	public int edit(OrderIn orderIn);
	public List<OrderIn> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
