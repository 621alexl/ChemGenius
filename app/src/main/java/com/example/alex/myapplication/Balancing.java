package com.example.alex.myapplication;

/**
 * Author: Ben Grass
 * Utility class
 * Balances chemical equation
 * This one method is more than 250 lines
 * RIP Ben
 */

//Balancing

import java.util.*;

public class Balancing{
    public static String balance(String formula){

        //Variable Declaration
        Scanner myScanner = new Scanner(System.in);
        List<String> reactants = new ArrayList<String>();//All molecules on reactants side
        List<String> products = new ArrayList<String>();//All molecules on products side
        List<String> totalArray = new ArrayList<String>();//All molecules on products side
        List<String> uniqueElements = new ArrayList<String>();//All unique elements side
        String uniqueElementsString = "";

        String[] noSpaces = formula.split(" ");
        formula = "";

        for(int i = 0; i < noSpaces.length; i++){
            formula+=noSpaces[i];
        }

        System.out.println("NO SPACES: " + formula);//WORKS
        String[] inHalf = formula.split("=");

        String productsString = inHalf[1];
        String reactantsString = inHalf[0];

        System.out.println("productsString NO EQUALS: " + productsString);//WORKS
        System.out.println("reactantsString NO EQUALS: " + reactantsString);//WORKS

        String[] productsArray = productsString.split("\\+");
        String[] reactantsArray = reactantsString.split("\\+");

        reactantsString = "";

        for(String s: reactantsArray){
            reactants.add(s);
            reactantsString+=s;
            totalArray.add(s);
        }

        for(String s: productsArray){
            products.add(s);
            totalArray.add(s);
        }

        String newFormula = "";

        for(String s: totalArray){
            newFormula += s;
        }
        System.out.println("NEW FORMULA: " + newFormula);//
        Analyze.analyze(newFormula);

        for(int i=0; i<Analyze.getElementsLength(); i++){
            if(i==0){
                uniqueElements.add(Analyze.getFinalElements(i));
                uniqueElementsString += Analyze.getFinalElements(i);
            }
            else{
                if(uniqueElementsString.indexOf(Analyze.getFinalElements(i)) == -1){
                    uniqueElements.add(Analyze.getFinalElements(i));
                    uniqueElementsString += Analyze.getFinalElements(i);
                }
            }
        }

        System.out.println("Products: " + products);
        System.out.println("Reactants: " + reactants);
        System.out.println();
        System.out.println("Unique Elements: " + uniqueElements);
        System.out.println("Total Array: " + totalArray);
        System.out.println();
        int matrixRows = uniqueElements.size();
        System.out.println("Matrix Rows: " + matrixRows);
        int matrixColumns = totalArray.size();
        System.out.println("Matrix Columns: " + matrixColumns);

        //Values in Matrix
        List<Integer> matrixValues = new ArrayList<Integer>();//ints in matrix

        for(int i=0; i<uniqueElements.size(); i++){
            for(int j=0; j<totalArray.size(); j++){
                if(totalArray.get(j).indexOf(uniqueElements.get(i)) == -1){
                    matrixValues.add(0);
                }
                else{
                    int indexOfElement = totalArray.get(j).indexOf(uniqueElements.get(i));
                    String elementalFormula = totalArray.get(j).substring(indexOfElement);
                    System.out.println("Before: " + elementalFormula);
                    if(elementalFormula.length() == 1){
                        matrixValues.add(1);
                        System.out.println("1 from length is 1");
                    }
                    else if(elementalFormula.length() == 2 && uniqueElements.get(i).length() == 2){
                        matrixValues.add(1);
                        System.out.println("1 from length is 2");
                    }
                    else{
                        int lengthOfElement = uniqueElements.get(i).length();
                        elementalFormula = elementalFormula.substring(lengthOfElement);
                        System.out.println(totalArray.get(j) + " After: " + elementalFormula);
                        boolean moreNumbers = true;
                        int characterIndex = 0;
                        String currentNumber = "";
                        int number = 0;

                        if((TestNextCharacter.testNextCharacter(Character.toString(elementalFormula.charAt(0)))) == 1){
                            matrixValues.add(1);
                            moreNumbers = false;
                        }

                        while(moreNumbers == true){

                            if(characterIndex > (elementalFormula.length()-1)){
                                number = Integer.parseInt(currentNumber);
                                matrixValues.add(number);
                                moreNumbers = false;
                                break;
                            }
                            else{
                                String character = Character.toString(elementalFormula.charAt(characterIndex));
                                int typeOf = TestNextCharacter.testNextCharacter(character);
                                if(typeOf == 1){
                                    number = Integer.parseInt(currentNumber);
                                    matrixValues.add(number);
                                    moreNumbers = false;
                                    break;
                                }
                                else{
                                    currentNumber+=character;
                                    characterIndex += 1;
                                }
                            }

                        }

                    }
                }
            }
        }
        System.out.println(matrixValues);
        //Format Matrix
        double[][] matrix = new double[matrixRows][matrixColumns];
        int indexInValues = 0;
        for(int i=0; i < matrixRows; i++){
            for(int j=0; j < matrixColumns; j++){
                matrix[i][j] = matrixValues.get(indexInValues);
                indexInValues++;
            }
        }

        matrix = Matrix.rref(matrix);


        double[] answers = new double[(matrixRows+1)];

        for(int i=0; i < matrixRows; i++){
            for(int j=0; j < matrixColumns; j++){
                if(j == (matrixColumns-1)){
                    answers[i] = Math.abs(matrix[i][j]);
                }
            }
        }

        answers[matrixRows] = 1;

        System.out.println();
        System.out.println("Matrix Answers: ");
        for(int i=0; i < answers.length; i++){
            System.out.println(answers[i]);
        }


        //Format Answers to eliminate decimals
        double smallest = -1;

        //Divide by smallest
        for(int i=0; i < answers.length; i++){
            if(i==0){
                smallest = answers[i];
            }
            else{
                if(answers[i] < smallest){
                    smallest = answers[i];
                }
            }
        }

        for(int i=0; i < answers.length; i++){
            answers[i] /= smallest;
            System.out.println(answers[i]);
        }

        /*
        boolean clean = false;

        while(clean == false){
            boolean wholeNumbers = true;
            double decimal = 1;

            for(int i=0; i < answers.length; i++){
                if((answers[i] % 1) != 0){
                    System.out.println("Not Clean!");
                    decimal = answers[i] % 1;
                    wholeNumbers = false;
                }
            }

            if(wholeNumbers == true){
                System.out.println("Clean!");
                clean = true;
                break;
            }
            else{
                for(int i=0; i < answers.length; i++){
                    answers[i] *= (1/decimal);
                }
            }
        }
        */

        //Formation of Final Balanced Equation
        List<String> finalReactants = new ArrayList<String>();

        for(int i=0; i < reactants.size(); i++){
            String coefficient = Integer.toString((int) answers[i]);
            String molecule = reactants.get(i);
            String finalMolecule = "";
            if(coefficient.equals("1")){
                finalMolecule = molecule;
            }
            else{
                finalMolecule = coefficient + molecule;
            }
            finalReactants.add(finalMolecule);
        }

        List<String> finalProducts = new ArrayList<String>();

        for(int i=0; i < products.size(); i++){
            String coefficient = Integer.toString((int) answers[(i + reactants.size())]);
            String molecule = products.get(i);
            String finalMolecule = "";
            if(coefficient.equals("1")){
                finalMolecule = molecule;
            }
            else{
                finalMolecule = coefficient + molecule;
            }
            finalProducts.add(finalMolecule);
        }

        String finalReactantsString = "";
        String finalProductsString = "";

        for(int i=0; i < finalReactants.size(); i++){
            if(i == (finalReactants.size() - 1)){
                finalReactantsString += finalReactants.get(i);
            }
            else{
                finalReactantsString += (finalReactants.get(i) + " + ");
            }
        }

        for(int i=0; i < finalProducts.size(); i++){
            if(i == (finalProducts.size()-1)){
                finalProductsString += finalProducts.get(i);
            }
            else{
                finalProductsString += (finalProducts.get(i) + " + ");
            }
        }

        String finalString = finalReactantsString + " -> " + finalProductsString;
        return finalString;
    }
}