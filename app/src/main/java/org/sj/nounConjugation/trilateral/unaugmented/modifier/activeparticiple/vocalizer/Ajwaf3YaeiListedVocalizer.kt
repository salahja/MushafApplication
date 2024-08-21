package org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer

import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.AbstractAjwafYaeiListedVocalizer
import java.util.LinkedList

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:فحص الأجوف حسب قائمة
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
class Ajwaf3YaeiListedVocalizer : AbstractAjwafYaeiListedVocalizer(),
    IUnaugmentedTrilateralNounModificationApplier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ايِ", "ائِ")) // EX: (بائِعٌ)
    }




    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        return super.isApplied(conjugationResult)
    }

    companion object {
        protected var appliedProunounsIndecies: MutableList<String> = ArrayList(18)

        init {
            for (i in 0..17) {
                appliedProunounsIndecies.add((i + 1).toString() + "")
            }
        }
    }
}