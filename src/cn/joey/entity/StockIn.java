package cn.joey.entity;
/**
 * 入库单实体
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class StockIn {
	private String id;//之后改为string
	private Float money;//采购单总金额
	private Integer productNum;//商品数量
	private int payType = 0;//支付方式，0：现金，1：银行转账，2：支付宝，3：微信，4：支票，5：其他
	private int status = 0;//状态：0：未支付，1：已支付
	private String operator;//操作人
	private String remark;//备注
	private String purchaseInId;//关联采购单Id
	private List<StockInDetail> stockInDetailList = new ArrayList<StockInDetail>();
	private Date createTime;//进货时间
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

	public String getPurchaseInId() {
		return purchaseInId;
	}

	public void setPurchaseInId(String purchaseInId) {
		this.purchaseInId = purchaseInId;
	}

	public List<StockInDetail> getStockInDetailList() {
		return stockInDetailList;
	}

	public void setStockInDetailList(List<StockInDetail> stockInDetailList) {
		this.stockInDetailList = stockInDetailList;
	}



	
}
