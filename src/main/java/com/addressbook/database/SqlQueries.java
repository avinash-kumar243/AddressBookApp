package com.addressbook.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.addressbook.app.model.Contact;

public class SqlQueries {
	
	//add new contact to database 
	public void addContact(Contact contact) {
		String sql = "INSERT INTO contacts (userId, firstName, lastName, address, city, state, zip, phoneNumber, email) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try(Connection connection = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setLong(1, contact.getUserId());
			statement.setString(2, contact.getFirstName());
			statement.setString(3, contact.getLastName());
			statement.setString(4, contact.getAddress());
			statement.setString(5, contact.getCity());
			statement.setString(6, contact.getState());
			statement.setString(7, contact.getZip());
			statement.setString(8, contact.getPhoneNumber());
			statement.setString(9, contact.getEmail());
			
			statement.executeUpdate();
			System.out.println("Contact addded.");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//view all contact to database
	public List<Contact> viewAllContacts() {		
		String sql = "SELECT * FROM contacts";
		List<Contact> contactList = new ArrayList<>(); 

		try(Connection connection = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Contact c = new Contact(result.getLong("userId"), result.getString("firstName"), result.getString("lastName"), result.getString("address"), result.getString("city"), result.getString("state"), result.getString("zip"), result.getString("phoneNumber"),result.getString("email"));
				contactList.add(c);
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return contactList;
	}
}