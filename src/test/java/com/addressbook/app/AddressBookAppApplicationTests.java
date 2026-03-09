package com.addressbook.app;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.addressbook.app.model.Contact;
import com.addressbook.database.SqlQueries;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest
class AddressBookAppApplicationTests {
	
	SqlQueries query = new SqlQueries();
	List<Contact> addressBookMemory = new ArrayList<>();

	@Test
	void contextLoads() {
	}  
	
	@Test
	public void getDetailTest() throws SQLException {
		assertTrue(query.viewAllContacts().size() > 0); 
	}

	 @Test
    public void givenMultipleContacts_whenAdded_shouldSyncWithMemory() {

        List<Contact> newContacts = new ArrayList<>();

        newContacts.add(new Contact(0,"John","Doe","Street1","Delhi","Delhi","110001","9999999999","john@gmail.com"));
        newContacts.add(new Contact(0,"Sara","Lee","Street2","Mumbai","MH","400001","8888888888","sara@gmail.com"));
        newContacts.add(new Contact(0,"Mike","Ross","Street3","Pune","MH","411001","7777777777","mike@gmail.com"));

        newContacts.forEach(contact -> {

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(contact)
                    .when()
                    .post("http://localhost:3000/contacts");

            Contact responseContact = response.as(Contact.class);

            // Sync with memory
            addressBookMemory.add(responseContact);
        });

        System.out.println("Contacts in AddressBook Memory:");

        addressBookMemory.forEach(System.out::println); 
    }
}
