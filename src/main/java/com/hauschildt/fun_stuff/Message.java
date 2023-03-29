package com.hauschildt.fun_stuff;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.io.StringWriter;

public class Message {
    private JsonObject json;

    public Message(JsonObject json) {
        this.json = json;
    }

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        Json.createWriter(stringWriter).write(this.json);
        return stringWriter.toString();
    }
}
