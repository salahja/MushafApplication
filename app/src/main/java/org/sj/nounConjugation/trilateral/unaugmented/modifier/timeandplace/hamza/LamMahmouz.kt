package org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace.hamza

import org.sj.nounConjugation.trilateral.unaugmented.modifier.AbstractLamMahmouz
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
        substitutions.add(InfixSubstitution("َءًا", "َأً")) // EX: (ملجأً، )
        substitutions.add(InfixSubstitution("َءٌ", "َأٌ")) // EX: (ملجأٌ، )
        substitutions.add(InfixSubstitution("َءٍ", "َأٍ")) // EX: (ملجأٍ، )
        substitutions.add(SuffixSubstitution("َءُ", "َأُ")) // EX: (الملجأُ، )
        substitutions.add(SuffixSubstitution("َءَ", "َأَ")) // EX: (الملجأَ ، )
        substitutions.add(SuffixSubstitution("َءِ", "َأِ")) // EX: (الملجأِ ، )
        substitutions.add(InfixSubstitution("َءَا", "َآ")) // EX: (مَلْجَآن، )
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (مَكْمَأَة، مَلْجَأَيْن، )
        substitutions.add(InfixSubstitution("ِء", "ِئ")) // EX: (مَوْطِئ،)
    }


}