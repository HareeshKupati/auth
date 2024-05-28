package com.hbk.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.DependsOn;

@SpringBootTest
@DependsOnDatabaseInitialization
class AuthApplicationTests {

	@Test
	void contextLoads() {
	}

}
