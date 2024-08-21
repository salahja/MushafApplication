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
class YaeiNakesLafifVocalizer : TrilateralNounSubstitutionApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ايَيٌ", "ايًا")) // EX: (هذا مُتَحايًا)
        substitutions.add(InfixSubstitution("ايَيًا", "ايًا")) // EX: (رأيتُ مُتَحايًا)
        substitutions.add(InfixSubstitution("ايَيٍ", "ايًا")) // EX: (مررتُ على مُتَحايًا)
        substitutions.add(SuffixSubstitution("ايَيُ", "ايَا")) // EX: (هذا المتحايا،)
        substitutions.add(SuffixSubstitution("ايَيَ", "ايَا")) // EX: (رأيتُ المتحايا ،  )
        substitutions.add(SuffixSubstitution("ايَيِ", "ايَا")) // EX: (مررتُ على المتحايا ، )
        substitutions.add(InfixSubstitution("ْيَيٌ", "ْيًا")) // EX: (هذا مُستحيًا)
        substitutions.add(InfixSubstitution("ْيَيًا", "ْيًا")) // EX: (رأيتُ مُستحيًا)
        substitutions.add(InfixSubstitution("ْيَيٍ", "ْيًا")) // EX: (مررتُ على مُستحيًا)
        substitutions.add(SuffixSubstitution("ْيَيُ", "ْيَا")) // EX: (هذا المستحيا،)
        substitutions.add(SuffixSubstitution("ْيَيَ", "ْيَا")) // EX: (رأيتُ المستحيا ،  )
        substitutions.add(SuffixSubstitution("ْيَيِ", "ْيَا")) // EX: (مررتُ على المستحيا ، )
        substitutions.add(InfixSubstitution("َيَيٌ", "َيًا")) // EX: (هذا مُزدَيًا)
        substitutions.add(InfixSubstitution("َيَيًا", "َيًا")) // EX: (رأيتُ مُزدَيًا)
        substitutions.add(InfixSubstitution("َيَيٍ", "َيًا")) // EX: (مررتُ على مُزدَيًا)
        substitutions.add(InfixSubstitution("َيَّيٌ", "َيًّا")) // EX: (هذا مُحَيًّا)
        substitutions.add(InfixSubstitution("َيَّيًا", "َيًّا")) // EX: (رأيتُ مُحَيًّا)
        substitutions.add(InfixSubstitution("َيَّيٍ", "َيًّا")) // EX: (مررتُ على مُحَيًّا)
        substitutions.add(SuffixSubstitution("َيَيُ", "َيَا")) // EX: (هذا المُزدَيا،)
        substitutions.add(SuffixSubstitution("َيَيَ", "َيَا")) // EX: (رأيتُ المُزدَيا ،  )
        substitutions.add(SuffixSubstitution("َيَيِ", "َيَا")) // EX: (مررتُ على المُزدَيا ، )
        substitutions.add(SuffixSubstitution("َيَّيُ", "َيَّا")) // EX: (هذا المُحيَّا،)
        substitutions.add(SuffixSubstitution("َيَّيَ", "َيَّا")) // EX: (رأيتُ المُحيَّا ،  )
        substitutions.add(SuffixSubstitution("َيَّيِ", "َيَّا")) // EX: (مررتُ على المُحيَّا ، )
        substitutions.add(InfixSubstitution("َيَة", "َاة")) // EX: (مُهْدَاةٌ،)
        substitutions.add(InfixSubstitution("َيَت", "َات")) // EX: (مُهْداتانِ، مُهداتَيْنِ)
        substitutions.add(InfixSubstitution("َيُو", "َوْ")) // EX: (مُهْدَونَ)
        substitutions.add(InfixSubstitution("َيِي", "َيْ")) // EX: (مُهْدَيْنَ)
        substitutions.add(SuffixSubstitution("َيُ", "َى")) // EX: (هذا المُهْدَى،)
        substitutions.add(SuffixSubstitution("َيَ", "َى")) // EX: (رأيتُ المُهْدَى ،  )
        substitutions.add(SuffixSubstitution("َيِ", "َى")) // EX: (مررتُ على المُهْدَى ، )
        substitutions.add(InfixSubstitution("َيٌ", "ًى")) // EX: (هذا مُهْدًى)
        substitutions.add(InfixSubstitution("َيًا", "ًى")) // EX: (رأيتُ مُهْدًى)
        substitutions.add(InfixSubstitution("َيٍ", "ًى")) // EX: (مررتُ على مُهْدًى)
    }

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        if (mazeedConjugationResult.root!!.c3 != 'ي') {
            return false
        }
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            24, 30 -> {
                when (formulaNo) {
                    1, 2, 3, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 2, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            25 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 2, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            26 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9, 10 -> return true
                }
                when (formulaNo) {
                    1, 2, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            27 -> {
                when (formulaNo) {
                    1, 2, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            28 -> {
                when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
                when (formulaNo) {
                    5, 7, 9 -> return true
                }
            }

            29 -> when (formulaNo) {
                5, 7, 9 -> return true
            }
        }
        return false
    }

   
}