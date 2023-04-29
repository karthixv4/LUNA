package Luna.start.Recipe.Repository;

import Luna.start.Recipe.Model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepo extends MongoRepository<Recipe,String> {

}
