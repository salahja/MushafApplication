package org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.vocalizer

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
class WawiNakesLafifVocalizer : TrilateralNounSubstitutionApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("َوَة", "َاة")) // EX: (مُدْناةٌ،)
        substitutions.add(InfixSubstitution("َوَت", "َات")) // EX: (مُدْناتان، مُدْناتَيْنِ)
        substitutions.add(InfixSubstitution("َوَا", "َيَا")) // EX: (مُدْنَيَانِ، مُدْنَيَاتٌ)
        substitutions.add(InfixSubstitution("َوَي", "َيَي")) // EX: (مُدْنَيَيْنِ،)
        substitutions.add(InfixSubstitution("َوُو", "َوْ")) // EX: (مُدْنَوْنَ)
        substitutions.add(InfixSubstitution("َوِي", "َيْ")) // EX: (مُدْنَيْنَ)
        substitutions.add(SuffixSubstitution("َوُ", "َى")) // EX: (هذا المُدْنَى،)
        substitutions.add(SuffixSubstitution("َوَ", "َى")) // EX: (رأيتُ المُدْنَى ،  )
        substitutions.add(SuffixSubstitution("َوِ", "َى")) // EX: (مررتُ على المُدْنَى ، )
        substitutions.add(InfixSubstitution("َوٌ", "ًى")) // EX: (هذا مُدْنًى)
        substitutions.add(InfixSubstitution("َوًا", "ًى")) // EX: (رأيتُ مُدْنًى)
        substitutions.add(InfixSubstitution("َوٍ", "ًى")) // EX: (مررتُ على مُدْنًى)
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