package edu.sabanciuniv.ipamdemo.repository;

import edu.sabanciuniv.ipamdemo.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories(repositoryBaseClass = DivisionRepository.class)
public interface DivisionRepository extends JpaRepository<Division,Long> {




}
