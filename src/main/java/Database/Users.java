package Database;

import Classes.User;

import java.util.ArrayList;
import java.util.Objects;

public class Users {

    static ArrayList<User> usersList = new ArrayList<>();
    static User loggedUser;

    public static ArrayList<User> getUsersList() {
        return usersList;
    }

    public static User checkIfUserIsInBase(String givenUsername) {
        for (User user : usersList) {
            if (Objects.equals(user.getLogin(), givenUsername)) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(String login, String password) {
        usersList.add(new User(login, password));
    }

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
}
