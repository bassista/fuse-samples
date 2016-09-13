# REST DSL Example

This example shows how to create a REST service using REST DSL on JBoss Fuse. The example uses servlet REST implementation, so that the REST service will use the container's HTTP(S) port to serve requests.

The key is to register the camel servlet with OSGi HttpService in the blueprint like this:
```xml
	<!-- to setup camel servlet with OSGi HttpService -->
	<reference id="httpService" interface="org.osgi.service.http.HttpService" />
	
	<bean class="org.apache.camel.component.servlet.osgi.OsgiServletRegisterer"
		init-method="register" destroy-method="unregister">
		<property name="alias" value="/rest-dsl-blueprint" />
		<property name="httpService" ref="httpService" />
		<property name="servlet" ref="camelServlet" />
	</bean>

	<bean id="camelServlet" class="org.apache.camel.component.servlet.CamelHttpTransportServlet" />
```
Thereafter, you can use REST DSL in the camel context, like this:
```xml
	<restConfiguration component="servlet" bindingMode="json" />

	<rest path="/say">
		<get uri="/hello">
			<to uri="direct:hello" />
		</get>
		<get uri="/bye">
			<to uri="direct:bye" />
		</get>
	</rest>
```

## Deployment
The container should have the camel-blueprint, camel-servlet and camel-jackson features.
