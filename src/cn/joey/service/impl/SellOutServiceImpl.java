package cn.joey.service.impl;
/**
 * 销售单service实现
 */
import java.util.List;
import java.util.Map;

/**

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.joey.dao.SellOutDao;
import cn.joey.entity.SellOut;
import cn.joey.entity.SellOutDetail;
import cn.joey.service.SellOutService;

@Service
public class SellOutServiceImpl implements SellOutService {
	@Autowired
	private SellOutDao sellOutDao;

	@Override
	public int add(SellOut sellOut) {
		// TODO Auto-generated method stub
		int rst = sellOutDao.add(sellOut);
		if(rst > 0){
			//表示进货单添加成功,接下来添加进货单子项
			for(SellOutDetail sellOutDetail : sellOut.getSellOutDetailList()){
				sellOutDetail.setSellOutId(sellOut.getId());
				sellOutDao.addDetail(sellOutDetail);
			}
		}
		return rst;
	}

	@Override
	public int edit(SellOut sellOut) {
		// TODO Auto-generated method stub
		int rst = sellOutDao.edit(sellOut);
		if(rst > 0){
			//表示进货单添加成功,接下来添加进货单子项
			for(SellOutDetail sellOutDetail : sellOut.getSellOutDetailList()){
				sellOutDetail.setSellOutId(sellOut.getId());
				sellOutDao.editDetail(sellOutDetail);
			}
		}
		return rst;
	}

	@Override
	public List<SellOut> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return sellOutDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return sellOutDao.getTotal(queryMap);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return sellOutDao.delete(id);
	}

}
