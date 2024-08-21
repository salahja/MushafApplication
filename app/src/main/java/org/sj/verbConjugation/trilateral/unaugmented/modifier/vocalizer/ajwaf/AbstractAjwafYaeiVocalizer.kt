package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: طريقة الفحص المشتركة للأجوف اليائي الماضي والمضارع والأمر
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
abstract class AbstractAjwafYaeiVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        // تطبيق القانون العام للفحص
        return conjugationResult.root!!.conjugation == "2" && (conjugationResult.kov == 18 || conjugationResult.kov == 19 || conjugationResult.kov== 20)
    }
}