package pl.jaskot.portalfordrivinginstructor.Backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jaskot.portalfordrivinginstructor.Backend.entity.Article;

@Repository
public interface ArticlesRepo extends CrudRepository<Article, Long> {
}
