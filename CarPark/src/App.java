public class App {
    Boolean isLoggedIn;
    public App() {
        isLoggedIn = false;
    }

    public static void main(String[] args) throws Exception {
        Authenticate auth = new Authenticate();

        auth.handleAuth();
    }

}
