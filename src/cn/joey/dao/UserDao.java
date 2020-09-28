package cn.joey.dao;

import org.springframework.stereotype.Repository;

import cn.joey.entity.User;

/**
 * User Dao
 * @author Joey_Fe
 *
 */
@Repository
public interface UserDao {
	public User findByUsername(String username);
}
