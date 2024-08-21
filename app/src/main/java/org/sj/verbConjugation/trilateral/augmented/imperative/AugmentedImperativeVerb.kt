package org.sj.verbConjugation.trilateral.augmented.imperative

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot

abstract class AugmentedImperativeVerb(
    var root:AugmentedTrilateralRoot,
    var lastDim: String?,
    var connectedPronoun: String?
) {

    abstract fun form(): String
    override fun toString(): String {
        return form()
    }
}
/*


public abstract class AugmentedImperativeVerb {
    protected AugmentedTrilateralRoot root!!;
    protected String lastDim;
    protected String connectedPronoun;

    public AugmentedImperativeVerb(AugmentedTrilateralRoot root!!, String lastDim, String connectedPronoun) {
        this.root!! = root!!;
        this.lastDim = lastDim;
        this.connectedPronoun = connectedPronoun;
    }

    public abstract String form();

    public String getConnectedPronoun() {
        return connectedPronoun;
    }

    public AugmentedTrilateralRoot getRoot() {
        return root!!;
    }

    public String getLastDpr() {
        return lastDim;
    }

    public String toString() {
        return form();
    }
}

 */