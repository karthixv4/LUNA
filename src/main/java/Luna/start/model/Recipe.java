package Luna.start.model;

import Luna.start.model.Cuisine;
import Luna.start.model.Ingredient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
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
    private List<Ingredient> ingredient;

    @Field
    private String cookingSteps;

    @DBRef
    private Cuisine cuisine;

    @Field
    private String mealType;

    @Field
    private String dietRestriction;

    @Field
    private String image;

    @DBRef
    private User userDetails;

    @Field
    private ArrayList<User> likes;

    @DBRef
    private ArrayList<Comments> comments;

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredient=" + ingredient +
                ", cookingSteps='" + cookingSteps + '\'' +
                ", cuisine=" + cuisine +
                ", mealType='" + mealType + '\'' +
                ", dietRestriction='" + dietRestriction + '\'' +
                ", image='" + image + '\'' +
                ", userDetails=" + userDetails +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }

    public ArrayList<Comments> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comments> comments) {
        this.comments = comments;
    }

    public ArrayList<User> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<User> likes) {
        this.likes = likes;
    }

    public User getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }

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


    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public String getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(String cookingSteps) {
        this.cookingSteps = cookingSteps;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getDietRestriction() {
        return dietRestriction;
    }

    public void setDietRestriction(String dietRestriction) {
        this.dietRestriction = dietRestriction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
