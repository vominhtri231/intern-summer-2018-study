package asiantech.internship.summer.unit.test;

public class Account {
    private String mUsername;
    private String mPassword;

    Account(){}

    Account(String mUsername, String mPassword) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }
}
