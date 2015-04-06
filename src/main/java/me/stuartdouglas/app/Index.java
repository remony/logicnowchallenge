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
        Contact contactz = new Contact();
        String input = null;
        while(true) {

            displayList(getAllContacts());
            System.out.println("Please select a contact");
            displayList(getContact(getMenuInput()));




            System.out.println("1: Change first name\n2: Edit second name\n3: Save\n4: Delete");


            int choice = getMenuInput();

            switch (choice)  {
                case 1:

                    break;
                case 2:
                    //System.out.println(contact.getFirstName());
                    break;
                case 3:
                    //input = getFirstName();
                    //contact.setLastName(input);
                    break;
                case 4:
                    //System.out.println(contact.getLastName());
                    break;
                default:
                    break;
            }

            if (input.equals("0"))  {
                return;
            }

        }

    }

    private static LinkedList getAllContacts()    {
        return DbConnection.getAllContacts();
    }

    private static LinkedList getContact(int id)   {
        return DbConnection.getContact(id);
    }

    private static void displayList(LinkedList contacts)  {
        ListIterator<Contact> listIterator = contacts.listIterator();
        System.out.println("There is " + contacts.size() + " contacts");
        while (listIterator.hasNext()) {
            Contact c = listIterator.next();
            System.out.println(listIterator.nextIndex() + ": " + c.getFirstName() + " " +  c.getLastName() + " id: " + c.getId());
        }
    }



    private static String getStringInput()  {
        Scanner scanIn = new Scanner(System.in);
        return scanIn.nextLine().toString();
    }

    private static Integer getMenuInput()   {
        Scanner scanIn = new Scanner(System.in);
        return Integer.parseInt(scanIn.nextLine());
    }


}
