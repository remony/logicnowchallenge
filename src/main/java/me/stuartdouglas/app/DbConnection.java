package me.stuartdouglas.app;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by remon on 06/04/2015.
 */
public class DbConnection {


    public static LinkedList getAllContacts()  {
        return getAllContactsDB();
    }



    private static LinkedList getAllContactsDB() {
        LinkedList<Contact> contacts = new LinkedList();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database");

            PreparedStatement ps = connection.prepareStatement("select id, first_name, last_name from contact");

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Contact contact = new Contact();
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                contact.setId(rs.getInt("id"));
                contacts.add(contact);
            }


        }   catch (SQLException e)  {
            System.err.println(e.getMessage());
        }   finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }   catch (SQLException e)  {
                //failed to close connection
                System.err.println(e.getMessage());
            }

        }
        return contacts;
    }

    public static LinkedList getContact(int id) {

        LinkedList<Contact> contact = new LinkedList();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database");

            PreparedStatement ps = connection.prepareStatement("select id, first_name, last_name from contact where id = " + id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Contact item = new Contact();
                item.setFirstName(rs.getString("first_name"));
                item.setLastName(rs.getString("last_name"));
                item.setId(rs.getInt("id"));
                contact.add(item);
            }


        }   catch (SQLException e)  {
            System.err.println(e.getMessage());
        }   finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }   catch (SQLException e)  {
                //failed to close connection
                System.err.println(e.getMessage());
            }

        }

        return contact;
    }
}
