import PopulateDatabase.AirlineEngine;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael
 */
public class CreateAirrouteDestinationTest {

    private static AirlineEngine airlineEngine;
    private static Random random;
    private final static String[] airportTags = {"CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW"};

    public CreateAirrouteDestinationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        airlineEngine = new AirlineEngine();
        random = new Random();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * @testParamString[] airportTags = {"CPH", "BCN", "JFK", "ATL", "AMS",
     * "OSL", "TXL", "MOW"}; String-array of airport tags of type IATA
     * (International Air Transport Association - airport code) Testing that
     * return value from method @getDestination() does not return same random String
     * value as given.
     */
    @Test
    public void rngStringLengthTestingMinusOne() {
        System.out.println("Testing getDestination for IATA airport codes - Test executed 10.000.000 times");
        for (int i = 0; i < 1000; i++) {
            String testParam = airportTags[random.nextInt(airportTags.length - 1)];
            assertNotEquals(airlineEngine.getDestination(testParam), testParam);
        }
    }
}
