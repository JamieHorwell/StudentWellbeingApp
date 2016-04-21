package team10.studentwellbeingapp;


public class loginResult {
    private Boolean loggedin;
    private String logintext;
    public loginResult(Boolean loggedin, String logintext){
        this.loggedin = loggedin;
        this.logintext = logintext;
    }
    public Boolean getLoginStatus(){
        return loggedin;
    }
    public String getLoginText(){
        return logintext;
    }
}
