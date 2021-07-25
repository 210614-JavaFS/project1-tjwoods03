package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.ConnectionUtil;

public class ReimbursementStatusDAOImpl implements ReimbursementStatusDAO{

	@Override
	public ReimbursementStatus findReimbursementByStatus(int reimbStatusID) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement_status WHERE reimb_status_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			statement.setInt(1, reimbStatusID);
			
			ReimbursementStatus reimbStatus = new ReimbursementStatus();
			
			while(result.next()) {
				reimbStatus.setReimbursementStatusID(result.getInt("reimb_status_id"));
				reimbStatus.setReimbursementStatus(result.getString("reimb_status"));
			}
			
			return reimbStatus;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
