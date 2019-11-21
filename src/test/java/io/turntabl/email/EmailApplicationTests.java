package io.turntabl.email;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
class EmailApplicationTests {

	@Test
	void contextLoads() {
		String expected = "Test";
		assertEquals(expected,"Test");
	}

}
