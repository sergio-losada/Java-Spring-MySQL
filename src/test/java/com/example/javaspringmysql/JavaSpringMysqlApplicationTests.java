package com.example.javaspringmysql;

import java.io.IOException;
import java.net.Socket;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(
    classes = {JavaSpringMysqlApplicationTests.class}, 
    properties = "spring.main.lazy-initialization=true")
@SuppressWarnings({"rawtypes"})
class JavaSpringMysqlApplicationTests {
	
    @ClassRule
	public static MySQLContainer container = new MySQLContainer("mysql:8.0.11");

	@Before
	public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
		TestPropertyValues values = TestPropertyValues.of(
			"spring.datasource.url=" + container.getJdbcUrl(),
			"spring.datasource.username=" + container.getUsername(),
			"spring.datasource.password=" + container.getPassword());
		values.applyTo(configurableApplicationContext);
	}

    @BeforeEach
    public void init() {
        container.start();;
    }
	
	@Test
    public void test01_port() {
        this.checkPortAvailability(container);
    }

	private void checkPortAvailability(MySQLContainer container) {
        Socket socket;
        try {
            socket = new Socket(container.getContainerIpAddress(), container.getFirstMappedPort());
            socket.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
