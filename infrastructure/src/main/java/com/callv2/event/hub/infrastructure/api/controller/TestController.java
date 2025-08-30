package com.callv2.event.hub.infrastructure.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.callv2.event.hub.infrastructure.event.model.RegisterEventMessage;
import com.callv2.event.hub.infrastructure.event.persistence.EventMongoRepository;
import com.callv2.event.hub.infrastructure.messaging.producer.Producer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TestController {

    private final EventMongoRepository eventMongoRepository;

    private final Producer<RegisterEventMessage> registerEventProducer;

    public TestController(final EventMongoRepository eventMongoRepository,
            final Producer<RegisterEventMessage> registerEventProducer) {
        this.eventMongoRepository = eventMongoRepository;
        this.registerEventProducer = registerEventProducer;
    }

    @PostMapping("/test")
    public void testEndpoint(@RequestBody RegisterEventMessage request) {

        this.registerEventProducer.send(request);

    }

    @GetMapping("path")
    public ResponseEntity<?> getMethodName(@RequestParam String param) {
        return ResponseEntity.ok(eventMongoRepository.findAll());
    }

}
