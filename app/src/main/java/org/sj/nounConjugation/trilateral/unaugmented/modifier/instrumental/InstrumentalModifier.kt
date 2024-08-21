package org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental

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
class InstrumentalModifier private constructor() : IUnaugmentedTrilateralNounModifier {
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
        geminator.apply(conjResult.finalResult as MutableList<Any>, root!!)
        vocalizer.apply(conjResult)
        mahmouz.apply(conjResult)

        NounLamAlefModifier.instance.apply(conjResult)
        NounSunLamModifier.instance.apply(conjResult)

        if (conjResult.nounFormula.equals("مِفْعَل")) {
            conjResult.alaMifalun.nomsinMifalun = conjResult.finalResult[0].toString()//sinM
            conjResult.alaMifalun.nomdualMifalun =
                conjResult.finalResult[2].toString()//[2]//dualM
            conjResult.alaMifalun.nomplurarMifalun = ""//[4]//plurarM
            conjResult.alaMifalun.accsinMifalun =
                conjResult.finalResult[6].toString()//[6]//sinM
            conjResult.alaMifalun.accdualMifalun =
                conjResult.finalResult[8].toString()//[8]//dualM
            conjResult.alaMifalun.accplurarlMifalun = ""
            conjResult.alaMifalun.gensinMifalun =
                conjResult.finalResult[12].toString()//[12]//sinM
            conjResult.alaMifalun.gendualMifalun =
                conjResult.finalResult[14].toString()//[14]//dualM
            conjResult.alaMifalun.genplurarMifalun = ""
        } else if (conjResult.nounFormula.equals("مِفْعَلَة")) {
            conjResult.alaMifalatun.nomsinMifalatun = conjResult.finalResult[1].toString()//sinM
            conjResult.alaMifalatun.nomdualMifalatun =
                conjResult.finalResult[3].toString()//[2]//dualM
            conjResult.alaMifalatun.nomplurarMifalatun = ""//[4]//plurarM
            conjResult.alaMifalatun.accsinMifalatun =
                conjResult.finalResult[7].toString()//[6]//sinM
            conjResult.alaMifalatun.accdualMifalatun =
                conjResult.finalResult[9].toString()//[8]//dualM
            conjResult.alaMifalatun.accplurarlMifalatun = ""
            conjResult.alaMifalatun.gensinMifalatun =
                conjResult.finalResult[13].toString()//[12]//sinM
            conjResult.alaMifalatun.gendualMifalatun =
                conjResult.finalResult[15].toString()//[14]//dualM
            conjResult.alaMifalatun.genplurarMifalatun = ""
        } else if (conjResult.nounFormula.equals("مِفْعَال")) {
            conjResult.alaMifaalun.nomsinMifaalun = conjResult.finalResult[0].toString()//sinM
            conjResult.alaMifaalun.nomdualMifaalun=
                conjResult.finalResult[2].toString()//[2]//dualM
            conjResult.alaMifaalun.nomplurarMifaalun= ""//[4]//plurarM
            conjResult.alaMifaalun.accsinMifaalun=
                conjResult.finalResult[6].toString()//[6]//sinM
            conjResult.alaMifaalun.accdualMifaalun=
                conjResult.finalResult[8].toString()//[8]//dualM
            conjResult.alaMifaalun.accplurarlMifaalun= ""
            conjResult.alaMifaalun.gensinMifaalun=
                conjResult.finalResult[12].toString()//[12]//sinM
            conjResult.alaMifaalun.gendualMifaalun=
                conjResult.finalResult[14].toString()//[14]//dualM
            conjResult.alaMifaalun.genplurarMifaalun= ""
        }







        return conjResult
    }

    companion object {
        val instance = InstrumentalModifier()
    }
}