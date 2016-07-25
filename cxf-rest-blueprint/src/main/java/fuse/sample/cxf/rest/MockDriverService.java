package fuse.sample.cxf.rest;

import java.security.InvalidParameterException;

import fuse.sample.cxf.rest.model.Driver;

public class MockDriverService implements DriverService {

	@Override
	public Driver retrieveDriver(Integer id) {
		if (id < 0) {
			throw new InvalidParameterException("driver id should be a positive integer");
		}

		Driver d = new Driver();
		d.setId(id);
		d.setName("Driver " + id);
		d.setRewardPoints(100);
		d.setSafetyScore(120);
		d.setServiceScore(110);
		d.setServiceCredits(500);
		return d;
	}
}
