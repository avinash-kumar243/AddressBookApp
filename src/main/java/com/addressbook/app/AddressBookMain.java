package com.addressbook.app;

import java.util.Scanner;

import com.addressbook.app.model.Contact;

public class AddressBookMain {
	
	static Scanner sc = new Scanner(System.in);
	static AddressBookSystem system = new AddressBookSystem();

	// Get Address Book Object
	public static AddressBook getAddressBook() {
		System.out.println("Please enter address book name");
		String addressBookName = sc.nextLine();
		
		return system.getAddressBook(addressBookName); 
	}

	public static void main(String[] args) {
		String name, cityName, stateName;
		AddressBook addressBook;
		
		while(true) {
			System.out.println("\n------------ Address Book App -------------");
			System.out.println("Enter 1 to add a contact");
			System.out.println("Enter 2 to update a contact");
			System.out.println("Enter 3 to delete a contact");
			System.out.println("Enter 4 to view all contacts");
			System.out.println("Enter 5 to search person in a city or state");
			System.out.println("Enter 6 to view person by city or state");
			System.out.println("Enter 0 to exit");
			System.out.println("-------------------------------------------");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1: 
					System.out.println("Please enter address book name");
					String addressBookName = sc.nextLine();

					system.addAddressBook(addressBookName);
					
					addressBook = system.getAddressBook(addressBookName); 
					
					addressBook.addContact();
					break;
					 
				case 2:
					addressBook = getAddressBook();
					
					if(addressBook == null) {
						System.out.println("\nAddress book doesn't exist!!!\n\nAvailable address books are");
						system.listAllAddressBook();
						break;
					}
										
					System.out.println("Please enter name to update contact: ");
					name = sc.nextLine();
			 
					if(addressBook.validateContact(name)) {
						Contact oldContact = addressBook.takeInput();
						addressBook.updateContact(name, oldContact);
					} else {
						System.out.println("Contact not found with this name!!!");
					}
					break;
					
				case 3:
					addressBook = getAddressBook();
					
					if(addressBook == null) {
						System.out.println("\nAddress book doesn't exist!!!\n\nAvailable address books are");
						system.listAllAddressBook();
						break;
					}
					
					System.out.println("Please enter name to delete contact: ");
					name = sc.nextLine();
					
					if(addressBook.validateContact(name)) {
						addressBook.deleteContact(name);	 
					} else {
						System.out.println("Contact not found with this name!!!");
					}
					break; 
					
				case 4:
					addressBook = getAddressBook();
					
					if(addressBook == null) {
						System.out.println("\nAddress book doesn't exist!!!\n\nAvailable address books are");
						system.listAllAddressBook();
						break;
					}
					addressBook.viewAllContacts();
					break; 
					
				case 5:
					addressBook = getAddressBook();
					
					if(addressBook == null) {
						System.out.println("\nAddress book doesn't exist!!!\n\nAvailable address books are");
						system.listAllAddressBook();
						break;
					}
					
					System.out.println("Please enter city name to search all contacts: ");
					cityName = sc.nextLine();
					
					addressBook.searchPersonByCity(cityName); 
					break;
					
				case 6:
					addressBook = getAddressBook();
					
					if(addressBook == null) {
						System.out.println("\nAddress book doesn't exist!!!\n\nAvailable address books are");
						system.listAllAddressBook();
						break;
					}
					
					System.out.println("Please enter city name to view all contacts: ");
					cityName = sc.nextLine();
					addressBook.viewPersonByCity(cityName); 
					
					System.out.println("\nPlease enter state name to view all contacts: ");
					stateName = sc.nextLine();
					addressBook.viewPersonByState(stateName); 
					
					break;
					
				case 0:
					sc.close();
					System.out.println("Than you for using our Address Book App"); 
					return;
					
				default:
					System.out.println("Invalid choice!!!");		
			}
		}
	}
}