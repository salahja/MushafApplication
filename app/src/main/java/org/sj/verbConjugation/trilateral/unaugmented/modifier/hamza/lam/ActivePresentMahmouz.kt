package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.AbstractLamMahmouz

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
class ActivePresentMahmouz : AbstractLamMahmouz() {
     override val substitutions: MutableList<Substitution> = ArrayList()

    init {
        substitutions.add(SuffixSubstitution("َءُ", "َأُ")) // EX: (هو يبدأ)
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (هنَّ يبدأْنَ، لم يَبْدَأْ)
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (هما يبدأان، لن يبدأ)
        substitutions.add(InfixSubstitution("َءُ", "َؤُ")) // EX: (هم يبدؤون)
        substitutions.add(
            InfixSubstitution(
                "ءِ",
                "ئِ"
            )
        ) // EX: (أنتِ تبدئين، تَجْرُئين، تَبُوئين، تدائين، تَقِيئِينَ)
        substitutions.add(InfixSubstitution("ُءُ", "ُؤُ")) // EX: (يَجْرُؤُ)
        substitutions.add(InfixSubstitution("ُءَ", "ُؤَ")) // EX: (لن يَجْرُؤَ)
        substitutions.add(
            InfixSubstitution(
                "ُءْ",
                "ُؤْ"
            )
        ) // EX: (لم يجرُؤْ، لم يَبُؤْ، هنَّ يَبُؤْنَ)
        substitutions.add(InfixSubstitution("ِءْ", "ِئْ")) // EX: (لم يَقِئْ، هنّ يَقِئْنَ)
        substitutions.add(InfixSubstitution("اءُو", "اؤُو")) // EX: (هم يَداؤون)
        substitutions.add(InfixSubstitution("اءُن", "اؤُن")) // EX: (هم يشاؤُنَّ،)
        substitutions.add(InfixSubstitution("يءَا", "يئَا")) // EX: (هما يَقِيئَان)
        substitutions.add(InfixSubstitution("يءَن", "يئَن")) // EX: (هو يَقِيئَنَّ)
        substitutions.add(InfixSubstitution("يءُو", "يئُو")) // EX: (هم يَقِيئُونَ)
        substitutions.add(InfixSubstitution("يءُن", "يئُن")) // EX: (هم يجيئنَّ)
    }


}