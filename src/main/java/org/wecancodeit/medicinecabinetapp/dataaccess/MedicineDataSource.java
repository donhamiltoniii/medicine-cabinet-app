package org.wecancodeit.medicinecabinetapp.dataaccess;

import java.util.List;

import org.wecancodeit.medicinecabinetapp.base.classes.Medication;
import org.wecancodeit.medicinecabinetapp.base.classes.MedicationAlarm;


public interface MedicineDataSource<History> {

    interface LoadMedicineCallbacks {

        void onMedicineLoaded(List<MedicationAlarm> medicineAlarmList);

        void onDataNotAvailable();
    }

    interface GetTaskCallback {

        void onTaskLoaded(MedicationAlarm medicineAlarm);

        void onDataNotAvailable();
    }

    

    void saveMedicine(MedicationAlarm medicineAlarm, Medication medication);

    void getMedicineListByDay(int day, LoadMedicineCallbacks callbacks);

    boolean medicineExits(String medicationName);

    List<Long> tempIds();

    void deleteAlarm(long alarmId);
    
    

    List<MedicationAlarm> getMedicineByMedicationName(String medicationName);

    Medication getMedicationsByName(String medicationName);

    long saveMedications(Medication medication);

    void saveToHistory(History history);

}

