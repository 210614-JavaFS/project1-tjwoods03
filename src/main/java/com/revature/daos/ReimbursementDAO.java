package com.revature.daos;

import java.sql.Timestamp;
import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {
	
	public List<Reimbursement> findAllReimbursements();
	public Reimbursement findReimbursementByID(int reimbID);
	public Reimbursement findReimbursementByResolveDate(Timestamp resolve);
	public boolean addReimbursement(Reimbursement reimb);

}
