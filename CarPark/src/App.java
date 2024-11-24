public class App {
    Boolean isLoggedIn;
    public App() {
        isLoggedIn = false;
    }

    public static void main(String[] args) throws Exception {
        // Authenticate auth = new Authenticate();
        // auth.handleAuth();

        HandleCSV CSV = new HandleCSV();

        String[] data = CSV.read("carpark");

        for (int i = 0; i < data.length; i++) {
            System.out.println("Row " + i + ": " + data[i]);
        }
    }

}
