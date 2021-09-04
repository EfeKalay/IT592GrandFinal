package edu.sabanciuniv.ipamdemo.repository;

import edu.sabanciuniv.ipamdemo.dto.NetworkDataResponseDTO;
import edu.sabanciuniv.ipamdemo.model.IpAddress;
import edu.sabanciuniv.ipamdemo.model.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories(repositoryBaseClass = NetworkRepository.class)
public interface NetworkRepository extends JpaRepository<Network, Long> {

    Network getById(Long id);

    Network findByName(String name);

    List<Network> findAll();

    @Query(value = "select new edu.sabanciuniv.ipamdemo.dto.NetworkDataResponseDTO( n.id,n.name,n.description,n.division.id,d.name) " +
            "from Network n join Division d on n.division.id = d.id")
    List<NetworkDataResponseDTO> getAllNetworks();

    @Override
    <S extends Network> List<S> saveAll(Iterable<S> iterable);

    @Query(value = "select new edu.sabanciuniv.ipamdemo.dto.NetworkDataResponseDTO( n.id,n.name,n.description,n.division.id,d.name) " +
            "from Network n join Division d on n.division.id = d.id " +
            "where n.division.id = :divId")
    List<NetworkDataResponseDTO> getAllNetworksByDiv(@Param("divId") Long divId);
}
