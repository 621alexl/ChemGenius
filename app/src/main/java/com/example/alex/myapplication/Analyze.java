package com.example.alex.myapplication;

/**
 * Author: Ben Grass
 */


import java.util.*;

public class Analyze{
    static List<Integer> finalNumbers = new ArrayList<Integer>();//Main Array
    static List<String> finalElements = new ArrayList<String>();//Main Array

    public static void analyze(String input){
        String data = input;

        data = Parenthesis.parenthesis(data);
        Parsing.parse(data);
        for(int i=0; i < Parsing.getElementsLength(); i++){
            finalElements.add(Parsing.getFinalElements(i));
        }
        for(int i=0; i < Parsing.getNumbersLength(); i++){
            finalNumbers.add(Parsing.getFinalNumbers(i));
        }
        System.out.println(finalNumbers);
        System.out.println(finalElements);
        System.out.println();
    }
    public static void setFinalNumbers(int num){
        finalNumbers.add(num);
    }
    public static void setFinalElements(String elem){
        finalElements.add(elem);
    }
    public static int getFinalNumbers(int num){
        return finalNumbers.get(num);
    }
    public static String getFinalElements(int num){
        return finalElements.get(num);
    }
    public static int getElementsLength(){
        return finalElements.size();
    }
    public static int getNumbersLength(){
        return finalNumbers.size();
    }
    public static void cleanArrays(){
        int iterations = finalNumbers.size();
        for(int i=0; i < iterations; i++){
            finalNumbers.remove(0);
            finalElements.remove(0);
        }
    }
}