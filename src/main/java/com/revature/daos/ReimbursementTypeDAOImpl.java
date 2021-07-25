package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.utils.ConnectionUtil;

public class ReimbursementTypeDAOImpl implements ReimbursementTypeDAO{

	@Override
	public ReimbursementType findReimbursementByTypeID(int reimbTypeID) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursement_type WHERE reimb_type_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			statement.setInt(1, reimbTypeID);
			
			ReimbursementType reimbType = new ReimbursementType();
			
			while(result.next()) {
				reimbType.setReimbursementTypeID(result.getInt("reimb_type_id"));
				reimbType.setReimbursementType(result.getString("reimb_type"));
			}
			
			return reimbType;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
