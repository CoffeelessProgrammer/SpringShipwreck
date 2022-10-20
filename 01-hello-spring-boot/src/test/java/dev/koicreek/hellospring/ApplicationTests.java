package dev.koicreek.hellospring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {

	@Autowired
	Environment env;

	@Test
	void PropertiesConfig_WHEN_PropertiesAndYamlPresent_THEN_PropertiesOverridesYaml() {
		final String property = env.getProperty("token.precedence");
		System.out.println("token.precedence=" + property);
		assertEquals("Hello properties!", property);
	}

}
