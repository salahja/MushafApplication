package org.sj.nounConjugation.trilateral.unaugmented.instrumental

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
object XmlNonStandardInstrumentalNounFormulaTreeCreator {
    @Throws(Exception::class)
    fun buildNounFormulaTree(xmlDiagramFile: File?): XmlNonStandardInstrumentalNounFormulaTree? {
        /*
            Digester digester = new Digester();
        digester.setValidating( false );

        digester.addObjectCreate("formulas", XmlNonStandardInstrumentalNounFormulaTree.class );

        digester.addObjectCreate("formulas/formula", XmlNonStandardInstrumentalNounFormula.class );
        digester.addSetProperties("formulas/formula", "c1","c1" );
        digester.addSetProperties("formulas/formula", "c2","c2" );
        digester.addSetProperties("formulas/formula", "c3", "c3" );
        digester.addSetProperties("formulas/formula", "form1", "form1" );
        digester.addSetProperties("formulas/formula", "form2", "form2" );


        digester.addSetNext( "formulas/formula" , "addFormula" );

        return (XmlNonStandardInstrumentalNounFormulaTree)digester.parse(xmlDiagramFile);
        */
        return null
    }
}