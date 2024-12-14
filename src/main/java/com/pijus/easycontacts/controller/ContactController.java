package com.pijus.easycontacts.controller;

import com.pijus.easycontacts.model.Contact;
import com.pijus.easycontacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
        try{
            Contact newContact=contactService.addContact(contact);
            return ResponseEntity.ok(newContact);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get all contacts
    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact> list=contactService.getAllContacts();
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

    // get contact by id
    @GetMapping("/contacts/{id}")
    public ResponseEntity<Optional<Contact>> getContactById(@PathVariable("id") int id){
        Optional<Contact> contact=contactService.getContactById(id);
        if(contact.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(contact);
    }

    // update contact
    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable ("id") int id, @RequestBody Contact contact){
         try{
             Contact updatedContact=contactService.updateContact(id,contact);
             return ResponseEntity.ok(updatedContact);
         }catch(Exception e) {
             e.printStackTrace();
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
         }
    }

    // delete contact
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteContact(@PathVariable("id") int id){
        try{
            Map<String,Boolean> res= contactService.deleteContact(id);
            return ResponseEntity.ok(res);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
