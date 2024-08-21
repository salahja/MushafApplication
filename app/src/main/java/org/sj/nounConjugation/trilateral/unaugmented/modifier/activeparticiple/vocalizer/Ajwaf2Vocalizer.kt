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
class Ajwaf2Vocalizer : TrilateralNounSubstitutionApplier(),
    IUnaugmentedTrilateralNounModificationApplier {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("اوِءٌ", "اءٍ")) // EX: (هذا ناءٍ)
        substitutions.add(InfixSubstitution("اوِءً", "ائِيً")) // EX: (رأيتُ نائِياً)
        substitutions.add(InfixSubstitution("اوِءٍ", "اءٍ")) // EX: (مررتُ على ناءٍ)
        substitutions.add(SuffixSubstitution("اوِءُ", "ائِي")) // EX: (هذا النائِي، نائِي الـ، )
        substitutions.add(SuffixSubstitution("اوِءَ", "ائِيَ")) // EX: (رأيتُ النائيَ، نائِي الـ، )
        substitutions.add(SuffixSubstitution("اوِءِ", "ائِي")) // EX: (مررتُ على النائِي، نائِي الـ)
        substitutions.add(InfixSubstitution("اوِءَ", "ائِيَ")) // EX: (نائِيَةٌ، نائِيانِ،  )
        substitutions.add(InfixSubstitution("اوِءُ", "اؤُ")) // EX: (ناؤُونَ، )
        substitutions.add(InfixSubstitution("اوِءِ", "ائِ")) // EX: (نائِينَ، )
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return kov == 16 && (noc == 1 || noc == 4)
    }
}