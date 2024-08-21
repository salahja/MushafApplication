package org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental

import org.sj.nounConjugation.TrilateralNounSubstitutionApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.vocalizer.PreMithalLafifVocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.vocalizer.WawiNakesLafifVocalizer
import org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.vocalizer.YaeiNakesLafifVocalizer
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
class Vocalizer {
    private val modifiers: MutableList<TrilateralNounSubstitutionApplier> = LinkedList()
    private val preMithalLafifVocalizer = PreMithalLafifVocalizer()

    init {
        modifiers.add(WawiNakesLafifVocalizer())
        modifiers.add(YaeiNakesLafifVocalizer())
    }

    fun apply(conjResult: ConjugationResult) {
        // تطبيق اعلال واحد اولا
        if (preMithalLafifVocalizer.isApplied(conjResult)) preMithalLafifVocalizer.apply(
            conjResult.finalResult as MutableList<Any>,
            conjResult.root!!
        )
        val iter: Iterator<TrilateralNounSubstitutionApplier> = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next() as IUnaugmentedTrilateralNounModificationApplier
            if (modifier.isApplied(conjResult)) {
                (modifier as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
    }
}