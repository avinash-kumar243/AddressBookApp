package com.addressbook.app;

import java.util.Scanner;

import com.addressbook.app.model.Contact;

public class AddressBookMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		AddressBook addressBook = new AddressBook();
		
		
		String name;
		while(true) {
			System.out.println("------------ Address Book App -------------");
			System.out.println("Enter 1 to add a contact");
			System.out.println("Enter 2 to update a contact");
			System.out.println("Enter 3 to delete a contact");
			System.out.println("Enter 0 to exit");
			System.out.println("-------------------------------------------");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
				case 1: 
					addressBook.addContact(); 
					break;
					 
				case 2:
					System.out.println("Please enter name to update contact: ");
					name = sc.nextLine();
			 
					if(addressBook.validateContact(name)) {
						Contact oldContact = addressBook.takeInput();
						addressBook.updateContact(name, oldContact);
					} else {
						System.out.println("Contact not found with this name!!!\n");
					}
					break;
					
				case 3:
					System.out.println("Please enter name to delete contact: ");
					name = sc.nextLine();
					
					if(addressBook.validateContact(name)) {
						addressBook.deleteContact(name);	 
					} else {
						System.out.println("Contact not found with this name!!!\n");
					}
					break; 
					
				case 0:
					System.out.println("Than you for using our Address Book App"); 
					return;
					
				default:
					System.out.println("Invalid choice!!!");
			}
		}
	}
}