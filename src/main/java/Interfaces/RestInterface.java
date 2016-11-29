/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Airroute;
import java.util.List;

/**
 *
 * @author Joakim
 */
public interface RestInterface {
    public List<Airroute> getFlightsByOrigin(String origin, String date, String tickets);
    public List<Airroute> getFlightsByOriginDest(String origin, String destination, String date, String tickets);
    
}
