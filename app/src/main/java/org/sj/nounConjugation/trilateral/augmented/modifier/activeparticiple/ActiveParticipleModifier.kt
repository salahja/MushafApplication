package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple

import org.sj.nounConjugation.NounLamAlefModifier
import org.sj.nounConjugation.NounSunLamModifier
import org.sj.nounConjugation.trilateral.augmented.modifier.Substituter
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.FormulaApplyingChecker
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.IFormulaApplyingChecker

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: ����� �������� ������ ��� ��� ������
 * ������ �������� �� ������� ������ ������
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
class ActiveParticipleModifier private constructor() {
    private val substituter = Substituter()
    private val geminator = Geminator()
    private val vocalizer = Vocalizer()
    private val mahmouz = Mahmouz()

    // AugmentedTrilateralModifierListener listener //todo
    fun build(
        root:AugmentedTrilateralRoot,
        kov: Int,
        formulaNo: Int,
        conjugations: List<*>,
        listener: Boolean
    ): MazeedConjugationResult {
        val conjResult = MazeedConjugationResult(
            kov, formulaNo,
            root!!, conjugations
        )
        substituter.apply(conjResult)
        geminator.apply(conjResult)
        var applyVocalization = true
        val result: Int = FormulaApplyingChecker.instance.check(root!!, formulaNo)
        if (result == IFormulaApplyingChecker.NOT_VOCALIZED) {
            applyVocalization = false
        } else if (result == IFormulaApplyingChecker.TWO_STATE) {
            applyVocalization = if (listener) //  if (listener == null)
                true else  //asking the listener to apply or not the vocaliztion
            //    applyVocalization = listener.doSelectVocalization();
                true
        }
        if (applyVocalization) {
            vocalizer.apply(conjResult)
        }
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
        val instance = ActiveParticipleModifier()
    }
}

