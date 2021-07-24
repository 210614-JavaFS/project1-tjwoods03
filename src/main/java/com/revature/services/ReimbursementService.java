package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDAOImpl;
import com.revature.models.Reimbursement;

public class ReimbursementService {

	
	private static ReimbursementDAO reimbursementDao = new ReimbursementDAOImpl();
	
	public List<Reimbursement> findAllReimbursements(){
		return reimbursementDao.findAllReimbursements();
	}
	
	public Reimbursement findReimbursementByID(int reimbID) {
		return reimbursementDao.findReimbursementByID(reimbID);
	}
	
	public boolean addReimbursement(Reimbursement reimb){
		return reimbursementDao.addReimbursement(reimb);
	}
	
}
