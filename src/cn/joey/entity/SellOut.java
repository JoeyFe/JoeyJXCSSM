package cn.joey.entity;
/**
 * 销售单实体
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class SellOut {
	private String id;//之后改为string
	private Float money;//销售单总金额
	private Integer productNum;//商品数量
	private int payType = 0;//支付方式，0：现金，1：银行转账，2：支付宝，3：微信，4：支票，5：其他
	private int status = 0;//状态：0：未支付，1：已支付
	private String operator;//操作人
	private Long customId;//顾客编号
	private String customPhone;//顾客电话
	private String customAddr;//顾客地址
	private String remark;//备注
	private List<SellOutDetail> sellOutDetailList = new ArrayList<SellOutDetail>();
	private Date createTime;//销售时间
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public List<SellOutDetail> getSellOutDetailList() {
		return sellOutDetailList;
	}
	public void setSellOutDetailList(List<SellOutDetail> sellOutDetailList) {
		this.sellOutDetailList = sellOutDetailList;
	}

	
}
