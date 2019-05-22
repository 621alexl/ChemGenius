package com.example.alex.myapplication;

/**
 * Author: Who knows
 * Don't know exactly what this class does
 */

public class GetUnit{
    public static String getUnit(String buttonName){
        int indexOne = buttonName.indexOf("(");
        int indexTwo = buttonName.indexOf(")");

        return buttonName.substring((indexOne+1),indexTwo);
    }
}