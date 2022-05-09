package com.SmartContactService.SMS.service;

import com.SmartContactService.SMS.dto.ContactsDTO;
import com.SmartContactService.SMS.dto.UserContactDetailsDTO;
import com.SmartContactService.SMS.entity.Contacts;
import com.SmartContactService.SMS.entity.UserContactDetails;
import com.SmartContactService.SMS.repository.UserContactDetailRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class UserContactDetailService {

    @Autowired
    private UserContactDetailRepository contactDetailRepository;


    @Autowired
    private MapDTOtoEntity mapDTOtoEntity;

    public UserContactDetails createUser(UserContactDetailsDTO userContactDetailsDTO) {

        try {
            UserContactDetails entity = mapDTOtoEntity.userContactDetailsDTOtoEntity(userContactDetailsDTO);
            log.info("mapped entity saved in db :{}", entity);
            return contactDetailRepository.save(entity);
        } catch (Exception e) {
            log.error("execption occured while saving in db :{}", e.getStackTrace());
        }
        return null;
    }


    public UserContactDetails findUserById(String id) {
        UserContactDetails userContactDetails = contactDetailRepository.findById(id).get();
        return userContactDetails;
    }


    public UserContactDetails getByIdAndEmail(String id, String email) {
        try {
            return contactDetailRepository.findByIdAndEmail(id, email);

        } catch (Exception e) {
            log.error("exception occured in querying id and email");
            e.printStackTrace();
            return null;
        }
    }

    public List<UserContactDetails> getByNameOrEmail(String name, String email) {
        try {
            return contactDetailRepository.findByNameOrEmail(name, email);

        } catch (Exception e) {
            log.error("exception occured in querying name or email");
            e.printStackTrace();
            return null;
        }

    }


//    public List<UserContactDetails> getAllWithPagination(int pageNo, int pageSize)
//    {
//      //  Pageable pageable = new PageRequest.of(pageNo-1 , pageSize);
//        return contactDetailRepository.findAll(pageable).getContent();
//    }

    public List<UserContactDetails> getAllWithSort() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name", "email");
        return contactDetailRepository.findAll(sort);
    }


    public List<UserContactDetails> getByAllContactContactType(String contactType) {
        return contactDetailRepository.findByAllContactsContactType(contactType);
    }


    public List<Contacts> getByIdAndAllContactContactType(String id, String contactType) {
        return contactDetailRepository.findByIdAndAllContactsContactType(id, contactType);
    }


    public List<Contacts> getAllContactsOfId(String id) {
        return contactDetailRepository.findById(id).get().getAllContacts();
    }


    public UserContactDetails findByIdAndUpdateContacts(String id, ContactsDTO contactsDTO) {
        List<ContactsDTO> contactsDTOS = new ArrayList<>();
        contactsDTOS.add(contactsDTO);
        List<Contacts> contacts = mapDTOtoEntity.mapContactToEntity(contactsDTOS);
        UserContactDetails userContactDetails = contactDetailRepository.getById(id);
        List<Contacts> allContactsList = userContactDetails.getAllContacts();
        allContactsList.add(contacts.get(0));
        userContactDetails.setAllContacts(allContactsList);
        return contactDetailRepository.save(userContactDetails);
    }



    public UserContactDetails findByIdAndRemoveContacts(String id, ContactsDTO contactsDTO) {
        List<ContactsDTO> contactsDTOS = new ArrayList<>();
        contactsDTOS.add(contactsDTO);
        List<Contacts> contacts = mapDTOtoEntity.mapContactToEntity(contactsDTOS);
        UserContactDetails userContactDetails = contactDetailRepository.getById(id);
        List<Contacts> allContactsList = userContactDetails.getAllContacts();
        allContactsList.remove(contacts.get(0));
        userContactDetails.setAllContacts(allContactsList);
        return contactDetailRepository.save(userContactDetails);
    }

}
