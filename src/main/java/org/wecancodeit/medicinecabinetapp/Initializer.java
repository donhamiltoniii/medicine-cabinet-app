package org.wecancodeit.medicinecabinetapp;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.wecancodeit.medicinecabinetapp.entities.Alert;
import org.wecancodeit.medicinecabinetapp.entities.Doctor;
import org.wecancodeit.medicinecabinetapp.entities.Medication;
import org.wecancodeit.medicinecabinetapp.entities.Pharmacy;
import org.wecancodeit.medicinecabinetapp.repositories.AlertRepository;
import org.wecancodeit.medicinecabinetapp.repositories.DoctorRepository;
import org.wecancodeit.medicinecabinetapp.repositories.MedicationRepository;
import org.wecancodeit.medicinecabinetapp.repositories.PharmacyRepository;

@Service
public class Initializer implements CommandLineRunner {

	@Resource
	private AlertRepository alertRepo;

	@Resource
	private MedicationRepository medicationRepo;

	@Resource
	private DoctorRepository doctorRepo;

	@Resource
	private PharmacyRepository pharmacyRepo;

	@Override
	public void run(String... args) throws Exception {

		LocalDateTime timeToSendAlert = LocalDateTime.now();

		Alert alertPill = new Alert("Pain Pill", false, 1, "pill", timeToSendAlert);
		alertPill = alertRepo.save(alertPill);

		Alert alertLiquid = new Alert("Liquid Medicine", false, 50, "mL", timeToSendAlert);
		alertLiquid = alertRepo.save(alertLiquid);

		Alert alertPatch = new Alert("Patch", false, 1, "patch", timeToSendAlert);
		alertPatch = alertRepo.save(alertPatch);

		Doctor primaryCare = doctorRepo.save(new Doctor("Doogie", "888-888-8888"));
		Pharmacy walgreens = pharmacyRepo.save(new Pharmacy("Walgreens", "444 Fake Dr", "999-999-9999"));

		LocalTime timeToTakeMedication = LocalTime.NOON;

		Medication pill1 = new Medication("ibuprofen", "mg", 1.1, "pill", "daily", "10", timeToTakeMedication,
				"instructions", primaryCare, walgreens);
		medicationRepo.save(pill1);

		Medication pill2 = new Medication("aspirin", "mg", 1.5, "pill", "daily", "20", timeToTakeMedication,
				"instructions", primaryCare, walgreens);
		medicationRepo.save(pill2);

		Medication liquid = new Medication("Nyquil", "mL", 50, "liquid", "daily", "1", timeToTakeMedication,
				"instructions", primaryCare, walgreens);
		medicationRepo.save(liquid);

		Medication patch = new Medication("Nicotine", "mg", 1, "patch", "weekly", "30", timeToTakeMedication,
				"instructions", primaryCare, walgreens);
		medicationRepo.save(patch);
	}

}
