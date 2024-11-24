public class App {
    Boolean isLoggedIn;
    static String[] data;
    
    public App() {
        isLoggedIn = false;
        data = new String[0];
    }

    public static void main(String[] args) throws Exception {
        // Authenticate auth = new Authenticate();
        // auth.handleAuth();
        
        HandleCSV CSV = new HandleCSV();
        CSV.read("carpark");
        
        for (int i = 0; i < data.length; i++) {
            System.out.println("Row " + i + ": " + data[i]);
        }

        // Update car details
        CSV.update(data);
    }
}