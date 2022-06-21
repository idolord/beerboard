package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

public class BiereId implements Serializable {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nom_marque")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Marque marque;

    @Column(name="version")
    private String version;

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public BiereId(){};
    public BiereId(Marque marque, String version) {
        this.marque = marque;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiereId biereId = (BiereId) o;
        return Objects.equals(marque, biereId.marque) && Objects.equals(version, biereId.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marque, version);
    }
}
