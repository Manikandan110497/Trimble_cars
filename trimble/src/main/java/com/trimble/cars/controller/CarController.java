package com.trimble.cars.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trimble.cars.entity.Car;
import com.trimble.cars.entity.Lease;
import com.trimble.cars.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
	 private final CarService carService;

	    public CarController(CarService carService) {
	        this.carService = carService;
	    }

	    @PostMapping("/add/{ownerId}")
	    public ResponseEntity<String> registerCar(@PathVariable Long ownerId,  @RequestBody Car car) {
	        return ResponseEntity.ok(carService.registerCar(ownerId, car.getModel()));
	    }

	    @GetMapping("/status/{carId}")
	    public ResponseEntity<String> getCarStatus(@PathVariable Long carId) {
	        return ResponseEntity.ok(carService.getCarStatus(carId));
	    }

	    @GetMapping("/history/{carId}")
	    public ResponseEntity<List<Lease>> getCarLeaseHistory(@PathVariable Long carId) {
	        return ResponseEntity.ok(carService.getCarLeaseHistory(carId));
	    }

	    @GetMapping("/list")
	    public ResponseEntity<List<Car>> getAvailableCars() {
	        return ResponseEntity.ok(carService.getAvailableCars());
	    }
}