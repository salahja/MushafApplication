package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: الصف الأب المشترك لمهموز العين حسب شرط الفحص المشترك للمضارع والأمر والماضي
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
abstract class AbstractEinMahmouz : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        when (kov) {
            6, 9, 13, 22, 25, 29 -> return true
        }
        return false
    }
}