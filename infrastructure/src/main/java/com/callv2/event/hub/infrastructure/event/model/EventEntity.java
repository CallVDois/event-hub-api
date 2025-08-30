package com.callv2.event.hub.infrastructure.event.model;

import java.io.Serializable;

public record EventEntity(String type, String id) implements Serializable {

}
