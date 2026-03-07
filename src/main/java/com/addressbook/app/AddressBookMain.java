package com.addressbook.app;

import java.util.Scanner;

public class AddressBookMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		AddressBook addressBook = new AddressBook();
		
		
		// Add a contact 
		addressBook.addContact(); 
		
		
		sc.close();
	}
}