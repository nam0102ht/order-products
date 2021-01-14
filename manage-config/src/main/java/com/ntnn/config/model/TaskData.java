package com.ntnn.config.model;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskData {
    private boolean result;
    private int resultCode;
    private String message;
    private JsonObject data;

    public TaskData(String string) {
        if(string == null || string.equals("")) {
            this.result = false;
            this.resultCode = 500;
            this.message = "";
            this.data = new JsonObject();
            return;
        }
        JsonObject jo = new JsonObject(string);
        this.result = jo.getBoolean("result");
        this.resultCode = jo.getInteger("resultCode");
        this.message = jo.getString("message");
        this.data = jo.getJsonObject("data", new JsonObject());
    }

    public String toString() {
        return new JsonObject()
                .put("result", this.result)
                .put("resultCode", this.resultCode)
                .put("message", this.message)
                .put("data", this.data).toString();
    }

    public JsonObject toJsonObject() {
        return new JsonObject()
                .put("result", this.result)
                .put("resultCode", this.resultCode)
                .put("message", this.message)
                .put("data", this.data);
    }
}
