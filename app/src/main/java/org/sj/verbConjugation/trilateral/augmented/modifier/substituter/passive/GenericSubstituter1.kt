package org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.AbstractGenericSubstituter
import java.util.LinkedList

class GenericSubstituter1 : AbstractGenericSubstituter() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ثْت", "ثّ")) // EX: (اثُّمِدَ ، يُثَّمَدُ)
        substitutions.add(InfixSubstitution("دْت", "دّ")) // EX: (ادُّخِرَ)
        substitutions.add(InfixSubstitution("طْت", "طّ")) // EX: (اطُّلِبَ)
        substitutions.add(InfixSubstitution("ذْت", "ذْد")) // EX: (اذْدُكِرَ)
        substitutions.add(InfixSubstitution("زْت", "زْد")) // EX: (ازْدُرِدَ)
        substitutions.add(InfixSubstitution("صْت", "صْط")) // EX: (اصْطُبِر)
        substitutions.add(InfixSubstitution("ضْت", "ضْط")) // EX: (اضْطُلِعَ)
        substitutions.add(InfixSubstitution("ظْت", "ظْط")) // EX: (اظْطُلِمَ)
        substitutions.add(InfixSubstitution("يْت", "تّ")) // EX: (اتُّسِرَ)
        substitutions.add(InfixSubstitution("وْت", "تّ")) // EX: (اتُّصِلَ ، اتُّقِيَ، اتُّئِيَ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        return mazeedConjugationResult.root!!.c1 == 'ث' && super.isApplied(mazeedConjugationResult)
    }
}