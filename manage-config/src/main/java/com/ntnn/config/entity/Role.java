package com.ntnn.config.entity;

import io.vertx.core.json.JsonObject;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;


    @OneToMany(mappedBy = "roleId", cascade =  CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Persons> persons;

    public Role(String str) {
        if(str == null || str.equals("")) {
            this.id = 0L;
            this.name = "";
            this.description = "";
            return;
        }
        JsonObject jo = new JsonObject(str);
        this.id = jo.getLong("id", 0L);
        this.name = jo.getString("name", "");
        this.description = jo.getString("description", "");
    }

    public String toString() {
        return new JsonObject()
                .put("id", this.id)
                .put("name", this.name)
                .put("description", this.name).toString();
    }

    public JsonObject toJsonObject() {
        return new JsonObject()
                .put("id", this.id)
                .put("name", this.name)
                .put("description", this.name);
    }
}
