package Luna.start.Cuisine.Service;

import Luna.start.Cuisine.Model.Cuisine;
import Luna.start.Cuisine.Repository.CuisineRepo;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
