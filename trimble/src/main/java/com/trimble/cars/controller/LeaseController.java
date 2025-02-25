package com.trimble.cars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trimble.cars.entity.Lease;
import com.trimble.cars.service.LeaseService;

@RestController
@RequestMapping("/lease")
public class LeaseController {

	 private final LeaseService leaseService;

	    public LeaseController(LeaseService leaseService) {
	        this.leaseService = leaseService;
	    }

	    @PostMapping("/start/{carId}/{customerId}")
	    public ResponseEntity<String> startLease(@PathVariable Long carId, @PathVariable Long customerId) {
	        return ResponseEntity.ok(leaseService.startLease(carId, customerId));
	    }

	    @PostMapping("/end/{leaseId}")
	    public ResponseEntity<String> endLease(@PathVariable Long leaseId) {
	        return ResponseEntity.ok(leaseService.endLease(leaseId));
	    }

	    @GetMapping("/history/{customerId}")
	    public ResponseEntity<List<Lease>> getLeaseHistory(@PathVariable Long customerId) {
	        return ResponseEntity.ok(leaseService.getLeaseHistory(customerId));
	    }
}
