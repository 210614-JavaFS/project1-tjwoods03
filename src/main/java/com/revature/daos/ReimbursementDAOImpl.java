package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO{

	@Override
	public List<Reimbursement> findAllReimbursements() {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM ers_reimbursement";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Reimbursement> list = new ArrayList<>();
			
			while(result.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setReimbursementID(result.getInt("reimb_id"));
				reimb.setReimbursementAmount(result.getDouble("reimb_amount"));
				reimb.setReimbursementSubmitted(result.getTimestamp("reimb_submitted"));
				reimb.setReimbursementResolved(result.getTimestamp("reimb_resolved"));
				reimb.setReimburementDescription(result.getString("reimb_description"));
				reimb.setReimbursementAuthor(result.getInt("reimb_author"));
				reimb.setReimbursementResolver(result.getInt("reimb_resolver"));
				reimb.setReimbursementStatusID(result.getInt("reimb_status_id"));
				reimb.setReimbursementTypeID(result.getInt("reimb_type_id"));
				list.add(reimb);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reimbursement findReimbursementByID(int reimbID) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			statement.setInt(1, reimbID);
			
			Reimbursement reimb = new Reimbursement();
			
			while(result.next()) {
				reimb.setReimbursementID(result.getInt("reimb_id"));
				reimb.setReimbursementAmount(result.getDouble("reimb_amount"));
				reimb.setReimbursementSubmitted(result.getTimestamp("reimb_submitted"));
				reimb.setReimbursementResolved(result.getTimestamp("reimb_resolbed"));
				reimb.setReimburementDescription(result.getString("reimb_description"));
				reimb.setReimbursementAuthor(result.getInt("reimb_author"));
				reimb.setReimbursementResolver(result.getInt("reimb_resolver"));
				reimb.setReimbursementStatusID(result.getInt("reimb_status_id"));
				reimb.setReimbursementTypeID(result.getInt("reimb_type_id"));
			}
			
			return reimb;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reimbursement findReimbursementByResolveDate(Timestamp resolve) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_resolved = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			statement.setTimestamp(1, resolve);
			
			Reimbursement reimb = new Reimbursement();
			
			while(result.next()) {
				reimb.setReimbursementID(result.getInt("reimb_id"));
				reimb.setReimbursementAmount(result.getDouble("reimb_amount"));
				reimb.setReimbursementSubmitted(result.getTimestamp("reimb_submitted"));
				reimb.setReimbursementResolved(result.getTimestamp("reimb_resolbed"));
				reimb.setReimburementDescription(result.getString("reimb_description"));
				reimb.setReimbursementAuthor(result.getInt("reimb_author"));
				reimb.setReimbursementResolver(result.getInt("reimb_resolver"));
				reimb.setReimbursementStatusID(result.getInt("reimb_status_id"));
				reimb.setReimbursementTypeID(result.getInt("reimb_type_id"));
			}
			
			return reimb;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addReimbursement(Reimbursement reimb) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO ers_reimbursement(reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;
			statement.setInt(++index, reimb.getReimbursementID());
			statement.setDouble(++index, reimb.getReimbursementAmount());
			statement.setTimestamp(++index, reimb.getReimbursementSubmitted());
			statement.setTimestamp(++index, reimb.getReimbursementResolved());
			statement.setString(++index, reimb.getReimburementDescription());
			statement.setByte(++index, reimb.getReimbursementReceipt());
			statement.setInt(++index, reimb.getReimbursementAuthor());
			statement.setInt(++index, reimb.getReimbursementResolver());
			statement.setInt(++index, reimb.getReimbursementStatusID());
			statement.setInt(++index, reimb.getReimbursementTypeID());
			
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
