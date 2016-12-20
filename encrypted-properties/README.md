# Encrypted Properties Example

This sample show how to use jasypt (http://www.jasypt.org/) to store (and retrieve) sensitive information in properties file (or PID) in a Fuse blueprint application.

### What we need

Jasypt takes two(2) inputs:
* encryption algorithm
* encryption key

We can get a list of supported algorithm using the ```listAlgorithms.sh|bat``` script from jasypt distribution.

The encryption key is just a string that can be supplied via environment variable or read from a file. This sample uses an environment variable.

### POM

Add the following dependency to POM
```xml
	<dependency>
		<groupId>org.jasypt</groupId>
		<artifactId>jasypt</artifactId>
	</dependency>
```

and import the jasypt packages...
```xml
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.felix</groupId>
				<artifactId>maven-bundle-plugin</artifactId>
				<version>2.3.7</version>
				<extensions>true</extensions>
				<configuration>
					<instructions>
						<Include-Resource>src/main/resources</Include-Resource>
						<Bundle-SymbolicName>${project.artifactId}</Bundle-SymbolicName>
						<Import-Package>
							org.jasypt.encryption.*,
							org.osgi.service.blueprint.*
						</Import-Package>
					</instructions>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

### Blueprint

Add property placeholder
```xml
    <cm:property-placeholder
        id="property-placeholder-6915a88b-e0a0-4cc5-9e56-31d2d4f494a1" persistent-id="fuse.sample.dummy.datasource"/>
```

and jasypt component:
```xml
    <enc:property-placeholder id="property-placeholder-a635da11-12b3-4ad1-912b-ce607869fa50">
        <enc:encryptor class="org.jasypt.encryption.pbe.StandardPBEStringEncryptor">
            <property name="config">
                <bean class="org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig">
                    <property name="algorithm" value="PBEWITHSHA1ANDRC4_128"/>
                    <property name="passwordEnvName" value="JASYPT_ENCRYPTION_KEY"/>
                </bean>
            </property>
        </enc:encryptor>
    </enc:property-placeholder>
```

Here we declare the encrypted property placeholder, specify the algorithm literally, and read the encryption key from environment variable ```JASYPT_ENCRYPTION_KEY```

Now we can define the encrypted properties using between ```ENC(``` and ```)```

```
password=ENC(EbPChNavajVW8rJN2G+qRj8=)
```

### Trying it out

Add the following features to the profile:
* jasypt-encryption

Add a property file named ```fuse.sample.dummy.datasource.properties``` with the following content
```
url=jdbc:dummy/dummyDB
username=user1
password=ENC(EbPChNavajVW8rJN2G+qRj8=)
```

Now, start JBoss Fuse like this (Linux, bash):
```
(export JASYPT_ENCRYPTION_KEY=secret88; bin/fuse)
```
and see check the logs for to find the secret password.


