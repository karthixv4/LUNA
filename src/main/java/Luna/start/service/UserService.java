package Luna.start.service;

import Luna.start.model.User;
import Luna.start.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
        // Check if the user already exists based on email
        Optional<User> existingUser = userRepo.findById(user.getEmail());
        // User already exists, return the existing user
        // User does not exist, add the new user to the database
        return existingUser.orElseGet(() -> userRepo.save(user));
    }

    public Optional<User> getUser(String id){
        return userRepo.findById(id);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

}
