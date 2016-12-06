/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Airroute;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Joakim
 */
public interface RestInterface {
    public List<Airroute> getFlightsByOrigin(String origin, Date date, String tickets);
    public List<Airroute> getFlightsByOriginDest(String origin, String destination, Date date, String tickets);
    public double calculateTotalPrice(String origin, String destination, int tickets); 
    
}
