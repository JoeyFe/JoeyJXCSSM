package cn.joey.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.joey.entity.SellOut;



/**
 * 销售单service
 * @author Joey_Fe
 *
 */
@Service
public interface SellOutService {
	public int add(SellOut sellOut);
	public int edit(SellOut sellOut);
	public List<SellOut> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(String id);
}
