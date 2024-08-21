package com.example.utility

interface CorpusConstants {
    interface Nominals {
        companion object {
            const val N = "Noun(اسم)"
            const val PN = "Proper Noun(اسم علم)"
            const val ADJ = "Adjective(صفة)"
            const val VN = "Verbal noun(مصدر)"
            const val IMPN = "Imperative Verbal Noun(اسم فعل أمر)"
            const val PRON = "Pronouns(ضمير)"
            const val DEM = "Demonstrative Pronoun(اسم اشارة)"
            const val REL = "Relative Pronoun(اسم موصول)"
            const val T = "Time Adverb(ظرف زمان)"
            const val LOC = "Location Adverb(ظرف مكان)"
        }
    }

    interface NominalsEnglish {
        companion object {
            const val N = "Noun "
            const val PN = "Proper Noun "
            const val ADJ = "Adjective "
            const val VN = "Verbal noun "
            const val IMPN = "Imperative Verbal Noun "
            const val PRON = "Pronouns(ضمير)"
            const val DEM = "Demonstrative Pronoun "
            const val REL = "Relative Pronoun "
            const val T = "Time Adverb "
            const val LOC = "Location Adverb "
        }
    }

    interface NominalsProp {
        companion object {
            const val ACT = "Active "
            const val PCPL = "PCPL"
            const val PASS = "Passive "
            const val ACTPCPL = "Active participle(اسم فاعل) "
            const val PASSPCPL = "Passive participle(سم مفعول) "
            const val VN = "Verbal noun(مصدر)"
            const val INDEF = "Indefinite(نكرة)"
            const val DEF = " Definite(معرفة) "
            const val NOM = "Nominative(مرفوع)"
            const val ACC = "Accusative( نصب)"
            const val GEN = " Genitive(نكرة)"
            const val T = "Time Adverb(ظرف زمان)"
            const val LOC = "Location Adverb(ظرف مكان)"
        }
    }

    interface Particles {
        companion object {
            const val P = "Prepositions(حرف جر)"
            const val DET = "determiner()"
            const val EMPH = "Emphatic lām prefix(لام التوكيد) "
            const val IMPV = "Imperative lāmprefix(لام الامر)"
            const val PRP = "Purpose lāmprefix(لام التعليل)"
            const val CONJ = "Coordinating conjunction(حرف عطف)"
            const val SUB = "	Subordinating conjunction(حرف مصدري)"
            const val ACC = "	Accusative particle(حرف نصب)"
            const val AMD = "	Amendment particle(حرف استدراك)	"
            const val ANS = "	Answer particle	(حرف جواب)"
            const val AVR = "	Aversion particle	(حرف ردع)"
            const val CAUS = "Particle of cause	(حرف سببية)"
            const val CERT = "Particle of certainty	(حرف تحقيق)"
            const val CIRC = "Circumstantial particle	(حرف حال)"
            const val COM = "	Comitative particle	(واو المعية)"
            const val COND = "Conditional particle(حرف شرط)"
            const val EQ = "	Equalization particle(حرف تسوية)"
            const val EXH = "	Exhortation particle(حرف تحضيض)"
            const val EXL = "	Explanation particle(حرف تفصيل)"
            const val EXP = "	Exceptive particle	(أداة استثناء)"
            const val FUT = "	Future particle	(حرف استقبال)"
            const val INC = "	Inceptive particle	(حرف ابتداء)"
            const val INT = "	Particle of interpretation(حرف تفسير)"
            const val INTG = "Interogative particle	(حرف استفهام)"
            const val NEG = "	Negative particle(حرف نفي)"
            const val PREV = "Preventive particle	(حرف كاف)"
            const val PRO = "	Prohibition particle(حرف نهي)"
            const val REM = "	Resumption particle	(حرف استئنافية)"
            const val RES = "	Restriction particle(أداة حصر)"
            const val RET = "	Retraction particle	(حرف اضراب)"
            const val RSLT = "Result particle(حرف واقع في جواب الشرط)"
            const val SUP = "	Supplemental particle	(حرف زائد)"
            const val SUR = "	Surprise particle	(حرف فجاءة)"
            const val VOC = "	Vocative particle	(حرف نداء)"
            const val INL = "	Quranic initials(	(حروف مقطعة	"
        }
    }

    interface verbfeaturesenglisharabic {
        companion object {
            const val PERF = "Perfect verb(فعل ماض)"
            const val IMPF = "Imperfect verb(فعل مضارع)"
            const val IMPV = "	Imperative verb(فعل أمر )"
            const val IND = "Indicative mood (مرفوع)"
            const val SUBJ = "	Subjunctive mood(منصوب)"
            const val JUS = "	Jussive mood(مجزوم)"
            const val ACT = "	Active voice(مبني للمعلوم)"
            const val V = "Verb(فعل)"
            const val PASS = "	Passive voice(مبني للمجهول)"
        }
    }

    interface verbfeaturesenglish {
        companion object {
            const val PERF = "Perfect "
            const val IMPF = "Imperfect"
            const val IMPV = "Imperative"
            const val IND = "Indicative "
            const val SUBJ = "Subjunctive. "
            const val JUS = "	Jussive  "
            const val ACT = "	Active"
            const val PASS = "Passive"
        }
    }

    interface png {
        companion object {
            const val one = "First person "
            const val two = "Second Person "
            const val three = "Third Person "
            const val M = "Masculine "
            const val F = "Feminine "
            const val S = "Singular "
            const val P = "Plural "
            const val D = "Dual "
        }
    }

    interface thulathi {
        companion object {
            const val NASARA = "نصر"
            const val ZARABA = "ضرب"
            const val FATAH = "فتح"

            //  A-A // FATHA-YAFTAHU
            const val SAMIA = "سمح"

            //  I-A  //SAMIA-YASMAHU
            const val KARUMU = "كرم"

            //   U-U  //KARUMA-YAKRUMU
            const val HASIBA = "حسب" //  I-I  //HASIBA-YAHSIU
        }
    }
}