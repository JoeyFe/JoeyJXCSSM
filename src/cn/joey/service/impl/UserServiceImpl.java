package cn.joey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.joey.dao.UserDao;
import cn.joey.entity.User;
import cn.joey.service.UserService;
/**
 * User ServiceImpl
 * @author Joey_Fe
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDAO;
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDAO.findByUsername(username);
	}

}
