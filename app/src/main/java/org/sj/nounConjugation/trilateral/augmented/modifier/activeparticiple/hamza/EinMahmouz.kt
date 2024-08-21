package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.hamza

import org.sj.nounConjugation.trilateral.augmented.modifier.AbstractEinMahmouz
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
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
class EinMahmouz : AbstractEinMahmouz() {
      override  var substitutions: MutableList<InfixSubstitution> = LinkedList()


    init {
        substitutions.add(InfixSubstitution("اءٍ", "اءٍ")) // EX: (متراءٍ، متشاءٍ)
        substitutions.add(InfixSubstitution("ْءٍ", "ْءٍ")) // EX: (مُنْءٍ، مَمْءٍ)
        substitutions.add(InfixSubstitution("َءٍ", "َأٍ")) // EX: (مُنْفَأٍ)
        substitutions.add(InfixSubstitution("َءٍّ", "َأٍّ")) // EX: (مُرَأٍّ، مُتَرَأٍّ)
        substitutions.add(InfixSubstitution("ءُ", "ؤُ")) // EX: (مُنْؤُون، مُمْؤُون، مُراؤُون)
        substitutions.add(
            InfixSubstitution(
                "ءِ",
                "ئِ"
            )
        ) // EX: (مُشْئِمٌ، مُبْتَئِسٌ، مُتَسائِلٌ، مُسْتَرئِفٌ)
        substitutions.add(InfixSubstitution("ءِّ", "ئِّ")) // EX: (مُرَئِّسٌ، مُتَرَئِّفٌ)
        substitutions.add(InfixSubstitution("ءُّ", "ؤُّ")) // EX: (مُرَؤُّون)
        substitutions.add(InfixSubstitution("ْءَ", "ْأَ")) // EX: (مُجْأَلٌّ)
    }


}

