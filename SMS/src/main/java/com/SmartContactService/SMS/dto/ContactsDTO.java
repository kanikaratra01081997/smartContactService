package com.SmartContactService.SMS.dto;

import com.SmartContactService.SMS.constants.ContactType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Model for Contact details")
public class ContactsDTO {


    private String name;
    private String lastname;
    private String email;
    @JsonProperty("phone-no")
    private String phoneNo;
    @JsonProperty("contact-type")
    private ContactType contactType;
}
