package com.addressbook.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.addressbook.app.model.Contact;

public class AddressBook {
	
	static Scanner sc = new Scanner(System.in);
	private List<Contact> contactList = new ArrayList<>();
	static long userId = 100;
	
	
	// Add a new Contact
	public void addContact() {
		Contact newContact = takeInput();
		contactList.add(newContact);
		
		System.out.println("Contact added successfully\n"); 
	}
	
	// Update person name
	public void updateContact(String oldName, Contact newContact) {
		String input[] = oldName.split(" ");
		
		for(Contact contact : contactList) {
			if(contact.getFirstName().equalsIgnoreCase(input[0]) && contact.getLastName().equalsIgnoreCase(input[1])) {				
				contact.setFirstName(newContact.getFirstName());
				contact.setLastName(newContact.getLastName());
				contact.setAddress(newContact.getAddress());
				contact.setCity(newContact.getCity());
				contact.setEmail(newContact.getEmail()); 
				contact.setPhoneNumber(newContact.getPhoneNumber());
				contact.setState(newContact.getState());
				contact.setZip(newContact.getZip()); 
				
				System.out.println("Contact updated successful\n");
				return;
			}
		}
		System.out.println("Contact not found with this name!!!");
	} 
	
	// Validate Contact details
	public boolean validateContact(String name) {
		String input[] = name.split(" ");
		
		for(Contact contact : contactList) {
			if(contact.getFirstName().equalsIgnoreCase(input[0]) && contact.getLastName().equalsIgnoreCase(input[1])) {
				return true;
			}
		}
		return false; 
	}
	
	
	public Contact takeInput() {
		System.out.println("Enter first name: ");
		String firstName = sc.nextLine();
		
		System.out.println("Enter last name: ");
		String lastName = sc.nextLine();
		
		System.out.println("Enter address name: ");
		String address = sc.nextLine();
		
		System.out.println("Enter city name: ");
		String city = sc.nextLine();
		
		System.out.println("Enter state name: ");
		String state = sc.nextLine();
		
		System.out.println("Enter zip: ");
		String zip = sc.nextLine();
		
		System.out.println("Enter phoneNumber: ");
		String phoneNumber = sc.nextLine();
		
		System.out.println("Enter email: ");
		String email = sc.nextLine();
		 
		++userId;
		
		return new Contact(userId, firstName, lastName, address, city, state, zip, phoneNumber, email); 
	}
}