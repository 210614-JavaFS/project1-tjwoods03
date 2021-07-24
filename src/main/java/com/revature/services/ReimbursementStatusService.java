package com.revature.services;

import com.revature.daos.ReimbursementStatusDAO;
import com.revature.daos.ReimbursementStatusDAOImpl;
import com.revature.models.ReimbursementStatus;

public class ReimbursementStatusService {

	private static ReimbursementStatusDAO reimbursementStatusDao = new ReimbursementStatusDAOImpl();
	
	public ReimbursementStatus findReimbursementByStatus(int reimbStatusID) {
		return reimbursementStatusDao.findReimbursementByStatus(reimbStatusID);
	}
	
}
