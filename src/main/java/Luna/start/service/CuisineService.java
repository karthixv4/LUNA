package Luna.start.service;

import Luna.start.model.Cuisine;
import Luna.start.model.Recipe;
import Luna.start.repository.CuisineRepo;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class CuisineService {

    @Autowired
    private CuisineRepo cuisineRepo;

    @Autowired
    private Cloudinary cloudinary;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
    }

    public Cuisine addCuisine(Cuisine cuisine){
        return cuisineRepo.save(cuisine);
    }

    public void deleteCuisine(String id){
        cuisineRepo.deleteById(id);
    }

    public Optional<Cuisine> getCuisineById(String id){
        return cuisineRepo.findById(id);
    }

    public List<Cuisine> getAllCuisine(){
        return cuisineRepo.findAll();
    }


}
