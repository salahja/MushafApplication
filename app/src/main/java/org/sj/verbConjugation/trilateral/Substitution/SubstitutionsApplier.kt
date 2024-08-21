package org.sj.verbConjugation.trilateral.Substitution

import org.sj.verbConjugation.trilateral.TrilateralRoot


//todo possible error




abstract class SubstitutionsApplier {
    open fun applyd(words: MutableList<Any>, root: TrilateralRoot?) {
        val wordObj = words[0]
        val word = wordObj.toString().trim { it <= ' ' }
        val split = word.split(",")
        for (i in appliedPronounsIndecies.indices) {
            val index = appliedPronounsIndecies[i].toString().toInt() - 1

            val oneword=split[index]
            var replace = oneword.replace("]", "")
          try{
              replace = replace.replace("\\[".toRegex(), "").replace("\\]".toRegex(), "")
          } catch (e:NullPointerException ) {
              println(e.localizedMessage)
            //  Toast.makeText(ShowMushafActivity.this, "null pointer udapte", Toast.LENGTH_SHORT).show();
          }

            val list = substitutions
            val subIter = substitutions.iterator()
            while (subIter.hasNext()) {
                val substitution = subIter.next() as Substitution
                val result = root?.let { substitution.apply(replace , it) }
                if (result != null) {
                    val set = words.set(index, result )
                    break
                }
            }
        }
    }


   open fun apply(words: MutableList<Any>, root: TrilateralRoot?) {
        for (i in appliedPronounsIndecies.indices) {
            val index: Int = appliedPronounsIndecies.get(i).toString().toInt() - 1
            val wordObj = words[index] ?: continue
            val word = wordObj.toString().trim { it <= ' ' }

            val subIter: Iterator<*> = substitutions.iterator()
            while (subIter.hasNext()) {
                val substitution = subIter.next() as Substitution
                val result = root?.let { substitution.apply(word, it) }
                if (result != null) {
                    val set: Any? = words.set(index, result )
                    break
                }
            }
        }
    }
    fun applySarfSagheer(words: MutableList<String?>, root: TrilateralRoot) {
        for (i in 0..0) {
            val index = appliedPronounsIndecies[i].toString().toInt() - 1
            val wordObj = words[index] ?: continue
            val word = wordObj.trim { it <= ' ' }
            val list = substitutions
            val subIter = substitutions.iterator()
            while (subIter.hasNext()) {
                val substitution = subIter.next() as Substitution
                val result = substitution.apply(word, root!!)
                if (result != null) {
                    val set: Any? = words.set(index, result)
                    break
                }
            }
        }
    }

    abstract val substitutions: List<*>
    protected open val appliedPronounsIndecies: List<*>
        protected get() = defaultAppliedProunounsIndecies

    companion object {
        protected var defaultAppliedProunounsIndecies: MutableList<String> = ArrayList(13)

        init {
            for (i in 0..12) {
                defaultAppliedProunounsIndecies.add((i + 1).toString() + "")
            }
        }
    }

  //  abstract fun getSubstitutions(): List<*>?
}



