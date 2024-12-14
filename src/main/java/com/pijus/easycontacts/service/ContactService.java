package com.pijus.easycontacts.service;

import com.pijus.easycontacts.model.Contact;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ContactService {
    public Contact addContact(Contact contact);
    public List<Contact> getAllContacts();
    public Optional<Contact> getContactById(int id);
    public Contact updateContact(int id, Contact contact);
    public Map<String,Boolean> deleteContact(int id);
}
