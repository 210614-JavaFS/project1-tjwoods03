package com.revature.models;

public class ReimbursementStatus {

	protected int ReimbursementStatusID;
	protected String ReimbursementStatus;
	
	public ReimbursementStatus(int reimbursementStatusID, String reimbursementStatus) {
		super();
		ReimbursementStatusID = reimbursementStatusID;
		ReimbursementStatus = reimbursementStatus;
	}
	
	public ReimbursementStatus() {
		super();
	}

	public int getReimbursementStatusID() {
		return ReimbursementStatusID;
	}

	public void setReimbursementStatusID(int reimbursementStatusID) {
		ReimbursementStatusID = reimbursementStatusID;
	}

	public String getReimbursementStatus() {
		return ReimbursementStatus;
	}

	public void setReimbursementStatus(String reimbursementStatus) {
		ReimbursementStatus = reimbursementStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ReimbursementStatus == null) ? 0 : ReimbursementStatus.hashCode());
		result = prime * result + ReimbursementStatusID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementStatus other = (ReimbursementStatus) obj;
		if (ReimbursementStatus == null) {
			if (other.ReimbursementStatus != null)
				return false;
		} else if (!ReimbursementStatus.equals(other.ReimbursementStatus))
			return false;
		if (ReimbursementStatusID != other.ReimbursementStatusID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [ReimbursementStatusID=" + ReimbursementStatusID + ", ReimbursementStatus="
				+ ReimbursementStatus + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
