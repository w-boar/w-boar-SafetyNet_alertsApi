package com.safetyNet.alertsApi.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.safetyNet.alertsApi.AlertsApiApplication;
import com.safetyNet.alertsApi.model.Firestation;

@Repository("firestationDao")
public class FirestationDataAccessService implements FirestationDAO {
	private static final Logger logger = LogManager.getLogger(AlertsApiApplication.class);
	private static JsonReader jsonReader = new JsonReader();
	private static ArrayList<Firestation> firestations;

	public FirestationDataAccessService() {
		super();
		JSONObject dataJsonObject = jsonReader.readDataFromJsonFile();
		firestations = jsonReader.getFirestationsFromJson(dataJsonObject);

	}

	@Override
	public ArrayList<Firestation> getAllFirestations() {
		return firestations;
	}

	@Override
	public int insertFirestation(Firestation firestation) {
		firestations.add(new Firestation(firestation.getAddress(), firestation.getStation()));
		return 1;
	}

	@Override
	public Optional<Firestation> getFirestationByAddress(String address) {
		Firestation namedFirestation = new Firestation();
		for (Firestation firestation : firestations) {
			if (firestation.getAddress().equals(address))
				namedFirestation = firestation;
		}
		return Optional.of(namedFirestation);
	}

	@Override
	public int updateFirestationByAddress(String address, Firestation updatedFirestation) {
		Optional<Firestation> firestationToUpdate = getFirestationByAddress(address);
		if (firestationToUpdate == null) {
			return 0;
		}
		ArrayList<Firestation> firestationsMemo = new ArrayList<Firestation>();
		for (Firestation firestation : firestations) {
			if (!firestation.getAddress().equals(address)) {
				firestationsMemo.add(firestation);
			} else if (firestation.getAddress().equals(address)) {
				firestationsMemo.add(updatedFirestation);
			}
		}
		firestations = firestationsMemo;
		return 1;
	}

	@Override
	public int deleteFirestationByAddress(String address) {
		Optional<Firestation> firestationToDelete = getFirestationByAddress(address);
		if (firestationToDelete == null) {
			return 0;
		}
		ArrayList<Firestation> firestationsMemo = new ArrayList<Firestation>();
		for (Firestation firestation : firestations) {
			if (!(firestation.getAddress().equals(address)))
				firestationsMemo.add(firestation);
		}
		firestations = firestationsMemo;
		return 1;
	}
}
