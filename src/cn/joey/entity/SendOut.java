package cn.joey.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class SendOut {
	private String id;//之后改为string
	private Float money;//发货单总金额
	private Integer productNum;//商品数量
	private int payType = 0;//邮寄方式，0：顺丰，1：中通，2：圆通，3：申通，4：京东，5：苏宁
	private int status = 0;//状态：0：包邮，1：不包邮
	private String operator;//操作人
	private String remark;//备注
	private Long customId;//顾客编号
	private String customPhone;//顾客电话
	private String customAddr;//顾客地址
	private String deliverOutId;//关联订货单Id
	private List<SendOutDetail> sendOutDetailList = new ArrayList<SendOutDetail>();
	private Date createTime;//发货时间
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public String getDeliverOutId() {
		return deliverOutId;
	}

	public void setDeliverOutId(String deliverOutId) {
		this.deliverOutId = deliverOutId;
	}

	public List<SendOutDetail> getSendOutDetailList() {
		return sendOutDetailList;
	}

	public void setSendOutDetailList(List<SendOutDetail> sendOutDetailList) {
		this.sendOutDetailList = sendOutDetailList;
	}

	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	public String getCustomPhone() {
		return customPhone;
	}

	public void setCustomPhone(String customPhone) {
		this.customPhone = customPhone;
	}

	public String getCustomAddr() {
		return customAddr;
	}

	public void setCustomAddr(String customAddr) {
		this.customAddr = customAddr;
	}





	
}
