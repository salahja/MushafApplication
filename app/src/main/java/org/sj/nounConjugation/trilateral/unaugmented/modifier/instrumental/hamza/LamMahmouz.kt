package org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.hamza

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
        substitutions.add(SuffixSubstitution("َءُ", "َأُ")) // EX: (المِقْرأُ)
        substitutions.add(SuffixSubstitution("َءِ", "َأِ")) // EX: (المِقْرأِ)
        substitutions.add(InfixSubstitution("اءًا", "اءً")) // EX: (مئثاءً)
        substitutions.add(InfixSubstitution("َءٌ", "َأٌ")) // EX: (مِفْقَأٌ)
        substitutions.add(InfixSubstitution("َءًا", "َأً")) // EX: (مِفْقَأً)
        substitutions.add(InfixSubstitution("َءٍ", "َأٍ")) // EX: (مِفْقَأٍ)
        substitutions.add(InfixSubstitution("َءَا", "َآ")) // EX: (مِفْقآن)
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (مِفقأيْن، مِفقأة، مِفقأتان)
        substitutions.add(InfixSubstitution("ِء", "ِئ")) // EX: (قارئة)
    }


}