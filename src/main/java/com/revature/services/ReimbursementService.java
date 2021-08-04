package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDAOImpl;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;

public class ReimbursementService {

	
	private static ReimbursementDAO reimbursementDao = new ReimbursementDAOImpl();
	
	public List<Reimbursement> findAllReimbursements(){
		return reimbursementDao.findAllReimbursements();
	}
	
	
	public boolean addReimbursement(Reimbursement reimb, String username){
		return reimbursementDao.addReimbursement(reimb, username);
	}


	public static List<ReimbursementType> getReimbursementType() {
		return reimbursementDao.findReimbursementByType();
	}

	public List<Reimbursement> getPendingRequest(String ers_username) {
		return reimbursementDao.findReimbursement(ers_username, true);
	}

	public List<Reimbursement> getPastRequest(String ers_username) {
		return reimbursementDao.findReimbursement(ers_username, false);
	}

	public boolean setPendingRequest(String ers_username, int reimb_id, String status) {
		
		return false;
	}

}
