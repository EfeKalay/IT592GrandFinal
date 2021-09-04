package edu.sabanciuniv.ipamdemo.dto;

public class IpInfoDTO {
    String status;
    String ip;
    String hostName;
    Long id;

    public IpInfoDTO(String status, String ip, String hostName, Long id) {
        this.status = status;
        this.ip = ip;
        this.hostName = hostName;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
