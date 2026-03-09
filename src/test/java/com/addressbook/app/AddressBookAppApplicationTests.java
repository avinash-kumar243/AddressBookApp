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
	
	@Test
    public void givenContact_whenUpdatedInJsonServer_shouldSyncWithMemory() {

        // 1. Get existing contact by id (assume id = 1)
        Response getResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/contacts/1");

        Contact contact = getResponse.as(Contact.class);

        assertNotNull(contact); // Ensure contact exists

        // 2️. Update fields locally
        contact.setCity("New Delhi");
        contact.setPhoneNumber("9998887777");

        // 3️. Send PUT request to JSON Server
        Response putResponse = given()
                .contentType(ContentType.JSON)
                .body(contact)
                .when()
                .put("http://localhost:3000/contacts/1");

        // Verify status code 200 (success)
        putResponse.then().statusCode(200);

        // 4️. Update in-memory AddressBook
        // Remove old contact if exists
        addressBookMemory.removeIf(c -> c.getUserId() == contact.getUserId());
        addressBookMemory.add(contact);

        // Print memory
        System.out.println("AddressBook Memory after update:");
        addressBookMemory.forEach(System.out::println);

        // Optional assertion
        assertTrue(addressBookMemory.stream()
                .anyMatch(c -> c.getUserId() == 1 && c.getCity().equals("New Delhi")));
    }
	 
}
