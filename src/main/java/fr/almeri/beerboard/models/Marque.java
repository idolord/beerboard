package fr.almeri.beerboard.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="marque")
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nomMarque;
    @Column(name="code_brasserie")
    private String brasserie;

    public Marque() {
    }

    public Marque(String pmarque) {
        this.nomMarque = pmarque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marque marque = (Marque) o;
        return Objects.equals(nomMarque, marque.nomMarque) && Objects.equals(brasserie, marque.brasserie);
    }

    public String getNomMarque() {
        return this.nomMarque;
    }

    public String getBrasserie() {
        return this.brasserie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomMarque, brasserie);
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public void setBrasserie(String brasserie) {
        this.brasserie = brasserie;
    }

    @Override
    public String toString() {
        return this.nomMarque + " " + this.brasserie;
    }
}
