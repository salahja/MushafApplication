package sj.hisnul.activity

/**
 * Created by sana on 26/7/18.
 */
class ParentItem {
    var name: String? = null
    var title: String? = null
    var activeFilter: String? = null
    var chapterid = 0
    var childList = ArrayList<ChildItem>()

    constructor() {}
    constructor(title: String?, activeFilter: String?) {
        this.title = title
        this.activeFilter = activeFilter
    }

    constructor(name: String?, childList: ArrayList<ChildItem>) {
        this.name = name
        this.childList = childList
    }
}