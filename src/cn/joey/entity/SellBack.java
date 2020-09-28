package cn.joey.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class SellBack {
	private String id;//之后改为string
	private Float money;//退货单总金额
	private Integer productNum;//商品数量
	private int payType = 0;//邮寄方式，0：顺丰，1：中通，2：圆通，3：申通，4：京东，5：苏宁
	private int status = 0;//状态：0：包邮，1：不包邮
	private String operator;//操作人
	private String remark;//备注
	private String sellOutId;//关联销售单Id
	private List<SellBackDetail> sellBackDetailList = new ArrayList<SellBackDetail>();
	private Date createTime;//退货时间
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

	public String getSellOutId() {
		return sellOutId;
	}

	public void setSellOutId(String sellOutId) {
		this.sellOutId = sellOutId;
	}

	public List<SellBackDetail> getSellBackDetailList() {
		return sellBackDetailList;
	}

	public void setSellBackDetailList(List<SellBackDetail> sellBackDetailList) {
		this.sellBackDetailList = sellBackDetailList;
	}




	
}
