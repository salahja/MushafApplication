package org.sj.verbConjugation.trilateral.augmented.modifier.substituter.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.substituter.AbstractGenericSubstituter
import java.util.LinkedList

class GenericSubstituter1 : AbstractGenericSubstituter() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ثْتَ", "ثَّ")) // EX: (اثَّمَدَ، يَثَّمِدُ، اثَّمِدْ)
        substitutions.add(InfixSubstitution("دْتَ", "دَّ")) // EX: (ادَّخَرَ، يَدَّخِرُ، ادَّخِرْ)
        substitutions.add(InfixSubstitution("طْتَ", "طَّ")) // EX: (اطَّلَبَ، يَطَّلِبُ، اطَّلِبْ)
        substitutions.add(
            InfixSubstitution(
                "ذْتَ",
                "ذْدَ"
            )
        ) // EX: (اذْدَكَرَ، يَذْدَكِرُ، اذْدَكِرْ)
        substitutions.add(
            InfixSubstitution(
                "زْتَ",
                "زْدَ"
            )
        ) // EX: (ازْدَرَدَ، يَزْدَرِدُ، ازْدَرِدْ)
        substitutions.add(
            InfixSubstitution(
                "صْتَ",
                "صْطَ"
            )
        ) // EX: (اصْطَبَر، يَصْطَبِرُ، اصْطَبِرْ)
        substitutions.add(
            InfixSubstitution(
                "ضْتَ",
                "ضْطَ"
            )
        ) // EX: (اضْطَلَعَ، يَضْطَلِعُ، اضْطَلِعْ)
        substitutions.add(
            InfixSubstitution(
                "ظْتَ",
                "ظْطَ"
            )
        ) // EX: (اظْطَلَمَ، يَظْطَلِمُ، اظْطَلِمْ)
        substitutions.add(InfixSubstitution("وْتَ", "تَّ")) // EX: (اتَّصَلَ، يَتَّصِلُ، اتَّصِلْ)
        substitutions.add(InfixSubstitution("يْتَ", "تَّ")) // EX: (اتَّسَرَ، يَتَّسِرُ، اتَّسِرْ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        return mazeedConjugationResult.root!!.c1 == 'ث' && super.isApplied(mazeedConjugationResult)
    }
}