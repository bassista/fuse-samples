# Implementing REST API using Jax-RS on JBoss Fuse

This example demonstrates how to implement a RESTful service using Jax-RS on JBoss Fuse with the following contract:
```java
public interface DriverService {

	@GET
	@Path(value = "/{driver.id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Driver retrieveDriver(@PathParam("driver.id") Integer id);

}
```

In the blueprint, define the service like this:
```xml
	<jaxrs:server id="driverService" address="/driver">
		<jaxrs:serviceBeans>
			<bean class="fuse.sample.cxf.rest.MockDriverService" />
		</jaxrs:serviceBeans>
		<jaxrs:providers>
			<ref component-id="jsonProvider" />
		</jaxrs:providers>
	</jaxrs:server>

	<bean id="jsonProvider" class="org.apache.cxf.jaxrs.provider.json.JSONProvider">
	</bean>
```

Where ```fuse.sample.cxf.rest.MockDriverService``` is an implementation of the ```DriverService``` interface.

## High Availablility

To make use of the Fuse Fabric HTTP gateway for load balancing and high availablility, add the following dependencies to the POM file:
```xml
	<dependency>
		<groupId>io.fabric8</groupId>
		<artifactId>fabric-cxf</artifactId>
	</dependency>
```
In addition, we also need to add the OSGi dependency ```io.fabric8.cxf``` like this:
```xml
	<build>
		<plugins>
			<!-- to generate the MANIFEST-FILE of the bundle -->
			<plugin>
				<groupId>org.apache.felix</groupId>
				<artifactId>maven-bundle-plugin</artifactId>
				<version>2.3.7</version>
				<extensions>true</extensions>
				<configuration>
					<instructions>
						<Include-Resource>src/main/resources</Include-Resource>
						<Bundle-SymbolicName>${pom.artifactId}</Bundle-SymbolicName>
						<Private-Package>fuse.sample.cxf.rest.*</Private-Package>
						<Import-Package>
							*,io.fabric8.cxf
						</Import-Package>
					</instructions>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

finall, add the following to the blueprint:
```xml	<reference id="curator" interface="org.apache.curator.framework.CuratorFramework" />

	<bean id="fabricLoadBalancerFeature" class="io.fabric8.cxf.FabricLoadBalancerFeature">
		<property name="curator" ref="curator" />
		<property name="fabricPath" value="cxf/driverService" />
	</bean>

	<cxf-core:bus>
		<cxf-core:features>
			<cxf-core:logging />
			<ref component-id="fabricLoadBalancerFeature" />
		</cxf-core:features>
	</cxf-core:bus>
```


## Deployment
The container should have the ```camel-cxf``` feature.

In addition, to work with the HTTP gateway, the container should also have the ```fabric-cxf``` and ```fabric-cxf-registry``` features.
