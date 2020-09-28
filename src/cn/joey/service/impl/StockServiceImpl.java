package cn.joey.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.joey.dao.StockDao;
import cn.joey.entity.Stock;
import cn.joey.service.StockService;
/**
 * stock service 方法
 * @author Joey_Fe
 *
 */
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDao stockDao;

	@Override
	public int add(Stock stock) {
		// TODO Auto-generated method stub
		return stockDao.add(stock);
	}

	@Override
	public int edit(Stock stock) {
		// TODO Auto-generated method stub
		return stockDao.edit(stock);
	}

	@Override
	public Stock findByProductId(Long id) {
		// TODO Auto-generated method stub
		return stockDao.findByProductId(id);
	}

	@Override
	public List<Stock> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return stockDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return stockDao.getTotal(queryMap);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return stockDao.delete(id);
	}
	
}
