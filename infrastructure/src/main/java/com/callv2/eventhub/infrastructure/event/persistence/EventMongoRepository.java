package com.callv2.eventhub.infrastructure.event.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventMongoRepository extends MongoRepository<EventMongoEntity, String> {

}
