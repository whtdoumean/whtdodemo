package com.whtdotest.whtdodemo.controllers;

import com.whtdotest.whtdodemo.entity.User;
import com.whtdotest.whtdodemo.projections.UserProjection;
import com.whtdotest.whtdodemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController("/")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository; // конструктор интерфейса
    @GetMapping(value = "get_user_by_id")
    public User getUser(@RequestParam(name = "id", required = true) UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @GetMapping(value = "get_user_by_first_name_letter")
    public List<UserProjection> getUserByNameLetter(@RequestParam(name = "letter", required = true) String letter) {
        return userRepository.findByFirstNameLike(letter + "%"); // начинается с буквы
    }

    @GetMapping(value = "get_user_by_passport_number")
    public List<UserProjection> getUserByPassport(@RequestParam(name = "passport_number", required = true) String passportNumber) {
        return userRepository.findByPassportNumber(passportNumber);
    }

    @GetMapping(value = "get_user_by_first_name_and_passport_number")
    public List<UserProjection> getUserByFirstNameAndPassportNumber(@RequestParam(name = "first_name") String firstName,
                                                                    @RequestParam(name = "passport_number") String passportNumber) {
        return userRepository.findByFirstNameAndPassportNumber(firstName, passportNumber);
    }

    @GetMapping(value = "add_user") // ловим url
    public User addUser(@RequestParam(name = "passport_number", required = false) String passportNumber,
                        @RequestParam(name = "first_name", required = false) String firstName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setPassportNumber(passportNumber);
        userRepository.save(user); // сохраняем в бд
        return user;
    }

    @GetMapping(value = "get_all_users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
