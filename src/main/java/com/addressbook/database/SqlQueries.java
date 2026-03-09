package com.addressbook.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.addressbook.app.model.Contact;

public class SqlQueries {
//	DatabaseConnection singleton = DatabaseConnection.getInstance();
//	Connection connection = singleton.getConnection(); 
	
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
	public void viewAllContact() {		
		String sql = "SELECT * FROM contacts";

		try(Connection connection = DatabaseConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Contact c = new Contact(result.getLong("userId"), result.getString("first_name"), result.getString("last_name"), result.getString("address"), result.getString("city"), result.getString("state"), result.getString("zip"), result.getString("phone_number"),result.getString("email"));
				System.out.println(c);
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<Contact> getAllContacts() {
	    List<Contact> contacts = new ArrayList<>();
	    String query = "SELECT * FROM contacts";

	    try(Connection con = DatabaseConnection.getInstance().getConnection();
	        PreparedStatement ps = con.prepareStatement(query);
	        ResultSet rs = ps.executeQuery()) {

	        while(rs.next()) {
	            Contact contact = new Contact(
	                    rs.getLong("userId"),
	                    rs.getString("firstName"),
	                    rs.getString("lastName"),
	                    rs.getString("address"),
	                    rs.getString("city"),
	                    rs.getString("state"),
	                    rs.getString("zip"),
	                    rs.getString("phoneNumber"),
	                    rs.getString("email")
	            );

	            contacts.add(contact);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return contacts;
	}
}