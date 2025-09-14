package com.lld.BookMyShow.models;

import com.lld.BookMyShow.enums.Feature;
import com.lld.BookMyShow.enums.Language;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String name;

    private Date releaseDate;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Language> languages;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
}
