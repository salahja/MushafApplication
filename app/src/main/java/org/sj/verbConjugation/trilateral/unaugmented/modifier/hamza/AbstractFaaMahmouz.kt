package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: الصف الأب المشترك لمهموز الفاء حسب شرط الفحص المشترك للمضارع والأمر والماضي
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
abstract class AbstractFaaMahmouz : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        when (kov) {
            3, 4, 5, 15, 18, 21, 24, 27 -> return true
        }
        return false
    }
}