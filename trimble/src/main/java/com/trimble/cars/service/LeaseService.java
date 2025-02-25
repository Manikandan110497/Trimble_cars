package com.trimble.cars.service;


import com.trimble.cars.entity.Car;
import com.trimble.cars.entity.Lease;
import com.trimble.cars.entity.User;
import com.trimble.cars.repo.CarRepository;
import com.trimble.cars.repo.LeaseRepository;
import com.trimble.cars.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaseService {

	@Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    public String startLease(Long carId, Long customerId) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (car.getStatus() != CarStatus.AVAILABLE.toString()) {
            throw new RuntimeException("Car is not available for lease");
        }

        long activeLeases = leaseRepository.countByCustomerIdAndLeaseEndIsNull(customerId);
        if (activeLeases >= 2) {
            throw new RuntimeException("Customer already has 2 active leases");
        }

        Lease lease = new Lease();
        lease.setCar(car);
        lease.setCustomer(customer);
        leaseRepository.save(lease);

        car.setStatus(CarStatus.ON_LEASE.toString());
        carRepository.save(car);

        return "Lease started successfully!";
    }

    public String endLease(Long leaseId) {
        Lease lease = leaseRepository.findById(leaseId)
                .orElseThrow(() -> new RuntimeException("Lease not found"));

        if (lease.getLeaseEnd() != null) {
            throw new RuntimeException("Lease already ended");
        }

        lease.setLeaseEnd(LocalDateTime.now().toString());
        leaseRepository.save(lease);

        Car car = lease.getCar();
        car.setStatus(CarStatus.AVAILABLE.toString());
        carRepository.save(car);

        return "Lease ended successfully!";
    }

    public List<Lease> getLeaseHistory(Long customerId) {
        return leaseRepository.findByCustomer(customerId);
    }
}

