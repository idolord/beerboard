package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.BiereId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BiereRepository extends CrudRepository<Biere, BiereId> {
    @Query("SELECT distinct p.tauxAlcool FROM Biere p group by p.tauxAlcool ORDER BY p.tauxAlcool ASC")
    public ArrayList<String> getThxAlcoolAsc();
    @Query("SELECT COUNT(p.version) FROM Biere p group by p.tauxAlcool ORDER BY p.tauxAlcool ASC")
    public int[] getNbrThxAlcoolAsc();

    @Query("SELECT distinct p.marque.nomMarque FROM Biere p ORDER BY p.marque.nomMarque ASC")
    public ArrayList<String> getmarqueAlcoolAsc();

    @Query("SELECT count(p.version) FROM Biere p group by p.marque.nomMarque ORDER BY p.marque.nomMarque ASC")
    public int[] getNbrVerparmarqueAlcoolAsc();
}
