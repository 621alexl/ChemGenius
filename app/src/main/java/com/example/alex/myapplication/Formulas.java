package com.example.alex.myapplication;


/**
 * Author: Alex Li
 * HTML Text used to populate webviews in formula_alert
 */

public class Formulas {
    public static final String path="file:///android_asset/mathscribe/";

    public static final String formula_atomic = "<html><head>"
            + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
            + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
            + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
            + "</head><body>"
            + "$E=hv$" + "<br>" +
            "$c=vλ$" + "<br>" +
            "</body>";
    public static final String constant_atomic = "<html><head>"
            + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
            + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
            + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
            + "</head><body>" + "$E=\\text\"Energy\"$" + "<br>" +
            "$v=\\text\"Frequency\"$" + "<br>" +
            "$\\text\"Planck's Constant, \" h = 6.626 \\text\" x \" 10^(-34)$" + "<br>" +
            "$\\text\"Speed of light, \" c = 2.998 × 108 ms^{-1 }$" + "<br>" +
            "$\\text\"Avogadro’s number\" = 6.022 × 10^23 \\mol^{−1}$" + "<br>" +
            "$\\text\"Electron charge, \" e = −1.602 × 10^{−19} \\coulomb $" +
            "</body>";
    public static final String formula_equilibrium = "<html><head>"
            + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
            + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
            + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
            + "</head><body>"
            + "$K_c={[\\C]^c[\\D]^d}/{[\\A]^a[\\B]^b} \\where a \\A + b \\B \\text\" ⇄ \" c\\C + d\\D$" +
            "<br>" + "$K_p = {(P_C)^c(P_D)^d}/{(P_A)^a(P_B)^b}$" + "<br>" +
            "$K_a = {[H^{\\text\" +\"}][A^{-}]}/[\\HA]$" + "<br>" +
            "$K_b = {[\\OH^{-}][\\HB^{+}]}/[B]$" + "<br>" +
            "$K_w =[\\H^{+}][\\OH^{-}] = 1.0 \\text\" * \" 10^{-14} \\at 25^{∘}\\C = K_aK_b$" + "<br>" +
            "$\\pH = -\\log[\\H^{+}], \\pOH = -\\log[\\OH^{-}]$" + "<br>" +
            "$14 = \\pH + \\pOH$" + "<br>" +
            "$\\pH = \\p K_a + \\log{[A^{-}]/[\\HA]}$" + "<br>" +
            "$\\p K_a = -\\log K_a, \\p K_b = -\\log K_b$" +
            "</body>";
    public static final String constant_equilibrium = "<html><head>"
            + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
            + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
            + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
            + "</head><body>" + "$K_c \\text\" (molar concentrations)\"$" + "<br>" +
            "$K_p \\text\" (gas pressures)\"$" + "<br>" +
            "$K_a \\text\" (weak acid)\"$" + "<br>" +
            "$K_b \\text\" (weak base)\"$" + "<br>" +
            "$K_w \\text\" (water)\"$" + "<br>" +
            "</body>";

    public static final String formula_kinetics = "<html><head>"
            + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
            + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
            + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
            + "</head><body>" + "$\\ln [\\A]_t - \\ln[\\A]_0=-kt$" + "<br>" +
            "$1/[\\A]_t-1/[A]_0=kt$" + "<br>" +
            "$t_½=0.693/k$" +
            "</body>";
    public static final String constant_kinetics = "<html><head>"
            + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
            + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
            + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
            + "</head><body>" + "$k = \\text\"rate constant\"$" + "<br>" +
            "$t = \\time $" + "<br>" + "$t_½= \\text\"half life\"$"+
            "</body>";

    public static final String formula_gases = "<html><head>"
            + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
            + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
            + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
            + "</head><body>" + "$PV = nRT$" + "<br>" +
            "$P_A = P_\\total X_A, \\where X_A = \\text\"moles A\" / \\text\"total moles\"$" + "<br>" +
            "$P_\\total = P_A + P_B + P_C \\text\"+ . . .\"$" + "<br>" +
            "$n =m/\\bo M$" + "<br>" + "$K = °C + 273 $" + "<br>" + "$D = m/V$" + "<br>" +
            "$\\KE \\per \\molecule = 1/2 mv ^ 2$" + "<br>" +
            "$\\Molarity, M = \\text\"moles of solute\"$" + "<br>" + "$\\text\"per liter of solution\"$" +
            "<br>" + "$A = abc$" + "</body>";

    public static final String constant_gases = "<html><head>"
            + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
            + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
            + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
            + "</head><body>" + "$P = \\pressure$" + "<br>" +"$V = \\volume$"
            + "<br>" +"$T = \\temperature$" + "<br>" +"$n = \\text\"number of moles\"$" + "<br>"
            +"$m = \\mass$"+ "<br>" +"$\\bo M = \\molar \\mass$"+ "<br>"
            +"$D = \\density$"+ "<br>" +"$KE = \\kinetic \\energy$"+ "<br>" +"$v = \\velocity$" + "<br>" +
            "$A = \\absorbance$" + "<br>" + "$a = \\molar \\absorptivity$" + "<br>" +
            "$b = \\path \\length$" +
            "<br>" + "$c = \\concentration$" + "<br>" +
            "$\\Gas \\Constant, R = 0.008206 \\text\" L\" \\atm \\mol^{-1} \\K^{-1}$" + "<br>" +
            "$1 \\atm = 760 \\mmHg = 760 \\torr$" + "<br>" + "$\\STP = 273.15 \\text\" K and 1.0 atm\" $"
            + "<br>" + "</body>";

    public static final String formula_thermo = "<html><head>"
            + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
            + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
            + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
            + "</head><body>" + "$q = mcΔT$" + "<br>" +
            "$ΔS^{∘} = ΣS^{∘}\\products - ΣS^{∘}\\reactants$" + "<br>" +
            "$ΔH^{∘} = ΣΔH_f^{∘}\\products - ΣΔH_f^{∘}\\reactants$" + "<br>" +
            "$ΔG^{∘} = ΣΔG_f^{∘}\\products - ΣGH_f^{∘}\\reactants$" + "<br>" +
            "$ΔG^{∘} = ΔH^{∘}-TΔS^{∘}$" + "<br>" + "          " + "$= -RT\\ln K$" + "<br>"
            + "           " +"       $= -nFE^{\\text\" ∘\"}$" + "<br>" +
            "$I = q/t$" + "<br>"+
            "</body>";

    public static final String constant_thermo = "<html><head>"
            + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
            + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
            + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
            + "</head><body>" + "$q = \\heat$" + "<br>" + "$m = \\mass$" + "<br>" +
            "$c = \\text\"specific heat capacity\"$"
            + "<br>" + "$T = \\temperature$" + "<br>" + "$S^{∘} = \\text\"standard entropy\"$" + "<br>"
            + "$H^{∘} = \\text\"standard enthalpy\"$" + "<br>" + "$G^{∘} = \\text\"standard enthalpy\"$"
            + "<br>" + "$n = \\text\"number of moles\"$" + "<br>" +
            "$E^{∘} = \\text\"standard reduction potential\"$" + "<br>" +
            "$I = \\text\"current (amperes)\"$" + "<br>" +  "$q = \\text\"charge (couloumbs)\"$" + "<br>" +
            "$t = \\text\"time (seconds)\"$" + "<br>" +
            "$\\text\"Faraday's constant, \" F = \\text\"96,485\"$"
            +"<br>"+ "$\\text\"coulombs per mole of electrons\"$" + "<br>" +
            "$1 \\volt = {1 \\joule}/{1 \\coulomb}$" + "</body>";

    //getFormulas says which formulas to display based on the spinnettext selected
    public static String[] getFormulas(String spinnertext) {
        String[] texts = new String[2];
        switch (spinnertext){
            case ("Atomic Structure"):
                texts[0] = Formulas.formula_atomic;
                texts[1] = Formulas.constant_atomic;
                break;
            case ("Equilibrium"):
                texts[0] = Formulas.formula_equilibrium;
                texts[1] = Formulas.constant_equilibrium;
                break;
            case ("Kinetics"):
                texts[0] = Formulas.formula_kinetics;
                texts[1] = Formulas.constant_kinetics;
                break;
            case ("Gases, Liquids, and Solutions"):
                texts[0] = Formulas.formula_gases;
                texts[1] = Formulas.constant_gases;
                break;
            case ("Thermodynamics / Electrochemistry"):
                texts[0] = Formulas.formula_thermo;
                texts[1] = Formulas.constant_thermo;
                break;
            default:
                System.out.println("HEY");

        }
        return texts;
    }

}
