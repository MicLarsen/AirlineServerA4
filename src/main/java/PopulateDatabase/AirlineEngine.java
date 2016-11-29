package PopulateDatabase;

import Entities.Airroute;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

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
    String airline, flightID, flightNumber;
    Date date;
    int numberOfSeats, travelTime;
    String origin, destination;
    private final static String[] airportTags = {"CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW", "CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW", "CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW"};
    Airroute aRoute;

    public AirlineEngine() {
        random = new Random();
        this.airline = "Group 4A Airline";
        this.numberOfSeats = 300;
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

    //2016-03-05T13:00:00.000Z
    //2016-11-29T12:24:56.120Z
    public static String createISO8601Date() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String date = df.format(new Date());
        return date;
    }

    public double getTravelTime(String origin, String destination) {

        switch (origin) {
            case "CPH":
                switch (destination) {
                    case "BCN": return 170;
                    case "JFK": return 510;
                    case "ATL": return 750;
                    case "AMS": return 85;
                    case "OSL": return 70;
                    case "TXL": return 60;
                    case "MOW": return 145;
                }
            case "BCN":
                switch (destination) {
                    case "CPH": return 170;
                    case "JFK": return 535;
                    case "ATL":
                    case "AMS":
                    case "OSL":
                    case "TXL":
                    case "MOW":
                }
            case "JFK":
                switch (destination) {
                    case "CPH": return 510;
                    case "BCN": return 535;
                    case "ATL":
                    case "AMS":
                    case "OSL":
                    case "TXL":
                    case "MOW":
                }
            case "ATL":
                switch (destination) {
                    case "CPH": return 750;
                    case "BCN":
                    case "JFK":
                    case "AMS":
                    case "OSL":
                    case "TXL":
                    case "MOW":
                }
            case "AMS":
                switch (destination) {
                    case "CPH": return 85;
                    case "BCN":
                    case "JFK":
                    case "ATL":
                    case "OSL":
                    case "TXL":
                    case "MOW":
                }
            case "OSL":
                switch (destination) {
                    case "CPH": return 70;
                    case "BCN":
                    case "JFK":
                    case "ATL":
                    case "AMS":
                    case "TXL":
                    case "MOW":
                }
            case "TXL":
                switch (destination) {
                    case "CPH": return 60;
                    case "BCN":
                    case "JFK":
                    case "ATL":
                    case "AMS":
                    case "OSL":
                    case "MOW":
                }
            case "MOW":
                switch (destination) {
                    case "CPH": return 145;
                    case "BCN":
                    case "JFK":
                    case "ATL":
                    case "AMS":
                    case "OSL":
                    case "TXL":
                }
        }
 return 0;
    }

    public static void main(String[] args) {
        String date = createISO8601Date();
        System.out.println(date);
    }

    public void createAirlines(int number) {

    }
}
