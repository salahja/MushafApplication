package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.vocalizer

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
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
class WawiNakesLafifVocalizer : TrilateralNounSubstitutionApplier(),
    IAugmentedTrilateralModifier {
      override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ِوُ", "ِي")) // EX: (هذا المُدْنِي،)
        substitutions.add(SuffixSubstitution("ِوَ", "ِيَ")) // EX: (رأيتُ المُدْنِيَ،  )
        substitutions.add(SuffixSubstitution("ِوِ", "ِي")) // EX: (مررتُ على المُدْنِي، )
        substitutions.add(InfixSubstitution("ِوٌ", "ٍ")) // EX: (هذا مُدْنٍ)
        substitutions.add(InfixSubstitution("ِوٍ", "ٍ")) // EX: (مررتُ على مُدْنٍ)
        substitutions.add(InfixSubstitution("ِوً", "ِيً")) // EX: (رأيتُ مُدْنِيًا)
        substitutions.add(
            InfixSubstitution(
                "ِوَ",
                "ِيَ"
            )
        ) // EX: (مُدْنِيَةٌ، مُدْنِيان، مُدْنِيتان، مُدْنِيات، )
        substitutions.add(InfixSubstitution("ِوُ", "ُ")) // EX: (مُدْنُونَ، )
        substitutions.add(InfixSubstitution("ِوِ", "ِ")) // EX: (مُدْنِينَ، )
    }

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        if (mazeedConjugationResult.root!!.c3 != 'و') return false
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            21 -> {
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 3, 4, 5, 7, 8 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
            }

            22 -> {
                when (formulaNo) {
                    1, 3, 4, 5, 7, 8 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
            }

            23 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
            }

            28 -> when (formulaNo) {
                1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
            }
        }
        return false
    }





}
