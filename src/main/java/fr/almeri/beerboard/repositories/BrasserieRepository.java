package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Brasserie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrasserieRepository extends CrudRepository<Brasserie,String> {
}
