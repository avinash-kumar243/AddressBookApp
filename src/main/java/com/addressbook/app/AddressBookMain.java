package com.addressbook.app;

import java.util.Scanner;

import com.addressbook.app.model.Contact;

public class AddressBookMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		AddressBook addressBook = new AddressBook();
		
		// Add a contact 
		addressBook.addContact(); 
		
		// Update Person name
		System.out.println("Please enter name to update contact: ");
		String name = sc.nextLine();
 
		if(addressBook.validateContact(name)) {
			Contact oldContact = addressBook.takeInput();
			addressBook.updateContact(name, oldContact);
		} else {
			System.out.println("Contact not found with this name!!!");
		}
		
		
		sc.close();
	}
}