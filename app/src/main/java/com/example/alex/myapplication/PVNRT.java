package com.example.alex.myapplication;

/**
 * Author: Ben Grass and Andrew Tsantilas
 * Calculates ideal gas law PV=nRT
 */

public class PVNRT{
    public static double pvnrt(double P, String PUnits, double V,
                               String VUnits, double n, String nUnits, double T, String TUnits){
        final double R = 0.0821;
        double result = 0;

        //P Conversion & Solution
        if(PUnits.equals("unknown")){
            //Convert Where Needed
            if(!VUnits.equals("L")){
                V = UnitConversion.unitConversion(VUnits, V, "L", "Volume");
            }
            else if(!TUnits.equals("K")){
                T = UnitConversion.unitConversion(TUnits, T, "K", "Temperature");
            }

            result = (n*R*T)/V;
        }

        //V Conversion & Solution
        else if(VUnits.equals("unknown")){
            //Convert Where Needed
            if(!PUnits.equals("atm")){
                P = UnitConversion.unitConversion(PUnits, P, "atm", "Pressure");
            }
            else if(!TUnits.equals("K")){
                T = UnitConversion.unitConversion(TUnits, T, "K", "Temperature");
            }

            result = (n*R*T)/P;
        }

        //n Conversion and Solution
        else if(nUnits.equals("unknown")){
            //Convert Where Needed
            if(!PUnits.equals("atm")){
                P = UnitConversion.unitConversion(PUnits, P, "atm", "Pressure");
            }
            else if(!VUnits.equals("L")){
                V = UnitConversion.unitConversion(VUnits, V, "L", "Volume");
            }
            else if(!TUnits.equals("K")){
                T = UnitConversion.unitConversion(TUnits, T, "K", "Temperature");
            }

            result = (P*V)/(R*T);
        }

        //T Conversion and Solution
        else if(TUnits.equals("unknown")){
            //Convert Where Needed
            if(!PUnits.equals("atm")){
                P = UnitConversion.unitConversion(PUnits, P, "atm", "Pressure");
            }
            else if(!VUnits.equals("L")){
                V = UnitConversion.unitConversion(VUnits, V, "L", "Volume");
            }

            result = (P*V)/(R*n);
        }

        return result;
    }
}