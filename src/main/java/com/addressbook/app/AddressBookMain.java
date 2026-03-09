package com.addressbook.app;

import java.util.List;
import java.util.Scanner;

import com.addressbook.app.model.Contact;
import com.addressbook.database.SqlQueries;

public class AddressBookMain {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		SqlQueries query = new SqlQueries();
		
		while(true) {
			System.out.println("\n------------ Address Book App -------------");
			System.out.println("Enter 1 to add a contact");
			System.out.println("Enter 2 to view all contacts");
			System.out.println("Enter 0 to exit");
			System.out.println("-------------------------------------------");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1: 
					Contact contact = takeInput();
					query.addContact(contact);
					break;
								
				case 2:
					List<Contact> contactList = query.viewAllContacts();
					contactList.stream().forEach(System.out::println); 
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
	
	public static Contact takeInput() {
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
		
		return new Contact(0, firstName, lastName, address, city, state, zip, phoneNumber, email); 
	}
}