package com.SmartContactService.SMS.controller;

import com.SmartContactService.SMS.dto.ContactsDTO;
import com.SmartContactService.SMS.dto.UserContactDetailsDTO;
import com.SmartContactService.SMS.model.ResponseHandler;
import com.SmartContactService.SMS.service.UserContactDetailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-contact-details")
@Log4j2
public class SmartContactServiceController {


    @Autowired
    private UserContactDetailService userContactDetailService;


    @GetMapping("/helloworld")
    public String helloworld() {
        return "Hello World";
    }

    @PostMapping("/create-new-user")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "CREATE NEW USER IN THE COLLECTION ",
            notes = "")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "successful"),
                    @ApiResponse(code = 400, message = "Missed required parameters, parameters are not valid"),
                    @ApiResponse(code = 401, message = "unauthorized"),
                    @ApiResponse(code = 403, message = "Forbidden"),
                    @ApiResponse(code = 500, message = "Internal server error")
            })
    public ResponseEntity<Object> createNewUser(@RequestBody UserContactDetailsDTO userContactDetailsDTO) {
        log.info("create user called in user contact detail sevice");
        log.info("user details got :{}", userContactDetailsDTO);
        Object responseObject = userContactDetailService.createUser(userContactDetailsDTO);
        return ResponseHandler.generateResponse("Successfully added data ", HttpStatus.ACCEPTED, responseObject);
    }


    @GetMapping("/find-user/{id}")
    public ResponseEntity<Object> findUser(@PathVariable String id) {
        log.info("find user called");
        Object responseObject = userContactDetailService.findUserById(id);
        return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseObject);

    }

    @GetMapping("/list-user/id/{id}/contact-type/{contact-type}")
    public ResponseEntity<Object> listUserContactType(@PathVariable String id, @PathVariable("contact-type") String contactType) {
        log.info("contact type in list User Contact :{}", contactType);
        Object responseObject = userContactDetailService.getByIdAndAllContactContactType(id, contactType);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, responseObject);
    }

    @GetMapping("/list-user/name/{name}/or/email/{email}")
    public ResponseEntity<Object> listOfNameOrEmail(@PathVariable String name, @PathVariable String email) {
        log.info("name or email " + name + " " + email);
        Object responseObject = userContactDetailService.getByNameOrEmail(name, email);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, responseObject);
    }

    @GetMapping("/list-user/id/{id}/and/email/{email}")
    public ResponseEntity<Object> listOfIdAndEmail(@PathVariable String id, @PathVariable String email) {
        log.info("name or email " + id + " " + email);
        Object responseObject = userContactDetailService.getByIdAndEmail(id, email);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, responseObject);
    }

    @GetMapping("/list-user/name/and/email")
    public ResponseEntity<Object> listOfSortedNameAndEmail() {

        Object responseObject = userContactDetailService.getAllWithSort();
        return ResponseHandler.generateResponse("success", HttpStatus.OK, responseObject);
    }


    @GetMapping("/list-user/contact/and/contact-type/{contact-type}")
    public ResponseEntity<Object> listOfConatctAndContactType(@PathVariable("contact-type") String contactType) {
        Object responseObject = userContactDetailService.getByAllContactContactType(contactType);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, responseObject);
    }


    @GetMapping("/list-user/{id}/all-contacts")
    public ResponseEntity<Object> listAllTheUserContacts(@PathVariable String id) {

        Object responseObject = userContactDetailService.getAllContactsOfId(id);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, responseObject);
    }


    @PutMapping("/add-contact/{id}")
    public ResponseEntity<Object> updateAddTheContactList(@PathVariable String id, @RequestBody ContactsDTO contactsDTO) {
        Object responseObject = userContactDetailService.findByIdAndUpdateContacts(id, contactsDTO);
        return ResponseHandler.generateResponse("success", HttpStatus.ACCEPTED, responseObject);
    }


    @PutMapping("/remove-contact/{id}")
    public ResponseEntity<Object> updateRemoveTheContactList(@PathVariable String id, @RequestBody ContactsDTO contactsDTO) {
        Object responseObject = userContactDetailService.findByIdAndRemoveContacts(id, contactsDTO);
        return ResponseHandler.generateResponse("success", HttpStatus.ACCEPTED, responseObject);
    }

    @PutMapping("/update-existing-contact/{id}")
    public ResponseEntity<Object> updateExistingContact(@PathVariable String id, @RequestBody ContactsDTO contactsDTO)
    {Object responseObject = userContactDetailService.findByIdAndRemoveContacts(id, contactsDTO);
        return ResponseHandler.generateResponse("success", HttpStatus.ACCEPTED, responseObject);

    }


}
