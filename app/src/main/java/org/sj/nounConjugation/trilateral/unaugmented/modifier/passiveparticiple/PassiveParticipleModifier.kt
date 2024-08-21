package org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple

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
 * Description: تطبيق المعالجة الخاصة على اسم المفعول
 * ابتداء بالاعلال واخيرا الهمزة
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
class PassiveParticipleModifier private constructor() : IUnaugmentedTrilateralNounModifier {
    private val vocalizer = Vocalizer()
    private val mahmouz = Mahmouz()
    override fun build(
        root: UnaugmentedTrilateralRoot,
        kov: Int,
        conjugations: List<*>,
        formula: String
    ): ConjugationResult {
        val conjResult = ConjugationResult(kov, root!!, conjugations as List<*>, formula)
        vocalizer.apply(conjResult)
        mahmouz.apply(conjResult)

        NounLamAlefModifier.instance.apply(conjResult)
        NounSunLamModifier.instance.apply(conjResult)

            val toString = conjResult.finalResult.toString()
            val split = toString.split(",")
            conjResult.faelMafool.nomsinM = split[0]//sinM
            conjResult.faelMafool.nomdualM = split[2]//dualM
            conjResult.faelMafool.nomplurarM = split[4]//plurarM
            conjResult.faelMafool.accsinM = split[6]//sinM
            conjResult.faelMafool.accdualM = split[8]//dualM
            conjResult.faelMafool.accplurarlM = split[10]//plurarlM
            conjResult.faelMafool.gensinM = split[12]//sinM
            conjResult.faelMafool.gendualM = split[14]//dualM
            conjResult.faelMafool.genplurarM = split[16]//plurarM
            conjResult.faelMafool.nomsinF = split[1]//sinF
            conjResult.faelMafool.nomdualF = split[3]//dualF
            conjResult.faelMafool.nomplurarF = split[5]//plurarF
            conjResult.faelMafool.accsinF = split[7]//sinF
            conjResult.faelMafool.accdualF = split[9]//dualF
            conjResult.faelMafool.accplurarlF = split[11]//plurarlF
            conjResult.faelMafool.gensinF = split[13]//sinF
            conjResult.faelMafool.gendualF = split[15]//dualF
            conjResult.faelMafool.genplurarF = split[17]//plurarF

        return conjResult
    }

    companion object {
        val instance = PassiveParticipleModifier()
    }
}