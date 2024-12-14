package com.pijus.easycontacts.service;

import com.pijus.easycontacts.exception.ResourceNotFoundException;
import com.pijus.easycontacts.model.Contact;
import com.pijus.easycontacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact) ;
        
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> getContactById(int id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact updateContact(int id, Contact contact) {
        Contact cont= contactRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Contact not found with this id: "+id));

        cont.setName(contact.getName());
        cont.setPhoto(contact.getPhoto());
        cont.setMobile(contact.getMobile());
        cont.setEmail(contact.getEmail());
        cont.setCompany(contact.getCompany());

        return contactRepository.save(cont);
    }

    @Override
    public Map<String, Boolean> deleteContact(int id) {
        Contact contact=contactRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Contact does not exist with this id: "+id));

        contactRepository.delete(contact);
        Map<String,Boolean> res=new HashMap<>();
        res.put("deleted",true);
        return res;
    }
}
