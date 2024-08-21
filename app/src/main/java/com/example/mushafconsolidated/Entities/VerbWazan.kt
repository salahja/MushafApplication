package com.example.mushafconsolidated.Entities

class VerbWazan {
    var root: String? = null
    var wazan: String? = null
        get() {
            if (field == "N") {
                field = "1"
            } else if (field == "Z") {
                field = "2"
            } else if (field == "F") {
                field = "3"
            } else if (field == "S") {
                field = "4"
            } else if (field == "K") {
                field = "5"
            } else if (field == "H") {
                field = "6"
            }
            return field
        }
    var arabicword: String? = null

    constructor() {}
    constructor(root: String?, arabicword: String?) {
        this.root = root
        this.arabicword = arabicword
    }

    fun getWazan(wazan: String): String {
        var wazan = wazan
        if (wazan == "N") {
            wazan = BNASARA
        } else if (wazan == "Z") {
            wazan = BZARABA
        } else if (wazan == "F") {
            wazan = BFATAH
        } else if (wazan == "S") {
            wazan = BSAMIA
        } else if (wazan == "K") {
            wazan = BKARUMU
        } else if (wazan == "H") {
            wazan = BHASIBA
        }
        return wazan
    }

    companion object {
        const val BNASARA = "نصر" //   A-U // NASRA-YANSURU
        const val BZARABA = "ضرب" //   A-I // ZARABA-YASZRIBU
        const val BFATAH = "فتح" //  A-A // FATHA-YAFTAHU
        const val BSAMIA = "سمح" //  I-A  //SAMIA-YASMAHU
        const val BKARUMU = "كرم" //   U-U  //KARUMA-YAKRUMU
        const val BHASIBA = "حسب" //  I-I  //HASIBA-YAHSIU
        fun getMazeedWazan(form: String?): String {
            var formdetails = ""
            formdetails = when (form) {
                "IV" -> "1"
                "II" -> "2"
                "III" -> "3"
                "VII" -> "4"
                "VIII" -> "5"
                "VI" -> "8"
                "V" -> "7"
                "X" -> "9"
                else -> ""
            }
            return formdetails
        }

        fun getVerbMood(selected: String?): String {
            var vmood = ""
            vmood = when (selected) {
                "IND" -> "Indicative"
                "SUBJ" -> "Subjunctive"
                "JUS" -> "Jussive"
                else -> ""
            }
            return vmood
        }
    }
}