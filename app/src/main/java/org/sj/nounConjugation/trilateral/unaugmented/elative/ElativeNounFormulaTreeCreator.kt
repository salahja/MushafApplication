package org.sj.nounConjugation.trilateral.unaugmented.elative

import java.io.File

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
object ElativeNounFormulaTreeCreator {
    @Throws(Exception::class)
    fun buildNounFormulaTree(xmlDiagramFile: File?): ElativeNounFormulaTree? {
        //todo xml
        /*
        Digester digester = new Digester();
        digester.setValidating( false );

        digester.addObjectCreate("formulas", ElativeNounFormulaTree.class );

        digester.addObjectCreate("formulas/formula", ElativeNounFormula.class );
        digester.addSetProperties("formulas/formula", "c1","c1" );
        digester.addSetProperties("formulas/formula", "c2","c2" );
        digester.addSetProperties("formulas/formula", "c3", "c3" );

        digester.addSetNext( "formulas/formula" , "addFormula" );

        return (ElativeNounFormulaTree)digester.parse(xmlDiagramFile);
     */
        return null
    }
}