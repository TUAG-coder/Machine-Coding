package com.lld.BookMyShow.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;
}

/*
We don't want to create table for BaseModel class.
But we want all the attributes of the BaseModel class to be present in the tables for the Model classes which extend BaseModel
For that we'll use @MappedSuperClass annotation
 */
