package org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.hamza

import org.sj.nounConjugation.trilateral.augmented.modifier.AbstractLamMahmouz
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
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
class LamMahmouz : AbstractLamMahmouz() {
     override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("اءَ", "اءَ")) // EX: (مستاءَ)
        substitutions.add(SuffixSubstitution("اءُ", "اءُ")) // EX: (مستاءُ)
        substitutions.add(SuffixSubstitution("اءِ", "اءِ")) // EX: (مستاءِ)
        substitutions.add(InfixSubstitution("َءٌ", "َأٌ")) // EX: (مُجْزَأٌ، مُستَهْزَأٌ)
        substitutions.add(InfixSubstitution("َءًا", "َأً")) // EX: (مُجْزَأً، مُستَهْزَأً)
        substitutions.add(InfixSubstitution("اءًا", "اءً")) // EX: (مُداءً)
        substitutions.add(InfixSubstitution("َءٍ", "َأٍ")) // EX: (مُجْزَأٍ، مُستَهْزَأٍ)
        substitutions.add(InfixSubstitution("َءَا", "َآ")) // EX: (مُجْزَآنِ، مُستَهْزَآنَ)
        substitutions.add(InfixSubstitution("َءَي", "َأَي")) // EX: (مُجْزَأيْنِ، مُستَهْزَأَيْنَ)
        substitutions.add(SuffixSubstitution("َءُ", "َأُ")) // EX: (المجزَأُ، المُستَهْزَأُ)
        substitutions.add(SuffixSubstitution("َءَ", "َأَ")) // EX: (المجزَأَ، المُستَهْزَأَ)
        substitutions.add(SuffixSubstitution("َءِ", "َأِ")) // EX: (المجزَأِ، المُستَهْزَأِ)
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (المجزأَة، المستهزأة)
        substitutions.add(InfixSubstitution("ءُ", "ؤُ")) // EX: (مُجْزَؤُونَ، مُستَهْزَؤُونَ)
        substitutions.add(InfixSubstitution("ءِ", "ئِ")) // EX: (مُجْزَئِينَ، مُستَهْزَئِينَ)
    }

   
}