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


import cn.joey.dao.OrderInDao;

import cn.joey.entity.OrderIn;
import cn.joey.entity.OrderInDetail;
import cn.joey.service.OrderInService;

@Service
public class OrderInServiceImpl implements OrderInService {
	@Autowired
	private OrderInDao orderInDao;

	@Override
	public int add(OrderIn orderIn) {
		// TODO Auto-generated method stub
		int rst = orderInDao.add(orderIn);
		if(rst > 0){
			//表示进货单添加成功,接下来添加进货单子项
			for(OrderInDetail orderInDetail : orderIn.getOrderInDetailList()){
				orderInDetail.setOrderInId(orderIn.getId());
				orderInDao.addDetail(orderInDetail);
			}
		}
		return rst;
	}

	@Override
	public int edit(OrderIn orderIn) {
		// TODO Auto-generated method stub
		int rst = orderInDao.edit(orderIn);
		if(rst > 0){
			//表示进货单修改成功,接下来添加进货单子项
			for(OrderInDetail orderInDetail : orderIn.getOrderInDetailList()){
				orderInDetail.setOrderInId(orderIn.getId());
				orderInDao.editDetail(orderInDetail);
			}
		}
		return rst;
	}

	@Override
	public List<OrderIn> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return orderInDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return orderInDao.getTotal(queryMap);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return orderInDao.delete(id);
	}
	

}
