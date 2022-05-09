package com.SmartContactService.SMS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "Model for User Contact Details")
public class UserContactDetailsDTO {

    @ApiModelProperty(name = "name of the user")
    private String name;


    @ApiModelProperty(name = "contact no of user")
    @JsonProperty("contact-no")
    private String contactNo;


    @ApiModelProperty(name = "email id of user")
    private String email;

    @ApiModelProperty(name = "All contacts of user")
    @JsonProperty("all-contacts" )
    List<ContactsDTO> allContacts;
}
