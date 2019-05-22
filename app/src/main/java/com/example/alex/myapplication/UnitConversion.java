package com.example.alex.myapplication;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Andrew Tsantilas
 * Unit Conversion utility library
 */

public class UnitConversion{
    public static final Map<String, Integer> categorymap = createMap();
    private static Map<String, Integer> createMap()
    {
        Map<String, Integer> catergorymap = new HashMap<>();
        catergorymap.put("Length", R.array.lengtharray);
        catergorymap.put("Volume", R.array.volumearray);
        catergorymap.put("Pressure", R.array.pressurearray);
        catergorymap.put("Mass", R.array.massarray);
        catergorymap.put("Temperature", R.array.temparray);
        return catergorymap;
    }


    public static double unitConversion(String unitA, double amount, String unitB, String category){
        double result = -1;

        if(unitA.equals(unitB)){
            result = amount;
        }
        else if(unitA.length() == 1 && !category.equals("Temperature")){
            if(unitA.equals("m")){
                result = amount * 1/Math.pow(10,ConversionFactors.lengthMap.get(unitB));
            }
            else if(unitA.equals("L")){
                result = amount * 1/Math.pow(10,ConversionFactors.volumeMap.get(unitB));
            }
            else if(unitA.equals("g")){
                result = amount * 1/Math.pow(10,ConversionFactors.massMap.get(unitB));
            }
        }
        else if(unitB.length() == 1 && !category.equals("Temperature")){
            if(unitB.equals("m")){
                result = amount * Math.pow(10,ConversionFactors.lengthMap.get(unitA));
            }
            else if(unitB.equals("L")){
                result = amount * Math.pow(10,ConversionFactors.volumeMap.get(unitA));
            }
            else if(unitB.equals("g")){
                result = amount * Math.pow(10,ConversionFactors.massMap.get(unitA));
            }
        }
        else{
            if(category.equals("Length")){
                //SI Conversion
                if(Character.toString(unitA.charAt(1)).equals("m")){
                    double toStandard = amount * (Math.pow(10,ConversionFactors.lengthMap.get(unitA)));
                    result = toStandard * 1/Math.pow(10,ConversionFactors.lengthMap.get(unitB));
                }
                //Metric to US
                else{

                }
            }
            else if(category.equals("Volume")){
                //SI Conversion
                if(Character.toString(unitA.charAt(1)).equals("L")){
                    double toStandard = amount * (Math.pow(10,ConversionFactors.volumeMap.get(unitA)));
                    result = toStandard * 1/Math.pow(10,ConversionFactors.volumeMap.get(unitB));
                }
                //Metric to US
                else{

                }
            }
            else if(category.equals("Pressure")){
                double toStandard = amount/(ConversionFactors.pressureMap.get(unitA));
                result = toStandard * (ConversionFactors.pressureMap.get(unitB));
            }
            else if(category.equals("Mass")){
                //SI Conversion
                if(Character.toString(unitA.charAt(1)).equals("g")){
                    double toStandard = amount * (Math.pow(10,ConversionFactors.massMap.get(unitA)));
                    result = toStandard * 1/Math.pow(10,ConversionFactors.massMap.get(unitB));
                }
                //Metric to US
                else{

                }
            }
            else if(category.equals("Temperature")){
                if(unitA.equals("K") && unitB.equals("C")){
                    result = amount - 273.0;
                }
                else if(unitA.equals("C") && unitB.equals("K")){
                    result = amount + 273.0;
                }
                else if(unitA.equals("K") && unitB.equals("F")){
                    result =  (1.8*(amount - 273.0) + 32.0);
                }
                else if(unitA.equals("F") && unitB.equals("K")){
                    result = (amount - 32.0)/1.8 + 273.0;
                }
                else if(unitA.equals("F") && unitB.equals("C")){
                    result = (amount - 32.0)/1.8;
                }
                else if(unitA.equals("C") && unitB.equals("F")){
                    result = 1.8*(amount) + 32.0;
                }
            }
        }

        return result;
    }
}