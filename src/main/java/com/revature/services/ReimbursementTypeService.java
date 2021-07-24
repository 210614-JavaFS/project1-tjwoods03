package com.revature.services;

import com.revature.daos.ReimbursementTypeDAO;
import com.revature.daos.ReimbursementTypeDAOImpl;
import com.revature.models.ReimbursementType;

public class ReimbursementTypeService {

	private static ReimbursementTypeDAO reimbursementTypeDao = new ReimbursementTypeDAOImpl();
	
	public ReimbursementType findReimbursementByTypeID(int reimbTypeID) {
		return reimbursementTypeDao.findReimbursementByTypeID(reimbTypeID);
	}
	
}
