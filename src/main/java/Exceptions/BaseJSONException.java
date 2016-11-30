/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import org.json.simple.JSONObject;

/**
 *
 * @author nicolaicornelis
 */
public class BaseJSONException extends Exception {

    private final int errorCode;

    public BaseJSONException(String msg, int errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public String getJSON() {

        JSONObject obj = new JSONObject();
        obj.put("error", this.getMessage());
        obj.put("code", this.errorCode);
        obj.put("exception", this.getClass().toString());

        return obj.toString();
    }
}
