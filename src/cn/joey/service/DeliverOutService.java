package cn.joey.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.joey.entity.DeliverOut;



/**
 * 发货出库service
 * @author Joey_Fe
 *
 */
@Service
public interface DeliverOutService {
	public int add(DeliverOut deliverOut);
	public int edit(DeliverOut deliverOut);
	public List<DeliverOut> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
