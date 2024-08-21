package org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration

import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.hamza.EinMahmouz
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.hamza.FaaMahmouz
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.hamza.LamMahmouz
import org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.hamza.NakesLafifMahmouz
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
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
class Mahmouz {
    private val modifiers: MutableList<IUnaugmentedTrilateralNounModificationApplier> = LinkedList()
    private val nakesLafifMahmouz = NakesLafifMahmouz()

    init {
        modifiers.add(FaaMahmouz())
        modifiers.add(EinMahmouz())
        modifiers.add(LamMahmouz())
    }

    fun apply(conjResult: ConjugationResult) {
        val iter: Iterator<IUnaugmentedTrilateralNounModificationApplier> = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next()
            if (modifier.isApplied(conjResult)) {
                (modifier as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
        //apply that after the 3 before
        if (nakesLafifMahmouz.isApplied(conjResult)) nakesLafifMahmouz.apply(
            conjResult.finalResult as MutableList<Any>,
            conjResult.root!!
        )
        //الأفعال الثلاثية المجردة المهموزة الفاء والمهموزة اللام (وهي: أبأ، أثأ، أجأ، أزأ، أكأ)
        if (conjResult.root!!.c3 == 'ء') (modifiers[2] as SubstitutionsApplier).apply(
            conjResult.finalResult as MutableList<Any>,
            conjResult.root!!
        )
    }
}