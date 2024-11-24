import java.util.Scanner;

public class Authenticate extends App {

    public Boolean handleAuth(){

        if(isLoggedIn == false){
            logIn();
        } else {
            changeLoginStatus();
        }

        return isLoggedIn;
    }

    public void logIn(){
        String username = "";
        String password = "";

        try(Scanner in = new Scanner(System.in)){
            System.out.print("Enter username: ");
            username = in.nextLine();

            System.out.print("Enter password: ");
            password = in.nextLine();
        } catch(Exception e){
            System.err.println(e);
        }

        authenticate(username, password);
    }

    public void authenticate(String username, String password){
        HandleCSV handleCSV = new HandleCSV();

        handleCSV.read("credentials");

        String loginUsername = App.credentials[0];
        String loginPassword = App.credentials[1];

        if(username.equals(loginUsername) && password.equals(loginPassword)){
            changeLoginStatus();
        } else {
            System.out.println("Bad credentials");
        }
    }

    public void changeLoginStatus(){
        isLoggedIn = !isLoggedIn;
        System.out.println("Logged in:" + isLoggedIn);
    }
}
