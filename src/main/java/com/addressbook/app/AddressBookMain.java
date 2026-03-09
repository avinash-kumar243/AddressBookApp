package com.addressbook.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.addressbook.app.model.Contact;
import com.addressbook.database.SqlQueries;

public class AddressBookMain {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		SqlQueries query = new SqlQueries();
		
		Contact contact;
		while(true) {
			System.out.println("\n------------ Address Book App -------------");
			System.out.println("Enter 1 to add a contact");
			System.out.println("Enter 2 to view all contacts");
			System.out.println("Enter 3 to update a contact");
			System.out.println("Enter 4 to get contact by date range"); 
			System.out.println("Enter 5 to get contacts count by city"); 
			System.out.println("Enter 6 to add multiple contacts using thread"); 
			System.out.println("Enter 0 to exit");
			System.out.println("-------------------------------------------");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1: 
					contact = takeInput();
					query.addContact(contact); 
					break;
								
				case 2:
					List<Contact> contactList = query.viewAllContacts();
					contactList.stream().forEach(System.out::println); 
					break;
					
				case 3:
					System.out.println("Enter contace firt name: ");
					String name = sc.nextLine();
					
					contact = query.getContactByName(name);
					query.updateContact(contact);
					break;
					
				case 4:
					System.out.println("Enter start date: ");
					String startDate = sc.nextLine();
					
					System.out.println("Enter end date: ");
					String endDate = sc.nextLine();
					
					query.getContactsByDateRange(startDate, endDate);
					break;
					
				case 5:
					System.out.println("Enter city: ");
					String city = sc.nextLine();
					
					System.out.println(query.getContactCountByCity(city));
					break;
					
				case 6:
					List<Contact> contacts = new ArrayList<>();

				    contacts.add(new Contact(0,"John","Doe","Street1","Delhi","Delhi","110001","9999999999","john@gmail.com"));
				    contacts.add(new Contact(0,"Mike","Ross","Street2","Mumbai","MH","400001","8888888888","mike@gmail.com"));
				    contacts.add(new Contact(0,"Sara","Lee","Street3","Pune","MH","411001","7777777777","sara@gmail.com"));

				    query.addMultipleContacts(contacts);
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