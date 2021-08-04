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
import com.revature.models.ReimbursementType;
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
	public boolean addReimbursement(Reimbursement reimb, String username) {
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

	
	@Override
	public List<Reimbursement> findReimbursement(String ers_username, boolean b) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	
	@Override
	public List<ReimbursementType> findReimbursementByType() {
		try(Connection conn = ConnectionUtil.getConnection()){
			ArrayList<ReimbursementType> reimbT = new ArrayList<ReimbursementType>();
			String sql = "SELECT * FROM ERS_REIMBURSMENT_TYPE";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet typeRS = statement.executeQuery();
			try {
				while (typeRS.next()) {
					reimbT.add(new ReimbursementType(typeRS.getInt(1), typeRS.getString(2)));
				}
				return reimbT;
			} catch (SQLException e) {
				System.err.println("Select From Database Fail" + e.getMessage());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
