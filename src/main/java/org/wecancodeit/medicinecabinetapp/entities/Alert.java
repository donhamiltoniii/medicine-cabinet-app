package org.wecancodeit.medicinecabinetapp.entities;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Alert {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private boolean taken;
	private int dosageUnits;
	private String dosageType;

	private LocalDateTime timeToSendAlert;

	@ManyToMany(mappedBy = "alerts")
	private Collection<Medication> medications;

	public Alert() {

	}

	public Alert(String name, boolean taken, int dosageUnits, String dosageType, LocalDateTime timeToSendAlert) {
		this.name = name;
		this.taken = taken;
		this.timeToSendAlert = timeToSendAlert;
		this.dosageUnits = dosageUnits;
		this.dosageType = dosageType;

	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isTaken() {
		return taken;
	}

	public int getDosageUnits() {
		return dosageUnits;
	}

	public String getDosageType() {
		return dosageType;
	}

	public Object getTimeToSendAlert() {
		return timeToSendAlert;
	}

	public Collection<Medication> getMedications() {
		return medications;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public void setDosageUnits(int dosageUnits) {
		this.dosageUnits = dosageUnits;
	}

	public void setDosageType(String dosageType) {
		this.dosageType = dosageType;
	}

	public void setTimeToSendAlert(LocalDateTime timeToSendAlert) {
		this.timeToSendAlert = timeToSendAlert;
	}

	public void setMedications(Collection<Medication> medications) {
		this.medications = medications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Alert other = (Alert) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
