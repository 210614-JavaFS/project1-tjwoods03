package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	
	protected int reimbursementID;
	protected double reimbursementAmount;
	protected Timestamp reimbursementSubmitted;
	protected Timestamp reimbursementResolved;
	protected String reimburementDescription; 
	protected byte reimbursementReceipt;
	protected int reimbursementAuthor;
	protected int reimbursementResolver;
	protected int reimbursementStatusID;
	protected int reimbursementTypeID;
	
	public Reimbursement(int reimbursementID, double reimbursementAmount, Timestamp reimbursementSubmitted,
			Timestamp reimbursementResolved, String reimburementDescription, byte reimbursementReceipt,
			int reimbursementAuthor, int reimbursementResolver, int reimbursementStatusID, int reimbursementTypeID) {
		super();
		this.reimbursementID = reimbursementID;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementSubmitted = reimbursementSubmitted;
		this.reimbursementResolved = reimbursementResolved;
		this.reimburementDescription = reimburementDescription;
		this.reimbursementReceipt = reimbursementReceipt;
		this.reimbursementAuthor = reimbursementAuthor;
		this.reimbursementResolver = reimbursementResolver;
		this.reimbursementStatusID = reimbursementStatusID;
		this.reimbursementTypeID = reimbursementTypeID;
	}

	public Reimbursement() {
		super();
	}

	public int getReimbursementID() {
		return reimbursementID;
	}

	public void setReimbursementID(int reimbursementID) {
		this.reimbursementID = reimbursementID;
	}

	public double getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public Timestamp getReimbursementSubmitted() {
		return reimbursementSubmitted;
	}

	public void setReimbursementSubmitted(Timestamp reimbursementSubmitted) {
		this.reimbursementSubmitted = reimbursementSubmitted;
	}

	public Timestamp getReimbursementResolved() {
		return reimbursementResolved;
	}

	public void setReimbursementResolved(Timestamp reimbursementResolved) {
		this.reimbursementResolved = reimbursementResolved;
	}

	public String getReimburementDescription() {
		return reimburementDescription;
	}

	public void setReimburementDescription(String reimburementDescription) {
		this.reimburementDescription = reimburementDescription;
	}

	public byte getReimbursementReceipt() {
		return reimbursementReceipt;
	}

	public void setReimbursementReceipt(byte reimbursementReceipt) {
		this.reimbursementReceipt = reimbursementReceipt;
	}

	public int getReimbursementAuthor() {
		return reimbursementAuthor;
	}

	public void setReimbursementAuthor(int reimbursementAuthor) {
		this.reimbursementAuthor = reimbursementAuthor;
	}

	public int getReimbursementResolver() {
		return reimbursementResolver;
	}

	public void setReimbursementResolver(int reimbursementResolver) {
		this.reimbursementResolver = reimbursementResolver;
	}

	public int getReimbursementStatusID() {
		return reimbursementStatusID;
	}

	public void setReimbursementStatusID(int reimbursementStatusID) {
		this.reimbursementStatusID = reimbursementStatusID;
	}

	public int getReimbursementTypeID() {
		return reimbursementTypeID;
	}

	public void setReimbursementTypeID(int reimbursementTypeID) {
		this.reimbursementTypeID = reimbursementTypeID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimburementDescription == null) ? 0 : reimburementDescription.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reimbursementAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reimbursementAuthor;
		result = prime * result + reimbursementID;
		result = prime * result + reimbursementReceipt;
		result = prime * result + ((reimbursementResolved == null) ? 0 : reimbursementResolved.hashCode());
		result = prime * result + reimbursementResolver;
		result = prime * result + reimbursementStatusID;
		result = prime * result + ((reimbursementSubmitted == null) ? 0 : reimbursementSubmitted.hashCode());
		result = prime * result + reimbursementTypeID;
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
		Reimbursement other = (Reimbursement) obj;
		if (reimburementDescription == null) {
			if (other.reimburementDescription != null)
				return false;
		} else if (!reimburementDescription.equals(other.reimburementDescription))
			return false;
		if (Double.doubleToLongBits(reimbursementAmount) != Double.doubleToLongBits(other.reimbursementAmount))
			return false;
		if (reimbursementAuthor != other.reimbursementAuthor)
			return false;
		if (reimbursementID != other.reimbursementID)
			return false;
		if (reimbursementReceipt != other.reimbursementReceipt)
			return false;
		if (reimbursementResolved == null) {
			if (other.reimbursementResolved != null)
				return false;
		} else if (!reimbursementResolved.equals(other.reimbursementResolved))
			return false;
		if (reimbursementResolver != other.reimbursementResolver)
			return false;
		if (reimbursementStatusID != other.reimbursementStatusID)
			return false;
		if (reimbursementSubmitted == null) {
			if (other.reimbursementSubmitted != null)
				return false;
		} else if (!reimbursementSubmitted.equals(other.reimbursementSubmitted))
			return false;
		if (reimbursementTypeID != other.reimbursementTypeID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementID=" + reimbursementID + ", reimbursementAmount=" + reimbursementAmount
				+ ", reimbursementSubmitted=" + reimbursementSubmitted + ", reimbursementResolved="
				+ reimbursementResolved + ", reimburementDescription=" + reimburementDescription
				+ ", reimbursementReceipt=" + reimbursementReceipt + ", reimbursementAuthor=" + reimbursementAuthor
				+ ", reimbursementResolver=" + reimbursementResolver + ", reimbursementStatusID="
				+ reimbursementStatusID + ", reimbursementTypeID=" + reimbursementTypeID + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	
	
}
