package cn.joey.entity;
/**
 * 进货单实体
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.joey.entity.OrderInDetail;


@Component
public class OrderIn {
	private String id;//之后改为string
	private Float money;//进货单总金额
	private Integer productNum;//商品数量
	private int payType = 0;//支付方式，0：现金，1：银行转账，2：支付宝，3：微信，4：支票，5：其他
	private int status = 0;//状态：0：未支付，1：已支付
	private String operator;//操作人
	private String remark;//备注
	private List<OrderInDetail> orderInDetailList = new ArrayList<OrderInDetail>();
	private Date createTime;//进货时间
	private String recivePlace;//备注
	private String reciveTime;//备注
	private int supplierId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<OrderInDetail> getOrderInDetailList() {
		return orderInDetailList;
	}
	public void setOrderInDetailList(List<OrderInDetail> orderInDetailList) {
		this.orderInDetailList = orderInDetailList;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRecivePlace() {
		return recivePlace;
	}
	public void setRecivePlace(String recivePlace) {
		this.recivePlace = recivePlace;
	}
	public String getReciveTime() {
		return reciveTime;
	}
	public void setReciveTime(String reciveTime) {
		this.reciveTime = reciveTime;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	
}
