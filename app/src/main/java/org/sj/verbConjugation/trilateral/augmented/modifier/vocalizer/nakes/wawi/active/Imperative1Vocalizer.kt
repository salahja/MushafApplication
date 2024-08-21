package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            InfixSubstitution(
                "ِوُو",
                "ُو"
            )
        ) // EX: (أنتم أدْنُوا، حابُوا، انجلُوا، ارتضُوا، ارعَوُوا، استرضُوا، احلَوْلُوا)
        substitutions.add(
            InfixSubstitution(
                "ِوُن",
                "ُن"
            )
        ) // EX: (أنتم أدْنُنَّ، حابُنَّ، انجلُنَّ، ارتضُنَّ، ارعَوُنَّ، استرضُنَّ، احلَوْلُنَّ)
        substitutions.add(
            SuffixSubstitution(
                "وْ",
                ""
            )
        ) // EX: (أدْنِ، حابِ، انجلِ، ارتضِ، ارعَوِ، استرضِ، احلَوْلِ)
        substitutions.add(
            InfixSubstitution(
                "وْن",
                "ين"
            )
        ) // EX: (أنتن أدنِينَ، حابِين، انجلِين، ارتضين، ارعوين، استرضين، احلولين)
        substitutions.add(
            InfixSubstitution(
                "ِوِ",
                "ِ"
            )
        ) // EX: (أنتِ أدْنِي، حابِي، انجلِي، ارتضي، ارعوي، استرضي، احلولي)
        substitutions.add(
            InfixSubstitution(
                "وَ",
                "يَ"
            )
        ) // EX: (أنتما أدْنِيا، حابِيا، انجلِيا، ارتضيا، ارعويا، استرضيا، احلوليا)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        if ((kov == 22 || kov == 23) && formulaNo == 4) return true
        if ((kov == 21 || kov == 23) && formulaNo == 9) return true
        if (kov == 23 && (formulaNo == 6 || formulaNo == 10)) return true
        when (kov) {
            21, 22, 23 -> when (formulaNo) {
                1, 3, 5 -> return true
            }
        }
        return false
    }
}