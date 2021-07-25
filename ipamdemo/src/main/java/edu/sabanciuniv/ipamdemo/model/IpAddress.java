package edu.sabanciuniv.ipamdemo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "IPAddress")
public class IpAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IPAddress", nullable = false)
    private String ip;

    @OneToMany(mappedBy = "ipAddress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Port> ports;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "HostName", nullable = false)
    private String hostName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "network_id", nullable = false)
    private Network network;

    @Column(name = "LastModified", nullable = false)
    private Date lastModifDate;

    @Column(name = "OS", nullable = false)
    private String operatingSystem;

    public IpAddress() {
    }

    public IpAddress(String ip, List<Port> ports, String status, String hostName, Network network, Date lastModifDate, String operatingSystem) {
        this.ip = ip;
        this.ports = ports;
        this.status = status;
        this.hostName = hostName;
        this.network = network;
        this.lastModifDate = lastModifDate;
        this.operatingSystem = operatingSystem;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Date getLastModifDate() {
        return lastModifDate;
    }

    public void setLastModifDate(Date lastModifDate) {
        this.lastModifDate = lastModifDate;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }


    @Override
    public String toString() {
        return "IpAddress{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", ports=" + ports +
                ", status='" + status + '\'' +
                ", hostName='" + hostName + '\'' +
                ", network=" + network +
                ", lastModifDate=" + lastModifDate +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
}
