package com.revature.daos;

import com.revature.models.ReimbursementStatus;

public interface ReimbursementStatusDAO {

	public ReimbursementStatus findReimbursementByStatus(int reimbStatusID);
	
}
