package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="brasserie")
public class Brasserie {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="code_brasserie")
    private String codeBrasserie;
    @Column(name="nom_brasserie")
    private String nomBrasserie;
    @Column(name="ville")
    private String ville;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    public Brasserie() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brasserie brasserie = (Brasserie) o;
        return Objects.equals(codeBrasserie, brasserie.codeBrasserie) && Objects.equals(nomBrasserie, brasserie.nomBrasserie) && Objects.equals(ville, brasserie.ville) && Objects.equals(region, brasserie.region);
    }

    public String getCodeBrasserie() {
        return this.codeBrasserie;
    }

    public String getNomBrasserie() {
        return this.nomBrasserie;
    }

    public Region getRegion() {
        return this.region;
    }

    public String getVille() {
        return this.ville;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeBrasserie, nomBrasserie, ville, region);
    }

    public void setCodeBrasserie(String codeBrasserie) {
        this.codeBrasserie = codeBrasserie;
    }

    public void setNomBrasserie(String nomBrasserie) {
        this.nomBrasserie = nomBrasserie;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return this.codeBrasserie + " " + this.nomBrasserie + " " + this.ville + " " + this.region;
    }
}
