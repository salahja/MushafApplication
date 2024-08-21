package com.example.mushafconsolidated.Activity;




import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot;

import java.util.ArrayList;
import java.util.List;

public class NewConjugationResult {
    protected int kov;
    protected UnaugmentedTrilateralRoot root;

    //13 conjugated verbs
    protected List originalResult;
    //القائمة بعد  الادغام والاعلال والهمزة
    protected List finalResult;

    public NewConjugationResult( UnaugmentedTrilateralRoot root, List originalResult) {

        this.originalResult = originalResult;
        this.root = root;
        this.finalResult = new ArrayList(originalResult);
    }

    public List getFinalResult() {
        return finalResult;
    }


    public List getOriginalResult() {
        return originalResult;
    }

    public UnaugmentedTrilateralRoot getRoot() {
        return root;
    }
}
