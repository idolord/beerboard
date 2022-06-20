package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Marque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarqueRepository extends CrudRepository<Marque,String> {
}
