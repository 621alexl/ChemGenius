package com.example.alex.myapplication;

/**
 * Author: Ben Grass
 */

import java.util.*;

public class Parsing{

    static List<Integer> finalNumbers = new ArrayList<Integer>();//Main Array
    static List<String> finalElements = new ArrayList<String>();//Main Array

    public static void parse(String input){

        //Variable Decaration
        int lastNumIndex = 0;//Position of last recorded number (Main)
        //Input Prompt


        //Seperation of Numbers and Letters into their respective arrays and grouping of two character elements and multi-digit numbers
        for(int i = 0; i < input.length(); i++){

            //Try-Catch attempts to parse the string literal into an integer
            try{
                //subscript = the integer that was previously a string literal
                int subscript = Integer.parseInt(Character.toString(input.charAt(i)));

                //Groups the number with another if necessary
                if(i <= 1){//i at 0 or 1 minus 0 (as seen below) returns true when it should be false
                    finalNumbers.add(subscript);
                    lastNumIndex = i;//Updates lastNumIndex
                }
                //If there was another number just before combine the two
                else if((i-lastNumIndex) == 1){
                    //Retrieves the string at index i-1
                    String lastDigit = finalNumbers.get((finalNumbers.size() - 1)).toString();
                    //Concats the last digit and the new digit
                    String newNumber = lastDigit + Character.toString(input.charAt(i));
                    //Converts the string version of concatenated digits to an int
                    int finalNum = Integer.parseInt(newNumber);
                    //Remove old number, add new one and update lastNumIndex
                    finalNumbers.remove((finalNumbers.size() - 1));
                    finalNumbers.add(finalNum);
                    lastNumIndex = i;
                }
                else{
                    finalNumbers.add(subscript);
                    lastNumIndex = i;//Updates lastNumIndex
                }
            }
            //Catch recieves all upper and lower case letters after parsing fails
            catch(NumberFormatException e){
                //Converts string literal letter to a character's ASCII value
                int AsciiLetter = Character.toString(input.charAt(i)).charAt(0);

                //If ASCII value is <= 90 it is a capital letter
                if(AsciiLetter <= 90){
                    //Adds uppercase letter to finalElements
                    finalElements.add(Character.toString(input.charAt(i)));

                    if(i == ((input.length() - 1))){
                        finalNumbers.add(1);
                    }
                    else{
                        //Test the type of next
                        int type = TestNextCharacter.testNextCharacter(Character.toString(input.charAt((i+1))));

                        //If next is an uppercase letter
                        if(type == 1){
                            //If an uppercase letter is followed by another uppercase leter there is only 1 of that atom
                            finalNumbers.add(1);
                        }
                    }
                }
                //Letter is lowercase and must be combined with previous letter
                else{
                    //Concats new and previous letters
                    String newElement = finalElements.get((finalElements.size() - 1)) + Character.toString(input.charAt(i));
                    //Remove the old element and add the concatenated one
                    finalElements.remove((finalElements.size() - 1));
                    finalElements.add(newElement);

                    if(i == (input.length() - 1)){
                        finalNumbers.add(1);
                    }
                    else{
                        //Test the type of next
                        int type = TestNextCharacter.testNextCharacter(Character.toString(input.charAt((i+1))));

                        //If next is an uppercase letter
                        if(type == 1){
                            //If a lowercase letter is followed by an uppercase leter there is only 1 of that atom
                            finalNumbers.add(1);
                        }
                    }
                }
            }
        }
        //for(int i=0; i < finalNumbers.size(); i++){
        //Analyze.setFinalNumbers(finalNumbers.get(i));
        //Analyze.setFinalElements(finalElements.get(i));
        //}
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
