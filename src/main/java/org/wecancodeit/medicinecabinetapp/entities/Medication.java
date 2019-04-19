package org.wecancodeit.medicinecabinetapp.entities;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity

public class Medication {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String dosageUnits;
	private double dosageAmount;
	private String medicationType;
	private String frequency;

	@Lob
	private String instructions;

	private String count;
	private LocalTime timeToTakeMedication;

	@OneToOne
	private Doctor doctor;

	@OneToOne
	private Pharmacy pharmacy;

	@ManyToMany
	private Collection<Alert> alerts;

	public Medication() {

	}

	public Medication(String name, String dosageUnits, double dosageAmount, String medicationType, String frequency,
			String count, LocalTime timeToTakeMedication, String instructions, Doctor doctor, Pharmacy pharmacy,
			Alert... alerts) {

		this.name = name;
		this.dosageUnits = dosageUnits;
		this.dosageAmount = dosageAmount;
		this.medicationType = medicationType;
		this.frequency = frequency;
		this.count = count;
		this.timeToTakeMedication = timeToTakeMedication;
		this.instructions = instructions;
		this.doctor = doctor;
		this.pharmacy = pharmacy;
		this.alerts = new HashSet<>(Arrays.asList(alerts));

	}

	public long getId() {
		return id;
	}

	public String getMedicationName() {
		return name;

	}

	public String getDosageUnits() {
		return dosageUnits;
	}

	public double getDosageAmount() {
		return dosageAmount;
	}

	public String getMedicationType() {
		return medicationType;
	}

	public String getFrequency() {

		return frequency;
	}

	public String getCount() {
		return count;
	}

	public LocalTime getTimeToTakeMedication() {
		return timeToTakeMedication;
	}

	public String getInstructions() {
		return instructions;
	}

	public Collection<Alert> getAlerts() {
		return alerts;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDosageUnits(String dosageUnits) {
		this.dosageUnits = dosageUnits;
	}

	public void setDosageAmount(double dosageAmount) {
		this.dosageAmount = dosageAmount;
	}

	public void setMedicationType(String medicationType) {
		this.medicationType = medicationType;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setTimeToTakeMedication(LocalTime timeToTakeMedication) {
		this.timeToTakeMedication = timeToTakeMedication;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public void setAlerts(Collection<Alert> alerts) {
		this.alerts = alerts;
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
		Medication other = (Medication) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
