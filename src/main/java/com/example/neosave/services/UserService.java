package com.example.neosave.services;

import com.example.neosave.dto.StateDTO;
import com.example.neosave.dto.UserDTO;
import com.example.neosave.exceptions.DuplicateEmailFound;
import com.example.neosave.exceptions.InvalidEmail;
import com.example.neosave.exceptions.PinCodeNotFound;
import com.example.neosave.exceptions.UserNotFound;
import com.example.neosave.models.User;
import com.example.neosave.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.*;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    final String uri = "https://api.postalpincode.in/pincode/";
    final String regex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository mUserRepository) {
        this.userRepository = mUserRepository;
    }

    public User addNewUser(UserDTO userDTO) throws PinCodeNotFound, DuplicateEmailFound, InvalidEmail {
        RestTemplate restTemplate = new RestTemplate();
        if (checkMailId(userDTO)) {
            throw new DuplicateEmailFound("Duplicate email ID");
        }
        if (validEmail(userDTO)) {
            throw new InvalidEmail("Invalid Email");
        }
        try {

            StateDTO[] stateDetails = restTemplate.getForObject(uri + userDTO.getPinCode(), StateDTO[].class);
            User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPinCode(), stateDetails[0].getPostOffice().get(0).getState());
            return userRepository.save(user);

        } catch (Exception e) {
            throw new PinCodeNotFound("Please check pincode");
        }
    }

    public User getNewUser(String userId) throws UserNotFound {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFound("Not found");
        }
        return user.get();
    }

    public boolean checkMailId(UserDTO userDTO) {
        List<User> user = userRepository.findAllByEmail(userDTO.getEmail());
        if (user.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validEmail(UserDTO userDTO) {
        return !Pattern.matches(regex, userDTO.getEmail());
    }
}
