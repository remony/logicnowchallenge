package me.stuartdouglas.app;

import java.util.*;

public class Index extends DbConnection {

    public static void main(String[] args) {
        Index index = new Index();
        index.run();
    }


    //Main menu loop
    private void run() {
        while (true) {
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


    //Creates a new contact and inserts it to the database.
    private void AddNewContact() {
        String firstName = null;
        String lastName = null;

        Scanner input = new Scanner(System.in);
        while (!validInput(firstName)) {
            System.out.print("\nFirst name: ");
            firstName = input.nextLine();
        }

        while (!validInput(lastName)) {
            System.out.print("\nLast name: ");
            lastName = input.nextLine();
        }

        if (validInput(firstName)) {
            createContact(firstName, lastName);
        } else {
            System.out.println("something was empty");
        }
    }

    //This method handles editing existing contacts
    private void editExisting() {
        displayContacts(getAllContacts());
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an ID of a contact");
        int choice = 0;
        int id = Integer.parseInt(input.nextLine());

        if (checkIfContactExists(id)) {

            Contact contact = getContact(id);

            while (choice != 6) {
                String firstname = null;
                String lastname = null;
                displayContact(contact);

                System.out.println("1: Change first name\n2: Edit second name\n3: Save\n4: Delete");
                choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("\nFirst name [" + contact.getFirstName() + "]: ");
                        firstname = input.nextLine();
                        if (validInput(firstname)) {
                            contact.setFirstName(firstname);
                        }
                        break;
                    case 2:
                        System.out.print("\nLast name [" + contact.getLastName() + "]: ");
                        lastname = input.nextLine();
                        if (validInput(lastname)) {
                            contact.setLastName(lastname);
                        }
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
        } else {
            System.out.println("ID " + id + " does not exist");
        }

    }

    //Checks if the contact is valid
    public boolean checkIfContactExists(int id) {
        Contact contact = getContact(id);

        if (!validInput(contact.getFirstName())) {
            return false;
        }
        return validInput(contact.getLastName());
    }

    //Returns true if the value is not empty or contains just spaces
    public boolean validInput(String input) {
        return !(input == null || input.trim().length() == 0);
    }

    //displays a list of given contacys
    private void displayContacts(LinkedList<Contact> contacts) {
        ListIterator<Contact> listIterator = contacts.listIterator();
        System.out.println("There is " + contacts.size() + " contacts");
        while (listIterator.hasNext()) {
            Contact c = listIterator.next();
            System.out.println("ID " + c.getId() + ": " + c.getFirstName() + " " + c.getLastName());
        }
    }

    //Displays a single contact
    private void displayContact(Contact contact) {
        System.out.println("First name: " + contact.getFirstName() + "\nLast name: " + contact.getLastName());
    }
}