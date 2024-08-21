package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: طريقة الفحص المشتركة للأجوف الواوي الماضي والمضارع والأمر
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
abstract class AbstractAjwafWawiVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        // تطبيق القانون العام للفحص
        return conjugationResult.root!!.conjugation == "1" && (conjugationResult.kov == 15 || conjugationResult.kov == 16 || conjugationResult.kov == 17)
    }
}