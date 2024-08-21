package com.example.mushafconsolidated.Activity;


import java.util.LinkedList;
import java.util.List;

public class SarfDictionaryNew {
    private SarfDictionaryNew() {
    }

    private static SarfDictionaryNew instance = new SarfDictionaryNew();

    public static SarfDictionaryNew getInstance() {
        return instance;
    }



    public List getUnaugmentedTrilateralRoots(String rootText) {
        char c1 = rootText.charAt(0);
        char c2 = rootText.charAt(1);
        char c3 = rootText.charAt(2);


        java.util.List result = new LinkedList();
        result.add(c1);
        result.add(c2);
        result.add(c3);


        return result;
    }





}
