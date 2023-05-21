package Luna.start.controller;

import Luna.start.model.Comments;
import Luna.start.model.Recipe;
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
    public Recipe saveComment(@RequestBody Comments comments) {
        commentsService.saveComment(comments);
        return recipeService.addComments(comments);
    }

    @DeleteMapping("/deleteComment")
    public void removeComment(@RequestBody Comments comments){
        commentsService.deleteComment(comments.getId());
        recipeService.removeComment(comments);
    }

}
