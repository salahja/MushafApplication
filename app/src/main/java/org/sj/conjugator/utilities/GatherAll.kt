package org.sj.conjugator.utilities

import org.sj.conjugator.activity.SystemConstants
import org.sj.nounConjugation.trilateral.augmented.AugmentedTrilateralActiveParticipleConjugator
import org.sj.nounConjugation.trilateral.augmented.AugmentedTrilateralPassiveParticipleConjugator
import org.sj.nounConjugation.trilateral.unaugmented.UnaugmentedTrilateralActiveParticipleConjugator
import org.sj.nounConjugation.trilateral.unaugmented.UnaugmentedTrilateralPassiveParticipleConjugator
import org.sj.nounConjugation.trilateral.unaugmented.instrumental.StandardInstrumentalConjugator
import org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.ActiveParticipleModifier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.InstrumentalModifier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple.PassiveParticipleModifier
import org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace.TimeAndPlaceModifier
import org.sj.nounConjugation.trilateral.unaugmented.timeandplace.TimeAndPlaceConjugator
import org.sj.verbConjugation.AmrNahiAmr
import org.sj.verbConjugation.FaelMafool
import org.sj.verbConjugation.IsmAlaMifaalun
import org.sj.verbConjugation.IsmAlaMifalatun
import org.sj.verbConjugation.IsmAlaMifalun
import org.sj.verbConjugation.IsmAlaZarfSagheer
import org.sj.verbConjugation.IsmZarfMafalatun
import org.sj.verbConjugation.IsmZarfMafalun
import org.sj.verbConjugation.IsmZarfMafilun
import org.sj.verbConjugation.MadhiMudharay
import org.sj.verbConjugation.VerbDetails
import org.sj.verbConjugation.trilateral.augmented.active.past.AugmentedActivePastConjugator
import org.sj.verbConjugation.trilateral.augmented.active.present.AugmentedActivePresentConjugator
import org.sj.verbConjugation.trilateral.augmented.imperative.AugmentedImperativeConjugator
import org.sj.verbConjugation.trilateral.augmented.modifier.AugmentedTrilateralModifier
import org.sj.verbConjugation.trilateral.augmented.passive.past.AugmentedPassivePastConjugator
import org.sj.verbConjugation.trilateral.augmented.passive.present.AugmentedPassivePresentConjugator
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedImperativeConjugator
import org.sj.verbConjugation.trilateral.unaugmented.active.ActivePastConjugator
import org.sj.verbConjugation.trilateral.unaugmented.active.ActivePresentConjugator
import org.sj.verbConjugation.trilateral.unaugmented.modifier.UnaugmentedTrilateralModifier
import org.sj.verbConjugation.trilateral.unaugmented.passive.PassivePastConjugator
import org.sj.verbConjugation.trilateral.unaugmented.passive.PassivePresentConjugator
import org.sj.verbConjugation.util.KovRulesManager
import org.sj.verbConjugation.util.OSarfDictionary

class GatherAll {
    fun getMujarradListing(
        verbmood: String?,
        verbroot: String?,
        unaugmentedFormula: String,
                          ): ArrayList<ArrayList<*>> {
        return buildUnaugmentedLists(verbmood, verbroot, unaugmentedFormula)
    }

    fun getMujarradListing(verbmood: String, verbroot: String): ArrayList<ArrayList<*>> {
        return buildUnaugmentedLists(verbmood, verbroot)
        /*
          ArrayList<ArrayList> skabeer = buildUnaugmentedLists(verbmood, verbroot);
        return skabeer;
         */
    }

    fun getMazeedListing(verbmood: String, verbroot: String): ArrayList<ArrayList<*>> {
        return buildMazeedList(verbmood, verbroot)
    }

    fun getMujarradParticiple(
        verbroot: String?,
        unaugmentedFormula: String?,
                             ): ArrayList<ArrayList<*>> {
        return buildMujarradParticipleList(verbroot!!, unaugmentedFormula!!)
    }

    fun getMujarradIsmAla(verbroot: String?, unaugmentedFormula: String?): ArrayList<ArrayList<*>> {
        return buildUnAugmentedNounofInstrument(verbroot!!, unaugmentedFormula!!)
    }

    fun getMujarradZarf(verbroot: String, unaugmentedFormula: String): ArrayList<ArrayList<*>> {
        return buildUnAugmentedNounofTimeAndPlace(verbroot, unaugmentedFormula)
    }

    private fun buildUnAugmentedNounofTimeAndPlace(
        verbroot: String,
        unaugmentedFormula: String,
                                                  ): ArrayList<ArrayList<*>> {
        val skabeer = ArrayList<ArrayList<*>>()
        val c1 = verbroot[0]
        val c2 = verbroot[1]
        val c3 = verbroot[2]
        val rule = KovRulesManager.instance.getTrilateralKovRule(c1, c2, c3)


        val ismZarfMafilunarr = ArrayList<IsmZarfMafilun>()
        val ismzarfmafalatunarr = ArrayList<IsmZarfMafalatun>()
        val ismzarfmafalunarr = ArrayList<IsmZarfMafalun>()
        val unaugmentedTrilateralRoot =
            OSarfDictionary.instance.getUnaugmentedTrilateralRoots(verbroot, unaugmentedFormula)
        /*
    "A" -> "مَفْعَل"
"B" -> "مَفْعِل"
"C" -> "مَفْعَلَة"
     */if (unaugmentedTrilateralRoot != null) {

            val zarfconjugator = TimeAndPlaceConjugator.instance
            val zarfmodifier = TimeAndPlaceModifier.instance
            val mafalconjuationlist =
                zarfconjugator.createNounList(unaugmentedTrilateralRoot, "مَفْعَل")
            val mafal = zarfmodifier.build(
                unaugmentedTrilateralRoot,
                rule!!.kov,
                mafalconjuationlist,
                "مَفْعَل"
                                          )
            val zarfinalmafal = mafal.finalResult
            val mafilconjugationlist =
                zarfconjugator.createNounList(unaugmentedTrilateralRoot, "مَفْعِل")
            val mafil = zarfmodifier.build(
                unaugmentedTrilateralRoot,
                rule.kov,
                mafilconjugationlist,
                "مَفْعِل"
                                          )
            val zarffinalmafil = mafil.finalResult
            val mafalatunconjugationlist =
                zarfconjugator.createNounList(unaugmentedTrilateralRoot, "مَفْعَلَة")
            val mafalatun = zarfmodifier.build(
                unaugmentedTrilateralRoot,
                rule.kov,
                mafalatunconjugationlist,
                "مَفْعَلَة"
                                              )
            val zarffinalmafalatun = mafalatun.finalResult
            val zarfmafal: MutableList<String> = ArrayList()
            val zarfmafil: MutableList<String> = ArrayList()
            val zarfmafalatun: MutableList<String> = ArrayList()
            for (s in zarfinalmafal) {
                if (s.toString().isNotEmpty()) {
                    zarfmafal.add(s.toString())
                }
            }
            for (s in zarffinalmafil) {
                if (s.toString().isNotEmpty()) {
                    zarfmafil.add(s.toString())
                }
            }
            for (s in zarffinalmafalatun) {
                if (s.toString().isNotEmpty()) {
                    zarfmafalatun.add(s.toString())
                }
            }


            ismZarfMafilunarr.add( mafil.zarfMafilun)
            ismzarfmafalatunarr.add(  mafalatun.zarfMafalatun)
            ismzarfmafalunarr.add( mafal.zarfMafalun)
            skabeer.add(ismZarfMafilunarr)
            skabeer.add( ismzarfmafalatunarr )
            skabeer.add(ismzarfmafalunarr)
            return skabeer
        }
        return skabeer
    }

    private fun buildUnAugmentedNounofInstrument(
        verbroot: String,
        unaugmentedFormula: String,
                                                ): ArrayList<ArrayList<*>> {
        val skabeer = ArrayList<ArrayList<*>>()
        val c1 = verbroot[0]
        val c2 = verbroot[1]
        val c3 = verbroot[2]
        val ismalamifalunarr = ArrayList<IsmAlaMifalun>()
        val ismalamifalatunarr= ArrayList<IsmAlaMifalatun>()
        val ismaalamifaalunarr = ArrayList<IsmAlaMifaalun>()

        val alamifal :IsmAlaMifalun
        val alaMifalatun:IsmAlaMifalatun

        val alaMifaalun:IsmAlaMifaalun

        val rule = KovRulesManager.instance.getTrilateralKovRule(c1, c2, c3)
        val unaugmentedTrilateralRoot =
            OSarfDictionary.instance.getUnaugmentedTrilateralRoots(verbroot, unaugmentedFormula)
        if (unaugmentedTrilateralRoot != null) {
            val conjugator = StandardInstrumentalConjugator.instance
            val modifier = InstrumentalModifier.instance
            val mifal = conjugator.createNounList(unaugmentedTrilateralRoot, "مِفْعَل")
            val mifalunResult: ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule!!.kov, mifal, "مِفْعَل")

            val mifalatun = conjugator.createNounList(unaugmentedTrilateralRoot, "مِفْعَلَة")
            val mifalatunResult: ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule.kov, mifalatun, "مِفْعَلَة")

            val mifaal = conjugator.createNounList(unaugmentedTrilateralRoot, "مِفْعَال")
            val MifaalResult            : ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule.kov, mifaal, "مِفْعَال")

            val faalatun = conjugator.createNounList(unaugmentedTrilateralRoot, "فَعَّالَة")
            val conjResultfaalatun: ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule.kov, faalatun, "فَعَّالَة")
            val alamifal: MutableList<String> = ArrayList()
            val alamifalatun: MutableList<String> = ArrayList()
            val alamifaal: MutableList<String> = ArrayList()

            val ismfalamifalun = mifalunResult.alaMifalun
            val ismfalamifalatun = mifalatunResult.alaMifalatun
            val ismfalamifaaal = MifaalResult.alaMifaalun
            ismalamifalunarr.add(ismfalamifalun)
            ismalamifalatunarr.add(ismfalamifalatun)
            ismaalamifaalunarr.add(ismfalamifaaal)
            skabeer.add(ismalamifalunarr)
            skabeer.add(ismalamifalatunarr)
            skabeer.add(ismaalamifaalunarr)
            return skabeer
        }
        return skabeer
    }

    private fun buildMujarradParticipleList(
        verbroot: String,
        unaugmentedFormula: String,
                                           ): ArrayList<ArrayList<*>> {

        val c1 = verbroot[0]
        val c2 = verbroot[1]
        val c3 = verbroot[2]

        val finallist = ArrayList<ArrayList<*>>()
        val arrayofFaelMafool = ArrayList<FaelMafool>()
        val faelobj: FaelMafool
        val mafoolobj: FaelMafool

        val rule = KovRulesManager.instance.getTrilateralKovRule(c1, c2, c3)
        val unaugmentedTrilateralRoot =
            OSarfDictionary.instance.getUnaugmentedTrilateralRoots(verbroot, unaugmentedFormula)
        //ismfale and ismmafool
        val conjugatedIsmFael =
            UnaugmentedTrilateralActiveParticipleConjugator.instance.createNounList(
                unaugmentedTrilateralRoot,
                unaugmentedTrilateralRoot.conjugation!!
                                                                                   )
        val conjugationResult = ActiveParticipleModifier.instance
            .build(unaugmentedTrilateralRoot, rule!!.kov, conjugatedIsmFael, "")
        val conjugatedIsmMafool =
            UnaugmentedTrilateralPassiveParticipleConjugator.instance.createNounList(
                unaugmentedTrilateralRoot,
                unaugmentedTrilateralRoot.conjugation!!
                                                                                    )
        val ismmafoolresult = PassiveParticipleModifier.instance
            .build(unaugmentedTrilateralRoot, rule.kov, conjugatedIsmMafool, "")


        faelobj = conjugationResult.faelMafool
        mafoolobj = ismmafoolresult.faelMafool
        arrayofFaelMafool.add(faelobj)
        arrayofFaelMafool.add(mafoolobj)
        finallist.add(arrayofFaelMafool)
        val vdetails: MutableList<String> = ArrayList()
        vdetails.add(rule.desc.toString())
        vdetails.add(unaugmentedTrilateralRoot.conjugationname!!)





        return finallist//buildMujarradParticipleList



    }

    fun getMazeedListing(
        verbmood: String?,
        verbroot: String?,
        augmentedFormula: String?,
                        ): ArrayList<ArrayList<*>> {
        return buildMazeedList(verbmood!!, verbroot!!, augmentedFormula!!)
    }

    private fun buildUnaugmentedLists(verbmood: String, verbroot: String): ArrayList<ArrayList<*>> {
        val skabeer = ArrayList<ArrayList<*>>()
        val c1 = verbroot[0]
        val c2 = verbroot[1]
        val c3 = verbroot[2]

        var madhimajhool: List<*>
        var mudharay: List<*>? = null
        var amr: MutableList<*>
        var nahiamr: List<*>
        var madhi: List<*>
        var mudharaymajhool: List<*>? = null
        val rule = KovRulesManager.instance.getTrilateralKovRule(c1, c2, c3)
        val unaugmentedTrilateralRoot =
            OSarfDictionary.instance.getUnaugmentedTrilateralRoots(verbroot)
        if (unaugmentedTrilateralRoot != null) {
            //   madhi = org.sj.verb.trilateral.unaugmented.active.ActivePastConjugator.instance.createVerbList((UnaugmentedTrilateralRoot) unaugmentedTrilateralRoot);
            madhi = ActivePastConjugator.instance.createVerbList(unaugmentedTrilateralRoot)
            madhimajhool =
                PassivePastConjugator.instance.createVerbList(unaugmentedTrilateralRoot)
            when (verbmood) {
                "Indicative" -> {
                    mudharay = ActivePresentConjugator.instance
                        .createNominativeVerbList(unaugmentedTrilateralRoot)
                    mudharaymajhool = PassivePresentConjugator.instance
                        .createNominativeVerbList(unaugmentedTrilateralRoot)
                }

                "Subjunctive" -> {
                    mudharay = ActivePresentConjugator.instance
                        .createAccusativeVerbList(unaugmentedTrilateralRoot)
                    mudharaymajhool = PassivePresentConjugator.instance
                        .createAccusativeVerbList(unaugmentedTrilateralRoot)
                }

                "Jussive" -> {
                    mudharay = ActivePresentConjugator.instance
                        .createJussiveVerbList(unaugmentedTrilateralRoot)
                    mudharaymajhool = PassivePresentConjugator.instance
                        .createJussiveVerbList(unaugmentedTrilateralRoot)
                }

                "Emphasized" -> {
                    mudharay = ActivePresentConjugator.instance
                        .createEmphasizedVerbList(unaugmentedTrilateralRoot)
                    mudharaymajhool = PassivePresentConjugator.instance
                        .createEmphasizedVerbList(unaugmentedTrilateralRoot)
                }
            }
            nahiamr = ActivePresentConjugator.instance
                .createJussiveVerbList(unaugmentedTrilateralRoot)
            amr = UnaugmentedImperativeConjugator.instance
                .createVerbList(unaugmentedTrilateralRoot).toMutableList()
            //    public ConjugationResult build(UnaugmentedTrilateralRoot root!!, int kov, List conjugations, String tense, boolean active, boolean applyGemination) {
            //   result =  AugmentedActivePastConjugator.instance.createVerbList(augmentedRoot, getForm());
            val madhiconjresult = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule!!.kov, madhi as MutableList<*>,
                SystemConstants.PAST_TENSE, active = true, applyGemination = true
                                                                              )
            val madhimajhoolconj = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule.kov, madhimajhool as MutableList<*>,
                SystemConstants.PAST_TENSE, false, true
                                                                               )
            val mudharayconj = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule.kov, mudharay!! as MutableList<*>,
                SystemConstants.PRESENT_TENSE, true, true
                                                                           )
            val mudharaymajhoolconj = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule.kov, mudharaymajhool!! as MutableList<*>,
                SystemConstants.PRESENT_TENSE, false, true
                                                                                  )
            val amrconj = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule.kov, amr,
                SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE, true, true
                                                                      )
            val nahiamrconj = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule.kov, nahiamr as MutableList<*>,
                SystemConstants.PRESENT_TENSE, true, true
                                                                          )
            //ismfale and ismmafool
            val conjugatedIsmFael =
                UnaugmentedTrilateralActiveParticipleConjugator.instance.createNounList(
                    unaugmentedTrilateralRoot,
                    unaugmentedTrilateralRoot.conjugation!!
                                                                                       )
            val conjugationResult = ActiveParticipleModifier.instance
                .build(unaugmentedTrilateralRoot, rule.kov, conjugatedIsmFael, "")
            val finalIsmFael = conjugationResult.finalResult
            val conjugatedIsmMafool =
                UnaugmentedTrilateralPassiveParticipleConjugator.instance.createNounList(
                    unaugmentedTrilateralRoot,
                    unaugmentedTrilateralRoot.conjugation!!
                                                                                        )
            val ismmafoolresult = PassiveParticipleModifier.instance
                .build(unaugmentedTrilateralRoot, rule.kov, conjugatedIsmMafool, "")
            val ismmafoolresultFinalResult = ismmafoolresult.finalResult
            ///ismala
            /*
    0 = "مِفْعَل"
1 = "مِفْعَلَة"
2 = "مِفْعَال"
3 = "فَعَّالَة"
     */
            val conjugator = StandardInstrumentalConjugator.instance
            val modifier = InstrumentalModifier.instance
            val mifal = conjugator.createNounList(unaugmentedTrilateralRoot, "مِفْعَل")
            val conjResultmifal: ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule.kov, mifal, "مِفْعَل")
            val finalAlamifal = conjResultmifal.finalResult
            val mifalatun = conjugator.createNounList(unaugmentedTrilateralRoot, "مِفْعَلَة")
            val conjResult: ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule.kov, mifalatun, "مِفْعَلَة")
            val finalAlamifalatun = conjResult.finalResult as ArrayList<Any>
            val mifaal = conjugator.createNounList(unaugmentedTrilateralRoot, "مِفْعَال")
            val conjResultmifaal: ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule.kov, mifaal, "مِفْعَال")
            val finalAlamifaal = conjResultmifaal.finalResult
            val faalatun = conjugator.createNounList(unaugmentedTrilateralRoot, "فَعَّالَة")
            //zarf
            val zarfconjugator = TimeAndPlaceConjugator.instance
            val zarfmodifier = TimeAndPlaceModifier.instance
            val mafalconjuationlist =
                zarfconjugator.createNounList(unaugmentedTrilateralRoot, "مَفْعَل")
            val mafilconjugationlist =
                zarfconjugator.createNounList(unaugmentedTrilateralRoot, "مَفْعِل")
            val mafil = zarfmodifier.build(
                unaugmentedTrilateralRoot,
                rule.kov,
                mafilconjugationlist,
                "مَفْعِل"
                                          )
            val zarffinalmafil = mafil.finalResult
            val mafalatunconjugationlist =
                zarfconjugator.createNounList(unaugmentedTrilateralRoot, "مَفْعَلَة")
            val mafalatun = zarfmodifier.build(
                unaugmentedTrilateralRoot,
                rule.kov,
                mafalatunconjugationlist,
                "مَفْعَلَة"
                                              )
            val zarffinalmafalatun = mafalatun.finalResult
            madhi = madhiconjresult.finalResult
            madhimajhool = madhimajhoolconj.finalResult
            mudharay = mudharayconj.finalResult
            mudharaymajhool = mudharaymajhoolconj.finalResult
            amr = amrconj.finalResult.toMutableList()
            nahiamr = nahiamrconj.finalResult
            amr.removeAll(setOf<Any?>(null))
            val la = "لا"
            val list = nahiamr.subList(6, 11)
            val nm = ArrayList<String>()
            var sb: StringBuilder
            for (o in list) {
                sb = StringBuilder()
                nm.add(sb.append(la).append(" ").append(o.toString()).toString())
            }
            val listmadhi: MutableList<String> = ArrayList()
            val listmadhimajhool: MutableList<String> = ArrayList()
            val listmudharay: MutableList<String> = ArrayList()
            val listmudharymajhool: MutableList<String> = ArrayList()
            val listamr: MutableList<String> = ArrayList()
            val listamrnahi: MutableList<String> = ArrayList()
            val listismfael: MutableList<String> = ArrayList()
            val listismmafool: MutableList<String> = ArrayList()
            val listmifal: MutableList<String> = ArrayList()
            val listmifalatun: MutableList<String> = ArrayList()
            val listlmifaal: MutableList<String> = ArrayList()
            val listlzarfmafal: MutableList<String> = ArrayList()
            val listlzarfmafil: MutableList<String> = ArrayList()
            val listlzarfmafalatun: MutableList<String> = ArrayList()
            for (s in finalAlamifal) {
                if (s.toString().isNotEmpty()) {
                    listmifal.add(s.toString())
                }
            }
            for (s in finalAlamifalatun) {
                if (s.toString().isNotEmpty()) {
                    listmifalatun.add(s.toString())
                }
            }
            for (s in finalAlamifaal) {
                if (s.toString().isNotEmpty()) {
                    listlmifaal.add(s.toString())
                }
            }
            for (s in zarffinalmafil) {
                if (s.toString().isNotEmpty()) {
                    listlzarfmafal.add(s.toString())
                }
            }
            for (s in zarffinalmafil) {
                if (s.toString().isNotEmpty()) {
                    listlzarfmafil.add(s.toString())
                }
            }
            for (s in zarffinalmafalatun) {
                if (s.toString().isNotEmpty()) {
                    listlzarfmafalatun.add(s.toString())
                }
            }
            for (s in madhi) {
                listmadhi.add(s.toString())
            }
            for (s in madhimajhool) {
                try {
                    listmadhimajhool.add(s.toString())
                }
                catch (e: NullPointerException) {
                    listmadhimajhool.add("-")
                }
            }
            for (s in mudharay) {
                listmudharay.add(s.toString())
            }
            for (s in mudharaymajhool) {
                try {
                    listmudharymajhool.add(s.toString())
                }
                catch (e: NullPointerException) {
                    listmudharymajhool.add("-")
                }
            }
            for (s in finalIsmFael) {
                listismfael.add(s.toString())
            }
            for (s in ismmafoolresultFinalResult) {
                listismmafool.add(s.toString())
            }
            for (s in amr) {
                listamr.add(s.toString())
            }
            for (s in nm) {
                listamrnahi.add(s)
            }
            val vdetails: MutableList<String> = ArrayList()
            vdetails.add(rule.desc!!)
            vdetails.add(unaugmentedTrilateralRoot.conjugationname!!)
            vdetails.add(verbroot)
            vdetails.add(listmadhi[0])
            vdetails.add(listmadhimajhool[0])
            vdetails.add(listmudharay[0])
            vdetails.add(listmudharymajhool[0])
            vdetails.add(listamr[0])
            vdetails.add(listamrnahi[0])
            vdetails.add(listismfael[0])
            vdetails.add(listismmafool[0])
            vdetails.add(listmifal[0])
            vdetails.add(listmifalatun[0])
            vdetails.add(listlmifaal[0])
            // vdetails.add(listlmifaal.get(0));
            vdetails.add(listlzarfmafal[0])
            vdetails.add(listlzarfmafil[0])
            vdetails.add(listlzarfmafalatun[0])
            vdetails.add("mujarrad")
            vdetails.add(unaugmentedTrilateralRoot.conjugation!!)
            skabeer.add(vdetails as ArrayList<*>)
            skabeer.add(listmadhi as ArrayList<*>)
            skabeer.add(listmadhimajhool as ArrayList<*>)
            skabeer.add(listmudharay as ArrayList<*>)
            skabeer.add(listmudharymajhool as ArrayList<*>)
            skabeer.add(listamr as ArrayList<*>)
            skabeer.add(listamrnahi as ArrayList<*>)
            skabeer.add(listismfael as ArrayList<*>)
            skabeer.add(listismmafool as ArrayList<*>)
            skabeer.add(listmifal as ArrayList<*>)
            skabeer.add(listlmifaal as ArrayList<*>)
            skabeer.add(listmifalatun as ArrayList<*>)
            skabeer.add(listlzarfmafal as ArrayList<*>)
            skabeer.add(listlzarfmafil as ArrayList<*>)
            skabeer.add(listlzarfmafalatun as ArrayList<*>)

            //  skabeer.add((ArrayList) strings);
            return skabeer
        }
        return skabeer
    }

    private fun buildUnaugmentedLists(
        verbmood: String?,
        verbroot: String?,
        unaugmentedFormula: String,
                                     ): ArrayList<ArrayList<*>> {

        val finallist = ArrayList<ArrayList<*>>()
        val madhimuhdarymainobj = ArrayList<MadhiMudharay>()
        val ismalamifalunarr = ArrayList<IsmAlaMifalun>()
        val ismalamifalatunarr= ArrayList<IsmAlaMifalatun>()
        val ismaalamifaalunarr = ArrayList<IsmAlaMifaalun>()

        val ismZarfMafilunarr = ArrayList<IsmZarfMafilun>()
        val ismzarfmafalatunarr = ArrayList<IsmZarfMafalatun>()
        val ismzarfmafalunarr = ArrayList<IsmZarfMafalun>()
        val skabeerismobj = ArrayList<FaelMafool>()
        val arrayofverbdetails = ArrayList<VerbDetails>()
        val arrayofamrobj = ArrayList<AmrNahiAmr>()
        val madhimajhoolobj: MadhiMudharay
        val mudharyobj: MadhiMudharay
        val mudharaymajhoolobj: MadhiMudharay
        val madhiobj: MadhiMudharay
        val faelobj: FaelMafool
        val mafoolobj: FaelMafool
        val amrobj: AmrNahiAmr
        val alamifal :IsmAlaMifalun
        val alaMifalatun:IsmAlaMifalatun

        val alaMifaalun:IsmAlaMifaalun

        val zarfMafilun :IsmZarfMafilun

        val zarfMafalun:IsmZarfMafalun
        val zarfMafalatun:IsmZarfMafalatun

        val verbDetailsobj = VerbDetails()
        val c1 = verbroot?.get(0)
        val c2 = verbroot?.get(1)
        val c3 = verbroot?.get(2)
        val madhimajhool: List<*>
        var mudharay: List<*>? = null
        val amr: MutableList<*>
        val nahiamr: List<*>
        val madhi: List<*>
        var mudharaymajhool: List<*>? = null
        val rule = KovRulesManager.instance.getTrilateralKovRule(c1!!, c2!!, c3!!)
        val unaugmentedTrilateralRoot =
            OSarfDictionary.instance.getUnaugmentedTrilateralRoots(verbroot, unaugmentedFormula)
        if (unaugmentedTrilateralRoot != null) {
            //   madhi = org.sj.verb.trilateral.unaugmented.active.ActivePastConjugator.instance.createVerbList((UnaugmentedTrilateralRoot) unaugmentedTrilateralRoot);
            madhi = ActivePastConjugator.instance.createVerbList(unaugmentedTrilateralRoot)
            //  result = sarf.verb.trilateral.unaugmented.active.ActivePastConjugator.getInstance().createVerbList((UnaugmentedTrilateralRoot) root);
            /*       val build = UnaugmentedTrilateralModifier.instance
                       .build(
                           unaugmentedTrilateralRoot, rule!!.kov, madhi as MutableList<*>,
                           SystemConstants.PAST_TENSE, true, true
                       )*/
            madhimajhool =
                PassivePastConjugator.instance.createVerbList(unaugmentedTrilateralRoot)

            when (verbmood) {
                "Indicative" -> {
                    mudharay = ActivePresentConjugator.instance
                        .createNominativeVerbList(unaugmentedTrilateralRoot)
                    mudharaymajhool = PassivePresentConjugator.instance
                        .createNominativeVerbList(unaugmentedTrilateralRoot)
                }

                "Subjunctive" -> {
                    mudharay = ActivePresentConjugator.instance
                        .createAccusativeVerbList(unaugmentedTrilateralRoot)
                    mudharaymajhool = PassivePresentConjugator.instance
                        .createAccusativeVerbList(unaugmentedTrilateralRoot)
                }

                "Jussive" -> {
                    mudharay = ActivePresentConjugator.instance
                        .createJussiveVerbList(unaugmentedTrilateralRoot)
                    mudharaymajhool = PassivePresentConjugator.instance
                        .createJussiveVerbList(unaugmentedTrilateralRoot)
                }

                "Emphasized" -> {
                    mudharay = ActivePresentConjugator.instance
                        .createEmphasizedVerbList(unaugmentedTrilateralRoot)
                    mudharaymajhool = PassivePresentConjugator.instance
                        .createEmphasizedVerbList(unaugmentedTrilateralRoot)
                }
            }
            nahiamr = ActivePresentConjugator.instance
                .createJussiveVerbList(unaugmentedTrilateralRoot)
            amr = UnaugmentedImperativeConjugator.instance
                .createVerbList(unaugmentedTrilateralRoot).toMutableList()
            //    public ConjugationResult build(UnaugmentedTrilateralRoot root!!, int kov, List conjugations, String tense, boolean active, boolean applyGemination) {
            //   result =  AugmentedActivePastConjugator.instance.createVerbList(augmentedRoot, getForm());
            val madhiconjresult = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule!!.kov, madhi as MutableList<*>,
                SystemConstants.PAST_TENSE, true, true
                                                                              )
/*
            val conjResult: ConjugationResult =
                UnaugmentedTrilateralModifier.instance()
                    .build(
                        unaugmentedTrilateralRoot,
                        unaugmentedTrilateralRoot, rule!!.kov,
                        madhi,
                        SystemConstants.PAST_TENSE,
                        true
                          )
*/




            val madhimajhoolconj = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule.kov, madhimajhool as MutableList<*>,
                SystemConstants.PAST_TENSE, false, true
                                                                               )
            val mudharayconj = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule.kov, mudharay!! as MutableList<*>,
                SystemConstants.PRESENT_TENSE, true, true
                                                                           )
            val mudharaymajhoolconj = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule.kov, mudharaymajhool!! as MutableList<*>,
                SystemConstants.PRESENT_TENSE, false, true
                                                                                  )
            val amrconj = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule.kov, amr,
                SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE, true, true
                                                                      )
            val nahiamrconj = UnaugmentedTrilateralModifier.instance.build(
                unaugmentedTrilateralRoot, rule.kov, nahiamr as MutableList<*>,
                SystemConstants.PRESENT_TENSE, true, true
                                                                          )
            //ismfale and ismmafool
            val conjugatedIsmFael =
                UnaugmentedTrilateralActiveParticipleConjugator.instance.createNounList(
                    unaugmentedTrilateralRoot,
                    unaugmentedTrilateralRoot.conjugation!!
                                                                                       )
            val faelconjugatoinResult = ActiveParticipleModifier.instance
                .build(unaugmentedTrilateralRoot, rule.kov, conjugatedIsmFael, "")
            val finalIsmFael = faelconjugatoinResult.finalResult
            val conjugatedIsmMafool =
                UnaugmentedTrilateralPassiveParticipleConjugator.instance.createNounList(
                    unaugmentedTrilateralRoot,
                    unaugmentedTrilateralRoot.conjugation!!
                                                                                        )
            val mafoolconjugationresult = PassiveParticipleModifier.instance
                .build(unaugmentedTrilateralRoot, rule.kov, conjugatedIsmMafool, "")
            val ismmafoolresultFinalResult = mafoolconjugationresult.finalResult
            ///ismala
            /*
    0 = "مِفْعَل"
1 = "مِفْعَلَة"
2 = "مِفْعَال"
3 = "فَعَّالَة"
     */
            val conjugator = StandardInstrumentalConjugator.instance
            val modifier = InstrumentalModifier.instance
            val title = "مِفْعَلَة"
            val mifal = conjugator.createNounList(unaugmentedTrilateralRoot, "مِفْعَل")
            val ismAalaMifalunResult: ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule.kov, mifal, "مِفْعَل")
            val finalAlamifal = ismAalaMifalunResult.finalResult
            val mifalatun = conjugator.createNounList(unaugmentedTrilateralRoot, "مِفْعَلَة")
            val ismALaMifalatunResult: ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule.kov, mifalatun, "مِفْعَلَة")
            val finalAlamifalatun = ismALaMifalatunResult.finalResult
            val mifaal = conjugator.createNounList(unaugmentedTrilateralRoot, "مِفْعَال")
            val ismAlaMifaalun: ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule.kov, mifaal, "مِفْعَال")
            val finalAlamifaal = ismAlaMifaalun.finalResult
            val faalatun = conjugator.createNounList(unaugmentedTrilateralRoot, "فَعَّالَة")
            val conjResultfaalatun: ConjugationResult =
                modifier.build(unaugmentedTrilateralRoot, rule.kov, faalatun, "فَعَّالَة")
            //zarf
            val zarfconjugator = TimeAndPlaceConjugator.instance
            val zarfmodifier = TimeAndPlaceModifier.instance
            val IsmZarfMafalunResult =
                zarfconjugator.createNounList(unaugmentedTrilateralRoot, "مَفْعَل")
            val ismZarafMafalunResult = zarfmodifier.build(
                unaugmentedTrilateralRoot,
                rule.kov,
                IsmZarfMafalunResult,
                "مَفْعَل"
                                                          )
            val zarfinalmafal = ismZarafMafalunResult.finalResult
            val mafilconjugationlist =
                zarfconjugator.createNounList(unaugmentedTrilateralRoot, "مَفْعِل")
            val ismZarfMafilunResult = zarfmodifier.build(
                unaugmentedTrilateralRoot,
                rule.kov,
                mafilconjugationlist,
                "مَفْعِل"
                                                         )
            val zarffinalmafil = ismZarfMafilunResult.finalResult
            val mafalatunconjugationlist =
                zarfconjugator.createNounList(unaugmentedTrilateralRoot, "مَفْعَلَة")
            val ismZarfMafalatunResult = zarfmodifier.build(
                unaugmentedTrilateralRoot,
                rule.kov,
                mafalatunconjugationlist,
                "مَفْعَلَة"
                                                           )
            val zarffinalmafalatun = ismZarfMafalatunResult.finalResult


            amrobj = amrconj.amrandnahi
            val la = "لا "
            val new = AmrNahiAmr()
            val fornahiamr = nahiamr.subList(6, 11)

            new.anta = la + fornahiamr[0].toString()
            new.antuma = la + fornahiamr[1].toString()
            new.antum = la + fornahiamr[2].toString()
            new.anti = la + fornahiamr[3].toString()
            new.antumaf = la + fornahiamr[1].toString()
            new.antunna = la + fornahiamr[4].toString()












            arrayofamrobj.add(amrobj)
            arrayofamrobj.add(new)

            faelobj = faelconjugatoinResult.faelMafool
            mafoolobj = mafoolconjugationresult.faelMafool

            skabeerismobj.add(faelobj)
            skabeerismobj.add(mafoolobj)
            //     arrayofskabeerismobj.add(skabeerismobj)
            madhiobj = madhiconjresult.madhiMudharay
            madhimajhoolobj = madhimajhoolconj.madhiMudharay


            mudharyobj = mudharayconj.madhiMudharay
            mudharaymajhoolobj = mudharaymajhoolconj.madhiMudharay
            /*
                    val ismalamifalunarr = ArrayList<IsmAlaMifalun>()
        val ismalamifalatunarr= ArrayList<IsmAlaMifalatun>()
        val ismaalamifaalunarr = ArrayList<IsmAlaMifaalun>()

        val ismZarfMafilunarr = ArrayList<IsmZarfMafilun>()
        val ismzarfmafalatunarr = ArrayList<IsmZarfMafalatun>()
        val ismzarfmafalunarr = ArrayList<IsmZarfMafalun>()

             */
   /*
           val ismalamifalunarr = ArrayList<IsmAlaMifalun>()
        val ismalamifalatunarr= ArrayList<IsmAlaMifalatun>()
        val ismaalamifaalunarr = ArrayList<IsmAlaMifaalun>()

        val  = ArrayList<IsmZarfMafilun>()
        val ismzarfmafalatunarr = ArrayList<IsmZarfMafalatun>()
        val ismzarfmafalunarr = ArrayList<IsmZarfMafalun>()
    */
             alamifal=ismAalaMifalunResult.alaMifalun
             alaMifaalun=ismAlaMifaalun.alaMifaalun
            alaMifalatun=ismALaMifalatunResult.alaMifalatun
             zarfMafilun =ismZarfMafilunResult.zarfMafilun
             zarfMafalun=ismZarafMafalunResult.zarfMafalun
             zarfMafalatun=ismZarfMafalatunResult.zarfMafalatun
            ismalamifalunarr.add(alamifal)
            ismalamifalatunarr.add(alaMifalatun)
            ismaalamifaalunarr.add(alaMifaalun)
            ismZarfMafilunarr.add(zarfMafilun)
            ismzarfmafalatunarr.add(zarfMafalatun)
            ismzarfmafalunarr.add(zarfMafalun)


            madhimuhdarymainobj.add(madhiobj)
            madhimuhdarymainobj.add(madhimajhoolobj)
            madhimuhdarymainobj.add(mudharyobj)
            madhimuhdarymainobj.add(mudharaymajhoolobj)

            verbDetailsobj.verbtype = rule.desc.toString()
            verbDetailsobj.babname = unaugmentedTrilateralRoot.conjugationname
            verbDetailsobj.mazeedormujarad = "mujarrad"
            verbDetailsobj.wazannumberorname = unaugmentedTrilateralRoot.rulename
            verbDetailsobj.verbroot = verbroot

            arrayofverbdetails.add(verbDetailsobj)
            //   arrayofskabeermadhimudharay.add(madhimuhdarymainobj)
            finallist.add(madhimuhdarymainobj)
            finallist.add(skabeerismobj)
            finallist.add(arrayofamrobj)
            finallist.add(arrayofverbdetails)
            val ismalazarf = IsmAlaZarfSagheer()

            ismalazarf.ismAlaMifal = finalAlamifal[0].toString()
            ismalazarf.ismALAMifalatun = finalAlamifalatun[1].toString()
            ismalazarf.ismAlaMifaal = finalAlamifaal[0].toString()

            ismalazarf.zarfMafilun = zarffinalmafil[0].toString()
            ismalazarf.zarfMafalun = zarfinalmafal[0].toString()
            ismalazarf.zarfMafalatun = zarffinalmafalatun[1].toString()
            val ismalazarfobject = ArrayList<IsmAlaZarfSagheer>()
            ismalazarfobject.add(ismalazarf)
            finallist.add(ismalazarfobject)

            finallist.add(ismalamifalunarr)
            finallist.add(ismalamifalatunarr)
            finallist.add(ismaalamifaalunarr)
            finallist.add(ismZarfMafilunarr)

            finallist.add(ismzarfmafalatunarr)
            finallist.add(ismzarfmafalunarr)





            //  skabeer.add((ArrayList) strings);
            return finallist
        }
        return finallist
    }

    private fun buildMazeedList(
        verbmood: String,
        verbroot: String,
        augmentedFormula: String,
                               ): ArrayList<ArrayList<*>> {
        val augmentedRoot =
            OSarfDictionary.instance.getAugmentedTrilateralRoot(verbroot, augmentedFormula)
        var ismfael: List<*>
        var ismmafool: List<*>
        var madhimajhool: List<*>
        lateinit var mudharay: List<*>
        var amr: MutableList<*>
        var nahiamr: MutableList<*>
        var madhi: List<*>
        lateinit var mudharaymajhool: List<*>
        val c1 = verbroot[0]
        val c2 = verbroot[1]
        val c3 = verbroot[2]
        val skabeer = ArrayList<ArrayList<*>>()
        val finallist = ArrayList<ArrayList<*>>()
        val madhimuhdarymainobj = ArrayList<MadhiMudharay>()
        val skabeerismobj = ArrayList<FaelMafool>()
        val arrayofamrobj = ArrayList<AmrNahiAmr>()
        val arrayofverbdetails = ArrayList<VerbDetails>()
        val madhimajhoolobj: MadhiMudharay
        val mudharyobj: MadhiMudharay
        val mudharaymajhoolobj: MadhiMudharay
        val madhiobj: MadhiMudharay
        val faelobj: FaelMafool
        val mafoolobj: FaelMafool
        val amrobj: AmrNahiAmr
        val verbDetailsobj = VerbDetails()



        if (augmentedRoot != null) {
            val rule = KovRulesManager.instance.getTrilateralKovRule(c1, c2, c3)
            madhi = AugmentedActivePastConjugator.instance
                .createVerbList(augmentedRoot, augmentedRoot.form!!.toInt())
            madhimajhool = AugmentedPassivePastConjugator.instance
                .createVerbList(augmentedRoot, augmentedRoot.form!!.toInt())
            when (verbmood) {
                "Indicative" -> {
                    mudharay =
                        AugmentedActivePresentConjugator.instance.nominativeConjugator.createVerbList(
                            augmentedRoot,
                            augmentedFormula.toInt()
                                                                                                     )
                    mudharaymajhool =
                        AugmentedPassivePresentConjugator.instance.nominativeConjugator.createVerbList(
                            augmentedRoot,
                            augmentedFormula.toInt()
                                                                                                      )
                }

                "Subjunctive" -> {
                    mudharay =
                        AugmentedActivePresentConjugator.instance.accusativeConjugator.createVerbList(
                            augmentedRoot,
                            augmentedFormula.toInt()
                                                                                                     )
                    mudharaymajhool =
                        AugmentedPassivePresentConjugator.instance.accusativeConjugator.createVerbList(
                            augmentedRoot,
                            augmentedFormula.toInt()
                                                                                                      )
                }

                "Jussive" -> {
                    mudharay =
                        AugmentedActivePresentConjugator.instance.jussiveConjugator.createVerbList(
                            augmentedRoot,
                            augmentedFormula.toInt()
                                                                                                  )
                    mudharaymajhool =
                        AugmentedPassivePresentConjugator.instance.jussiveConjugator.createVerbList(
                            augmentedRoot,
                            augmentedFormula.toInt()
                                                                                                   )
                }

                "Emphasized" -> {
                    mudharay =
                        AugmentedActivePresentConjugator.instance.emphasizedConjugator.createVerbList(
                            augmentedRoot,
                            augmentedFormula.toInt()
                                                                                                     )
                    mudharaymajhool =
                        AugmentedPassivePresentConjugator.instance.emphasizedConjugator.createVerbList(
                            augmentedRoot,
                            augmentedFormula.toInt()
                                                                                                      )
                }
            }



            nahiamr =
                AugmentedActivePresentConjugator.instance.jussiveConjugator.createVerbList(
                    augmentedRoot,
                    augmentedFormula.toInt()
                                                                                          )
                    .toMutableList()
            amr = AugmentedImperativeConjugator.instance.notEmphsizedConjugator.createVerbList(
                augmentedRoot,
                augmentedFormula.toInt()
                                                                                              )
                .toMutableList()
            val madhiconjresult = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule!!.kov, augmentedRoot.form!!.toInt(), madhi,
                SystemConstants.PAST_TENSE, true, true
                                                                            )
            val madhimajhoolconj = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), madhimajhool,
                SystemConstants.PAST_TENSE, true, true
                                                                             )
            val mudharayconj = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), mudharay,
                SystemConstants.PRESENT_TENSE, true, true
                                                                         )
            val mudharaymajhoolconj =
                AugmentedTrilateralModifier.instance.build(
                    augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(),
                    mudharaymajhool,
                    SystemConstants.PRESENT_TENSE, false, true
                                                          )
            val amrconj = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), amr,
                SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE, true, true
                                                                    )
            val nahiamrconj = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), nahiamr,
                SystemConstants.PRESENT_TENSE, true, true
                                                                        )
            ismfael = AugmentedTrilateralActiveParticipleConjugator.instance
                .createNounList(augmentedRoot, augmentedFormula.toInt())
            val ismfaleresult =
                org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.ActiveParticipleModifier.instance
                    .build(augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), ismfael, true)

            ismmafool = AugmentedTrilateralPassiveParticipleConjugator.instance
                .createNounList(augmentedRoot, augmentedFormula.toInt())
            val ismmafoolresult =
                org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.ActiveParticipleModifier.instance
                    .build(augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), ismmafool, true)
            val la = "لا "
            amrobj = amrconj.amrandnahi
            val new = AmrNahiAmr()
            val fornahiamr = nahiamr.subList(6, 11)

            new.anta = la + fornahiamr[0].toString()
            new.antuma = la + fornahiamr[1].toString()
            new.antum = la + fornahiamr[2].toString()
            new.anti = la + fornahiamr[3].toString()
            new.antumaf = la + fornahiamr[1].toString()
            new.antunna = la + fornahiamr[4].toString()


            arrayofamrobj.add(amrobj)
            arrayofamrobj.add(new)

            faelobj = ismfaleresult.faelMafool
            mafoolobj = ismmafoolresult.faelMafool

            skabeerismobj.add(faelobj)
            skabeerismobj.add(mafoolobj)
            //     arrayofskabeerismobj.add(skabeerismobj)
            madhiobj = madhiconjresult.madhiMudharay
            madhimajhoolobj = madhimajhoolconj.madhiMudharay


            mudharyobj = mudharayconj.madhiMudharay
            mudharaymajhoolobj = mudharaymajhoolconj.madhiMudharay
            madhimuhdarymainobj.add(madhiobj)
            madhimuhdarymainobj.add(madhimajhoolobj)
            madhimuhdarymainobj.add(mudharyobj)
            madhimuhdarymainobj.add(mudharaymajhoolobj)

            verbDetailsobj.verbtype = rule.desc.toString()
            verbDetailsobj.babname = augmentedRoot.babname
            verbDetailsobj.mazeedormujarad = "mazeed"
            verbDetailsobj.wazannumberorname = augmentedRoot.form
            verbDetailsobj.verbroot = verbroot
            finallist.add(madhimuhdarymainobj)
            finallist.add(skabeerismobj)
            finallist.add(arrayofamrobj)
            arrayofverbdetails.add(verbDetailsobj)
            finallist.add(arrayofverbdetails)









            return finallist//buildAugmentedLists
        }
        return finallist
    }

    private fun buildMazeedList(verbmood: String, verbroot: String): ArrayList<ArrayList<*>> {
        val augmentedRoot = OSarfDictionary.instance.getAugmentedTrilateralRoot(verbroot)
        var ismfael: List<*>
        var ismmafool: List<*>
        var madhimajhool: List<*>
        var mudharay: List<*>? = null
        var amr: MutableList<*>
        var nahiamr: List<*>
        var madhi: List<*>
        var mudharaymajhool: List<*>? = null
        val finallist = ArrayList<ArrayList<*>>()
        val c1 = verbroot[0]
        val c2 = verbroot[1]
        val c3 = verbroot[2]
        val madhimuhdarymainobj = ArrayList<MadhiMudharay>()
        val arrayofFaelMafool = ArrayList<FaelMafool>()
        val arrayofamrobj = ArrayList<AmrNahiAmr>()
        val skabeer = ArrayList<ArrayList<*>>()
        val madhimajhoolobj: MadhiMudharay
        val mudharyobj: MadhiMudharay
        val mudharaymajhoolobj: MadhiMudharay
        val madhiobj: MadhiMudharay
        val faelobj: FaelMafool
        val mafoolobj: FaelMafool
        val amrobj: AmrNahiAmr
        val amrnahiobj: AmrNahiAmr
        val verbDetailsobj = VerbDetails()
        val skabeerismobj = ArrayList<FaelMafool>()

        val arrayofverbdetails = ArrayList<VerbDetails>()
        val rule = KovRulesManager.instance.getTrilateralKovRule(c1, c2, c3)
        if (augmentedRoot != null) {
            //    if (selectionInfo.isAugmented()) {
            madhi = AugmentedActivePastConjugator.instance
                .createVerbList(augmentedRoot, augmentedRoot.form!!.toInt())
            madhimajhool = AugmentedPassivePastConjugator.instance
                .createVerbList(augmentedRoot, augmentedRoot.form!!.toInt())
            when (verbmood) {
                "Indicative" -> {
                    mudharay =
                        AugmentedActivePresentConjugator.instance.nominativeConjugator.createVerbList(
                            augmentedRoot,
                            augmentedRoot.form!!.toInt()
                                                                                                     )
                    mudharaymajhool =
                        AugmentedPassivePresentConjugator.instance.nominativeConjugator.createVerbList(
                            augmentedRoot,
                            augmentedRoot.form!!.toInt()
                                                                                                      )
                }

                "Subjunctive" -> {
                    mudharay =
                        AugmentedActivePresentConjugator.instance.accusativeConjugator.createVerbList(
                            augmentedRoot,
                            augmentedRoot.form!!.toInt()
                                                                                                     )
                    mudharaymajhool =
                        AugmentedPassivePresentConjugator.instance.accusativeConjugator.createVerbList(
                            augmentedRoot,
                            augmentedRoot.form!!.toInt()
                                                                                                      )
                }

                "Jussive" -> {
                    mudharay =
                        AugmentedActivePresentConjugator.instance.jussiveConjugator.createVerbList(
                            augmentedRoot,
                            augmentedRoot.form!!.toInt()
                                                                                                  )
                    mudharaymajhool =
                        AugmentedPassivePresentConjugator.instance.jussiveConjugator.createVerbList(
                            augmentedRoot,
                            augmentedRoot.form!!.toInt()
                                                                                                   )
                }

                "Emphasized" -> {
                    mudharay =
                        AugmentedActivePresentConjugator.instance.emphasizedConjugator.createVerbList(
                            augmentedRoot,
                            augmentedRoot.form!!.toInt()
                                                                                                     )
                    mudharaymajhool =
                        AugmentedPassivePresentConjugator.instance.emphasizedConjugator.createVerbList(
                            augmentedRoot,
                            augmentedRoot.form!!.toInt()
                                                                                                      )
                }
            }
            nahiamr =
                AugmentedActivePresentConjugator.instance.jussiveConjugator.createVerbList(
                    augmentedRoot,
                    augmentedRoot.form!!.toInt()
                                                                                          )
            amr = AugmentedImperativeConjugator.instance.notEmphsizedConjugator.createVerbList(
                augmentedRoot,
                augmentedRoot.form!!.toInt()
                                                                                              )
                .toMutableList()
            val madhiconjresult = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule!!.kov, augmentedRoot.form!!.toInt(), madhi,
                SystemConstants.PAST_TENSE, true, true
                                                                            )
            val madhimajhoolconj = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), madhimajhool,
                SystemConstants.PAST_TENSE, true, true
                                                                             )
            val mudharayconj = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), mudharay!!,
                SystemConstants.PRESENT_TENSE, true, true
                                                                         )
            val mudharaymajhoolconj = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), mudharaymajhool!!,
                SystemConstants.PRESENT_TENSE, false, true
                                                                                )
            val amrconj = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), amr,
                SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE, true, true
                                                                    )
            val nahiamrconj = AugmentedTrilateralModifier.instance.build(
                augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), nahiamr,
                SystemConstants.PRESENT_TENSE, true, true
                                                                        )
            ismfael = AugmentedTrilateralActiveParticipleConjugator.instance
                .createNounList(augmentedRoot, augmentedRoot.form!!.toInt())
            ismmafool = AugmentedTrilateralPassiveParticipleConjugator.instance
                .createNounList(augmentedRoot, augmentedRoot.form!!.toInt())
            val conjResult =
                org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.ActiveParticipleModifier.instance
                    .build(augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), ismfael, true)
            val mafoolresult =
                org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.ActiveParticipleModifier.instance
                    .build(augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), ismmafool, true)


            val la = "لا "
            amrobj = amrconj.amrandnahi
            val new = AmrNahiAmr()
            val fornahiamr = nahiamr.subList(6, 11)

            new.anta = la + fornahiamr[0].toString()
            new.antuma = la + fornahiamr[1].toString()
            new.antum = la + fornahiamr[2].toString()
            new.anti = la + fornahiamr[3].toString()
            new.antumaf = la + fornahiamr[1].toString()
            new.antunna = la + fornahiamr[4].toString()


            arrayofamrobj.add(amrobj)
            arrayofamrobj.add(new)

            faelobj = conjResult.faelMafool
            mafoolobj = mafoolresult.faelMafool

            skabeerismobj.add(faelobj)
            skabeerismobj.add(mafoolobj)
            //     arrayofskabeerismobj.add(skabeerismobj)
            madhiobj = madhiconjresult.madhiMudharay
            madhimajhoolobj = madhimajhoolconj.madhiMudharay


            mudharyobj = mudharayconj.madhiMudharay
            mudharaymajhoolobj = mudharaymajhoolconj.madhiMudharay
            madhimuhdarymainobj.add(madhiobj)
            madhimuhdarymainobj.add(madhimajhoolobj)
            madhimuhdarymainobj.add(mudharyobj)
            madhimuhdarymainobj.add(mudharaymajhoolobj)

            verbDetailsobj.verbtype = rule.desc.toString()
            verbDetailsobj.babname = augmentedRoot.babname
            verbDetailsobj.mazeedormujarad = "mazeed"
            verbDetailsobj.wazannumberorname = augmentedRoot.form
            verbDetailsobj.verbroot = verbroot
            finallist.add(madhimuhdarymainobj)
            finallist.add(skabeerismobj)
            finallist.add(arrayofamrobj)
            arrayofverbdetails.add(verbDetailsobj)
            finallist.add(arrayofverbdetails)





            ismfael = conjResult.finalResult
            ismmafool = AugmentedTrilateralPassiveParticipleConjugator.instance
                .createNounList(augmentedRoot, augmentedRoot.form!!.toInt())
            ismmafool = conjResult.finalResult
            madhi = madhiconjresult.finalResult
            madhimajhool = madhimajhoolconj.finalResult
            mudharay = mudharayconj.finalResult
            mudharaymajhool = mudharaymajhoolconj.finalResult
            amr = amrconj.finalResult.toMutableList()
            nahiamr = nahiamrconj.finalResult
            amr.removeAll(setOf<Any?>(null))

            val list = nahiamr.subList(6, 11)
            val nm = ArrayList<String>()
            var sb: StringBuilder
            for (o in list) {
                sb = StringBuilder()
                nm.add(sb.append(la).append(" ").append(o.toString()).toString())
            }
            val listmadhi: MutableList<String> = ArrayList()
            val listmadhimajhool: MutableList<String> = ArrayList()
            val listmudharay: MutableList<String> = ArrayList()
            val listmudharymajhool: MutableList<String> = ArrayList()
            val listamr: MutableList<String> = ArrayList()
            val listamrnahi: MutableList<String> = ArrayList()
            val listismfael: MutableList<String> = ArrayList()
            val listismmafool: MutableList<String> = ArrayList()
            val vdetails: MutableList<String> = ArrayList()
            vdetails.add(rule.desc.toString())
            vdetails.add(augmentedRoot.babname!!)
            //vdetails.add(    rule.getDesc());
            vdetails.add(verbroot)
            for (s in madhi) {
                listmadhi.add(s.toString())
            }
            for (s in madhimajhool) {
                try {
                    listmadhimajhool.add(s.toString())
                }
                catch (e: NullPointerException) {
                    listmadhimajhool.add("-")
                }
            }
            for (s in mudharay) {
                listmudharay.add(s.toString())
            }
            for (s in mudharaymajhool) {
                try {
                    listmudharymajhool.add(s.toString())
                }
                catch (e: NullPointerException) {
                    listmudharymajhool.add("-")
                }
            }
            for (s in ismfael) {
                listismfael.add(s.toString())
            }
            for (s in ismmafool) {
                listismmafool.add(s.toString())
            }
            for (s in amr) {
                listamr.add(s.toString())
            }
            for (s in nm) {
                listamrnahi.add(s)
            }
            vdetails.add(listmadhi[0])
            vdetails.add(listmadhimajhool[0])
            vdetails.add(listmudharay[0])
            vdetails.add(listmudharymajhool[0])
            vdetails.add(listamr[0])
            vdetails.add(listamrnahi[0])
            vdetails.add(listismfael[0])
            vdetails.add(listismmafool[0])
            vdetails.add("")
            vdetails.add("")
            vdetails.add("")
            vdetails.add("")
            vdetails.add("")
            vdetails.add("")
            vdetails.add("mazeed")
            vdetails.add(augmentedRoot.form!!)
            skabeer.add(vdetails as ArrayList<*>)
            skabeer.add(listmadhi as ArrayList<*>)
            skabeer.add(listmadhimajhool as ArrayList<*>)
            skabeer.add(listmudharay as ArrayList<*>)
            skabeer.add(listmudharymajhool as ArrayList<*>)
            skabeer.add(listamr as ArrayList<*>)
            skabeer.add(listamrnahi as ArrayList<*>)
            skabeer.add(listismfael as ArrayList<*>)
            skabeer.add(listismmafool as ArrayList<*>)
            return finallist
        }
        return finallist
    }

    fun buildMazeedParticiples(
        verbroot: String,
        augmentedFormula: String,
                              ): ArrayList<ArrayList<*>> {
        var verbroot1 = verbroot
        val c1 = verbroot1.get(0)
        val c2 = verbroot1.get(1)
        val c3 = verbroot1.get(2)
        val finallist = ArrayList<ArrayList<*>>()
        val arrayofFaelMafool = ArrayList<FaelMafool>()
        val faelobj: FaelMafool
        val mafoolobj: FaelMafool
        val aleph = verbroot1.indexOf("ا")
        val alephhamza = verbroot1.indexOf("أ")
        if (aleph != -1) {
            if (verbroot1 != null) {
                verbroot1 = verbroot1.replace("ا", "ء")
            }
        } else if (alephhamza != -1) {
            if (verbroot1 != null) {
                verbroot1 = verbroot1.replace("أ", "ء")
            }
        }
        val augmentedRoot =
            OSarfDictionary.instance.getAugmentedTrilateralRoot(verbroot1, augmentedFormula)
        var ismfael: List<*>?
        var ismmafool: List<*>?

        if (augmentedRoot != null) {
            val rule = KovRulesManager.instance.getTrilateralKovRule(c1, c2, c3)
            ismfael = AugmentedTrilateralActiveParticipleConjugator.instance
                .createNounList(augmentedRoot, augmentedFormula.toInt())
            val conjResult =
                org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.ActiveParticipleModifier.instance
                    .build(augmentedRoot, rule!!.kov, augmentedRoot.form!!.toInt(), ismfael, true)

            ismmafool = AugmentedTrilateralPassiveParticipleConjugator.instance
                .createNounList(augmentedRoot, augmentedFormula.toInt())
            val ismmafoolresult =
                org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.ActiveParticipleModifier.instance
                    .build(augmentedRoot, rule.kov, augmentedRoot.form!!.toInt(), ismmafool, true)



            faelobj = conjResult.faelMafool
            mafoolobj = ismmafoolresult.faelMafool
            arrayofFaelMafool.add(faelobj)
            arrayofFaelMafool.add(mafoolobj)

            finallist.add(arrayofFaelMafool)

            return finallist
        }
        return finallist
    }

    companion object {
        val instance = GatherAll()
    }
}