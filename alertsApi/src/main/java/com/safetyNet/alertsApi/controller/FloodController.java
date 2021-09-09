package com.safetyNet.alertsApi.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.alertsApi.AlertsApiApplication;
import com.safetyNet.alertsApi.model.PersonForEmergencyCase;
import com.safetyNet.alertsApi.service.FloodService;

@RequestMapping("/flood")
@RestController
public class FloodController {
	private static final Logger logger = LogManager.getLogger(AlertsApiApplication.class);
	private final FloodService floodService;

	public FloodController(FloodService floodService) {
		super();
		this.floodService = floodService;
	}
	@RequestMapping("/stations")
	@GetMapping
	public ArrayList<PersonForEmergencyCase> getPersonsListByFirestationNumberList(@RequestParam ArrayList<String> stations){
		logger.info("Get persons list by firestation's number list : ");
		for (String station :stations) {
			logger.info(station);
		}
//		stations.forEach(station -> System.out.println(station));
		
		for(PersonForEmergencyCase pfec : floodService.getPersonsListByFirestationNumberList(stations)) {
			logger.info(pfec.getAddress()+" "+pfec.getFirestation()+" "+pfec.getFirstName()+" "+pfec.getLastName()+" "+pfec.getAge()+" "+pfec.getPhone()+" "+ pfec.getMedications(), pfec.getAllergies());
		}
//		stations.forEach(pfec -> System.out.println(pfec.toString()));
		return floodService.getPersonsListByFirestationNumberList(stations);
	}
}
