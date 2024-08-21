package org.sj.nounConjugation.trilateral.unaugmented.modifier.elative

import org.sj.nounConjugation.trilateral.unaugmented.elative.ElativeSuffixContainer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:
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
class AlkhairModifier {
    fun apply(conjResult: ConjugationResult) {
        val conjugations = conjResult.finalResult.toMutableList()
        //جدول تصريف اسم التفضيل المعرّف بـ (أل)
        if (ElativeSuffixContainer.Companion.instance.isDefinite) {
            for (i in 0..5) {
                conjugations[i] = "الْخَيْرُ"
            }
            for (i in 6..11) {
                conjugations[i] = "الْخَيْرَ"
            }
            for (i in 12..17) {
                conjugations[i] = "الْخَيْرِ"
            }
        } else if (ElativeSuffixContainer.Companion.instance
                .isAnnexed || ElativeSuffixContainer.Companion.instance.isIndefinite
        ) {
            for (i in 0..5) {
                conjugations[i] = "خَيْرُ"
            }
            for (i in 6..11) {
                conjugations[i] = "خَيْرَ"
            }
            for (i in 12..17) {
                conjugations[i] = "خَيْرِ"
            }
        } else {
            for (i in 0..5) {
                conjugations[i] = "خَيْرٌ"
            }
            for (i in 6..11) {
                conjugations[i] = "خَيْرًا"
            }
            for (i in 12..17) {
                conjugations[i] = "خَيْرٍ"
            }
        }
    }

    fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val root = conjugationResult.root!!
        return root!!.c1 == 'خ' && root!!.c2 == 'ي' && root!!.c3 == 'ر'
    }
}