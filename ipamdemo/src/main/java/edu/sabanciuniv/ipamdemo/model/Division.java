package edu.sabanciuniv.ipamdemo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Division")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Div_name")
    private String name;

    @Column(name = "Description")
    private  String description;

    @OneToMany(mappedBy = "division", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Network> networks;

    public Division() {
    }

    public Division(String name, String description, List<Network> networks) {
        this.name = name;
        this.description = description;
        this.networks = networks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", networks=" + networks +
                '}';
    }
}
