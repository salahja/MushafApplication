package org.sj.verbConjugation.trilateral.augmented

import org.sj.verbConjugation.AmrNahiAmr
import org.sj.verbConjugation.FaelMafool
import org.sj.verbConjugation.MadhiMudharay

class MazeedConjugationResult {

    val kov: Int
    val formulaNo: Int
    var originalResult: List<*>
    var root: AugmentedTrilateralRoot
        protected set

    //القائمة بعد  الادغام والاعلال والهمزة
    var finalResult: List<*>
    var madhiMudharay: MadhiMudharay = MadhiMudharay()
    var faelMafool: FaelMafool = FaelMafool()
    var amrandnahi: AmrNahiAmr = AmrNahiAmr()
        protected set
    //13 conjugated verbs
    constructor(kov: Int, formulaNo: Int, root: AugmentedTrilateralRoot, originalResult: List<*>) {
        this.kov = kov
        this.formulaNo = formulaNo
        this.originalResult = originalResult

        this.root = root
        finalResult = ArrayList(originalResult)





    }


}