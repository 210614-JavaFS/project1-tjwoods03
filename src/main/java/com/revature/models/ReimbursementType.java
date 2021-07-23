package com.revature.models;

public class ReimbursementType {

	protected int ReimbursementTypeID;
	protected String ReimbursementType;
	
	public ReimbursementType(int reimbursementTypeID, String reimbursementType) {
		super();
		ReimbursementTypeID = reimbursementTypeID;
		ReimbursementType = reimbursementType;
	}

	public ReimbursementType() {
		super();
	}

	public int getReimbursementTypeID() {
		return ReimbursementTypeID;
	}

	public void setReimbursementTypeID(int reimbursementTypeID) {
		ReimbursementTypeID = reimbursementTypeID;
	}

	public String getReimbursementType() {
		return ReimbursementType;
	}

	public void setReimbursementType(String reimbursementType) {
		ReimbursementType = reimbursementType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ReimbursementType == null) ? 0 : ReimbursementType.hashCode());
		result = prime * result + ReimbursementTypeID;
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
		ReimbursementType other = (ReimbursementType) obj;
		if (ReimbursementType == null) {
			if (other.ReimbursementType != null)
				return false;
		} else if (!ReimbursementType.equals(other.ReimbursementType))
			return false;
		if (ReimbursementTypeID != other.ReimbursementTypeID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementType [ReimbursementTypeID=" + ReimbursementTypeID + ", ReimbursementType="
				+ ReimbursementType + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
