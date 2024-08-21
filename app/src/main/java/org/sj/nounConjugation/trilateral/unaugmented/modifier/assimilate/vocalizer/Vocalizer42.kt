package org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.vocalizer

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
class Vocalizer42 : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
     override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ِيُ", "ِي")) // EX: (هذا الصّدِي )
        substitutions.add(SuffixSubstitution("ِيَ", "ِيَ")) // EX: (رأيتُ الصّدِيَ)
        substitutions.add(SuffixSubstitution("ِيِ", "ِي")) // EX: (مررت على الصّدِي)
        substitutions.add(InfixSubstitution("ِيٌ", "ٍ")) // EX: (هذا صَدٍ، )
        substitutions.add(InfixSubstitution("ِيٍ", "ٍ")) // EX: (مررت على صَدٍ، )
        substitutions.add(InfixSubstitution("ِيُو", "ُو")) // EX: (صَدُونَ، )
        substitutions.add(InfixSubstitution("ِيِي", "ِي")) // EX: (صَدِينَ )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val nounFormula = conjugationResult.nounFormula
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return (nounFormula == "فَعِل") && ((kov == 24) || (kov == 26) || ((kov == 28) && (noc == 4)))
    }
}