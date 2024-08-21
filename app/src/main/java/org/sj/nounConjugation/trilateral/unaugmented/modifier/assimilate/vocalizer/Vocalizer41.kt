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
class Vocalizer41 : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ِوُ", "ِي")) // EX: (هذا الرَّضِي )
        substitutions.add(SuffixSubstitution("ِوَ", "ِيَ")) // EX: (رأيتُ الرَّضِيَ )
        substitutions.add(SuffixSubstitution("ِوِ", "ِي")) // EX: (مررت على الرَّضِي )
        substitutions.add(InfixSubstitution("ِوٌ", "ٍ")) // EX: (هذا رَضٍ، )
        substitutions.add(InfixSubstitution("ِوٍ", "ٍ")) // EX: (مررت على رَضٍ، )
        substitutions.add(InfixSubstitution("ِوً", "ِيً")) // EX: (رأيت رَضِيًا، )
        substitutions.add(InfixSubstitution("ِوَ", "ِيَ")) // EX: (رَضِيان، رَضِيَةٌ، )
        substitutions.add(InfixSubstitution("ِوُو", "ُو")) // EX: (رَضُونَ، )
        substitutions.add(InfixSubstitution("ِوِي", "ِي")) // EX: (رَضِينَ )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val nounFormula = conjugationResult.nounFormula
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return nounFormula == "فَعِل" && kov == 23 && noc == 4
    }
}