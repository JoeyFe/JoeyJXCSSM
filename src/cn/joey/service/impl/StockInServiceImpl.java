package cn.joey.service.impl;
/**
 * 入库单service方法实现
 */
import java.util.List;
import java.util.Map;

/**

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.joey.dao.StockInDao;
import cn.joey.entity.StockIn;
import cn.joey.entity.StockInDetail;
import cn.joey.service.StockInService;

@Service
public class StockInServiceImpl implements StockInService {
	@Autowired
	private StockInDao stockInDao;

	@Override
	public int add(StockIn stockIn) {
		// TODO Auto-generated method stub
		int rst = stockInDao.add(stockIn);
		if(rst > 0){
			//表示进货单添加成功,接下来添加进货单子项
			for(StockInDetail stockInDetail : stockIn.getStockInDetailList()){
				stockInDetail.setStockInId(stockIn.getId());
				stockInDao.addDetail(stockInDetail);
			}
		}
		return rst;
	}

	@Override
	public int edit(StockIn stockIn) {
		// TODO Auto-generated method stub
		return stockInDao.edit(stockIn);
	}

	@Override
	public List<StockIn> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return stockInDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return stockInDao.getTotal(queryMap);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return stockInDao.delete(id);
	}
	
	

}
