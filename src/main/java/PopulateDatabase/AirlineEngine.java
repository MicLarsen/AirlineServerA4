package PopulateDatabase;

import Entities.Airroute;
import Entities.Airport;
import Entities.FlightPrices;
import JPA.FlightJPA;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    private String dateString;
    private int numberOfSeats, travelTime;
    private String origin, destination;
    DateFormat format;
    private final String[] airportTags
            = {"CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW"};

    public AirlineEngine() {
        random = new Random();
        this.numberOfSeats = 300;
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm.ss");
    }

    public String generateRandomNumbers(int n) {
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        dateFormat.setTimeZone(timeZone);
        String returnDate = dateFormat.format(getFutureDate(plusDays));

        if (mm == 0 && HH != 0) {
            return returnDate + HH + ":00.00";
        }
        if (mm != 0 && HH == 0) {
            return returnDate + "00:" + mm + ".00";
        }
        if (mm == 0 && HH == 0) {
            return returnDate + "00:00.00";
        } else {
            return returnDate + HH + ":" + mm + ".00";
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

    public int getFlightPrice(String origin, String destination) {

        switch (origin) {
            case "CPH":
                switch (destination) {
                    case "BCN":
                        return 120;
                    case "JFK":
                        return 520;
                    case "ATL":
                        return 510;
                    case "AMS":
                        return 190;
                    case "OSL":
                        return 200;
                    case "TXL":
                        return 195;
                    case "MOW":
                        return 235;
                }
            case "BCN":
                switch (destination) {
                    case "CPH":
                        return 120;
                    case "JFK":
                        return 580;
                    case "ATL":
                        return 1020;
                    case "AMS":
                        return 195;
                    case "OSL":
                        return 310;
                    case "TXL":
                        return 215;
                    case "MOW":
                        return 270;
                }
            case "JFK":
                switch (destination) {
                    case "CPH":
                        return 520;
                    case "BCN":
                        return 580;
                    case "ATL":
                        return 310;
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
                        return 510;
                    case "BCN":
                        return 1020;
                    case "JFK":
                        return 310;
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
                        return 190;
                    case "BCN":
                        return 195;
                    case "JFK":
                        return 420;
                    case "ATL":
                        return 480;
                    case "OSL":
                        return 305;
                    case "TXL":
                        return 210;
                    case "MOW":
                        return 185;
                }
            case "OSL":
                switch (destination) {
                    case "CPH":
                        return 200;
                    case "BCN":
                        return 310;
                    case "JFK":
                        return 420;
                    case "ATL":
                        return 645;
                    case "AMS":
                        return 305;
                    case "TXL":
                        return 185;
                    case "MOW":
                        return 155;
                }
            case "TXL":
                switch (destination) {
                    case "CPH":
                        return 195;
                    case "BCN":
                        return 215;
                    case "JFK":
                        return 450;
                    case "ATL":
                        return 660;
                    case "AMS":
                        return 210;
                    case "OSL":
                        return 155;
                    case "MOW":
                        return 165;
                }
            case "MOW":
                switch (destination) {
                    case "CPH":
                        return 235;
                    case "BCN":
                        return 180;
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

//     public static void main(String[] args) throws ParseException {
//        AirlineEngine e = new AirlineEngine();
//        e.createFlightPrices();
//        e.createAirlines(365);
//    }
    
    public void createFlightPrices() {
        String from = "";
        String to = "";
        for (int i = 0; i < airportTags.length-1; i++) {
            for (int n = 0; n < airportTags.length-1; n++) {
            from = airportTags[i];
                to = airportTags[n];
                       
                new FlightJPA().persistFlightPrices(new FlightPrices(from, to , getFlightPrice(from,to)));
            }

        }
    }

    public void createAirlines(int numberOfDays) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirlinePU");
        EntityManager em = emf.createEntityManager();

        for (int i = 0; i < numberOfDays; i++) {
            int HH = 10;
            int mm = 00;
            for (int n = 0; n < 7; n++) {
                flightID = generateRandomNumbers(4) + "-" + generateRandomNumbers(9);
                flightNumber = "G4A" + generateRandomNumbers(4);
                this.dateString = createISO8601Date(i, HH, mm);
                Date date = format.parse(dateString);

                origin = airportTags[random.nextInt(airportTags.length - 1)];
                destination = getDestination(origin);
                this.travelTime = getTravelTime(origin, destination);

                Airport originAirport = em.getReference(Airport.class, origin);
                Airport destinationAirport = em.getReference(Airport.class, destination);

                try {

                    new FlightJPA().persistAirroute(new Airroute(flightID, flightNumber, date, numberOfSeats, travelTime, originAirport, destinationAirport));

                } catch (SQLIntegrityConstraintViolationException ex) {
                    Logger.getLogger(AirlineEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
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
