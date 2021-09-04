package edu.sabanciuniv.ipamdemo.dto;

public class NewNetworkRequest {

    private String name;
    private String description;
    private String address;
    private String mask;
    private String cidr;
    private Long divId;

    public NewNetworkRequest() {
    }

    public NewNetworkRequest(String name, String description, String address, String mask, String cidr, Long divId) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.mask = mask;
        this.cidr = cidr;
        this.divId = divId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public Long getDivId() {
        return divId;
    }

    public void setDivId(Long divId) {
        this.divId = divId;
    }
}
