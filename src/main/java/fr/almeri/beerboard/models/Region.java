package fr.almeri.beerboard.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nomRegion;
    @Column(name="nom_pays")
    private String nomPays;

    public Region() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(nomRegion, region.nomRegion) && Objects.equals(nomPays, region.nomPays);
    }

    public String getNomRegion() {
        return this.nomRegion;
    }

    public String getNomPays() {
        return this.nomPays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomRegion, nomPays);
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    @Override
    public String toString() {
        return this.nomRegion + " " + this.nomPays;
    }
}
