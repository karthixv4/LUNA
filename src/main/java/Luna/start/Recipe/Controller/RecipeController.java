package Luna.start.Recipe.Controller;
import Luna.start.Recipe.Model.Recipe;
import Luna.start.Recipe.Service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/getAll")
    public List<Recipe> getAllRecipe(){
        return  recipeService.getAllRecipe();
    }

    @PostMapping("/saveRecipe")
    public Recipe saveRecipe(@RequestParam("recipe") String recipe,@RequestParam(name = "file",required = false) MultipartFile file) throws IOException {
        System.out.println("HEY: "+file);
        Recipe recipeNew = objectMapper.readValue(recipe,Recipe.class);
        if(file != null ){
            recipeNew.setImage(recipeService.uploadFile(file)) ;
    }
       return recipeService.addRecipe(recipeNew);
    }

//    @PostMapping("/saveRecipe")
//    public String saveRecipe(@RequestParam("file") MultipartFile file) throws IOException {
//
//        return recipeService.uploadFile(file) ;
//
//    }
    @PutMapping("/update")
    public Recipe updateRecipe(@RequestBody Recipe recipe){
        return recipeService.addRecipe(recipe);
    }

    @GetMapping("/getById")
    public Optional<Recipe> getRecipeById(@RequestParam String Id){
        return recipeService.getRecipeById(Id);
    }

    @DeleteMapping("/delete")
    public void deleteRecipeById(@RequestParam(name="Id",required = true) String Id){
        recipeService.deleteRecipe(Id);
    }

}
