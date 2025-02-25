package com.trimble.cars.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leases")
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String leaseStart;
    private String leaseEnd;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

	public Lease() {
		super();
	}

	public Lease(Long id, String leaseStart, String leaseEnd, Car car, User customer) {
		super();
		this.id = id;
		this.leaseStart = leaseStart;
		this.leaseEnd = leaseEnd;
		this.car = car;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeaseStart() {
		return leaseStart;
	}

	public void setLeaseStart(String leaseStart) {
		this.leaseStart = leaseStart;
	}

	public String getLeaseEnd() {
		return leaseEnd;
	}

	public void setLeaseEnd(String leaseEnd) {
		this.leaseEnd = leaseEnd;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

   
}
