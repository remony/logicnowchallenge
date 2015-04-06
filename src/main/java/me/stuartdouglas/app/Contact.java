/**
 * Created by remon on 03/04/2015.
 */
package me.stuartdouglas.app;

import java.util.ArrayList;

public class Contact {
    private String firstName = null;
    private String lastName = null;
    private int id;
    public Contact(){}

    public String getFirstName()  {
        return this.firstName;
    }

    public void setFirstName(String firstName)    {
        this.firstName = firstName;
    }

    public String getLastName()  {
        return this.lastName;
    }

    public void setLastName(String lastName)    {
        this.lastName = lastName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void save()  {
        DbConnection.saveContact(this.id, this.firstName, this.lastName);
    }

    public void delete()    {
        DbConnection.deleteContact(this.id);
    }
}
