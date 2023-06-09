package com.devnatao.springrestfulapicourse.integrationetests.swagger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;
import org.springframework.boot.test.context.SpringBootTest;

import com.devnatao.springrestfulapicourse.config.TestConfigs;
import com.devnatao.springrestfulapicourse.integrationetests.testcontainers.AbstractIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {
	
	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content = 
		given()
		.basePath("/swagger-ui/index.html")
		.port(TestConfigs.SERVER_PORT)
		.when()
			.get()
		.then()
			.statusCode(200)
		.extract()
			.body().asString();
		
		assertTrue(content.contains("Swagger UI"));
	}

}
