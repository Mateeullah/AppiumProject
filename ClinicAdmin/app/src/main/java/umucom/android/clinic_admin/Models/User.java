package umucom.android.clinic_admin.Models;

public class User {

    private int ID;
    private String Name;
    private String Username;
    private String Password;
    public User(){

    }

    public User(String name, String username, String password) {
        Name = name;
        Username = username;
        Password = password;
    }

    public User(int ID, String name, String username, String password) {
        this.ID = ID;
        Name = name;
        Username = username;
        Password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
