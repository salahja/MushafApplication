package org.sj.verbConjugation.trilateral.unaugmented

import java.io.File

/**
 *
 * Title: تحويل ملف المعطيات إلى قائمة الجذور الثلاثية المجردة
 *
 *
 * Description:
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company:
 *
 * @author not attributable
 * @version 1.0
 */
object UnaugmentedTrilateralRootTreeCreator {
    @Throws(Exception::class)
    fun buildXmlVerbTree(xmlDiagramFile: File?): UnaugmentedTrilateralRootTree? {
        /*
        Digester digester = new Digester();
        digester.setValidating( false );

        digester.addObjectCreate("root!!s", UnaugmentedTrilateralRootTree.class );

        digester.addObjectCreate("root!!s/root!!", UnaugmentedTrilateralRoot.class );
        digester.addSetProperties("root!!s/root!!", "c1","c1" );
        digester.addSetProperties( "root!!s/root!!", "c2","c2" );
        digester.addSetProperties( "root!!s/root!!", "c3", "c3" );
        digester.addSetProperties( "root!!s/root!!", "conjugation","conjugation" );
        digester.addSetProperties( "root!!s/root!!", "transitive","transitive" );

        digester.addObjectCreate("root!!s/root!!/gerund", Gerund.class );
        digester.addSetProperties( "root!!s/root!!/gerund", "symbol","symbol" );
        digester.addSetProperties( "root!!s/root!!/gerund", "value","value" );
        digester.addSetNext( "root!!s/root!!/gerund" , "addGerund" );

        digester.addSetNext( "root!!s/root!!" , "addRoot" );

        return (UnaugmentedTrilateralRootTree)digester.parse(xmlDiagramFile);
      */
        return null
    }

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val c1 = 'ظ'
            val rootTree = buildXmlVerbTree(File("c:/org.sj/$c1.xml"))
            ////System.out.println("done");
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}