package AirlineEngineTests;

import PopulateDatabase.AirlineEngine;
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
public class CreateAirrouteTraveltimeTest {
    
    private final static String[] airportTagsCPH = {"BCN", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW"};
    private final static int[] expectedTimeCPH = {170,510,750,85,70,60,145};
    private final static String[] airportTagsBCN = {"CPH", "JFK", "ATL", "AMS", "OSL", "TXL", "MOW"};
    private final static int[] expectedTimeBCN = {170,535,780,140,215,165,270};
    private final static String[] airportTagsJFK = {"CPH", "BCN", "ATL", "AMS", "OSL", "TXL", "MOW"};
    private final static int[] expectedTimeJFK = {510,535,155,420,420,450,525};
    private final static String[] airportTagsATL = {"CPH", "BCN", "JFK", "AMS", "OSL", "TXL", "MOW"};
    private final static int[] expectedTimeATL = {750,780,155,480,645,660,755};
    private final static String[] airportTagsAMS = {"CPH", "BCN", "JFK", "ATL", "OSL", "TXL", "MOW"};
    private final static int[] expectedTimeAMS = {85,140,420,480,105,70,185};
    private final static String[] airportTagsOSL = {"CPH", "BCN", "JFK", "ATL", "AMS", "TXL", "MOW"};
    private final static int[] expectedTimeOSL = {70,215,420,645,105,185,155};
    private final static String[] airportTagsTXL = {"CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "MOW"};
    private final static int[] expectedTimeTXL = {60,165,450,660,70,155,165};
    private final static String[] airportTagsMOW = {"CPH", "BCN", "JFK", "ATL", "AMS", "OSL", "TXL"};
    private final static int[] expectedTimeMOW = {145,270,525,755,185,155,165};
    
    private static AirlineEngine airlineEngine;
    private double expectedTime;
    private double returnedTime;
    private String origin,destination;
    
    public CreateAirrouteTraveltimeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
         airlineEngine = new AirlineEngine();
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
     * 
     */
    @Test
    public void checkTravelTimeCPHTest() {
        origin = "CPH";
        for(int i = 0; i < airportTagsCPH.length-1; i++) {
            destination = airportTagsCPH[i];
            expectedTime = expectedTimeCPH[i];
            returnedTime = airlineEngine.getTravelTime(origin,destination);
            assertEquals(expectedTime, returnedTime,0.1);
        }    
    }
    /**
     * 
     */
    @Test
    public void checkTravelTimeBCNTest() {
        origin = "BCN";
        for(int i = 0; i < airportTagsBCN.length-1; i++) {
            destination = airportTagsBCN[i];
            expectedTime = expectedTimeBCN[i];
            returnedTime = airlineEngine.getTravelTime(origin,destination);
            assertEquals(expectedTime, returnedTime,0.1);
        }    
    }
    /**
     * 
     */
    @Test
    public void checkTravelTimeJFKTest() {
        origin = "JFK";
        for(int i = 0; i < airportTagsJFK.length-1; i++) {
            destination = airportTagsJFK[i];
            expectedTime = expectedTimeJFK[i];
            returnedTime = airlineEngine.getTravelTime(origin,destination);
            assertEquals(expectedTime, returnedTime,0.1);
        }    
    }
    /**
     * 
     */
    @Test
    public void checkTravelTimeATLTest() {
        origin = "ATL";
        for(int i = 0; i < airportTagsATL.length-1; i++) {
            destination = airportTagsATL[i];
            expectedTime = expectedTimeATL[i];
            returnedTime = airlineEngine.getTravelTime(origin,destination);
            assertEquals(expectedTime, returnedTime,0.1);
        }    
    }
    /**
     * 
     */
    @Test
    public void checkTravelTimeAMSTest() {
        origin = "AMS";
        for(int i = 0; i < airportTagsAMS.length-1; i++) {
            destination = airportTagsAMS[i];
            expectedTime = expectedTimeAMS[i];
            returnedTime = airlineEngine.getTravelTime(origin,destination);
            assertEquals(expectedTime, returnedTime,0.1);
        }    
    }
    /**
     * 
     */
    @Test
    public void checkTravelTimeOSLTest() {
        origin = "OSL";
        for(int i = 0; i < airportTagsOSL.length-1; i++) {
            destination = airportTagsOSL[i];
            expectedTime = expectedTimeOSL[i];
            returnedTime = airlineEngine.getTravelTime(origin,destination);
            assertEquals(expectedTime, returnedTime,0.1);
        }    
    }
    /**
     * 
     */
    @Test
    public void checkTravelTimeTXLTest() {
        origin = "TXL";
        for(int i = 0; i < airportTagsTXL.length-1; i++) {
            destination = airportTagsTXL[i];
            expectedTime = expectedTimeTXL[i];
            returnedTime = airlineEngine.getTravelTime(origin,destination);
            assertEquals(expectedTime, returnedTime,0.1);
        }    
    }
    /**
     * 
     */
    @Test
    public void checkTravelTimeMOWTest() {
        origin = "MOW";
        for(int i = 0; i < airportTagsMOW.length-1; i++) {
            destination = airportTagsMOW[i];
            expectedTime = expectedTimeMOW[i];
            returnedTime = airlineEngine.getTravelTime(origin,destination);
            assertEquals(expectedTime, returnedTime,0.1);
        }    
    }
}
