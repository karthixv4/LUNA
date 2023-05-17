package Luna.start.controller;

import Luna.start.model.Comments;
import Luna.start.service.CommentsService;
import Luna.start.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/addComment")
    public Comments saveComment(@RequestBody Comments comments) {
        System.out.println("COMMENT:"+comments);
        Comments savedComment = commentsService.saveComment(comments);
        recipeService.addComments(comments);
        return savedComment;
    }

    @DeleteMapping("/deleteComment")
    public void removeComment(@RequestBody Comments comments){
        commentsService.deleteComment(comments.getId());
        recipeService.removeComment(comments);
    }

}
