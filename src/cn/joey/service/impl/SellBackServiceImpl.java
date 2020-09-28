package cn.joey.service.impl;
/**
 * 退货单service实现
 */
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.joey.dao.SellBackDao;
import cn.joey.entity.SellBack;
import cn.joey.entity.SellBackDetail;
import cn.joey.service.SellBackService;

@Service
public class SellBackServiceImpl implements SellBackService {
	@Autowired
	private SellBackDao sellBackDao;

	@Override
	public int add(SellBack sellBack) {
		// TODO Auto-generated method stub
		int rst = sellBackDao.add(sellBack);
		if(rst > 0){
			//表示发货出库单添加成功,接下来添加发货出库单子项
			for(SellBackDetail sellBackDetail : sellBack.getSellBackDetailList()){
				sellBackDetail.setSellBackId(sellBack.getId());
				sellBackDao.addDetail(sellBackDetail);
			}
		}
		return rst;
	}

	@Override
	public int edit(SellBack sellBack) {
		// TODO Auto-generated method stub
		return sellBackDao.edit(sellBack);
	}

	@Override
	public List<SellBack> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return sellBackDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return sellBackDao.getTotal(queryMap);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return sellBackDao.delete(id);
	}
	

}
