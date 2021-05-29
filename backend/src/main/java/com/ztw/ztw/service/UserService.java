package com.ztw.ztw.service;

import com.ztw.ztw.model.User;
import com.ztw.ztw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // CREATE
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // READ
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email){
        User userObject = userRepository.findByEmail(email);
        return userObject;
    }

    public List<String> getUsersEmails() {
        return userRepository.getAllEmails();
    }

    public User getSingleUser(Integer id) {
        Optional<User> userObject = userRepository.findById(id);
        return userObject.orElse(null);
    }

    // UPDATE
    @Transactional
    public User editUser(User user) {
        Optional<User> userEditedOptional = userRepository.findById(user.getUserId());
        if (userEditedOptional.isPresent()) {
            User editedUser = userEditedOptional.get();
            editedUser.setName(user.getName());
            editedUser.setSurname(user.getSurname());
            editedUser.setSex(user.getSex());
            editedUser.setWeight(user.getWeight());
            editedUser.setHeight(user.getHeight());
            editedUser.setActivity(user.getActivity());
            editedUser.setRecommendedDailyKcal(user.getRecommendedDailyKcal());
            editedUser.setRecommendedDailyProtein(user.getRecommendedDailyProtein());
            editedUser.setRecommendedDailyFats(user.getRecommendedDailyFats());
            editedUser.setRecommendedDailyCarbohydrates(user.getRecommendedDailyCarbohydrates());
            return editedUser;
        } else {
            return null;
        }
    }

    // DELETE
    public boolean deleteUser(Integer id) {
        Optional<User> deletedUser = userRepository.findById(id);
        if (deletedUser.isPresent()) {
            userRepository.delete(deletedUser.get());
            return true;
        } else {
            return false;
        }
    }
}
