package com.revature.daos;

import java.sql.Timestamp;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;

public interface ReimbursementDAO {
	

	public boolean addReimbursement(Reimbursement reimb, String username);
	public List<Reimbursement> findAllReimbursements();
	public List<Reimbursement> findReimbursement(String username, boolean pending);
	public List<ReimbursementType> findReimbursementByType();
	

}
