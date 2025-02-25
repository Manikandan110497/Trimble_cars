package com.trimble.cars.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trimble.cars.entity.Lease;
import com.trimble.cars.entity.User;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {

	long countByCustomerIdAndLeaseEndIsNull(Long customerId);

	List<Lease> findByCustomer(Long customerId);

	List<Lease> findByCarId(Long carId);
}
