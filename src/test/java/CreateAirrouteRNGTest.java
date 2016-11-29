/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class CreateAirrouteRNGTest {

    private static AirlineEngine airlineEngine;
    int testParam;

    public CreateAirrouteRNGTest() {
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
     * @testParam -1 Testing if return value from the RNG-engine returns empty
     * String with @testParam as expected Checked by taking the length of an
     * empty String and comparing it with the length of the returnvalue.
     */
    @Test
    public void rngStringLengthTestingMinusOne() {
        System.out.println("Testing 'generateFlightID for testParam extrema : -1");
        testParam = -1;
        assertEquals(airlineEngine.generateFlightID(testParam).length(), "".length());
    }

    /**
     * @testParam 0 Testing if return value from the RNG-engine returns empty
     * String with @testParam as expected. Checked by taking the length of an
     * empty String and comparing it with the length of the returnvalue.
     */
    @Test
    public void rngStringLengthTestingzero() {
        System.out.println("Testing 'generateFlightID for testParam : 0");
        testParam = 0;
        assertEquals(airlineEngine.generateFlightID(testParam).length(), "".length());
    }

    /**
     * @testParam 1 Testing if return value from the RNG-engine returns String
     * length of 1 with @testParam as expected Checked by taking the @testParam
     * and comparing this number with the length of the returnvalue.
     */
    @Test
    public void rngStringLengthTestingFour() {
        System.out.println("Testing 'generateFlightID for testParam (need for program) : 4");
        testParam = 4;
        assertEquals(airlineEngine.generateFlightID(testParam).length(), testParam);
    }

    /**
     * @testParam 1 Testing if return value from the RNG-engine returns String
     * length of 1 with @testParam as expected Checked by taking the @testParam
     * and comparing this number with the length of the returnvalue.
     */
    @Test
    public void rngStringLengthTestingNine() {
        System.out.println("Testing 'generateFlightID for testParam (need for program) : 9");
        testParam = 9;
        assertEquals(airlineEngine.generateFlightID(testParam).length(), testParam);
    }

    /**
     * @testParam 1 Testing if return value from the RNG-engine returns String
     * length of 1 with @testParam as expected Checked by taking the @testParam
     * and comparing this number with the length of the returnvalue.
     */
    @Test
    public void rngStringLengthTestingOneThousand() {
        System.out.println("Testing 'generateFlightID for testParam : 1000");
        testParam = 10000;
        assertEquals(airlineEngine.generateFlightID(testParam).length(), testParam);
    }
}
