package fuse.sample.cxf.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fuse.sample.cxf.rest.model.Driver;

public interface DriverService {

	@GET
	@Path(value = "/{driver.id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Driver retrieveDriver(@PathParam("driver.id") Integer id);

}
