package com.addressbook.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.addressbook.app.model.Contact;

public class AddressBook {
	
	static Scanner sc = new Scanner(System.in);
	private List<Contact> contactList = new ArrayList<>();
	static long userId = 100;
	
	
	// Add a new Contact
	public void addContact() {
		Contact newContact = takeInput();
		
		if(!duplicateCheck(newContact)) {
			contactList.add(newContact);
			System.out.println("Contact added successfully\n"); 
		} else {
			System.out.println("Duplicate contact\n"); 
		}
	}
	
	// Update Contact
	public void updateContact(String oldName, Contact newContact) {		
		for(Contact contact : contactList) {
			if(oldName.equalsIgnoreCase(contact.getFirstName() + " " + contact.getLastName())) {				
				contact.setFirstName(newContact.getFirstName());
				contact.setLastName(newContact.getLastName());
				contact.setAddress(newContact.getAddress());
				contact.setCity(newContact.getCity());
				contact.setEmail(newContact.getEmail()); 
				contact.setPhoneNumber(newContact.getPhoneNumber());
				contact.setState(newContact.getState());
				contact.setZip(newContact.getZip()); 
				
				System.out.println("Contact updated successful");
				break;
			}
		}
	} 
	
	// Delete Contact
	public void deleteContact(String name) {		
		for(Contact contact : contactList) {
			if(name.equalsIgnoreCase(contact.getFirstName() + " " + contact.getLastName())) {
				contactList.remove(contact);
				System.out.println("Contact deleted successfully");
				break;
			} 
		}
	}
	
	// Validate Contact details
	public boolean validateContact(String name) {		
		for(Contact contact : contactList) {
			if(name.equalsIgnoreCase(contact.getFirstName() + " " + contact.getLastName())) {
				return true;
			}
		}
		return false; 
	}
	
	// View all Contacts
	public void viewAllContacts() {
		for(Contact contact : contactList) {
			System.out.println(contact);
		}
		System.out.println();
	}
	
	// Ensure no duplicate contacts
	public boolean duplicateCheck(Contact thatContact) {
		for(Contact contact : contactList) {
			if(contact.equals(thatContact)) {
				return true; // Duplicate contact found
			}
		}
		return false; // No duplicate
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

	public void searchPersonByCity(String cityName) {
		List<Contact> contacts = contactList.stream()
											.filter(c -> c.getCity().equalsIgnoreCase(cityName))
											.collect(Collectors.toList());
		if(contacts.isEmpty()) {
			System.out.println("No contact found with this city name!!!\n");
			
			System.out.println("Please enter state name to search all contacts: ");
			String stateName = sc.nextLine();
			
			searchPersonByStateName(stateName);
			return;
		}
		
		for(Contact c : contacts) {
			System.out.println(c);
		}
	}

	private void searchPersonByStateName(String stateName) {
		List<Contact> contacts = contactList.stream()
											.filter(c -> c.getState().equalsIgnoreCase(stateName))
											.collect(Collectors.toList());
		
		if(contacts.isEmpty()) {
			System.out.println("No contact found with this state name!!!\n");
			return;
		}
		
		for(Contact c : contacts) {
			System.out.println(c);
		}
	}
	
	// View person name and city
	public void viewPersonByCity(String cityName) {
		contactList.stream()
		   .filter(c -> c.getCity().equalsIgnoreCase(cityName))
		   .forEach(c -> System.out.println("Name: " + c.getFirstName() + " " + c.getLastName() + " || City: " + c.getCity()));
	}

	// View person name and state
	public void viewPersonByState(String stateName) {
		contactList.stream()
				   .filter(c -> c.getState().equalsIgnoreCase(stateName))
				   .forEach(c -> System.out.println("Name: " + c.getFirstName() + " " + c.getLastName() + " || State: " + c.getState()));
	}

	public void countPersonByCity(String cityName) {
		long count = contactList.stream()
				   .filter(c -> c.getCity().equalsIgnoreCase(cityName))
				   .count();
		System.out.println("Total number of contacts: " + count + " for city: " + cityName);
	}

	public void countPersonByState(String stateName) {
		long count = contactList.stream()
				   .filter(c -> c.getState().equalsIgnoreCase(stateName))
				   .count();
		System.out.println("Total number of contacts: " + count + " for state: " + stateName);
	}

	public void sortPersonContactByName() {
		contactList.stream()
				   .sorted(Comparator.comparing(Contact::getFirstName))
				   .collect(Collectors.toList())
				   .forEach(System.out::println);
	}

	public void sortPersonContactByZip() {
		contactList.stream()
				   .sorted(Comparator.comparing(Contact::getZip))
				   .collect(Collectors.toList())
				   .forEach(System.out::println);
	}
	
	public void writePersonContact() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt"))) {
			for(Contact contact : contactList) {
				writer.write(contact.toString());
				writer.newLine();
			}
			
			System.out.println("Contacts written to file successfully");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void readPersonContact() {
		try(BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
			
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}