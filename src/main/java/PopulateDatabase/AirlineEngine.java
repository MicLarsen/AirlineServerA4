package PopulateDatabase;

import Entities.Airroute;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    private Random random;
    private String flightID, flightNumber;
    private String date;
    private int numberOfSeats, travelTime;
    private String origin, destination;
    private final String[] airportTags
            = {"CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW",
                "CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW",
                "CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW"};

    private Airroute route;
    private List<Airroute> airline; // for testing purposes

    public AirlineEngine() {
        random = new Random();
        this.numberOfSeats = 300;
        this.airline = new ArrayList();
    }

    public String generateFlightID(int n) {
        String id = "";
        for (int i = 0; i < n; i++) {
            int a = random.nextInt(8) + 1;
            id += (String.valueOf(a));
        }
        return id;
    }

    public String getDestination(String origin) {
        String dest = airportTags[random.nextInt(airportTags.length - 1)];
        while (origin == dest) {
            dest = airportTags[random.nextInt(airportTags.length - 1)];
            if (origin != dest) {
                return dest;
            }
        }
        return dest;
    }

    public String createISO8601Date(int plusDays, int HH, int mm) {

        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'"); // Quoted "Z" to indicate UTC, no timezone offset
        dateFormat.setTimeZone(timeZone);
        String returnDate = dateFormat.format(getFutureDate(plusDays));

        if (mm == 0 && HH != 0) {
            return returnDate + HH + ":00.00.000Z";
        }
        if (mm != 0 && HH == 0) {
            return returnDate + "00:" + mm + ".00.000Z";
        }
        if (mm == 0 && HH == 0) {
            return returnDate + "00:00.00.000Z";
        } else {
            return returnDate + HH + ":" + mm + ".00.000Z";
        }
    }

    public Date getFutureDate(int plusDays) {
        Calendar c = Calendar.getInstance();

        c.setTime(new Date()); // Now use today date.

        c.add(Calendar.DATE, plusDays);
        return c.getTime();
    }

    public int getTravelTime(String origin, String destination) {

        switch (origin) {
            case "CPH":
                switch (destination) {
                    case "BCN":
                        return 170;
                    case "JFK":
                        return 510;
                    case "ATL":
                        return 750;
                    case "AMS":
                        return 85;
                    case "OSL":
                        return 70;
                    case "TXL":
                        return 60;
                    case "MOW":
                        return 145;
                }
            case "BCN":
                switch (destination) {
                    case "CPH":
                        return 170;
                    case "JFK":
                        return 535;
                    case "ATL":
                        return 780;
                    case "AMS":
                        return 140;
                    case "OSL":
                        return 215;
                    case "TXL":
                        return 165;
                    case "MOW":
                        return 270;
                }
            case "JFK":
                switch (destination) {
                    case "CPH":
                        return 510;
                    case "BCN":
                        return 535;
                    case "ATL":
                        return 155;
                    case "AMS":
                        return 420;
                    case "OSL":
                        return 420;
                    case "TXL":
                        return 450;
                    case "MOW":
                        return 525;
                }
            case "ATL":
                switch (destination) {
                    case "CPH":
                        return 750;
                    case "BCN":
                        return 780;
                    case "JFK":
                        return 155;
                    case "AMS":
                        return 480;
                    case "OSL":
                        return 645;
                    case "TXL":
                        return 660;
                    case "MOW":
                        return 755;
                }
            case "AMS":
                switch (destination) {
                    case "CPH":
                        return 85;
                    case "BCN":
                        return 140;
                    case "JFK":
                        return 420;
                    case "ATL":
                        return 480;
                    case "OSL":
                        return 105;
                    case "TXL":
                        return 70;
                    case "MOW":
                        return 185;
                }
            case "OSL":
                switch (destination) {
                    case "CPH":
                        return 70;
                    case "BCN":
                        return 215;
                    case "JFK":
                        return 420;
                    case "ATL":
                        return 645;
                    case "AMS":
                        return 105;
                    case "TXL":
                        return 185;
                    case "MOW":
                        return 155;
                }
            case "TXL":
                switch (destination) {
                    case "CPH":
                        return 60;
                    case "BCN":
                        return 165;
                    case "JFK":
                        return 450;
                    case "ATL":
                        return 660;
                    case "AMS":
                        return 70;
                    case "OSL":
                        return 155;
                    case "MOW":
                        return 165;
                }
            case "MOW":
                switch (destination) {
                    case "CPH":
                        return 145;
                    case "BCN":
                        return 270;
                    case "JFK":
                        return 525;
                    case "ATL":
                        return 755;
                    case "AMS":
                        return 185;
                    case "OSL":
                        return 155;
                    case "TXL":
                        return 165;
                }
        }
        return 0;
    }

    public static void main(String[] args) {

        AirlineEngine e = new AirlineEngine();
        e.createAirlines(200);
        System.out.println(e.airline.size());
        System.out.println(e.airline.get(1200).getOrigin());
    }

    public void createAirlines(int numberOfDays) {
        for (int i = 0; i < numberOfDays; i++) {
            int HH = 10;
            int mm = 00;
            for (int n = 0; n < 7; n++) {
                flightID = generateFlightID(4) + "-" + generateFlightID(9);
                flightNumber = "G4A" + generateFlightID(4);
                this.date = createISO8601Date(i, HH, mm);
                origin = airportTags[random.nextInt(airportTags.length - 1)];
                destination = getDestination(origin);
                this.travelTime = getTravelTime(origin,destination);
                this.route = new Airroute("", flightID, flightNumber, date, numberOfSeats, travelTime, origin, destination);
                this.airline.add(route);
                HH += 2;
                if (n % 2 == 0) {
                    mm = 30;
                } else {
                    mm = 0;
                }
            }
        }
    }
}
