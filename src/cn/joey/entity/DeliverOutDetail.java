package cn.joey.entity;

import org.springframework.stereotype.Component;
/**
 * 发货出库单详细商品实体
 * @author Joey_Fe
 *
 */
@Component
public class DeliverOutDetail {
	private Long id;
	private String deliverOutId;//所属采购订单id
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

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDeliverOutId() {
		return deliverOutId;
	}
	public void setDeliverOutId(String deliverOutId) {
		this.deliverOutId = deliverOutId;
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
