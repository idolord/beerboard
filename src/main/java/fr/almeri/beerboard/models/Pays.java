package fr.almeri.beerboard.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="pays")
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nomPays;
    @Column(name="consommation")
    private double consommation;
    @Column(name="production")
    private double production;

    public Pays() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pays pays = (Pays) o;
        return Objects.equals(nomPays, pays.nomPays) && Objects.equals(consommation, pays.consommation);
    }

    public String getNomPays() {
        return this.nomPays;
    }

    public double getConsommation() {
        return this.consommation;
    }

    public double getProduction() {
        return this.production;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomPays, consommation);
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public void setProduction(double production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return this.nomPays + " " + this.consommation;
    }
}
