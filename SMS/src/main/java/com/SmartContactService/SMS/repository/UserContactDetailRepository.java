package com.SmartContactService.SMS.repository;

import com.SmartContactService.SMS.entity.Contacts;
import com.SmartContactService.SMS.entity.UserContactDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserContactDetailRepository extends MongoRepository<UserContactDetails, String> {

    // not returning right values
    @Query("{$and :[ {id:?0}, {contact-no: ?1}] }")
    List<UserContactDetails> getContactsByIdAndContactType(String id, String contactType);

    // returning right values
    @Query("{ id:?0}")
    UserContactDetails getById(String id);

    Optional<UserContactDetails> findById(String id);

    UserContactDetails findByIdAndEmail(String id, String email);

    // return type should be list when not sure whether the result is unique or not.
    List<UserContactDetails> findByNameOrEmail(String name, String email);

    List<UserContactDetails> findByAllContactsContactType(String contactType);

    List<Contacts> findByIdAndAllContactsContactType(String id, String contactType);

}
