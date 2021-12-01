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
    LocalDate accountCreationTime;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.name = "";
        this.surname = "";
        this.birthDate = LocalDate.now();
        this.accountCreationTime = LocalDate.now();
    }

    public User(String username, String password, String name, String surname, LocalDate birthDate, LocalDate accountCreationTime) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.accountCreationTime = accountCreationTime;
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getAccountCreationTime() {
        return accountCreationTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
