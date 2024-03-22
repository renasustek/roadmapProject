package com.github.group37.roadmap.percistance.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "subjects", schema = "roadmap_project")
public class SubjectsDao {
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)", nullable = false, unique = true, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "subject_name", columnDefinition = "VARCHAR(20)", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String subjectName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSubject() {
        return subjectName;
    }

    public void setSubject(String subject) {
        this.subjectName = subject;
    }
}
