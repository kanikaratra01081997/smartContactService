package com.SmartContactService.SMS.service;

import com.SmartContactService.SMS.dto.ContactsDTO;
import com.SmartContactService.SMS.dto.UserContactDetailsDTO;
import com.SmartContactService.SMS.entity.Contacts;
import com.SmartContactService.SMS.entity.UserContactDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapDTOtoEntity {

    public UserContactDetails userContactDetailsDTOtoEntity(UserContactDetailsDTO userContactDetailsDTO) {
        return UserContactDetails.builder().name(userContactDetailsDTO.getName())
                .email(userContactDetailsDTO.getEmail())
                .contactNo(userContactDetailsDTO.getContactNo())
                .allContacts(mapContactToEntity(userContactDetailsDTO.getAllContacts()))
                .build();
    }

    public List<Contacts> mapContactToEntity(List<ContactsDTO> contactsDTOS) {
        List<Contacts> contactsList ;
        contactsList = contactsDTOS.stream().map(dto -> Contacts.builder().name(dto.getName())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .phoneNo(dto.getPhoneNo())
                .contactType(dto.getContactType())
                .build()).collect(Collectors.toList());
        return contactsList;
    }


}
