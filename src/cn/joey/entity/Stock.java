package cn.joey.entity;

import org.springframework.stereotype.Component;


@Component
public class Stock {
	private Long id;
	private Long productId;//商品ID
	private Integer productNum;//库存数量
	private Integer sellNum;//已售数量
	private Product product;//映射商品
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public Integer getSellNum() {
		return sellNum;
	}
	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}

