# BOM dependency management sample

This sample shows how to take advantage of the JBoss Fuse BOM to manage 
the dependencies of bundles and jars distributed with JBoss Fuse. This 
ensures that the correct (supported) version of bundles are used in your
projects.

To take advantage of the BOM, add the jboss-fuse-parent POM as a dependency
in the dependencyManagement POM entry:

```xml
	<properties>
		<jboss.fuse.version>6.2.1.redhat-117</jboss.fuse.version>
	</properties>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.jboss.fuse.bom</groupId>
				<artifactId>jboss-fuse-parent</artifactId>
				<version>${jboss.fuse.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```

Typically this will be done in a parent project. In this sample, it is in 
parent/pom.xml

Next in the child project, instead of adding the GAV maven dependency coordinates,
simply specify the groupId and artifactId. If the specified artifact is listed
in the BOM, then maven will be able to resolve and retrieve the correct version.

For example:
```xml
	<dependencies>
		<!-- fuse/camel components -->
		<!-- notice that we don't specify version here, it's inferred from the 
			BOM we imported in the parent project -->
		<dependency>
			<groupId>org.apache.camel</groupId>
			<artifactId>camel-core</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.camel</groupId>
			<artifactId>camel-blueprint</artifactId>
		</dependency>
	</dependencies>
```
Of course, you'll still need to specify the version if the dependency is not listed
in the BOM.

