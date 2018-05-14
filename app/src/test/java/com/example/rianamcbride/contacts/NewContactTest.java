package com.example.rianamcbride.contacts;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NewContactTest {
    @Test
    public void enoughFieldsFilled(){
        Contact contact = new Contact(null, "Riana", "McBride", null, null, null);
        assertThat("Last name and first name are filled in", contact.isValidContact(),is(true));
        contact.setValues(null, null, "McBride", null, null, null);
        assertThat("Last name was filled in", contact.isValidContact(), is(true));
    }
    @Test
    public void notEnoughFieldsFilled(){
        Contact contact = new Contact(null,null,null,null,null,null);
        assertThat("No data filled in", contact.isValidContact(), is(false));
        contact.setValues(null, null, null, "123 Front St.", null, "riana.mcbride@scenedoc.com");
        assertThat("First name, last name, and phone number are not completed", contact.isValidContact(), is(false));
    }
    @Test
    public void testPhoneLength(){
        Contact contact = new Contact(null, "test", "test", null, "555", null);
        assertThat("Not enough numbers", contact.isValidPhone(), is(false));
    }
    @Test
    public void testPhoneValid(){
        Contact contact = new Contact(null, "test", "test", null, "abcdefghijk", null);
        assertThat("There are letters included in the phone number", contact.isValidPhone(), is(false));
    }
    @Test
    public void acceptsCorrectPhone(){
        Contact contact = new Contact(null, "test", "test", null, "5555555555", null);
        assertThat("Not enough numbers", contact.isValidPhone(), is(true));
    }
    @Test
    public void testGetPhone(){
        Contact contact = new Contact(null, "test", "test", null, "5555555555", null);
        assertTrue(contact.getPhone().equals("5555555555"));
    }
    @Test
    public void testGetfName(){
        Contact contact = new Contact(null, "testFirst", "testLast", null, "5555555555", null);
        assertTrue(contact.getfName().equals("testFirst"));
    }
    @Test
    public void testGetlName(){
        Contact contact = new Contact(null, "testFirst", "testLast", null, "5555555555", null);
        assertTrue(contact.getlName().equals("testLast"));
    }
    @Test
    public void testGetAddress(){
        Contact contact = new Contact(null, "test", "test", "1 Front St", "5555555555", null);
        assertTrue(contact.getAddress().equals("1 Front St"));
    }
    @Test
    public void testGetEmail(){
        Contact contact = new Contact(null, "test", "test", null, "5555555555", "riana.mcbride@scenedoc.com");
        assertTrue(contact.getEmail().equals("riana.mcbride@scenedoc.com"));
    }
    @Test
    public void testSetValues(){
        Contact contact = new Contact(null, "test", "test", null, "5555555555", null);
        contact.setValues(null, "John", "Doe", "123 Main St", "1234567890", "jdoe@gmail.com");
        assertTrue(contact.getAddress().equals("123 Main St"));
        assertTrue(contact.getEmail().equals("jdoe@gmail.com"));
        assertTrue(contact.getfName().equals("John"));
        assertTrue(contact.getlName().equals("Doe"));
        assertTrue(contact.getPhone().equals("1234567890"));
    }
    @Test
    public void addFromAppValid(){
        DisplayMessageActivity dma = new DisplayMessageActivity();
        assertThat(dma.isValidContact("John", "Doe", "9055558765", "jdoe@gmail.com"), is(true));
    }
    @Test
    public void addFromAppInvalid(){
        DisplayMessageActivity dma = new DisplayMessageActivity();
        assertThat(dma.isValidContact("", "", "", ""), is(false));
    }
    @Test
    public void addValidPhone(){
        DisplayMessageActivity dma = new DisplayMessageActivity();
        assertThat(dma.isValidPhone("9055558765"), is(true));
    }
    @Test
    public void addInvalidPhone(){
        DisplayMessageActivity dma = new DisplayMessageActivity();
        assertThat(dma.isValidPhone("abc"), is(false));
    }
    @Test
    public void editFromAppValid(){
        DisplayMessageActivity dma = new DisplayMessageActivity();
        assertThat(dma.isValidContact("John", "Doe", "9055558765", "jdoe@gmail.com"), is(true));
    }
    @Test
    public void editFromAppInvalid(){
        DisplayMessageActivity dma = new DisplayMessageActivity();
        assertThat(dma.isValidContact("", "", "", ""), is(false));
    }
    @Test
    public void editValidPhone(){
        DisplayMessageActivity dma = new DisplayMessageActivity();
        assertThat(dma.isValidPhone("9055558765"), is(true));
    }
    @Test
    public void editInvalidPhone(){
        DisplayMessageActivity dma = new DisplayMessageActivity();
        assertThat(dma.isValidPhone("abc"), is(false));
    }
    @Test
    public void correctNumberIfZero(){
        List<Contact> contactList = new ArrayList<>();
        MyAdapter myAdapter = new MyAdapter(contactList);
        assertThat(myAdapter.getItemCount(), is(0));
    }
    @Test
    public void correctNumberIfGreaterThanZero(){
        List<Contact> contactList = new ArrayList<>();
        MyAdapter myAdapter = new MyAdapter(contactList);
        contactList.add(new Contact(null, "John", "Doe", null, null, null));
        contactList.add(new Contact(null, "Anna", "Smith", null, null, null));
        assertThat(myAdapter.getItemCount(), is(2));
    }
}
