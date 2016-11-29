package PopulateDatabase;

import java.util.Random;

/**
 *
 * @author Michael
 */
public class AirlineEngine {
    
    Random random;
    
    public AirlineEngine() {
        random = new Random();
    }
 
     public String generateFlightID(int n) {
        String id = "";
        for (int i = 0; i < n; i++) {
            int a = random.nextInt(8) + 1;
            id += (String.valueOf(a));
        }
        return id;
    }
    
}
