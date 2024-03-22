package com.github.group37.roadmap.percistance;

import com.github.group37.roadmap.percistance.models.SubjectsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubjectsRepo  extends JpaRepository<SubjectsDao, UUID> {

}
