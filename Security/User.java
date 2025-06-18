// hannah ali - hma220003

package LinkedInventoryManagement.Security; 


public class User implements Comparable<User> {

    // fields for user's info
    private String firstName;
    private String lastName;
    private String username;
    private String hashedPassword;
    private boolean isManager;

    // constructor
    public User(String username, String hashedPassword, boolean isManager) {        
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.isManager = isManager;
    }

    // getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public boolean getIsManager() {
        return isManager;
    }

    // setters: update user’s details
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    // comparable method: compares this user with another user by username
    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }
}
