package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Biere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiereRepository extends CrudRepository<Biere,String> {
}
