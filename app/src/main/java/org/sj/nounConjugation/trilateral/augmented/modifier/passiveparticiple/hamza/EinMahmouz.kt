package org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.hamza

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
        substitutions.add(InfixSubstitution("وْءَ", "وْءَ")) // EX: (مُسْتَوْءَل)
        substitutions.add(InfixSubstitution("يْءَ", "يْئَ")) // EX: (مُسْتَيْئَس)
        substitutions.add(InfixSubstitution("ْءً", "ْأً")) // EX: (مُنْأًى، مُمْأًى)
        substitutions.add(InfixSubstitution("َءً", "َأً")) // EX: (مُنْفَأًى)
        substitutions.add(InfixSubstitution("ْءَا", "ْآ")) // EX: (مُنْآة، مُمْآة)
        substitutions.add(InfixSubstitution("َءَا", "َآ")) // EX: (مُنْفَآة)
        substitutions.add(
            InfixSubstitution(
                "ْءَ",
                "ْأَ"
            )
        ) // EX: (مُشْأَمٌ، مُبْتَأَسٌ، مُسْتَرْأَفٌ، مُجْأَلٌّ)
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (مُنْذَأج)
        substitutions.add(InfixSubstitution("َءَّا", "َآَّ")) // EX: (مُرَآَّة)
        substitutions.add(InfixSubstitution("َءَّ", "َأَّ")) // EX: (مُرَأَّسٌ، مُتَرَأَّفٌ)
        substitutions.add(InfixSubstitution("َءًّ", "َأًّ")) // EX: (مُرَأًّى)
    }

   
}
