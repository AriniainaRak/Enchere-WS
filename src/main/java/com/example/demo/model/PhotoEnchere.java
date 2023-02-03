package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PhotoEnchere")
public class PhotoEnchere {
    
    @Id
    private String id;
    private int idEnchere;
    private String name;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getIdEnchere() {
        return idEnchere;
    }
    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
