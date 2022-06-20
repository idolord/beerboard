package fr.almeri.beerboard.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="type")
public class Type {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="no_type")
    private Integer noType;
    @Column(name="nom_type")
    private String nomType;
    @Column(name="description")
    private String description;
    @Column(name="fermentation")
    private String fermentation;
    @Column(name="commentaire")
    private String commentaire;

    public Type() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(noType, type.noType) && Objects.equals(nomType, type.nomType) && Objects.equals(description, type.description) && Objects.equals(fermentation, type.fermentation) && Objects.equals(commentaire, type.commentaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noType, nomType, description, fermentation, commentaire);
    }

    @Override
    public String toString() {
        return this.noType + " " + this.nomType + " " + this.description + " " + this.fermentation + " " + this.commentaire;
    }

    public Integer getNoType() {
        return this.noType;
    }

    public void setNoType(Integer noType) {
        this.noType = noType;
    }

    public String getNoTypeStr() {
        return this.noType.toString();
    }

    public void setNoTypeStr(String noType) {
        this.noType = Integer.parseInt(noType);
    }

    public String getNomType() {
        return this.nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFermentation() {
        return this.fermentation;
    }

    public void setFermentation(String fermentation) {
        this.fermentation = fermentation;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
