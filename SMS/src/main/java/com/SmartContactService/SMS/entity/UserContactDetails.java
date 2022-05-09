package com.SmartContactService.SMS.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("user-contact-details")
@Builder
public class UserContactDetails {

    @Id
    private String id;

    private String name;

    @Field(name = "contact-no")
    private String contactNo;

    private String email;

    @Field(name = "all-contacts")
    List<Contacts> allContacts;
}
