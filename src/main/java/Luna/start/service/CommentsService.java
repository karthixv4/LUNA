package Luna.start.service;

import Luna.start.model.Comments;
import Luna.start.repository.CommentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepo commentsRepo;

    public void saveComment(Comments comment){
        commentsRepo.save(comment);
    }

    public Optional<Comments> getComments(String id){
       return commentsRepo.findById(id);
    }

    public void deleteComment(String id){
        commentsRepo.deleteById(id);
    }


}
