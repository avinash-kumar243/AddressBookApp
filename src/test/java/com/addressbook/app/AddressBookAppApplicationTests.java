package com.addressbook.app;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.addressbook.database.SqlQueries;

@SpringBootTest
class AddressBookAppApplicationTests {

	@Test
	void contextLoads() {
	}  
	
	@Test
	public void getDetailTest() throws SQLException {
		System.out.println(SqlQueries.getAllContacts());
		assertTrue(SqlQueries.getAllContacts().size() > 0);
	}

}
