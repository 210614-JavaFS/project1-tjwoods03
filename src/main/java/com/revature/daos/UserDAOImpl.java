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
			String sql = "INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)"
					+ " VALUES (?,?,?,?,?,?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setString(++index, user.getUserName());
			statement.setString(++index, user.getPass());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			statement.setInt(++index, 1);
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int checkValidAccount(User user) {
		
		try {
			Connection connect = ConnectionUtil.getConnection();
			Statement statement = connect.createStatement();
			String username = user.getUserName();
			String pass = user.getPass();
			ResultSet result = statement.executeQuery("SELECT user_role_id FROM ers_users WHERE ers_username = '" + username+ "' AND ers_password = '" + pass + "';");
			
			int index = 0;
			if(result.next()) {
				username = user.getUserName();
				pass = user.getPass();
				index = result.getInt(1);
			}
			return index;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}

	@Override
	public String login(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT ers_password FROM ERS_USERS WHERE ers_username = '" + user.getUserName() + "'";
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			ResultSet result = prepStatement.executeQuery();
			try {
				if (result.next()) {
					return result.getString(1);
				} else {
					System.out.println("User not found.");
				}
			} catch(SQLException e) {
				System.err.println("Access Result Set Fail: " + e.getMessage());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
