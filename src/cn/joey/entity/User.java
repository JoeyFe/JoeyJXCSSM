package cn.joey.entity;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * @author Joey_Fe
 *
 */
@Component
public class User {
	private long id;//id,自增
	private String username;//用户名
	private String password;//密码
	private long phone;//电话
	private String role;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
