package edu.sabanciuniv.ipamdemo.dto;

public class NetworkAssignRequest {

    private Long divId;
    private String cidr;
    private String description;

    public NetworkAssignRequest(Long divId, String cidr, String description) {
        this.divId = divId;
        this.cidr = cidr;
        this.description = description;
    }

    public Long getDivId() {
        return divId;
    }

    public void setDivId(Long divId) {
        this.divId = divId;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
