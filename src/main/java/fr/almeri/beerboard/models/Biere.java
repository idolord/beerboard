package fr.almeri.beerboard.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(BiereId.class)
@Table(name="biere")
public class Biere implements Serializable {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nom_marque")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Marque marque;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="version")
    private String version;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="no_type")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Type type;
    @Column(name="couleur_biere")
    private String couleurBiere;
    @Column(name="taux_alcool")
    private Double tauxAlcool;
    @Column(name="caracteristiques")
    private String caracteristiques;
    //private String noTypeStr;

    public Biere() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biere biere = (Biere) o;
        return Double.compare(biere.tauxAlcool, tauxAlcool) == 0 && Objects.equals(marque, biere.marque) && Objects.equals(version, biere.version) && Objects.equals(type, biere.type) && Objects.equals(couleurBiere, biere.couleurBiere) && Objects.equals(caracteristiques, biere.caracteristiques);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marque, version, type, couleurBiere, tauxAlcool, caracteristiques);
    }

    public Marque getMarque() {
        return this.marque;
    }

    public String getVersion() {
        return this.version;
    }

    public Type getType() {
        return this.type;
    }

    public String getCouleurBiere() {
        return this.couleurBiere;
    }

    public Double getTauxAlcool() {
        return this.tauxAlcool;
    }

    public String getCaracteristiques() {
        return this.caracteristiques;
    }

    //public String getNoTypeStr() {
    //    return this.noTypeStr;
    //}

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCouleurBiere(String couleurBiere) {
        this.couleurBiere = couleurBiere;
    }

    public void setTauxAlcool(Double tauxAlcool) {
        this.tauxAlcool = tauxAlcool;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    //public void setNoTypeStr(String noTypeStr) {
    //    this.noTypeStr = noTypeStr;
    //}
}
