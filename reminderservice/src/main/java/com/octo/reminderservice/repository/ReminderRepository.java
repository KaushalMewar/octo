package com.octo.reminderservice.repository;

import com.octo.reminderservice.entity.Reminders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "reminders", path = "reminder")
public interface ReminderRepository extends MongoRepository<Reminders, UUID> {


}
