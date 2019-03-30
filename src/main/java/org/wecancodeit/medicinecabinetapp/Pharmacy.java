package org.wecancodeit.medicinecabinetapp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Pharmacy {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String pharmacyName;
	private String pharmacyAddress;
	private String pharmacyPhone;
	
	//user id? Many pharmacies to one user
	
	
	public Pharmacy() {
		
	}
	
	public Pharmacy(String pharmacyName, String pharmacyAddress, String pharmacyPhone) {
		this.pharmacyName = pharmacyName;
		this.pharmacyAddress = pharmacyAddress;
		this.pharmacyPhone = pharmacyPhone;
	}
	
	
	public String getPharmacyName() {
		return pharmacyName;
	}
	
	public String getPharmacyAddress() {
		return pharmacyAddress;
	}
	
	public String getPharmacyPhone() {
		return pharmacyPhone;
	}

}
