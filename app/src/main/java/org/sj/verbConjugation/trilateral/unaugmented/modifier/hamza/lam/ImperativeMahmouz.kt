package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
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
class ImperativeMahmouz : AbstractLamMahmouz() {
      override val substitutions: MutableList<InfixSubstitution> = ArrayList()

    init {
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (ابدَأْ، دَأَ [داء]، شَأْ [شاء])
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (ابدَأَا)
        substitutions.add(InfixSubstitution("َءُ", "َؤُ")) // EX: (ابدَؤُوا)
        substitutions.add(
            InfixSubstitution(
                "ءِ",
                "ئِ"
            )
        ) // EX: (ابدَئِي، شَئِنَّ، اجرُئِي، بُوئِي، قِيئِي)
        substitutions.add(InfixSubstitution("ُءُ", "ُؤُ")) // EX: (اجرُؤُوا)
        substitutions.add(InfixSubstitution("ُءَ", "ُؤَ")) // EX: (اجرُؤَا)
        substitutions.add(InfixSubstitution("ُءْ", "ُؤْ")) // EX: (اجرُؤْ، بُؤْ، بُؤْنَ، بُؤْنانِّ)
        substitutions.add(InfixSubstitution("يءَ", "يئَ")) // EX: (قِيئَا)
        substitutions.add(InfixSubstitution("يءُ", "يئُ")) // EX: (قِيئُوا)
        substitutions.add(InfixSubstitution("اءِ", "ائِ")) // EX: (شائِي)
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (شاؤُوا)
        substitutions.add(InfixSubstitution("ِء", "ِئ")) // EX: (قِئْ، قِئْنَ، قِئُنَّ)
    }

   
}