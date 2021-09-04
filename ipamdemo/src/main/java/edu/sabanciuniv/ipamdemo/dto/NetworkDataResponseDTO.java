package edu.sabanciuniv.ipamdemo.dto;

public class NetworkDataResponseDTO {

    private Long id;
    private String ip;
    private  String description;
    private Long divisionId;
    private String divisionName;

    public NetworkDataResponseDTO(Long id, String ip, String description, Long divisionId, String divisionName) {
        this.id = id;
        this.ip = ip;
        this.description = description;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Long divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
}
