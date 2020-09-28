package cn.joey.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.joey.entity.SellBack;



/**
 * 退货service
 * @author Joey_Fe
 *
 */
@Service
public interface SellBackService {
	public int add(SellBack sellBack);
	public int edit(SellBack sellBack);
	public List<SellBack> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
