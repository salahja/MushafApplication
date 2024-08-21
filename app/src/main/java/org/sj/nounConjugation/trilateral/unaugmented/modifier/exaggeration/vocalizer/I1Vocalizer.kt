package org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.vocalizer

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
class I1Vocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ِوٌ", "ٍ")) // EX: (هذا زَهٍ)
        substitutions.add(InfixSubstitution("ِوً", "ِيً")) // EX: (رأيتُ زَهِيًا)
        substitutions.add(InfixSubstitution("ِوٍ", "ٍ")) // EX: (مررتُ على زَهٍ)
        substitutions.add(SuffixSubstitution("ِوُ", "ِي")) // EX: (هذا الزاهي، )
        substitutions.add(SuffixSubstitution("ِوَ", "ِيَ")) // EX: (رأيتُ الزاهيَ، )
        substitutions.add(SuffixSubstitution("ِوِ", "ِي")) // EX: (مررتُ على الزاهي، )
        substitutions.add(InfixSubstitution("ِوَ", "ِيَ")) // EX: (زَهِيَةٌ، زَهِيان )
        substitutions.add(InfixSubstitution("ِوُ", "ُ")) // EX: (زَهُون، )
        substitutions.add(InfixSubstitution("ِوِ", "ِ")) // EX: (زَهِينَ، )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val nounFormula = conjugationResult.nounFormula
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return nounFormula == "فَعِل" && kov == 23 && (noc == 1 || noc == 3 || noc == 5)
    }
}