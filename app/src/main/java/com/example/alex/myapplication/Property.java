package com.example.alex.myapplication;

/**
 * Author: Alex Li
 * Used to make chemical property objects that are used with PropertyListAdapter to display PubChem
 * Chemical properties
 */

public class Property {
    private String name;
    private String result;

    public Property (String name, String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
