package pl.jaskot.portalfordrivinginstructor.Backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.User;

@Repository
public interface UsersRepo extends CrudRepository<User, Long> {
}
