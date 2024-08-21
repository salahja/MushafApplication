package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.connected

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier
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
class ActivePast2Vocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ِوْ", "ِي"))
        substitutions.add(InfixSubstitution("ِوُ", "ُ"))
        substitutions.add(InfixSubstitution("ِيْ", "ِي"))
        substitutions.add(InfixSubstitution("ِيُ", "ُ"))
        substitutions.add(InfixSubstitution("ِوَ", "ِيَ"))
    }

   

    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return kov == 28 && noc == 4
    }
}