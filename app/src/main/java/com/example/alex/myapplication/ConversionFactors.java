package com.example.alex.myapplication;

/**
 * Authors: Ben Grass/Andreas Tsantilas
 * Conversion factors to be used in UnitConversion class
 */

//Conversion Factors

import java.util.*;

public class ConversionFactors{

    public static final Map<String, Double>lengthMap;
    static {
        lengthMap = new HashMap<String, Double>();
        lengthMap.put("Tm", 12.0);
        lengthMap.put("Gm", 9.0);
        lengthMap.put("Mm", 6.0);
        lengthMap.put("km", 3.0);
        lengthMap.put("hm", 2.0);
        lengthMap.put("Dm", 1.0);
        lengthMap.put("dm", -1.0);
        lengthMap.put("cm", -2.0);
        lengthMap.put("mm", -3.0);
        lengthMap.put("um", -6.0);
        lengthMap.put("nm", -9.0);
        lengthMap.put("pm", -12.0);
    }

    public static final Map<String, Double>massMap;
    static {
        massMap = new HashMap<String, Double>();
        massMap.put("Tg", 12.0);
        massMap.put("Gg", 9.0);
        massMap.put("Mg", 6.0);
        massMap.put("kg", 3.0);
        massMap.put("hg", 2.0);
        massMap.put("Dg", 1.0);
        massMap.put("dg", -1.0);
        massMap.put("cg", -2.0);
        massMap.put("mg", -3.0);
        massMap.put("ug", -6.0);
        massMap.put("ng", -9.0);
        massMap.put("pg", -12.0);
    }

    public static final Map<String, Double>volumeMap;
    static {
        volumeMap = new HashMap<String, Double>();
        volumeMap.put("TL", 12.0);
        volumeMap.put("GL", 9.0);
        volumeMap.put("ML", 6.0);
        volumeMap.put("kL", 3.0);
        volumeMap.put("hL", 2.0);
        volumeMap.put("DL", 1.0);
        volumeMap.put("dL", -1.0);
        volumeMap.put("cL", -2.0);
        volumeMap.put("mL", -3.0);
        volumeMap.put("uL", -6.0);
        volumeMap.put("nL", -9.0);
        volumeMap.put("pL", -12.0);
    }

    public static final Map<String, Double>pressureMap;
    static {
        pressureMap = new HashMap<String, Double>();
        pressureMap.put("atm", 1.0);
        pressureMap.put("Pa", 101325.01000043828);
        pressureMap.put("kPa", 101.32501000043828);
        pressureMap.put("Bar", 1.013250100004383);
        pressureMap.put("psi", 14.695950254304199);
        pressureMap.put("torr", 760.0);
        pressureMap.put("mmHg", 760.0);
    }

}