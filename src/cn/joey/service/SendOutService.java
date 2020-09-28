package cn.joey.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.joey.entity.SendOut;



/**
 * 发货service
 * @author Joey_Fe
 *
 */
@Service
public interface SendOutService {
	public int add(SendOut sendOut);
	public int edit(SendOut sendOut);
	public List<SendOut> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
