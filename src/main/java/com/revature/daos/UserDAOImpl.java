package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<User> findAllUsers() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_users";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<User> list = new ArrayList<>();
			
			while(result.next()) {
				User user = new User();
				user.setUserID(result.getInt("ers_user_id"));
				user.setUserName(result.getString("ers_username"));
				user.setPass(result.getString("ers_password"));
				user.setFirstName(result.getString("user_first_name"));
				user.setLastName(result.getString("user_last_name"));
				user.setEmail(result.getString("user_email"));
				user.setUserRole(result.getInt("user_role_id"));
				list.add(user);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findByUsersName(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_users WHERE ers_username = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			statement.setString(1, username);
			
			User user = new User();
			
			while(result.next()) {
				user.setUserID(result.getInt("ers_user_id"));
				user.setUserName(result.getString("ers_username"));
				user.setPass(result.getString("ers_password"));
				user.setFirstName(result.getString("user_first_name"));
				user.setLastName(result.getString("user_last_name"));
				user.setEmail(result.getString("user_email"));
				user.setUserRole(result.getInt("user_role_id"));
			}
			
			return user;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email)"
					+ "VALUES (?,?,?,?,?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, user.getUserName());
			statement.setString(++index, user.getPass());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addUser(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email)"
					+ "VALUES (?,?,?,?,?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, user.getUserName());
			statement.setString(++index, user.getPass());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
