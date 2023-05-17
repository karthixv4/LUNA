package Luna.start.service;

import Luna.start.model.Comments;
import Luna.start.model.Recipe;
import Luna.start.model.User;
import Luna.start.repository.RecipeRepo;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private Cloudinary cloudinary;
    public Recipe addRecipe(Recipe recipe){
        return recipeRepo.save(recipe);
    }

    public Optional<Recipe> getRecipeById(String id){
        return recipeRepo.findById(id);
    }

    public List<Recipe> getAllRecipe(){
        return recipeRepo.findAll();
    }

    public void deleteRecipe(String id){
        recipeRepo.deleteById(id);
    }

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
    }

    public void addComments(Comments comments){
        String recipeId = comments.getRecipeId();
         Optional<Recipe> recipe = recipeRepo.findById(recipeId);
         if(recipe.isPresent()) {
             if (recipe.get().getComments() !=null) {
                 ArrayList<Comments> recipeComments = recipe.get().getComments();
                 recipeComments.add(comments);
                 recipe.get().setComments(recipeComments);
             } else {
                 ArrayList<Comments> recipeComments = new ArrayList<>();
                 recipeComments.add(comments);
                 recipe.get().setComments(recipeComments);
             }
             recipeRepo.save(recipe.get());
         }

    }

    public void removeComment(Comments comment){
        String recipeId = comment.getRecipeId();
        Optional<Recipe> recipe = recipeRepo.findById(recipeId);
        if(recipe.isPresent()){
            if(recipe.get().getComments().contains(comment)){
                recipe.get().getComments().remove(comment);
            }
            recipeRepo.save(recipe.get());
        }

    }

    public Recipe addLikes(User user,String id){
        Optional<Recipe> recipe = recipeRepo.findById(id);
        if(recipe.isPresent()) {
            if (recipe.get().getLikes() != null) {
                ArrayList<User> existingLikes = recipe.get().getLikes();
                existingLikes.add(user);
                recipe.get().setLikes(existingLikes);
            } else {
                ArrayList<User> likes = new ArrayList<>();
                likes.add(user);
                recipe.get().setLikes(likes);
            }
            return recipeRepo.save(recipe.get());
        }
        return new Recipe();
    }

    public Recipe removeLike(String id, User userLiked){
        Optional<Recipe> recipe = recipeRepo.findById(id);
        if(recipe.isPresent()){
            if(recipe.get().getLikes()!= null){
               ArrayList<User> likedUsers = recipe.get().getLikes();
                for (Iterator<User> iterator = likedUsers.iterator();
                     iterator.hasNext();) {
                    User user = iterator.next();
                    if (user.getEmail().equals(userLiked.getEmail())) {
                        iterator.remove();
                        break;
                    }
                }
                recipe.get().setLikes(likedUsers);
                return recipeRepo.save(recipe.get());
            }
        }
        return new Recipe();
    }

}
