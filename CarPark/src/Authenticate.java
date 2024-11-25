import java.util.Scanner;

public class Authenticate extends App {
    public void authenticate(){
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

        HandleCSV handleCSV = new HandleCSV();
        handleCSV.read("credentials");

        String loginUsername = App.credentials[0];
        String loginPassword = App.credentials[1];

        if(username.equals(loginUsername) && password.equals(loginPassword)){
            isLoggedIn = true;
        } else {
            System.out.println("Bad credentials");
        }
    }

    public void logout(){
        isLoggedIn = false;
        System.out.println("Logged in:" + isLoggedIn);
    }

    public Boolean isAuthenticated(){
        return isLoggedIn;
    }
}
