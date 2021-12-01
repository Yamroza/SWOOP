package Classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    String login;
    String password;
    String name;
    String surname;
    LocalDate birthDate;
    // profile pic
    // ArrayList Item albo pointer/referencja/cokolwiek to tu jest na Item
    LocalDate accountCreationDate;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.name = "";
        this.surname = "";
        this.birthDate = LocalDate.now();
        this.accountCreationDate = LocalDate.now();
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public User(String login, String password, String name, String surname, LocalDate birthDate, LocalDate accountCreationDate) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.accountCreationDate = accountCreationDate;
    }

    public User() {
        this.birthDate = LocalDate.now();
        this.accountCreationDate = LocalDate.now();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String Login) {
        this.login = Login;
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

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
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
