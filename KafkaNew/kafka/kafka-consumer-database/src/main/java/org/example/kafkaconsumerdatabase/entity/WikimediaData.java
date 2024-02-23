/*
 * This class represents an entity called WikimediaData, which is mapped to a database table named "test1".
 * It utilizes JPA (Java Persistence API) annotations for object-relational mapping and Lombok annotations
 * for generating getters and setters.
 */

package org.example.kafkaconsumerdatabase.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "test1")
@Getter
@Setter
public class WikimediaData {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key
    private Long id; // Represents the primary key of the WikimediaData entity

    @Column(name = "wiki_event_data", columnDefinition = "TEXT") // Specifies the column mapping for a field
    private String wikiEventData; // Represents the wiki event data stored in the database

}
