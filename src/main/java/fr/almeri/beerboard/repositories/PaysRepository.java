package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Pays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PaysRepository extends CrudRepository<Pays,String> {
    @Query("SELECT p.nomPays FROM Pays p ORDER BY p.nomPays ASC")
    public ArrayList<String> getNomsPaysAsc();
    @Query("SELECT p.consommation FROM Pays p ORDER BY p.nomPays ASC")
    public ArrayList<String> getConsoPaysAsc();
    @Query("SELECT p.production FROM Pays p ORDER BY p.nomPays ASC")
    public ArrayList<String> getProdPaysAsc();
}
