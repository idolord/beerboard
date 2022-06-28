package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    @Query("SELECT user from Users user WHERE user.login = :login AND user.password = :password")
    public Users getUser(String login, String password);

    @Query("SELECT user from Users user WHERE user.login = :login")
    public Users findByLogin(String login);
}
