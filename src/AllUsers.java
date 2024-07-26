public class AllUsers {
    
    private String username, email, password;

    public AllUsers(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return password;
    }
}
