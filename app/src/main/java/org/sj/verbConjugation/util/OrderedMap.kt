package org.sj.verbConjugation.util

import java.util.LinkedList

class OrderedMap : HashMap<Any?, Any?>() {
    var orderedKeys: MutableList<Any?> = LinkedList()

    /**
     * according to the order of put method a list of keys is ccreated
     *
     * @param key   Object
     * @param value Object
     * @return Object
     */
    override fun put(key: Any?, value: Any?): Any? {
        orderedKeys.add(key)
        return super.put(key, value)
    }

    override fun remove(key: Any?): Any? {
        orderedKeys.remove(key)
        return super.remove(key)
    }
}
/*

    fun getOrderedKeys(): List<Any?> {
        return orderedKeys
    }
}*/
