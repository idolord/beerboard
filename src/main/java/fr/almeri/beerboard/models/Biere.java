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
    @Transient
    private String noTypeStr;

    public Biere() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biere biere = (Biere) o;
        return Objects.equals(getMarque(), biere.getMarque()) && Objects.equals(getVersion(), biere.getVersion()) && Objects.equals(getType(), biere.getType()) && Objects.equals(getCouleurBiere(), biere.getCouleurBiere()) && Objects.equals(getTauxAlcool(), biere.getTauxAlcool()) && Objects.equals(getCaracteristiques(), biere.getCaracteristiques()) && Objects.equals(getNoTypeStr(), biere.getNoTypeStr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarque(), getVersion(), getType(), getCouleurBiere(), getTauxAlcool(), getCaracteristiques(), getNoTypeStr());
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

    public String getNoTypeStr() {
        this.type = new Type();
        return this.type.getNoTypeStr();
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setType(String type) {
        Type temp = new Type();
        temp.setNoType(Integer.parseInt(type));
        this.type = temp;
    }
    public void setType(int type) {
        Type temp = new Type();
        temp.setNoType(type);
        this.type = temp;
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

    public void setNoTypeStr(String noTypeStr) {
        Type type = new Type();
        type.setNoTypeStr(noTypeStr);
        this.type = type;
    }
}
