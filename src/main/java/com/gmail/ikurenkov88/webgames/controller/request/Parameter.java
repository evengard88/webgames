package com.gmail.ikurenkov88.webgames.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Created by ilia on 27.02.16.
 */
public class Parameter {
    @JsonProperty("client_id")
    long clientId;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
