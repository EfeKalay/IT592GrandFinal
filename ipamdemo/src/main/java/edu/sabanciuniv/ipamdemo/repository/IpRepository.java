package edu.sabanciuniv.ipamdemo.repository;

import edu.sabanciuniv.ipamdemo.model.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories(repositoryBaseClass = IpRepository.class)
public interface IpRepository extends JpaRepository<IpAddress,Long> {



    List<IpAddress> findByNetwork_Id(Long networkId);

    List<IpAddress> findByNetwork_Division_Id(Long divId);




}
