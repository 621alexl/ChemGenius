package com.example.alex.myapplication;

/**
 *  Author: Ben Grass?
 */


public class ReadInput{
    public static String readInput(String formula){
        String output = "";

        try{
            output = MolarMass.molarMass(formula) + " grams";
        }
        catch(NullPointerException e){
            Analyze.cleanArrays();
            Parsing.cleanArrays();
            output = Balancing.balance(formula);
        }


        Analyze.cleanArrays();
        Parsing.cleanArrays();
        return output;
    }
}