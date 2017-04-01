package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

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
        Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        boolean specials = m.find();
        // validity check of username and password
        if(username.length() < 4 || password.length() < 8 || !specials) {
            return true;
        }
        char[] uChars = username.toCharArray();
        char[] pChars = password.toCharArray();
        for (int i = 0; i < uChars.length; i++) {
            if(!Character.isLetter(uChars[i])) {
                return true;
            }
        }
        for (int i = 0; i < pChars.length; i++) {
            if(Character.isDigit(pChars[i])) {
                return false;
            }
        }
        return false;
    }
}
