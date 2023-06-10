package Luna.start.service;

import Luna.start.model.Comments;
import Luna.start.model.Cuisine;
import Luna.start.model.Recipe;
import Luna.start.model.User;
import Luna.start.repository.RecipeRepo;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private Cloudinary cloudinary;
    public Recipe addRecipe(Recipe recipe){
        System.out.println("SERVICE: "+recipe);
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

    public Recipe addComments(Comments comments){
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
            return recipeRepo.save(recipe.get());
         }
        return new Recipe();
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

    public Recipe removeLike(String id, User userLiked) {
        Optional<Recipe> recipe = recipeRepo.findById(id);
        if (recipe.isPresent()) {
            ArrayList<User> likedUsers = recipe.get().getLikes();
            if (likedUsers != null) {
                System.out.println("likedUsersBef: "+likedUsers);
                System.out.println("EMAIL : "+userLiked.getEmail());

                likedUsers.removeIf(user -> user.getEmail().equals(userLiked.getEmail()));
                System.out.println("likedUsersAfter: "+likedUsers);
                recipe.get().setLikes(likedUsers);
                return recipeRepo.save(recipe.get());
            }
        }
        return new Recipe();
    }

    public List<Recipe> findAllRecipeByUser(User user){
        List<Recipe> allRecipe =  recipeRepo.findAll();
        List<Recipe> sortedRecipe = new ArrayList<>();
        for(Recipe recipe : allRecipe){
            if(recipe.getUserDetails().getEmail().equals(user.getEmail())){
                sortedRecipe.add(recipe);
            }
        }
        return sortedRecipe;
    }

    public List<Recipe> findRecipeByCuisine(String id){
        List<Recipe> allRecipe =  recipeRepo.findAll();
        List<Recipe> sortedRecipe = new ArrayList<>();
        for(Recipe recipe : allRecipe){
            if(recipe.getCuisine().getId().equals(id)){
                sortedRecipe.add(recipe);
            }
        }
        return sortedRecipe;
    }

    public List<Recipe> getTop3RecipesByLikes() {
        Sort sortByLikesDesc = Sort.by(Sort.Direction.DESC, "likes.size");
        PageRequest pageRequest = PageRequest.of(0, 3, sortByLikesDesc);
        return recipeRepo.findAll(pageRequest).getContent();
    }


}
