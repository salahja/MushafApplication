package com.example.mushafconsolidated.DAO

class BookMarksPojo() {
    var id: Int = 0
    var header: String? = null
    private var verseno: String? = null
    private var chapterno: String? = null
    private var surahname: String? = null
    private var datetime: String? = null
    var count: String? = null
    fun getVerseno(): String {
        return (verseno)!!
    }

    fun setVerseno(verseno: String) {
        this.verseno = verseno
    }

    fun getChapterno(): String {
        return (chapterno)!!
    }

    fun setChapterno(chapterno: String) {
        this.chapterno = chapterno
    }

    fun getSurahname(): String {
        return (surahname)!!
    }

    fun setSurahname(surahname: String) {
        this.surahname = surahname
    }

    fun getDatetime(): String {
        return (datetime)!!
    }

    fun setDatetime(datetime: String?) {
        this.datetime = datetime
    }
}