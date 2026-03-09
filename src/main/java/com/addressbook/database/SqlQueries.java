package com.addressbook.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.addressbook.app.model.Contact;

public class SqlQueries {
	
	private List<Contact> contactList = new ArrayList<>();
	
	//add new contact to database 
	public void addContact(Contact contact) {
		String sql = "INSERT INTO contacts (firstName, lastName, address, city, state, zip, phoneNumber, email) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		try(Connection connection = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, contact.getFirstName());
			statement.setString(2, contact.getLastName());
			statement.setString(3, contact.getAddress());
			statement.setString(4, contact.getCity());
			statement.setString(5, contact.getState());
			statement.setString(6, contact.getZip());
			statement.setString(7, contact.getPhoneNumber());
			statement.setString(8, contact.getEmail());
			
			statement.executeUpdate();
			
			contactList.add(contact); 
			System.out.println("Contact addded.");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//view all contact to database
	public List<Contact> viewAllContacts() {	
		contactList.clear();
		String sql = "SELECT * FROM contacts";

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
	
	// Get Contact by name
	public Contact getContactByName(String firstName) {

	    String sql = "SELECT * FROM contacts WHERE firstName = ?";

	    try(Connection connection = DatabaseConnection.getInstance().getConnection();
	        PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, firstName);

	        ResultSet result = statement.executeQuery();

	        if(result.next()) {
	            return new Contact(
	                    result.getLong("userId"),
	                    result.getString("firstName"),
	                    result.getString("lastName"),
	                    result.getString("address"),
	                    result.getString("city"),
	                    result.getString("state"),
	                    result.getString("zip"),
	                    result.getString("phoneNumber"),
	                    result.getString("email")
	            );
	        }

	    } catch(Exception e) {
	        System.out.println(e.getMessage());
	    }

	    return null;
	}
	
	// Update Contacts
	public void updateContact(Contact contact) {
		String sql = "UPDATE contacts SET firstName=?, lastName=?, address=?, city=?, state=?, zip=?, phoneNumber=?, email=? WHERE userId=?";
		
		try(Connection connection = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, contact.getFirstName());
			statement.setString(2, contact.getLastName());
			statement.setString(3, contact.getAddress());
			statement.setString(4, contact.getCity());
			statement.setString(5, contact.getState());
			statement.setString(6, contact.getZip());
			statement.setString(7, contact.getPhoneNumber());
			statement.setString(8, contact.getEmail());
			statement.setLong(9, contact.getUserId());
			
			int rowUpdated = statement.executeUpdate();
			
			if(rowUpdated > 0) {
				System.out.println("Contact updated successfully");
				
				for(int i=0; i<contactList.size(); i++) {
					if(contactList.get(i).getUserId() == contact.getUserId()) {
						contactList.set(i, contact);
						break;
					}
				}
			} else {
				System.out.println("Contact not found!!!"); 
			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage()); 
		}
		
	}
	
	// Retrieve contact from database
	public List<Contact> getContactsByDateRange(String startDate, String endDate) {
	    List<Contact> contacts = new ArrayList<>();

	    String sql = "SELECT * FROM contacts WHERE date_added BETWEEN ? AND ?";

	    try(Connection connection = DatabaseConnection.getInstance().getConnection();
	        PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, startDate);
	        statement.setString(2, endDate);

	        ResultSet result = statement.executeQuery();

	        while(result.next()) {
	            Contact c = new Contact(
	                    result.getLong("userId"),
	                    result.getString("firstName"),
	                    result.getString("lastName"),
	                    result.getString("address"),
	                    result.getString("city"),
	                    result.getString("state"),
	                    result.getString("zip"),
	                    result.getString("phoneNumber"),
	                    result.getString("email")
	            );
	            contacts.add(c);
	        }
	    } catch(SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return contacts;
	}
}