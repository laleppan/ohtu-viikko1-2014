package ohtu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;
import ohtu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private UserDao userDao;
    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        boolean passwordInvalid = true;
        boolean usernameInvalid = true;
        if (password.length() < 8) {
           return true;
        }
        Pattern hasDigit = Pattern.compile("(.)*(\\d)(.)*");
        Pattern hasSpecialCharacter = Pattern.compile("(.)*(\\W)(.)*");
        Matcher digit = hasDigit.matcher(password);
        Matcher special = hasSpecialCharacter.matcher(password);
        if (digit.matches() || special.matches()) {
            passwordInvalid = false;
        } 
        
        Pattern validUsername = Pattern.compile("[a-z]{3,}");
        Matcher un = validUsername.matcher(username);
        System.out.println(un.matches());
        if (un.matches()) {
            usernameInvalid = false;
        }
        return passwordInvalid || usernameInvalid;
    }
}
