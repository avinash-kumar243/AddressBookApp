package com.addressbook.app;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.addressbook.database.SqlQueries;

@SpringBootTest
class AddressBookAppApplicationTests {
	
	SqlQueries query = new SqlQueries();

	@Test
	void contextLoads() {
	}  
	
	@Test
	public void getDetailTest() throws SQLException {
		assertTrue(query.viewAllContacts().size() > 0); 
	}

}
