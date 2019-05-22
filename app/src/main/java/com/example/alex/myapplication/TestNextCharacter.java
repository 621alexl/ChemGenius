package com.example.alex.myapplication;

/**
 * Author: Ben Grass?
 */

//Test Next Character

public class TestNextCharacter{
    //Method to test if next string in characters is a number
    public static int testNextCharacter(String next){
        //Type will be the return value that indicates type of next
        int type = -1;//0 = int, 1 = uppercase letter, 2 = lowercase leter
        //Attempts to parse next
        try{
            int num = Integer.parseInt(next);
            type = 0;
        }
        //Executes if next is a letter
        catch(NumberFormatException e1){
            char letterASCII = next.charAt(0);
            //If uppercase
            if(letterASCII <= 90){
                type = 1;
            }
            //If Lowercase
            else{
                type = 2;
            }
        }
        return type;
    }//End of testNextCharacter Method
}