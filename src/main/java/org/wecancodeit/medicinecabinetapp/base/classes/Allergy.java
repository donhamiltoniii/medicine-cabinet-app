package org.wecancodeit.medicinecabinetapp.base.classes;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Allergy {
	
	@Id
	@GeneratedValue
	private long id;
	private String allergyName;
	
	public Allergy () {
		
	}
	
	public Allergy(String allergyName) {
		this.allergyName = allergyName;
	}
	
	public long getId() {
		return id;
	}
	
	public String getAllergyName() {
		return allergyName;
	}
	

}