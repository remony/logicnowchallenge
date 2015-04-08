import junit.framework.TestCase;
import me.stuartdouglas.app.Contact;
import me.stuartdouglas.app.DbConnection;
import me.stuartdouglas.app.Index;

import java.util.ListIterator;

public class IndexTest extends TestCase {

    //if the first name or last name are empty the test should not pass
    public void testValidInput() throws Exception {
        Index index = new Index();

        assertTrue(index.validInput("John"));

    }

    public void testInvalidInput() throws Exception {
        Index index = new Index();
        assertFalse(index.validInput(""));
    }

    public void testInvalidInput2() throws Exception {
        Index index = new Index();
        assertFalse(index.validInput("     "));
    }


    //Checks that the database returns contacts
    public void testGetContacts() {
        assertTrue((DbConnection.getAllContacts().size() > 0));
    }

    //checks that the contact contains valid information
    public void testGetContact() {
        assertTrue((DbConnection.getContact(3).getFirstName() != null && DbConnection.getContact(3).getLastName() != null));
    }


    //checks if all the contacts in the database have true values
    public void testGetContactsValidInfo() {
        ListIterator<Contact> listIterator = DbConnection.getAllContacts().listIterator();
        while (listIterator.hasNext()) {
            Contact c = listIterator.next();
            assertTrue((c.getFirstName() != null && c.getLastName() != null));
        }
    }

    public void testDatabaseConnection() {
        assertTrue(DbConnection.getDBConnection() != null);
    }

    public void testDatabaseAddContact() {
        assertTrue(DbConnection.createContact("John", "Doe"));
    }


}