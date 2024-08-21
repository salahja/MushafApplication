package org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace

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
class TimeAndPlaceModifier private constructor() : IUnaugmentedTrilateralNounModifier {
    private val geminator = Geminator()
    private val vocalizer = Vocalizer()
    private val mahmouz = Mahmouz()
    override fun build(
        root: UnaugmentedTrilateralRoot,
        kov: Int,
        conjugations: List<*>,
        formula: String,
                      ): ConjugationResult {
        val conjResult = ConjugationResult(kov, root!!, conjugations as List<*>, formula)
        geminator.apply(conjResult.finalResult as MutableList<Any>, root!!)
        vocalizer.apply(conjResult)
        mahmouz.apply(conjResult)

        NounLamAlefModifier.instance.apply(conjResult)
        NounSunLamModifier.instance.apply(conjResult)
        if (conjResult.nounFormula.equals("مَفْعَل")) {
            conjResult.zarfMafalun.nomsinMafalun = conjResult.finalResult[0].toString()//sinM
            conjResult.zarfMafalun.nomdualMafalun =
                conjResult.finalResult[2].toString()//[2]//dualM
            conjResult.zarfMafalun.nomplurarMafalun = ""//[4]//plurarM
            conjResult.zarfMafalun.accsinMafalun =
                conjResult.finalResult[6].toString()//[6]//sinM
            conjResult.zarfMafalun.accdualMafalun =
                conjResult.finalResult[8].toString()//[8]//dualM
            conjResult.zarfMafalun.accplurarlMafalun = ""
            conjResult.zarfMafalun.gensinMafalun =
                conjResult.finalResult[12].toString()//[12]//sinM
            conjResult.zarfMafalun.gendualMafalun =
                conjResult.finalResult[14].toString()//[14]//dualM
            conjResult.zarfMafalun.genplurarMafalun = ""
        } else if (conjResult.nounFormula.equals("مَفْعِل")) {
            conjResult.zarfMafilun.nomsinMafilun = conjResult.finalResult[0].toString()//sinM
            conjResult.zarfMafilun.nomdualMafilun =
                conjResult.finalResult[2].toString()//[2]//dualM
            conjResult.zarfMafilun.nomplurarMafilun = ""//[4]//plurarM
            conjResult.zarfMafilun.accsinMafilun =
                conjResult.finalResult[6].toString()//[6]//sinM
            conjResult.zarfMafilun.accdualMafilun =
                conjResult.finalResult[8].toString()//[8]//dualM
            conjResult.zarfMafilun.accpluralMafilun = ""
            conjResult.zarfMafilun.gensinMafilun =
                conjResult.finalResult[12].toString()//[12]//sinM
            conjResult.zarfMafilun.gendualMafilun =
                conjResult.finalResult[14].toString()//[14]//dualM
            conjResult.zarfMafilun.genplurarMafilun = ""
        } else if (conjResult.nounFormula.equals("مَفْعَلَة")) {
            conjResult.zarfMafalatun.nomsinMafalatun = conjResult.finalResult[1].toString()//sinM
            conjResult.zarfMafalatun.nomdualMafalatun =
                conjResult.finalResult[3].toString()//[2]//dualM
            conjResult.zarfMafalatun.nomplurarMafalatun = ""//[4]//plurarM
            conjResult.zarfMafalatun.accsinMafalatun =
                conjResult.finalResult[7].toString()//[6]//sinM
            conjResult.zarfMafalatun.accdualMafalatun =
                conjResult.finalResult[9].toString()//[8]//dualM
            conjResult.zarfMafalatun.accplurarlMafalatun = ""
            conjResult.zarfMafalatun.gensinMafalatun =
                conjResult.finalResult[13].toString()//[12]//sinM
            conjResult.zarfMafalatun.gendualMafalatun =
                conjResult.finalResult[15].toString()//[14]//dualM
            conjResult.zarfMafalatun.genplurarMafalatun = ""
        }


        return conjResult
    }

    companion object {
        val instance = TimeAndPlaceModifier()
    }
}