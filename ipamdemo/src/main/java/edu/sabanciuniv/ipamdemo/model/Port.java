package edu.sabanciuniv.ipamdemo.model;

import javax.persistence.*;

@Entity
@Table(name = "Port")
public class Port {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="ipAddress_id", referencedColumnName = "id", nullable = false)
    private IpAddress ipAddress;

    @Column(name = "PortNumber", nullable = false)
    private int portNumber;

    @Column(name = "Service", nullable = true)
    private String service;

    @Column(name = "Status", nullable = false)
    private boolean status;

    public Port() {
    }

    public Port(int portNumber, String service, boolean status) {
        this.portNumber = portNumber;
        this.service = service;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Port{" +
                "id=" + id +
                ", portNumber=" + portNumber +
                ", service='" + service + '\'' +
                ", status=" + status +
                '}';
    }
}
