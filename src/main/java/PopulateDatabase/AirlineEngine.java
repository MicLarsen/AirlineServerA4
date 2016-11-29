package PopulateDatabase;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author Michael
 */
public class AirlineEngine {
    
    private static Random random;
    private final static String[] airportTags = {"CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW", "CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW", "CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW"};

    
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
     
      public static String getDestination(String origin) {
        String dest = airportTags[random.nextInt(airportTags.length - 1)];
        while (origin == dest) {
            dest = airportTags[random.nextInt(airportTags.length - 1)];
            if (origin != dest) {
                return dest;
            }
        }
        return dest;
    }

    
}
