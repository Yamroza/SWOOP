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
/*
    jeszcze nie wiem co to zwraca ale może się przydać
    public ResultSet query(String query) throws SQLException
    {
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }
}
*/