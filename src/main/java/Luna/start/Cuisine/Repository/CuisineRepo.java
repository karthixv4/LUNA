package Luna.start.Cuisine.Repository;

import Luna.start.Cuisine.Model.Cuisine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepo extends MongoRepository<Cuisine,String> {
}
