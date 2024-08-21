package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.passive

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.PrefixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.AbstractFaaMahmouz
import java.util.LinkedList

class PastMahmouz : AbstractFaaMahmouz() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("أُءْيِC3ْ", "أُئِC3ْ")) // EX: (أُئِستُ )
        substitutions.add(InfixSubstitution("أُءْيِ", "أُئِي")) // EX: (أُئِيس )
        substitutions.add(InfixSubstitution("أُءْ", "أُو")) // EX: (أُوثِرَ، )
        substitutions.add(InfixSubstitution("اءْتِ", "ائْتِ")) // EX: (ائْتِلْتُ، )
        substitutions.add(InfixSubstitution("ءِ", "ئِ")) // EX: (انْئِيدََ، استُئمّ )
        substitutions.add(InfixSubstitution("ْءُ", "ْؤُ")) // EX: (انْؤُطِرَ، )
        substitutions.add(PrefixSubstitution("ءُ", "أُ")) // EX: (أُثِّرَ، أُوجِرَ، )
        substitutions.add(InfixSubstitution("اءْ", "اؤْ")) // EX: (اؤْتُمِرَ، )
        substitutions.add(InfixSubstitution("ُءُ", "ُؤُ")) // EX: (تُؤُوكِلَ، تُؤُكِّدَ، )
        substitutions.add(InfixSubstitution("ُءْ", "ُؤْ")) // EX: (استُؤْكِلَ، )
    }


}