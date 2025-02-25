package Classes;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;

public class User {
    private String login;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String profilePhoto;
    private LocalDate accountCreationDate;

    // creating empty user object
    public User() {
        this.birthDate = LocalDate.now();
        this.accountCreationDate = LocalDate.now();
    }

    // creating user from given data and filling with default values
    public User(String login, String password) {
        this.login = login;
        this.setPassword(password);
        this.name = "";
        this.surname = "";
        this.birthDate = LocalDate.now();
        this.accountCreationDate = LocalDate.now();
        this.profilePhoto = "https://i.imgur.com/c9Bk75O.png";
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
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
        this.password = DigestUtils.shaHex(password);
    }

    public void setPasswordNoHash(String password) { this.password = password; }

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

    public String getProfilePhoto() { return profilePhoto; }

    public void setProfilePhoto(String profilePhoto) { this.profilePhoto = profilePhoto; }
}
