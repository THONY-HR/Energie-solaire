package utilitaire;
public class DBUtil {
    String url = "jdbc:mysql://localhost/EnergieSolaire";
    String user = "root";
    String password = "root";

    public String getUrl(){
        return url;
    }
    public String getUser(){
        return user;
    }
    public String getPassword(){
        return password;
    }
}
