package org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import java.util.LinkedList

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
class Ajwaf4Vocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
     override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ايِءٌ", "اءٍ")) // EX: (هذا جاءٍ)
        substitutions.add(InfixSubstitution("ايِءٍ", "اءٍ")) // EX: (مررتُ على جاءٍ)
        substitutions.add(SuffixSubstitution("ايِءُ", "ائِي")) // EX: (هذا الجائِي، )
        substitutions.add(SuffixSubstitution("ايِءِ", "ائِي")) // EX: (مررتُ على الجائِي )
        substitutions.add(InfixSubstitution("ايِءُ", "اؤُ")) // EX: (جاؤُونَ، )
        substitutions.add(InfixSubstitution("ايِءِ", "ائِ")) // EX: (جائِينَ، )
        substitutions.add(
            InfixSubstitution(
                "ايِء",
                "ائِي"
            )
        ) // EX: (جائيان، جائيَيْن، جائية، جائيًا، رأيتُ الجائِيَ)
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return kov == 19 && (noc == 2 || noc == 4)
    }
}