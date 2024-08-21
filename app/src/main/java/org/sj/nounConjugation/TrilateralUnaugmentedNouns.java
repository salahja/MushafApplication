package org.sj.nounConjugation;

import org.sj.nounConjugation.trilateral.unaugmented.exaggeration.StandardExaggerationConjugator;
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot;

import java.util.List;

/**
 * <p>Title: Sarf Program</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ALEXO</p>
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class TrilateralUnaugmentedNouns {
    //������ ����� ������
    private final List standardExaggerations;
    private final List nonStandardExaggerations;
    //����� ������ �������
    private final List timeAndPlaces;
    //����� �����
    private final List standardInstrumentals;
    private final List nonStandardInstrumentals;
    //����� �������
    private final List elatives;
    //������ �������
    private final List assimilates;
    private UnaugmentedTrilateralRoot root;

    public TrilateralUnaugmentedNouns(UnaugmentedTrilateralRoot root) {
        standardExaggerations = StandardExaggerationConjugator.Companion.getInstance().getAppliedFormulaList(root);
        nonStandardExaggerations = org.sj.nounConjugation.trilateral.unaugmented.exaggeration.NonStandardExaggerationConjugator.Companion.getInstance().getAppliedFormulaList(root);
        timeAndPlaces = org.sj.nounConjugation.trilateral.unaugmented.timeandplace.TimeAndPlaceConjugator.Companion.getInstance().getAppliedFormulaList(root);
        standardInstrumentals = org.sj.nounConjugation.trilateral.unaugmented.instrumental.StandardInstrumentalConjugator.Companion.getInstance().getAppliedFormulaList(root);
        nonStandardInstrumentals = org.sj.nounConjugation.trilateral.unaugmented.instrumental.NonStandardInstrumentalConjugator.Companion.getInstance().getAppliedFormulaList(root);
        elatives = org.sj.nounConjugation.trilateral.unaugmented.elative.ElativeNounConjugator.Companion.getInstance().getAppliedFormulaList(root);
        assimilates = org.sj.nounConjugation.trilateral.unaugmented.assimilate.AssimilateAdjectiveConjugator.Companion.getInstance().getAppliedFormulaList(root);
    }

    public List getAssimilates() {
        return assimilates;
    }

    public List getElatives() {
        return elatives;
    }

    public List getNonStandardExaggerations() {
        return nonStandardExaggerations;
    }

    public List getNonStandardInstrumentals() {
        return nonStandardInstrumentals;
    }

    public UnaugmentedTrilateralRoot getRoot() {
        return root;
    }

    public List getStandardExaggerations() {
        return standardExaggerations;
    }

    public List getStandardInstrumentals() {
        return standardInstrumentals;
    }

    public List getTimeAndPlaces() {
        return timeAndPlaces;
    }
}

