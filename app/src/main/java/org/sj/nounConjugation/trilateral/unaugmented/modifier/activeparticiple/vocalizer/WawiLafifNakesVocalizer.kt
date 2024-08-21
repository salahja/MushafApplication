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
class WawiLafifNakesVocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
     override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ِوٌ", "ٍ")) // EX: (هذا غازٍ)
        substitutions.add(InfixSubstitution("ِوً", "ِيً")) // EX: (رأيتُ غازِيًا)
        substitutions.add(InfixSubstitution("ِوٍ", "ٍ")) // EX: (مررتُ على غازٍ)
        substitutions.add(SuffixSubstitution("ِوُ", "ِي")) // EX: (هذا الغازِي، غازِي المدينة، )
        substitutions.add(SuffixSubstitution("ِوَ", "ِيَ")) // EX: (رأيتُ الغازيَ، غازِيَ المدينة، )
        substitutions.add(
            SuffixSubstitution(
                "ِوِ",
                "ِي"
            )
        ) // EX: (مررتُ على الغازي، غازِي المدينة، )
        substitutions.add(
            InfixSubstitution(
                "ِوَ",
                "ِيَ"
            )
        ) // EX: (غازِيَةٌ، غازِيانِ، غازِيَتَانِ، غازِياتٌ، )
        substitutions.add(InfixSubstitution("ِوُ", "ُ")) // EX: (غازُونَ، )
        substitutions.add(InfixSubstitution("ِوِ", "ِ")) // EX: (غازِينَ، )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        if (conjugationResult.root!!.c3 != 'و') return false
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        when (kov) {
            21 -> return noc == 1 || noc == 5
            22 -> return noc == 1 || noc == 3
            23 -> {
                when (noc) {
                    1, 3, 4, 5 -> return true
                }
                return noc == 4
            }

            28 -> return noc == 4
        }
        return false
    }
}