package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Brasserie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BrasserieRepository extends CrudRepository<Brasserie,String> {
    @Query("SELECT Distinct p.region.nomRegion FROM Brasserie p ORDER BY p.region.nomRegion ASC")
    public ArrayList<String> getRegionFromBrasserieAsc();

    @Query("SELECT COUNT(p.codeBrasserie) FROM Brasserie p group by p.region.nomRegion ORDER BY p.region.nomRegion ASC")
    public int[] getRegionNumberFromBrasserieAsc();

    @Query("SELECT Distinct p.nomBrasserie FROM Brasserie p ORDER BY p.nomBrasserie ASC")
    public ArrayList<String> getBrasserieAsc();
}
