package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.PrefixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.AbstractEinMahmouz

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
class ImperativeMahmouz : AbstractEinMahmouz() {
     override val substitutions: MutableList<Substitution> = ArrayList()

    init {
        substitutions.add(PrefixSubstitution("ءِ", "إِ")) // EX: (إِدْ [وأد]، إِ [وأى])
        substitutions.add(PrefixSubstitution("ءُ", "أُ")) // EX: (أنتم أُوا)
        substitutions.add(InfixSubstitution("ءِ", "ئِ")) // EX: (امْئِي، انْئِمْ)
        substitutions.add(InfixSubstitution("ايْءَ", "ايئَ")) // EX: (ايْئَس)
        substitutions.add(InfixSubstitution("َيْءَ", "َيْئَ")) // EX: (يَيْئَس)
        substitutions.add(InfixSubstitution("يءَ", "يئَ")) // EX: (ايئب)
        substitutions.add(
            InfixSubstitution(
                "ْءَ",
                "ْأَ"
            )
        ) // EX: (اثْأَرْ، انْأَ[المتطرفة عروضاً لها حكم المتوسطة أصالة])
        substitutions.add(
            InfixSubstitution(
                "ْءُ",
                "ْؤُ"
            )
        ) // EX: (اضْؤُل، امْؤُ[المتطرفة عروضاً لها حكم المتوسطة أصالة])
    }


}