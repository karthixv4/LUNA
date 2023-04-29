package Luna.start.Recipe.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Document(collection = "lunarecipe")
public class Recipe {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private Map<String,String> ingredient;

    @Field
    private Map<String,String> cookingSteps;

    @Field
    private String cuisine;

    @Field
    private String mealType;

    @Field
    private List<String> dietRestriction;

    @Field
    private String image;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Map<String, String> ingredient) {
        this.ingredient = ingredient;
    }

    public Map<String, String> getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(Map<String, String> cookingSteps) {
        this.cookingSteps = cookingSteps;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public List<String> getDietRestriction() {
        return dietRestriction;
    }

    public void setDietRestriction(List<String> dietRestriction) {
        this.dietRestriction = dietRestriction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
