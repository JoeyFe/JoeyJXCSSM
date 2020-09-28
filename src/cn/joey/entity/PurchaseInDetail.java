package cn.joey.entity;

import org.springframework.stereotype.Component;
/**
 * 采购单详细商品实体
 * @author Joey_Fe
 *
 */
@Component
public class PurchaseInDetail {
	private Long id;
	private String PurchaseInId;//所属采购订单id
	private String productName;//商品名称
	private Float price;//商品价格
	private Integer productNum;//商品数量
	private Float money;//该商品金额
	private Integer pid;//商品的编号
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Long getId() {
		return id;
	}
	public String getPurchaseInId() {
		return PurchaseInId;
	}
	public void setPurchaseInId(String purchaseInId) {
		PurchaseInId = purchaseInId;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	
}
