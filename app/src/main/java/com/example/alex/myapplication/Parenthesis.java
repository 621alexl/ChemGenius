package com.example.alex.myapplication;

/**
 * Author :Ben Grass
 *
 */

//Parenthesis

import java.util.*;

public class Parenthesis{
    public static String parenthesis(String input){
        //Variable Declaration

        //Find Interior of parenthesis, multiplier, multiply and reconcatenate
        boolean clean = false;

        while(clean == false){
            //Variable Declaration
            List<Integer> numbers = new ArrayList<Integer>();//Multiplying Array
            List<String> elements = new ArrayList<String>();//Multiplying Array
            int lastNumberIndex = 0;//Same as lastNumIndex (Multiplying)
            int firstIndex = input.indexOf("(");
            int secondIndex = input.indexOf(")");
            String currentMultiplier = "";
            String interior;
            int multiplier = -1;
            int indexOver = 1;

            //No more parenthesis left break out of while loop
            if(firstIndex == -1 && secondIndex == -1){
                clean = true;
                break;
            }
            else{
                //Finds the right interior
                interior = input.substring((firstIndex+1), secondIndex);
                System.out.println("Interior: " + interior);
                //Find multiplier
                //If ')' is last character multiplier is 1
                if(secondIndex == (input.length() - 1)){
                    multiplier = 1;
                }
                //If next character is an Uppercase letter multiplier is 1
                else if((TestNextCharacter.testNextCharacter(Character.toString(input.charAt((indexOver + secondIndex))))) == 1){
                    multiplier = 1;
                }
                //Next Character must be a number
                else{
                    boolean moreNumbers = true;

                    while(moreNumbers == true){
                        currentMultiplier = currentMultiplier + Integer.parseInt(Character.toString(input.charAt(secondIndex + indexOver)));
                        indexOver += 1;
                        //If incremented index is over length
                        if((indexOver + secondIndex) > (input.length() - 1)){
                            multiplier = Integer.parseInt(currentMultiplier);
                            moreNumbers = false;
                        }
                        //If next character is an uppercase letter
                        else if((TestNextCharacter.testNextCharacter(Character.toString(input.charAt((indexOver + secondIndex))))) == 1){
                            multiplier = Integer.parseInt(currentMultiplier);
                            moreNumbers = false;
                        }
                    }
                }
            }
            System.out.println("Multiplier: " + multiplier);
            //Multiplying
            //Parse into Arrays
            for(int i=0;i <= (interior.length() - 1); i++){
                //Repurposing testNextCharacter to test the current character
                int typeOf = TestNextCharacter.testNextCharacter(Character.toString(interior.charAt(i)));
                //Number in string form
                if(typeOf == 0){
                    if(i <= 1){
                        numbers.add(Integer.parseInt(Character.toString(interior.charAt(i))));
                        lastNumberIndex = i;
                    }
                    else if((i - lastNumberIndex) == 1){
                        String newNumber = Integer.toString(numbers.get((numbers.size() - 1))) + (Character.toString(interior.charAt(i)));
                        int finalNumber = Integer.parseInt(newNumber);
                        numbers.remove((numbers.size() - 1));
                        numbers.add(finalNumber);
                        lastNumberIndex = i;
                    }
                    else{
                        numbers.add(Integer.parseInt(Character.toString(interior.charAt(i))));
                        lastNumberIndex = i;
                    }
                }
                //Uppercase Letter
                else if(typeOf == 1){
                    elements.add(Character.toString(interior.charAt(i)));

                    //If next character outOfBounds
                    if(i == ((interior.length()) - 1)){
                        numbers.add(1);
                    }
                    //If next character is uppercase letter
                    else if(TestNextCharacter.testNextCharacter(Character.toString(interior.charAt((i + 1)))) == 1){
                        numbers.add(1);
                    }

                }
                //Lowercase Letter
                else{
                    String newElement = elements.get((elements.size() - 1)) + (Character.toString(interior.charAt(i)));
                    elements.remove((elements.size() - 1));
                    elements.add(newElement);

                    //If next character outOfBounds
                    if(i == ((interior.length()) - 1)){
                        numbers.add(1);
                    }
                    //If next character is uppercase letter
                    else if(TestNextCharacter.testNextCharacter(Character.toString(interior.charAt((i + 1)))) == 1){
                        numbers.add(1);
                    }

                }
            }

            System.out.println(elements);
            System.out.println(numbers);
            //Multiply numbers array and remove old numbers
            int initialSize = numbers.size();
            for(int i=0;i<initialSize;i++){
                numbers.add(numbers.get(i) * multiplier);
            }
            for(int i=0;i<initialSize;i++){
                numbers.remove(0);
            }

            //Concat and remove parenthesisas well as multiplier
            String interiorFinal = "";
            for(int i=0;i<initialSize;i++){
                interiorFinal += elements.get(i) + Integer.toString(numbers.get(i));
            }


            String beforeFirst = input.substring(0,firstIndex)  ;
            String afterSecond = input.substring((secondIndex + indexOver));
            String newString = beforeFirst + interiorFinal + afterSecond;

            System.out.println("Reached the end!");
            input = newString;
        }
        return input;
    }
}
