package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="marque")
public class Marque {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nom_marque")
    private String nomMarque;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="code_brasserie")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Brasserie brasserie;

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

    public Brasserie getBrasserie() {
        return this.brasserie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomMarque, brasserie);
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public void setBrasserie(Brasserie brasserie) {
        this.brasserie = brasserie;
    }

    @Override
    public String toString() {
        return this.nomMarque + " " + this.brasserie;
    }
}
