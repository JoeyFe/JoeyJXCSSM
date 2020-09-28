package cn.joey.service.impl;
/**
 * 发货出库单service实现
 */
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.joey.dao.DeliverOutDao;
import cn.joey.entity.DeliverOut;
import cn.joey.entity.DeliverOutDetail;
import cn.joey.service.DeliverOutService;

@Service
public class DeliverOutServiceImpl implements DeliverOutService {
	@Autowired
	private DeliverOutDao deliverOutDao;
	@Override
	public int add(DeliverOut deliverOut) {
		// TODO Auto-generated method stub
		int rst = deliverOutDao.add(deliverOut);
		if(rst > 0){
			//表示发货出库单添加成功,接下来添加发货出库单子项
			for(DeliverOutDetail deliverOutDetail : deliverOut.getDeliverOutDetailList()){
				deliverOutDetail.setDeliverOutId(deliverOut.getId());
				deliverOutDao.addDetail(deliverOutDetail);
			}
		}
		return rst;
	}

	@Override
	public int edit(DeliverOut deliverOut) {
		// TODO Auto-generated method stub
		return deliverOutDao.edit(deliverOut);
	}

	@Override
	public List<DeliverOut> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return deliverOutDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return deliverOutDao.getTotal(queryMap);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return deliverOutDao.delete(id);
	}
	
	

}
