package asiantech.internship.summer;

public class Account {
    private String mPassword;
    private String mEmail;
    private String info;
    public Account(String email,String password,String info){
        this.mPassword=password;
        this.mEmail=email;
        this.info=info;
    }
    public boolean check(String email,String password){
        return this.mEmail.equals(email)&&this.mPassword.equals(password);
    }

    public String getInfo(){
        return info;
    }
}
