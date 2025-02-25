package com.trimble.cars.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trimble.cars.entity.Car;
import com.trimble.cars.repo.CarRepository;
import com.trimble.cars.repo.LeaseRepository;
import com.trimble.cars.repo.UserRepository;
import com.trimble.cars.entity.Car;
import com.trimble.cars.entity.Lease;
import com.trimble.cars.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

	 @Autowired
	    private CarRepository carRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private LeaseRepository leaseRepository;

	    public String registerCar(Long ownerId, String model) {
	        User owner = userRepository.findById(ownerId)
	                .orElseThrow(() -> new RuntimeException("Owner not found"));

	        Car car = new Car();
	        car.setModel(model);
	        car.setOwner(owner);
	        car.setStatus(CarStatus.AVAILABLE.toString());

	        carRepository.save(car);
	        return "Car registered successfully!";
	    }

	    public String getCarStatus(Long carId) {
	        Car car = carRepository.findById(carId)
	                .orElseThrow(() -> new RuntimeException("Car not found"));
	        return "Car Status: " + car.getStatus();
	    }

	    public List<Lease> getCarLeaseHistory(Long carId) {
	        return leaseRepository.findByCarId(carId);
	    }

	    public List<Car> getAvailableCars() {
	        return carRepository.findByStatus(CarStatus.AVAILABLE.toString());
	    }
}

