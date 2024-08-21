package org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate

import org.sj.nounConjugation.NounLamAlefModifier
import org.sj.nounConjugation.NounSunLamModifier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModifier
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: تطبيق المعالجة الخاصة على اسم الفاعل
 * ابتداء بالادغام ثم الاعلال واخيرا الهمزة
 *
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
class AssimilateModifier private constructor() : IUnaugmentedTrilateralNounModifier {
    private val substituter = Substituter()
    private val geminator = Geminator()
    private val vocalizer = Vocalizer()
    private val mahmouz = Mahmouz()
    override fun build(
        root: UnaugmentedTrilateralRoot,
        kov: Int,
        conjugations: List<*>,
        formula: String
    ): ConjugationResult {
        val conjResult = ConjugationResult(kov, root!!, conjugations as List<*>, formula)
        substituter.apply(conjResult.finalResult as MutableList<Any>, root!!)
        geminator.apply(conjResult.finalResult as MutableList<Any>, root!!)
        vocalizer.apply(conjResult)
        mahmouz.apply(conjResult)

        NounLamAlefModifier.instance.apply(conjResult)
        NounSunLamModifier.instance.apply(conjResult)
        return conjResult
    }

    companion object {
        val instance = AssimilateModifier()
    }
}