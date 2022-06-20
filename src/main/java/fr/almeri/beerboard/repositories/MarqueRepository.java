package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Marque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarqueRepository extends CrudRepository<Marque,String> {
    @Query("SELECT COUNT(m.nomMarque) FROM Marque m GROUP BY m.brasserie.codeBrasserie ORDER BY m.brasserie.nomBrasserie ASC")
    public int[] getMarquesBrasserieAsc();
}
