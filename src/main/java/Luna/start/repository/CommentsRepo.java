package Luna.start.repository;

import Luna.start.model.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepo extends MongoRepository<Comments,String> {
}
