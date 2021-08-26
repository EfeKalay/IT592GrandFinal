package edu.sabanciuniv.ipamdemo.repository;

import edu.sabanciuniv.ipamdemo.model.IpAddress;
import edu.sabanciuniv.ipamdemo.model.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories(repositoryBaseClass = NetworkRepository.class)
public interface NetworkRepository extends JpaRepository<Network, Long> {

    Network getById(Long id);

    Network findByName(String name);

    List<Network> findAll();


    @Override
    <S extends Network> List<S> saveAll(Iterable<S> iterable);



}
