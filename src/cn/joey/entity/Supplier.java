package cn.joey.entity;

import org.springframework.stereotype.Component;

/**
 * 供应商实体类
 * @author Joey_Fe
 *
 */
@Component
public class Supplier {
	private Long id;
	private String name;//供应商名称
	private String tel;//供应商电话，固话
	private String address;//联系地址
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
