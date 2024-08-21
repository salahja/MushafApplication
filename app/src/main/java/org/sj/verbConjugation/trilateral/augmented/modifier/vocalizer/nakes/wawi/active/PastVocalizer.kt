package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class PastVocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "وَ",
                "ى"
            )
        ) // EX: (هو أدنى، سَمَّى ، حابى، انجلى، ارتضى، ارعَوَى، تسامى، تزكّى ، استرضى، احلولى)
        substitutions.add(
            InfixSubstitution(
                "وْت",
                "يْت"
            )
        ) // EX: (أنا أدنَيْتُ، سَمَّيْتُ ، حابَيْتُ، انجليتُ، ارتضيتُ، ارعَوَيْتُ، تساميت، تزكَّيْتُ،  استرضيت، احلوليتُ)
        substitutions.add(
            InfixSubstitution(
                "وْن",
                "يْن"
            )
        ) // EX: (أنا أدنَيْتُ، سَمَّيْتُ ، حابَيْتُ، انجليتُ، ارتضيتُ، ارعَوَيْتُ، تساميت، تزكَّيْتُ،  استرضيت، احلوليتُ)
        substitutions.add(
            InfixSubstitution(
                "وُو",
                "وْ"
            )
        ) // EX: (هم أدنَوْا، سَمَّوْا ، حابَوْا، انجَلَوْا، ارتضَوْا، ارعَوَوْا، تسامَوْا، تزكَّوْا ، استرضوا، احلولوا)
        substitutions.add(
            InfixSubstitution(
                "وَت",
                "ت"
            )
        ) // EX: (هي أدْنَتْ، سَمَّتْ ، حابَتْ، انْجَلَتْ، ارتضت، ارْعَوَتْ، تسامَتْ، تزكَّتْ ، استرضت، احلولت)
        substitutions.add(
            InfixSubstitution(
                "وَا",
                "يَا"
            )
        ) // EX: (هما أدنَيَا، سَمَّيَا ، حابَيَا، انْجَلَيَا، ارتضيا، ارعَوَيَا، تسامَيَا، تزكَّيَا ، استرضيا، احلوليا)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        if ((kov == 22 || kov == 23) && formulaNo == 4) return true
        if ((kov == 21 || kov == 23) && (formulaNo == 2 || formulaNo == 9)) return true
        if (kov == 23 && (formulaNo == 6 || formulaNo == 10)) return true
        when (kov) {
            21, 22, 23 -> when (formulaNo) {
                1, 3, 5, 7, 8 -> return true
            }
        }
        return false
    }
}