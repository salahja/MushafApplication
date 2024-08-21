package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            InfixSubstitution(
                "ِوُو",
                "ُو"
            )
        ) // EX: (هم يُدْنُون، يحابون، ينجلون، يرتضون، يرعوون، يسترضون، يحلولون)
        substitutions.add(
            InfixSubstitution(
                "ِوُن",
                "ُن"
            )
        ) // EX: (هم يُدْنُنَّ، يحابُنَّ، ينجلُنَّ، يرتضُنَّ، يرعوُنَّ، يسترضُنَّ، يحلولُنَّ)
        substitutions.add(
            SuffixSubstitution(
                "وُ",
                "ي"
            )
        ) // EX: (هو يُدْنِي، يحابي، ينجلي، يرتضي، يرعَوِي، يسترضي، يحلولي)
        substitutions.add(
            SuffixSubstitution(
                "وْ",
                ""
            )
        ) // EX: (لم يُدْنِ، يحاب، ينجلِ، يرتضِ، يرعَوِ، يسترضِ، يحلولِ)
        substitutions.add(
            InfixSubstitution(
                "وْن",
                "ين"
            )
        ) // EX: (أنتن تُدنِينَ، تحابين، تنجلين، ترتضين، ترعوين، تسترضين، تحلولين)
        substitutions.add(
            InfixSubstitution(
                "ِوِ",
                "ِ"
            )
        ) // EX: (أنتِ تُدْنِينَ، تُحابِينَ، تنجلين، ترتضين، ترعوين، تسترضين، تحلولين)
        substitutions.add(
            InfixSubstitution(
                "وَ",
                "يَ"
            )
        ) // EX: (لن يُدْنِيَ، هما يُدنِيان، يحابيان، ينجليان، يرتضيان، يرعويان، يسترضيان، يحلوليان)
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