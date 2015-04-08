package me.stuartdouglas.app;

import java.sql.*;
import java.util.LinkedList;


public class DbConnection {

    public static LinkedList<Contact> getAllContacts() {
        LinkedList<Contact> contacts = new LinkedList<Contact>();
        Connection connection = null;

        try {
            connection = getDBConnection();

            PreparedStatement ps = connection.prepareStatement("select id, first_name, last_name from contact");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                contact.setId(rs.getInt("id"));
                contacts.add(contact);
            }


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //failed to close connection
                System.err.println(e.getMessage());
            }

        }
        return contacts;
    }

    public static Contact getContact(int id) {

        Contact contact = new Contact();
        Connection connection = null;

        try {
            connection = getDBConnection();

            PreparedStatement ps = connection.prepareStatement("select id, first_name, last_name from contact where id = " + id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                contact.setId(rs.getInt("id"));
            }


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //failed to close connection
                System.err.println(e.getMessage());
            }

        }

        return contact;
    }


    //Saves the contact to the database by updating it using the id
    public static void saveContact(int id, String firstName, String lastName) {
        Connection connection = null;

        try {
            connection = getDBConnection();

            PreparedStatement ps = connection.prepareStatement("update contact set first_name = ?, last_name = ? WHERE id = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //failed to close connection
                System.err.println(e.getMessage());
            }

        }

    }


    public static void deleteContact(int id) {
        Connection connection = null;
        try {
            connection = getDBConnection();

            PreparedStatement ps = connection.prepareStatement("DELETE FROM contact WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //failed to close connection
                System.err.println(e.getMessage());
            }

        }
    }


    public static boolean createContact(String firstName, String lastName) {
        Connection connection = null;
        try {
            connection = getDBConnection();

            PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (first_name, last_name) VALUES (?, ?)");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //failed to close connection
                System.err.println(e.getMessage());
            }

        }
        return true;
    }


    public static Connection getDBConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return connection;
    }
}
