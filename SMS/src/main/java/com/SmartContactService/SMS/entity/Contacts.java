package com.SmartContactService.SMS.entity;

import com.SmartContactService.SMS.constants.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contacts {


   private String name;
   private String lastname;
   private String email;

   @Field(name= "phone-no")
   private String phoneNo;

   @Field(name = "contact-type")
   private ContactType contactType;
}
