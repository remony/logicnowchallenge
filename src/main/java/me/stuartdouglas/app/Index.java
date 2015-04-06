package me.stuartdouglas.app; /**
 * Created by remon on 02/04/2015.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

import me.stuartdouglas.app.DbConnection;

public class Index {

    public static void main(String[] args)   {
        Index index = new Index();
        index.run();
    }

    public void run()    {

        while(true) {
            int choice = 0;

            Scanner input = new Scanner(System.in);

            while (choice != 3) {
                System.out.println("1: Add new contact\n2: Edit existing contact\n3: quit");
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        AddNewContact();
                        break;
                    case 2:
                        editExisting();
                        break;
                    case 3:
                        return;
                    default:
                        break;
                }

            }

        }
    }

    private void AddNewContact() {
        String firstName = null;
        String lastName = null;

        Scanner input = new Scanner(System.in);
        while (!validInput(firstName))
        {
            System.out.print("\nFirst name: ");
            firstName = input.nextLine();
        }

        while (!validInput(lastName))    {
            System.out.print("\nLast name: ");
            lastName = input.nextLine();
        }

        if (validInput(firstName))
        {
            DbConnection.createContact(firstName, lastName);
        }   else    {
            System.out.println("something was empty");
        }
    }


    //This method handles editing existing contacts
    private void editExisting() {
        displayContacts(getContacts());
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an ID of a contact");
        int choice = 0;
        int id = input.nextInt();

        if (getContact(id) != null) {

            Contact contact = getContact(id);

            while (choice != 6) {
                displayContact(contact);

                System.out.println("1: Change first name\n2: Edit second name\n3: Save\n4: Delete");
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        String firstname = input.nextLine();
                        contact.setFirstName(firstname);
                        break;
                    case 2:
                        String lastname = input.nextLine();
                        contact.setLastName(lastname);
                        break;
                    case 3:
                        contact.save();
                        choice = 6;
                        break;
                    case 4:
                        contact.delete();
                        choice = 6;
                        break;
                    case 5:
                        choice = 2;
                        break;
                    default:
                        break;
                }
            }
        }   else    {
            System.out.println("ID " + id + " does not exist");
        }
    }



    //Returns true if the value is not empty or contains just spaces
    public boolean validInput(String input) {
        return  !(input == null || input.trim().length() == 0);
    }

    public LinkedList<Contact> getContacts(){
        return DbConnection.getAllContacts();
    }

    public Contact getContact(int id){

        Contact contact = DbConnection.getContact(id);

        if(!validInput(contact.getFirstName()))  {
            return null;
        }
        if (!validInput(contact.getLastName()))  {
            return null;
        }
        return contact;
    }


    public void displayContacts(LinkedList contacts)  {
        ListIterator<Contact> listIterator = contacts.listIterator();
        System.out.println("There is " + contacts.size() + " contacts");
        while (listIterator.hasNext()) {
            Contact c = listIterator.next();
            System.out.println(listIterator.nextIndex() + ": " + c.getFirstName() + " " + c.getLastName() + " id: " + c.getId());
        }
    }

    public void displayContact (Contact contact)   {
        System.out.println("First name: " + contact.getFirstName()+ "\nLast name: " + contact.getLastName());
    }








}
