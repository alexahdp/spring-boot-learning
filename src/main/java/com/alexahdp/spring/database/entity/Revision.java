package com.alexahdp.spring.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RevisionEntity
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionTimestamp
    private Integer id;

    @RevisionTimestamp
    private Long timestamp;
}
