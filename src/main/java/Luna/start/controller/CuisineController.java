package Luna.start.controller;

import Luna.start.model.Cuisine;
import Luna.start.service.CuisineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Cuisine")
public class CuisineController {

    @Autowired
    private CuisineService cuisineService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/getAll")
    public List<Cuisine> getAllCuisines(){
        return  cuisineService.getAllCuisine();
    }

    @GetMapping("/getById")
    public Optional<Cuisine> getCuisineById(String id){
        return cuisineService.getCuisineById(id);
    }

    @PostMapping(value = "/saveCuisine", consumes = { "multipart/form-data","application/json" })
    public Cuisine addCuisine(@RequestParam("cuisine") String cuisine, @RequestParam(name = "file",required = false) MultipartFile file) throws IOException {
        Cuisine cuisineNew = objectMapper.readValue(cuisine,Cuisine.class);

        if(file != null ){
            cuisineNew.setImageUrl(cuisineService.uploadFile(file));
        }
        return cuisineService.addCuisine(cuisineNew);
    }
}
