package org.sj.verbConjugation.util

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.utility.QuranGrammarApplication
import database.VerbDatabaseUtils
import database.verbrepo.VerbModel
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import java.util.LinkedList

class OSarfDictionary private constructor() {
    fun getAugmentedTrilateralRoot(rootText: String): AugmentedTrilateralRoot? {
        val c1 = rootText[0]
        val c2 = rootText[1]
        val c3 = rootText[2]
        val roots: MutableList<String> = LinkedList()
        val augroot = AugmentedTrilateralRoot()
        //  AugmentedTrilateralRootTree augmentedRootsTree = DatabaseManager.getInstance().getAugmentedTrilateralRootTree(c1);
        val utils = VerbDatabaseUtils(QuranGrammarApplication.context)
        //  final ArrayList<VerbsTriMazeedDictEntity> triVerbMazeed = utils.getTriVerbMazeed(rootText);
        for (root in utils.getMazeedRoot(rootText)!!) {
            roots.add(root!!.root)
            augroot.c1 = root.root[0]
            augroot.c2 = root.root[1]
            augroot.c3 = root.root[2]
            augroot.form = root.form
            augroot.babname = root.babname
            augroot.verbtype = root.verbtype
            return augroot
        }
        return null
    }

    fun getAugmentedTrilateralRoot(rootText: String, formula: String): AugmentedTrilateralRoot? {
        val c1 = rootText[0]
        val c2 = rootText[1]
        val c3 = rootText[2]
        val roots: MutableList<String> = LinkedList()
        val augroot = AugmentedTrilateralRoot()
        //  AugmentedTrilateralRootTree augmentedRootsTree = DatabaseManager.getInstance().getAugmentedTrilateralRootTree(c1);
        val utils = VerbDatabaseUtils(QuranGrammarApplication.context)
        val triVerbMazeed = utils.getMazeedRoot(rootText)
     val   viewmodel = ViewModelProvider.AndroidViewModelFactory(QuranGrammarApplication.context!! as Application).create(VerbModel::class.java)
       // val myViewModel3 = ViewModelProvider(viewLifecycleOwner).get(VerbModel::class.java)
      //  val viewmodel: VerbModel by viewModels()
        //val myViewModel5 by viewModels<VerbModel>()
      /*  viewmodel.getMazeedRoot(rootText).observe(Requireac,Observer){


        }*/
        for (root in triVerbMazeed!!) {
            if (root!!.form == formula) {
                roots.add(root.root)
                augroot.c1 = root.root[0]
                augroot.c2 = root.root[1]
                augroot.c3 = root.root[2]
                augroot.form = root.form
                augroot.babname = root.babname
                augroot.verbtype = root.verbtype
                //   return augroot;
            }
        }
        if (augroot.babname == null && !triVerbMazeed.isEmpty()) {
            roots.add(triVerbMazeed[0]!!.root)
            augroot.c1 = triVerbMazeed[0]!!.root[0]
            augroot.c2 = triVerbMazeed[0]!!.root[1]
            augroot.c3 = triVerbMazeed[0]!!.root[2]
            augroot.form = triVerbMazeed[0]!!.form
            augroot.babname = triVerbMazeed[0]!!.babname
            augroot.verbtype = triVerbMazeed[0]!!.verbtype
        }
        return if (augroot.babname != null) {
            augroot
        } else null
    }

    /*
    Iterator iter = roots.iterator();
    while (iter.hasNext()) {
      AugmentedTrilateralRoot aRoot = (AugmentedTrilateralRoot) iter.next();
      if (aRoot.getC1() == c1 && aRoot.getC2() == c2 && aRoot.getC3() == c3) {
        return aRoot;
      }
    }
    return null;
  }
*/
    /*
  public List getUnaugmentedTrilateralRoots(String rootText) {
    char c1 = rootText.charAt(0);
    char c2 = rootText.charAt(1);
    char c3 = rootText.charAt(2);

    UnaugmentedTrilateralRootTree unaugmentedRootsTree = DatabaseManager.getInstance().getUnaugmentedTrilateralRootTree(c1);
    List roots = unaugmentedRootsTree.getRoots();
    java.util.List result = new LinkedList();
    Iterator iter = roots.iterator();
    while (iter.hasNext()) {
      UnaugmentedTrilateralRoot root = (UnaugmentedTrilateralRoot) iter.next();
      if (root.getC1() == c1 && root.getC2() == c2 && root.getC3() == c3) {
        result.add(root);
      }
    }
    return result;
  }

  public AugmentedQuadriliteralRoot getAugmentedQuadrilateralRoot(String rootText) {
    char c1 = rootText.charAt(0);
    char c2 = rootText.charAt(1);
    char c3 = rootText.charAt(2);
    char c4 = rootText.charAt(3);

    AugmentedQuadriliteralRootTree augmentedRootsTree = null;
    try {
      augmentedRootsTree = DatabaseManager.getInstance().getAugmentedQuadriliteralRootTree(c1);
    }
    catch (Exception ex) {
      //الملف غير موجود
      return null;
    }
    List roots = augmentedRootsTree.getRoots();
    Iterator iter = roots.iterator();
    while (iter.hasNext()) {
      AugmentedQuadriliteralRoot aRoot = (AugmentedQuadriliteralRoot) iter.next();
      if (aRoot.getC1() == c1 && aRoot.getC2() == c2 && aRoot.getC3() == c3 && aRoot.getC4() == c4) {
        return aRoot;
      }
    }
    return null;
  }

  public UnaugmentedQuadriliteralRoot getUnaugmentedQuadrilateralRoot(String rootText) {
    char c1 = rootText.charAt(0);
    char c2 = rootText.charAt(1);
    char c3 = rootText.charAt(2);
    char c4 = rootText.charAt(3);

    UnaugmentedQuadriliteralRootTree rootsTree = null;
    try {
      rootsTree = DatabaseManager.getInstance().getUnaugmentedQuadriliteralRootTree(c1);
    }
    catch (Exception ex) {
      //الملف غير موجود
      return null;
    }
    java.util.List roots = rootsTree.getRoots();

    Iterator iter = roots.iterator();
    while (iter.hasNext()) {
      UnaugmentedQuadriliteralRoot aRoot = (UnaugmentedQuadriliteralRoot) iter.next();
      if (aRoot.getC1() == c1 && aRoot.getC2() == c2 && aRoot.getC3() == c3 && aRoot.getC4() == c4) {
        return aRoot;
      }
    }

    return null;
  }


  */
    fun getUnaugmentedTrilateralRootsLists(rootText: String): List<UnaugmentedTrilateralRoot> {
        val c1 = rootText[0]
        val c2 = rootText[1]
        val c3 = rootText[2]
        val result: MutableList<UnaugmentedTrilateralRoot> = LinkedList()
        val utils = VerbDatabaseUtils(QuranGrammarApplication.context)
        val unaugmentedTrilateralRoot = UnaugmentedTrilateralRoot()
        //  UnaugmentedTrilateralRootTree unaugmentedRootsTree = DatabaseManager.getInstance().getUnaugmentedTrilateralRootTree(c1);
        for (trimujarrad in utils.getMujarradVerbs(rootText)!!) {
            unaugmentedTrilateralRoot.c1 = trimujarrad!!.root[0]
            unaugmentedTrilateralRoot.c2 = trimujarrad.root[1]
            unaugmentedTrilateralRoot.c3 = trimujarrad.root[2]
            unaugmentedTrilateralRoot.conjugation = trimujarrad.bab
            unaugmentedTrilateralRoot.conjugationname = trimujarrad.babname
            unaugmentedTrilateralRoot.verbtype = trimujarrad.verbtype
            unaugmentedTrilateralRoot.verb = trimujarrad.verb
            result.add(unaugmentedTrilateralRoot)
        }
        //  return unaugmentedTrilateralRoot;
        return result
    }

    fun getUnaugmentedTrilateralRoots(rootText: String): UnaugmentedTrilateralRoot {
        val c1 = rootText[0]
        val c2 = rootText[1]
        val c3 = rootText[2]
        val utils = VerbDatabaseUtils(QuranGrammarApplication.context)
        val trimujarrad = utils.getMujarradVerbs(rootText)
        val unaugmentedTrilateralRoot = UnaugmentedTrilateralRoot()
        //  UnaugmentedTrilateralRootTree unaugmentedRootsTree = DatabaseManager.getInstance().getUnaugmentedTrilateralRootTree(c1);
        unaugmentedTrilateralRoot.c1 = trimujarrad!![0]!!.root[0]
        unaugmentedTrilateralRoot.c2 = trimujarrad[0]!!.root[1]
        unaugmentedTrilateralRoot.c3 = trimujarrad[0]!!.root[2]
        unaugmentedTrilateralRoot.conjugation = trimujarrad[0]!!.bab
        unaugmentedTrilateralRoot.conjugationname = trimujarrad[0]!!.babname
        unaugmentedTrilateralRoot.verbtype = trimujarrad[0]!!.verbtype
        unaugmentedTrilateralRoot.verb = trimujarrad[0]!!.verb
        unaugmentedTrilateralRoot.rulename = trimujarrad[0]!!.kovname
        val result: MutableList<UnaugmentedTrilateralRoot> = LinkedList()
        result.add(unaugmentedTrilateralRoot)
        return unaugmentedTrilateralRoot
        // return result;
    }

    fun getUnaugmentedTrilateralRoots(
        rootText: String,
        formula: String
    ): UnaugmentedTrilateralRoot {
        val c1 = rootText[0]
        val c2 = rootText[1]
        val c3 = rootText[2]
        val utils = VerbDatabaseUtils(QuranGrammarApplication.context)
        val trimujarrad = utils.getMujarradVerbs(rootText)
        val unaugmentedTrilateralRoot = UnaugmentedTrilateralRoot()

        for (tri in trimujarrad!!) {
            if (tri!!.bab == formula) {
                unaugmentedTrilateralRoot.c1 = tri.root[0]
                unaugmentedTrilateralRoot.c2 = tri.root[1]
                unaugmentedTrilateralRoot.c3 = tri.root[2]
                unaugmentedTrilateralRoot.conjugation = tri.bab
                unaugmentedTrilateralRoot.conjugationname = tri.babname
                unaugmentedTrilateralRoot.verbtype = tri.verbtype
                unaugmentedTrilateralRoot.verb = tri.verb
            }
        }
        if (unaugmentedTrilateralRoot.conjugation == null && !trimujarrad.isEmpty()) {
            unaugmentedTrilateralRoot.c1 = trimujarrad[0]!!.root[0]
            unaugmentedTrilateralRoot.c2 = trimujarrad[0]!!.root[1]
            unaugmentedTrilateralRoot.c3 = trimujarrad[0]!!.root[2]
            unaugmentedTrilateralRoot.conjugation = trimujarrad[0]!!.bab
            unaugmentedTrilateralRoot.conjugationname = trimujarrad[0]!!.babname
            unaugmentedTrilateralRoot.verbtype = trimujarrad[0]!!.verbtype
            unaugmentedTrilateralRoot.verb = trimujarrad[0]!!.verb
        }
        val result: MutableList<UnaugmentedTrilateralRoot> = LinkedList()
        result.add(unaugmentedTrilateralRoot)
        return unaugmentedTrilateralRoot
        // return result;
    }

    companion object {
        val instance = OSarfDictionary()
    }
}