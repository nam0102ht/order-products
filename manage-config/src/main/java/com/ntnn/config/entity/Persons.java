package com.ntnn.config.entity;

import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lastname;
    private String firstname;
    private String numberPhone;
    private String email;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
    private boolean isDelete;

    public Persons(String strPerson) {
        if(strPerson == null || strPerson.equals("")) {
            this.id = 0;
            this.lastname = "";
            this.firstname = "";
            this.numberPhone = "";
            this.role = new Role();
            this.isDelete = false;
            return;
        }
        JsonObject jo = new JsonObject(strPerson);
        this.id = jo.getInteger("id");
        this.lastname = jo.getString("lastename");
        this.firstname = jo.getString("firstname");
        this.numberPhone = jo.getString("numberPhone");
        this.role.setId(jo.getInteger("roleId"));
        this.isDelete = jo.getBoolean("isDelete");
    }

    public JsonObject toJsonObject() {
        return new JsonObject()
                .put("id", this.id)
                .put("lastname", this.lastname)
                .put("firstname", this.firstname)
                .put("numberPhone", this.numberPhone)
                .put("email", this.email)
                .put("roleId", this.role.getId())
                .put("isDelete", this.isDelete);
    }

    public String toString() {
        return new JsonObject()
                .put("id", this.id)
                .put("lastname", this.lastname)
                .put("firstname", this.firstname)
                .put("numberPhone", this.numberPhone)
                .put("email", this.email)
                .put("roleId", this.role.getId())
                .put("isDelete", this.isDelete).toString();
    }
}
