package Database;

public class Database {

    Users users;

    public Database() {
        Users.addUser("admin", "admin");
    }

    public Users getUsers() {
        return users;
    }
}
