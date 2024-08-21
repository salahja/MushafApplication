package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present3Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "وُ",
                "ى"
            )
        ) // EX: (يُسْوَى، يُسَوَّى، يُساوَى، يُنْغَوَى، يُسْتَوَى، يُتَساوَى، يُتَسَوَّى، يُسْتَسْوَى، يُحْوَوَّى)
        substitutions.add(
            SuffixSubstitution(
                "َوَ",
                "َى"
            )
        ) // EX: (لن يُسْوَى، يُسَوَّى، يُساوَى، يُنْغَوَى، يُسْتَوَى، يُتَساوَى، يُتَسَوَّى، يُسْتَسْوَى، يُحْوَوَّى)
        substitutions.add(
            SuffixSubstitution(
                "وْ",
                ""
            )
        ) // EX: (لم يُسْوَ، يُسَوَّ، يُساوَ، يُنْغَوَ، يُسْتَوَ، يُتَساوَ، يُتَسَوَّ، يُسْتَسْوَ، يُحْوَوَّ)
        substitutions.add(
            InfixSubstitution(
                "وَا",
                "يَا"
            )
        ) // EX: (أنتما تُسْوَيانِ، تُسَوَّيان، تُساوَيان، تُنْغَوَيان، تُسْتَوَيان، تُتَساوَيان، تُتَسَوَّيان، تُسْتَسْوَيان، تُحْوَوَّيان)
        substitutions.add(
            InfixSubstitution(
                "وِي",
                "يْ"
            )
        ) // EX: (أنتِ تُسْوَيْنَ، تُسَوَّيْنَ، تُساوَيْنَ، تُنْغَوَيْنَ، تُسْتَوَيْنَ، تُتَساوَيْنَ، تُتَسَوَّيْنَ، تُسْتَسْوَيْنَ، تُحْوَوَّيْنَ)
        substitutions.add(
            InfixSubstitution(
                "وِن",
                "يِن"
            )
        ) // EX: (أنتِ تُسْوَيِنَّ، تُسَوَّيِنَّ، تُساوَيِنَّ، تُنْغَوَيِنَّ، تُسْتَوَيِنَّ، تُتَساوَيِنَّ، تُتَسَوَّيِنَّ، تُسْتَسْوَيِنَّ، تُحْوَوَّيِنَّ)
        substitutions.add(
            InfixSubstitution(
                "وُو",
                "وْ"
            )
        ) // EX: (أنتم تُسْوَوْنَ، تُسَوَّوْنَ، تُساوَوْنَ، تُنْغَوَوْنَ، تُسْتَوَوْنَ، تُتَساوَوْنَ، تُتَسَوَّوْنَ، تُسْتَسْوَوْنَ، تُحْوَوَّوْنَ)
        substitutions.add(
            InfixSubstitution(
                "وْن",
                "يْن"
            )
        ) // EX: (أنتن تُسْوَيْنَ، تُسَوَّيْنَ، تُساوَيْنَ، تُنْغَوَيْنَ، تُسْتَوَيْنَ، تُتَساوَيْنَ، تُتَسَوَّيْنَ، تُسْتَسْوَيْنَ، تُحْوَوَّيْنَ)
        substitutions.add(
            InfixSubstitution(
                "وَن",
                "يَن"
            )
        ) // EX: (تُسْوَيَنَّ، تُسَوَّيَنَّ، تُساوَيَنَّ، تُنْغَوَيَنَّ، تُسْتَوَيَنَّ، تُتَساوَيَنَّ، تُتَسَوَّيَنَّ، تُسْتَسْوَيَنَّ، تُحْوَوَّيَنَّ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        if (root!!.c2 == root!!.c3 && root!!.c3 == 'و' && kov == 28) {
            when (formulaNo) {
                1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
            }
        }
        return false
    }
}