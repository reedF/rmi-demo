package com.reed.rmi.version.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * load service-jar's pom version if war dependened jars to import service
 * class,then this config can get version for jar
 * 
 * @author reed
 * 
 */
@Configuration
@PropertySource(name = "version", value = { "classpath:META-INF/maven/org.mybatis/mybatis/pom.properties" })
public class VersionConfig {

}
