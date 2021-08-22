package com.safetyNet.alertsApi.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.safetyNet.alertsApi.model.Firestation;

public interface FirestationDAO {

	ArrayList<Firestation> getAllFirestations();

	Optional<Firestation> getFirestationByAddress(String address);

	int insertFirestation(Firestation firestation);

	int updateFirestationByAddress(String address, Firestation updatedFirestation);

	int deleteFirestationByAddress(String address);
}
