package Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    String username;
    String password;
    String name;
    String surname;
    LocalDate birthDate;
    // profile pic
    // ArrayList Item albo pointer/referencja/cokolwiek to tu jest na Item
    LocalDateTime accountCreationTime;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountCreationTime = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getAccountCreationTime() {
        return accountCreationTime;
    }
}
