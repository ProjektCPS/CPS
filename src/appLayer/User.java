package appLayer;

public class User {
    public boolean isValidUserCredentials(String userName, String userPassword){
        if (userName.equals("kvet@pekny.fri")&& userPassword.equals("presadeny")){
            return true;
        }
        return  false;
    }
}
