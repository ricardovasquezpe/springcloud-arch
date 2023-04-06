package com.revendedor.ticketservice.integration;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class AbstractionBaseTest {
    static final MySQLContainer MY_SQL_CONTAINER;

    static{
        MY_SQL_CONTAINER = new MySQLContainer("mysql:latest")
                .withUsername("username")
                .withPassword("123456")
                .withDatabaseName("ticketdb");

        MY_SQL_CONTAINER.start();
    }

    /*@DynamicPropertySource
    public static void dynamicPropertySource(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }*/
}
