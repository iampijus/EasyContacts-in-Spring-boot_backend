package com.pijus.easycontacts.controller;

import com.pijus.easycontacts.model.Contact;
import com.pijus.easycontacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/easy-contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // add new contact
    @PostMapping("/contacts")
    public Contact addContact(@RequestBody Contact contact){
        return contactService.addContact(contact);
    }

    // get all contacts
    @GetMapping("/contacts")
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts();
    }

    // get contact by id
    @GetMapping("/contacts/{id}")
    public Optional<Contact> getContactById(@PathVariable("id") int id){
        return contactService.getContactById(id);
    }

    // update contact
    @PutMapping("/contacts/{id}")
    public Contact updateContact(@PathVariable ("id") int id, @RequestBody Contact contact){
        return contactService.updateContact(id,contact);
    }

    // delete contact
    @DeleteMapping("/contacts/{id}")
    public Map<String,Boolean> deleteContact(@PathVariable("id") int id){
        return contactService.deleteContact(id);
    }

}
