package cn.joey.service.impl;
/**
 * 进货单service实现
 */
import java.util.List;
import java.util.Map;

/**

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.joey.dao.PurchaseInDao;
import cn.joey.entity.PurchaseIn;
import cn.joey.entity.PurchaseInDetail;
import cn.joey.service.PurchaseInService;

@Service
public class PurchaseServiceImpl implements PurchaseInService {
	@Autowired
	private PurchaseInDao purchaseInDao;
	@Override
	public int add(PurchaseIn purchaseIn) {
		// TODO Auto-generated method stub
		int rst = purchaseInDao.add(purchaseIn);
		if(rst > 0){
			//表示进货单添加成功,接下来添加进货单子项
			for(PurchaseInDetail purchaseInDetail : purchaseIn.getPurchaseInDetailList()){
				purchaseInDetail.setPurchaseInId(purchaseIn.getId());
				purchaseInDao.addDetail(purchaseInDetail);
			}
		}
		return rst;
	}

	@Override
	public int edit(PurchaseIn purchaseIn) {
		// TODO Auto-generated method stub
		return purchaseInDao.edit(purchaseIn);
	}

	@Override
	public List<PurchaseIn> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return purchaseInDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return purchaseInDao.getTotal(queryMap);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return purchaseInDao.delete(id);
	}
	
	

}
