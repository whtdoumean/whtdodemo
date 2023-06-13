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

    @GetMapping(value = "get_user_by_name_letter")
    public List<UserProjection> getUserByNameLetter(@RequestParam(name = "letter", required = true) String letter) {
        return userRepository.findByFirstNameLike(letter + "%"); // начинается с буквы
    }

    @GetMapping(value = "get_user_by_passport")
    public List<User> getUserByPassport(@RequestParam(name = "passport", required = true) String passport) {
        return userRepository.findByPassportNumber(passport);
    }

    @GetMapping(value = "get_projection_by_passport")
    public List<UserProjection> getUserByPassportIs(@RequestParam(name = "passport", required = true) String passport) {
        return userRepository.findByPassportNumberIs(passport);
    }

    @GetMapping(value = "add_user") // ловим url
    public User addUser(@RequestParam(name = "passport", required = false) String passportNumber,
                        @RequestParam(name = "name", required = false) String name) {
        User user = new User();
        user.setFirstName(name);
        user.setPassportNumber(passportNumber);
        userRepository.save(user); // сохраняем в бд
        return user;
    }

    @GetMapping(value = "get_all_users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
