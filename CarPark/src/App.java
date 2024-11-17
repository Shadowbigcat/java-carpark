public class App {
    Boolean isLoggedIn;
    public App() {
        isLoggedIn = false;
    }

    public static void main(String[] args) throws Exception {
        // Authenticate auth = new Authenticate();
        HandleCSV handleCSV = new HandleCSV();

        String[] credentials = handleCSV.read("credentials");
        String username = credentials[0];
        String password = credentials[1];

        System.out.println(username + password);

        // auth.handleAuth();
    }

}
