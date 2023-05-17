package Luna.start.repository;

import Luna.start.model.Cuisine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepo extends MongoRepository<Cuisine,String> {
}
