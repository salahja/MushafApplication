package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: الصف الأب المشترك لمهموز اللام حسب شرط الفحص المشترك للمضارع والأمر والماضي
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
abstract class AbstractLamMahmouz : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        when (kov) {
            4, 7, 10, 16, 19 -> return true
        }
        return false
    }
}