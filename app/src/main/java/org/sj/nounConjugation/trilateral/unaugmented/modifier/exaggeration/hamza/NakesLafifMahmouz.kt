package org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.hamza

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
class NakesLafifMahmouz : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            InfixSubstitution(
                "اءٌ",
                "اءٌ"
            )
        ) // EX: (غزّاءٌ، رمَّاءٌ، مِعطاءٌ، مِجناء، مِعواء، مِيفاء)
        substitutions.add(InfixSubstitution("اءً", "اءً")) // EX: (مِعطاءً ،  )
        substitutions.add(InfixSubstitution("اءٍ", "اءٍ")) // EX: (مِعطاءٍ ،   )
        substitutions.add(SuffixSubstitution("اءُ", "اءُ")) // EX: (المعطاءُ )
        substitutions.add(SuffixSubstitution("اءِ", "اءِ")) // EX: (المعطاءِ )
        substitutions.add(SuffixSubstitution("آَّءُ", "آَّءُ")) // EX: (مَآَّءُ )
        substitutions.add(SuffixSubstitution("آَّءِ", "آَّءِ")) // EX: (مَآَّءِ )
        substitutions.add(InfixSubstitution("آَّءُ", "آَّؤُ")) // EX: (مَآَّؤُونَ )
        substitutions.add(InfixSubstitution("آَّءِ", "آَّئِ")) // EX: (مَآَّئِينَ )
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (معطاؤونَ )
        substitutions.add(InfixSubstitution("اءِ", "ائِ")) // EX: (معطائِينَ )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val nounFormula = conjugationResult.nounFormula
        if (nounFormula != "فَعَّال") {
            return false
        }
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        when (kov) {
            21 -> return noc == 1 || noc == 5
            22 -> return noc == 1 || noc == 3
            23 -> {
                when (noc) {
                    1, 3, 4, 5 -> return true
                }
                when (noc) {
                    2, 3, 4 -> return true
                }
                return noc == 3 || noc == 4
            }

            24, 26 -> {
                when (noc) {
                    2, 3, 4 -> return true
                }
                return noc == 3 || noc == 4
            }

            25 -> return noc == 3 || noc == 4
            27, 29 -> return noc == 2
            28 -> return noc == 2 || noc == 4
            30 -> when (noc) {
                2, 4, 6 -> return true
            }
        }
        return false
    }
}