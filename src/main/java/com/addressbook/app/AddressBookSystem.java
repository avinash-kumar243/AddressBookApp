package com.addressbook.app;

import java.util.HashMap;
import java.util.Map;

public class AddressBookSystem {
	Map<String, AddressBook> addressBookSystem = new HashMap<>();
	
	// Add address book to the system
	public void addAddressBook(String name) {
		if(!existAddressBook(name)) {
			addressBookSystem.put(name, new AddressBook()); 
		}
	}
	
	// Validate duplicate address book
	public boolean existAddressBook(String name) {
		return addressBookSystem.containsKey(name); 
	}
	
	// List all address book
	public void listAllAddressBook() {
		addressBookSystem.keySet().forEach(System.out::println); 
		System.out.println();
	}
	
	// Get address book by their name
	public AddressBook getAddressBook(String name) {
		if(existAddressBook(name)) {
			return addressBookSystem.get(name); 
		} 
		return null; 
	}
}