package org.sj.verbConjugation.trilateral.unaugmented

import org.sj.verbConjugation.AmrNahiAmr
import org.sj.verbConjugation.FaelMafool
import org.sj.verbConjugation.IsmAlaMifaalun
import org.sj.verbConjugation.IsmAlaMifalatun
import org.sj.verbConjugation.IsmAlaMifalun
import org.sj.verbConjugation.IsmZarfMafalatun
import org.sj.verbConjugation.IsmZarfMafalun
import org.sj.verbConjugation.IsmZarfMafilun
import org.sj.verbConjugation.MadhiMudharay

open class ConjugationResult(
    var kov: Int, root: UnaugmentedTrilateralRoot, //13 conjugated verbs
    var originalResult: List<*>,
) {
    var root: UnaugmentedTrilateralRoot
        protected set

    //القائمة بعد  الادغام والاعلال والهمزة
    var finalResult: List<*>
    var madhiMudharay: MadhiMudharay = MadhiMudharay()
    var faelMafool: FaelMafool = FaelMafool()

    var zarfMafalun: IsmZarfMafalun = IsmZarfMafalun()
    var zarfMafilun: IsmZarfMafilun = IsmZarfMafilun()
    var zarfMafalatun: IsmZarfMafalatun =IsmZarfMafalatun()
    var alaMifalun: IsmAlaMifalun =IsmAlaMifalun()
    var alaMifalatun: IsmAlaMifalatun =IsmAlaMifalatun()
    var alaMifaalun: IsmAlaMifaalun =IsmAlaMifaalun()

        var amrandnahi: AmrNahiAmr= AmrNahiAmr()
        protected set

    init {
        originalResult = originalResult
        this.root = root
        finalResult = ArrayList(originalResult)



    }
}

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: يمثل نتيجة التصريف مع الجذر ونوع الجذر
 * يستعمل في المعالجة بعد التوليد
 *
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company: ALEXO
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */


/*
open class ConjugationResult(
    kov: Int,
    root: UnaugmentedTrilateralRoot,
    originalResult:List<*>,
) {
    var kov: Int
    lateinit var root: UnaugmentedTrilateralRoot

    //13 conjugated verbs
    var originalResult: MutableList<Any>
  //  val finalResult: MutableList<Any>
    //القائمة بعد  الادغام والاعلال والهمزة
    var finalResult: List<Any>

    init {
        this.kov = kov
        this.originalResult = originalResult as MutableList<Any>
        this.root = root!!
        finalResult = ArrayList(originalResult)
    }






    override fun toString(): String {
        return "ConjugationResult{" +
                "kov=" + kov +
                ", root!!=" + root!! +
                ", originalResult=" + originalResult +
                ", finalResult=" + finalResult +
                '}'
    }
}

 */