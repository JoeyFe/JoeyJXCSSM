package cn.joey.service.impl;
/**
 * 发货单service实现
 */
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.joey.dao.SendOutDao;
import cn.joey.entity.SendOut;
import cn.joey.entity.SendOutDetail;
import cn.joey.service.SendOutService;

@Service
public class SendOutServiceImpl implements SendOutService {
	@Autowired
	private SendOutDao sendOutDao;

	@Override
	public int add(SendOut sendOut) {
		// TODO Auto-generated method stub
		int rst = sendOutDao.add(sendOut);
		if(rst > 0){
			//表示发货出库单添加成功,接下来添加发货出库单子项
			for(SendOutDetail sendOutDetail : sendOut.getSendOutDetailList()){
				sendOutDetail.setSendOutId(sendOut.getId());
				sendOutDao.addDetail(sendOutDetail);
			}
		}
		return rst;
	}

	@Override
	public int edit(SendOut sendOut) {
		// TODO Auto-generated method stub
		return sendOutDao.edit(sendOut);
	}

	@Override
	public List<SendOut> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return sendOutDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return sendOutDao.getTotal(queryMap);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return sendOutDao.delete(id);
	}
	
	

}
