package asiantech.internship.summer.unit_test;

public class Account {
    private String mUsername;
    private String mPassword;

    Account(String mUsername, String mPassword) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    Account() {
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }
}
