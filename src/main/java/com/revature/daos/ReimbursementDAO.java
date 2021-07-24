package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {
	
	public List<Reimbursement> findAllReimbursements();
	public Reimbursement findReimbursementByID(int reimbID);
	public boolean addReimbursement(Reimbursement reimb);

}
