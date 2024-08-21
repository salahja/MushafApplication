package com.example.utility

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.Group

/**
 * Manage a ConstraintLayout Group view membership as a view's visibility is changed. Calling
 * [.setVisibility] will set a view's visibility and remove it from the group.
 * Other methods here provide explicit means to manage a group's view membership.
 *
 *
 * Usage: In XML define
 * <pre>`<[Package].ManagedGroup
 * android:id="@+id/group"
 * android:layout_width="wrap_content"
 * android:layout_height="wrap_content"
 * android:visibility="visible"
 * app:constraint_referenced_ids="id1,id2,id3..." />`
</pre> *
 */
class ManageGroup : Group {
    private val mRemovedRefIds: MutableSet<Int> = HashSet()

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    /**
     * Set the reference ids for the group and clear the removed id array.
     *
     * @param ids All identifiers in the group.
     */
    override fun setReferencedIds(ids: IntArray) {
        super.setReferencedIds(ids)
        mRemovedRefIds.clear()
    }

    /**
     * Set visibility for  view and remove the view's id from the group.
     *
     * @param view       View for visibility change
     * @param visibility View.VISIBLE, View.INVISIBLE or View.GONE.
     */
    fun setVisibility(view: View, visibility: Int) {
        removeReferencedIds(view.id)
        view.visibility = visibility
    }

    /**
     * Add all removed views back into the group.
     */
    fun resetGroup() {
        referencedIds = allReferencedIds
    }

    /**
     * Remove reference ids from the group. This is done automatically when
     * setVisibility(View view, int visibility) is called.
     *
     * @param idsToRemove All the ids to remove from the group.
     */
    private fun removeReferencedIds(vararg idsToRemove: Int) {
        for (id in idsToRemove) {
            mRemovedRefIds.add(id)
        }
        val refIds = referencedIds
        val newRefIdSet: MutableSet<Int> = HashSet()
        for (id in refIds) {
            if (!mRemovedRefIds.contains(id)) {
                newRefIdSet.add(id)
            }
        }
        super.setReferencedIds(copySetToIntArray(newRefIdSet))
    }

    /**
     * Add reference ids to the group.
     *
     * @param idsToAdd Identifiers to add to the group.
     */
    fun addReferencedIds(vararg idsToAdd: Int) {
        for (id in idsToAdd) {
            mRemovedRefIds.remove(id)
        }
        super.setReferencedIds(joinArrays(referencedIds, idsToAdd))
    }

    /**
     * Return int[] of all ids in the group plus those removed.
     *
     * @return All current ids in group plus those removed.
     */
    private val allReferencedIds: IntArray
        get() = joinArrays(referencedIds, copySetToIntArray(mRemovedRefIds))

    private fun copySetToIntArray(fromSet: Set<Int>): IntArray {
        val toArray = IntArray(fromSet.size)
        var i = 0
        for (id in fromSet) {
            toArray[i++] = id
        }
        return toArray
    }

    private fun joinArrays(array1: IntArray, array2: IntArray): IntArray {
        val joinedArray = IntArray(array1.size + array2.size)
        System.arraycopy(array1, 0, joinedArray, 0, array1.size)
        System.arraycopy(array2, 0, joinedArray, array1.size, array2.size)
        return joinedArray
    }
}