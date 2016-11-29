package PopulateDatabase;

import Entities.Airroute;
import java.util.Random;

/**
 * @author Michael
 *
 * @JSON-object: "airline": "AngularJS Airline", "flights": [ { "flightID":
 * "2257-1457179200000", "flightNumber": "COL2257", "date":
 * "2016-03-05T13:00:00.000Z", "numberOfSeats": 3, "totalPrice": 180,
 * "traveltime": 120, "origin": "CDG", "destination": "CPH", } ] }
 */
public class AirlineEngine {
    
    private static Random random;
    private final static String[] airportTags = {"CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW", "CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW", "CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW"};
    Airroute aRoute;
    
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
