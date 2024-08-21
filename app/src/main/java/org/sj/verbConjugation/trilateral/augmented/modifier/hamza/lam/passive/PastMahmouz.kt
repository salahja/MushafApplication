package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.AbstractLamMahmouz
import java.util.LinkedList

class PastMahmouz : AbstractLamMahmouz() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("يءَ", "يءَ")) // EX: (أُسِيءَ، )
        substitutions.add(InfixSubstitution("يء", "يئ")) // EX: (أسِيئَا، أُسِيئُوا)
        substitutions.add(InfixSubstitution("ُءْ", "ُؤْ")) // EX: (استُؤْتُ، )
        substitutions.add(
            InfixSubstitution(
                "ِء",
                "ِئ"
            )
        ) // EX: (أُجْزِئَ، كُوفِئَ، انْفُقِئَ، ابتُدِئَ، تُدُورِئَ، استُهْزِئَ، احْزُوزِئَ، )
        substitutions.add(InfixSubstitution("ِّء", "ِّئ")) // EX: (جُزِّئَ، تُخُبِّئَ، )
    }


}