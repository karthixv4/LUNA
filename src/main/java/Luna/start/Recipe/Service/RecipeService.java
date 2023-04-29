package Luna.start.Recipe.Service;

import Luna.start.Recipe.Model.Recipe;
import Luna.start.Recipe.Repository.RecipeRepo;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private Cloudinary cloudinary;
    public Recipe addRecipe(Recipe recipe){
        System.out.println("RECIPE: "+recipe);
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


}
