package com.rameysoft.easypos.backend.services;

import org.springframework.stereotype.Service;

import com.rameysoft.easypos.backend.model.User;

@Service
public class LoginService {

	public User authenticate(String userName, String password) {
        User user = new User();
        user.setFirstName("Mubasher");
        user.setLastName("Usman");
        user.setRole("admin");
        String email = user.getFirstName().toLowerCase() + "."
                + user.getLastName().toLowerCase() + "@"
                + "rameysoft" + ".com";
        user.setEmail(email.replaceAll(" ", ""));
        user.setLocation("FSD");
        user.setBio("Quis aute iure reprehenderit in voluptate velit esse."
                + "Cras mattis iudicium purus sit amet fermentum.");
        return user;
    }
}
