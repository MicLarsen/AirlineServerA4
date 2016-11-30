/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import java.io.Serializable;

/**
 *
 * @author nicolaicornelis
 */
public class NoFlightsFoundException extends BaseJSONException implements Serializable {

    public NoFlightsFoundException(String msg, int errorCode) {
        super(msg, errorCode);
    }

}
