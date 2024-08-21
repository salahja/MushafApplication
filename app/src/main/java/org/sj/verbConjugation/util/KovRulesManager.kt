package org.sj.verbConjugation.util

import com.example.utility.QuranGrammarApplication
import database.VerbDatabaseUtils
import database.entity.kov

class KovRulesManager private constructor() {
    var c1: String? = null
    var c2: String? = null
    var c3: String? = null
    var kov = 0

    // private QuadrilateralKovRuleList quadrilateralRulesList;
    var desc: String? = null
    var example: String? = null
    private val trilateralRulesLists: TrilateralKovRuleList? = null
    private var trilateralRulesList: ArrayList<kov?>? = ArrayList()

    init {
        val tri = "./src/main/resources/db/Trilateralkov.xml"
        val quad = "./src/main/resources/db/Quadrilateralkov.xml"
        try {
            //    trilateralRulesList = buildTrilateral(new File("/Trilateralkov.xml"));
            //   quadrilateralRulesList = buildQuadrilateral(new File("/Quadrilateralkov.xml"));
            trilateralRulesList = buildTrilateral()
            //   quadrilateralRulesList = buildQuadrilateral(new File(quad));
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @Throws(Exception::class)
    private fun buildTrilateral(): ArrayList<kov?>? {
        // Context context = DarkThemeApplication.getContext();
        val utils = VerbDatabaseUtils(QuranGrammarApplication.context)
        trilateralRulesList = utils.kov
        return trilateralRulesList
    }

    /**
     * the rules is sorted according to its application, so the first applied rule
     * will be returned its kov
     *
     * @param c1 char
     * @param c2 char
     * @param c3 char
     * @return int
     */
    fun getTrilateralKov(c1: Char, c2: Char, c3: Char): Int {
        val rule = getTrilateralKovRule(c1, c2, c3)
        return rule?.kov ?: -1
    }

    fun getTrilateralKovRule(c1: Char, c2: Char, c3: Char): TrilateralKovRule? {
        for (iter in trilateralRulesList!!) {
            this.c1 = iter!!.c1
            this.c2 = iter.c2
            this.c3 = iter.c3
            kov = iter.kov.toInt()
            example = iter.example
            desc = iter.rulename
            val cc1 = iter.c1[0]
            val cc2 = iter.c2[0]
            val cc3 = iter.c3[0]
            val ifrule = check(c1, c2, c3)
            if (ifrule) {
                val rule = TrilateralKovRule()
                rule.setC1(iter.c1)
                rule.setC2(iter.c2)
                rule.setC3(iter.c3)
                rule.kov = iter.kov.toInt()
                rule.example = iter.example
                rule.desc = iter.rulename
                return rule
            }
        }
        return null
    }

    /*
   public int getQuadrilateralKov(char c1, char c2, char c3, char c4) {
    QuadrilateralKovRule rule = getQuadrilateralKovRule(c1, c2, c3 ,c4);
    return rule!= null? rule.getKov(): -1;
  }

  public QuadrilateralKovRule getQuadrilateralKovRule(char c1, char c2, char c3, char c4) {
    Iterator iter = quadrilateralRulesList.getRules().iterator();
    while (iter.hasNext()) {
      QuadrilateralKovRule rule = (QuadrilateralKovRule) iter.next();
      if (rule.check(c1, c2, c3, c4))
        return rule;
    }
    return null;
  }


   */
    fun check(verbC1: Char, verbC2: Char, verbC3: Char): Boolean {
        val b1 = c1 == "?" || c1 == "null" || c1 == verbC1.toString() + ""
        var b2 = false
        var b3 = false
        if (c2.equals("c3", ignoreCase = true) && c3.equals("c2", ignoreCase = true)) {
            b3 = verbC2 == verbC3
            b2 = b3
        } else {
            b2 = c2 == "?" || c2 == "null" || c2 == verbC2.toString() + ""
            b3 = c3 == "?" || c3 == "null" || c3 == verbC3.toString() + ""
        }
        return b1 && b2 && b3
    }

    companion object {
        val instance = KovRulesManager()
        @JvmStatic
        fun main(args: Array<String>) {
            val c1 = 'ح'
            val c2 = 'ي'
            val c3 = 'ح'
            val c4 = 'ي'
            //   ////System.out.println(""+ KovRulesManager.getInstance().getQuadrilateralKov(c1,c2,c3,c4));
        }
    }
}