package cn.joey.service;

import org.springframework.stereotype.Service;

import cn.joey.entity.User;

/**
 * user service
 * @author Joey_Fe
 *
 */
@Service
public interface UserService {
	public User findByUsername(String username);
}
