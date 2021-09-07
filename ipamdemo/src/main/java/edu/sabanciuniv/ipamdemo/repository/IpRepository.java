package edu.sabanciuniv.ipamdemo.repository;

import edu.sabanciuniv.ipamdemo.dto.IpInfoDTO;
import edu.sabanciuniv.ipamdemo.model.IpAddress;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories(repositoryBaseClass = IpRepository.class)
public interface IpRepository extends JpaRepository<IpAddress,Long> {


    @Query(value = "select new edu.sabanciuniv.ipamdemo.dto.IpInfoDTO(i.status, i.ip, i.hostName, i.id) " +
            "from IpAddress i ORDER BY i.id asc")
    List<IpInfoDTO> getIps(Pageable pageable);

    @Query(value = "select new edu.sabanciuniv.ipamdemo.dto.IpInfoDTO(i.status, i.ip, i.hostName, i.id) " +
            "from IpAddress i where i.network.id = :netId ORDER BY i.id asc")
    List<IpInfoDTO> getIpsByNetId(@Param("netId") Long networkId, Pageable pageable);

    @Query(value = "select count(*) from IpAddress i where i.status = 'Available'")
    Long getAvailableIpAddressCount();

    @Query(value = "select count(*) from IpAddress i where i.status = 'Unavailable'")
    Long getUnavailableIpAddressCount();

    @Query(value = "select new edu.sabanciuniv.ipamdemo.dto.IpInfoDTO(i.status, i.ip, i.hostName, i.id) " +
            "from IpAddress i where i.id = :ipId")
    IpInfoDTO getIpDetails(@Param("ipId") Long ipId);

    Boolean existsIpAddressByIp(String ip);




}
