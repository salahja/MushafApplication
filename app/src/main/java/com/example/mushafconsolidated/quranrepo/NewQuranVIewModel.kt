package com.example.mushafconsolidated.quranrepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.Entities.BadalErabNotesEnt
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.GrammarRules
import com.example.mushafconsolidated.Entities.HalEnt
import com.example.mushafconsolidated.Entities.LiajlihiEnt
import com.example.mushafconsolidated.Entities.MafoolBihi
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt
import com.example.mushafconsolidated.Entities.NewKanaEntity
import com.example.mushafconsolidated.Entities.NewMudhafEntity
import com.example.mushafconsolidated.Entities.NewNasbEntity
import com.example.mushafconsolidated.Entities.NewShartEntity
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.SifaEntity
import com.example.mushafconsolidated.Entities.TameezEnt
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.Entities.hanslexicon
import com.example.mushafconsolidated.Entities.lanerootdictionary
import com.example.mushafconsolidated.Entities.lughat
import com.example.mushafconsolidated.Entities.surahsummary
import com.example.mushafconsolidated.Entities.wbwentity
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.model.Juz
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.utility.QuranGrammarApplication
import database.entity.kov
import kotlinx.coroutines.launch
import sj.hisnul.entity.hcategoryEnt

class NewQuranVIewModel(
    private val newrepository: QuranRepository = QuranGraph.repository,
) : ViewModel() {


    private var allquran: MutableLiveData<List<QuranEntity>> = MutableLiveData()
    private val qurancorpus: MutableLiveData<List<QuranCorpusWbw>> = MutableLiveData()
    private var sursumm: LiveData<List<surahsummary>> = MutableLiveData()
    private var chapters: LiveData<List<ChaptersAnaEntity>> = MutableLiveData()
    private var chapterslist: MutableLiveData<List<ChaptersAnaEntity>> = MutableLiveData()
    var juz: LiveData<List<Juz>> = MutableLiveData()
    val util = Utils(QuranGrammarApplication.context)
    private var mafoolb: MutableLiveData<List<MafoolBihi>> = MutableLiveData()
    private var haliya: MutableLiveData<List<HalEnt>> = MutableLiveData()
    var tameez: MutableLiveData<List<TameezEnt>> = MutableLiveData()
    var mutlaq: MutableLiveData<List<MafoolMutlaqEnt>> = MutableLiveData()
    var liajlihi: MutableLiveData<List<LiajlihiEnt>> = MutableLiveData()
    var badal: MutableLiveData<List<BadalErabNotesEnt>> = MutableLiveData()
    private var bokmarks: LiveData<List<BookMarks>> = MutableLiveData()

    private var hanslist: MutableLiveData<List<hanslexicon>> = MutableLiveData()
    private var laneslist: MutableLiveData<List<lanerootdictionary>> = MutableLiveData()

    private var halword: MutableLiveData<List<HalEnt>> = MutableLiveData()
    private var ajlihiword: MutableLiveData<List<LiajlihiEnt>> = MutableLiveData()
    private var mutlaqword: MutableLiveData<List<MafoolMutlaqEnt>> = MutableLiveData()
    private var mafoolBihi: MutableLiveData<List<MafoolBihi>> = MutableLiveData()
    private var nounbywordno: MutableLiveData<List<NounCorpus>> = MutableLiveData()
    var duacategory: LiveData<List<hcategoryEnt>> = MutableLiveData()
    private var verbcorpuslist: MutableLiveData<List<VerbCorpus>> = MutableLiveData()

    var kovs: LiveData<List<kov>> = MutableLiveData()


    val allchapters: MutableLiveData<List<ChaptersAnaEntity>> = MutableLiveData()
    private var corpuswbwlist: MutableLiveData<List<QuranCorpusWbw>> = MutableLiveData()
    private var quranlist: MutableLiveData<List<QuranEntity>> = MutableLiveData()

    var kana: MutableLiveData<List<NewKanaEntity>> = MutableLiveData()
    var shart: MutableLiveData<List<NewShartEntity>> = MutableLiveData()
    private var nasab: MutableLiveData<List<NewNasbEntity>> = MutableLiveData()
    var mudhaf: MutableLiveData<List<NewMudhafEntity>> = MutableLiveData()
    private var sifa: MutableLiveData<List<SifaEntity>> = MutableLiveData()
    var wbw: MutableLiveData<List<wbwentity>> = MutableLiveData()

    var lughat: MutableLiveData<List<lughat>> = MutableLiveData()
    private var grammarules: MutableLiveData<List<GrammarRules>> = MutableLiveData()
    //  getQuranCorpusWbwBysurah

    fun getGramarRulesbyHarf(root: String): LiveData<List<GrammarRules>> {
        grammarules.value = newrepository.grammarrulesDao.getGrammarRulesByHarf(root)
        return grammarules
    }

    fun getGramarRules(): LiveData<List<GrammarRules>> {
        grammarules.value = newrepository.grammarrulesDao.grammarRules
        return grammarules
    }


    fun getRootWordDictionary(root: String): LiveData<List<lughat>> {
        lughat.value = newrepository.lughatdao.getRootWordDictionary(root)
        return lughat
    }

    fun getArabicWord(root: String): LiveData<List<lughat>> {
        lughat.value = newrepository.lughatdao.getArabicWord(root)
        return lughat
    }


    fun getwbwQuranTranslationRange(surahid: Int, ayahid: Int, startindex: Int, endindex: Int):
            LiveData<List<wbwentity>> {
        wbw.value =
            this.newrepository.wbwdao.getwbwQuranbTranslationbyrange(
                surahid,
                ayahid,
                startindex,
                endindex
            )
        return wbw

    }


    fun getwbwTranslationbywordno(
        surahid: Int,
        ayahid: Int,
        wordno: Int,
    ): LiveData<List<wbwentity>> {
        wbw.value = this.newrepository.wbwdao.getwbwTranslationbywordno(surahid, ayahid, wordno)
        return wbw

    }


    fun getkana(surah: Int, ayah: Int): LiveData<List<NewKanaEntity>> {

        kana.value = this.newrepository.getkana(surah, ayah)
        return kana
    }

    fun getshart(surah: Int, ayah: Int): LiveData<List<NewShartEntity>> {

        shart.value = this.newrepository.getshart(surah, ayah)
        return shart
    }

    fun getnasab(surah: Int, ayah: Int): LiveData<List<NewNasbEntity>> {
        nasab.value = this.newrepository.getnasab(surah, ayah)
        return nasab
    }

    fun getmudhaf(surah: Int, ayah: Int): LiveData<List<NewMudhafEntity>> {
        mudhaf.value = this.newrepository.getmudhaf(surah, ayah)
        return mudhaf
    }

    fun getsifa(surah: Int, ayah: Int): LiveData<List<SifaEntity>> {
        sifa.value = this.newrepository.getsifa(surah, ayah)
        return sifa
    }

    fun getkanasurah(surah: Int): LiveData<List<NewKanaEntity>> {
        kana.value = this.newrepository.getkanasurah(surah)
        return kana
    }

    fun getshartsurah(surah: Int): LiveData<List<NewShartEntity>> {
        shart.value = this.newrepository.getshartsurah(surah)
        return shart
    }

    fun getnasabsurah(surah: Int): LiveData<List<NewNasbEntity>> {
        nasab.value = this.newrepository.getnasabsurah(surah)
        return nasab
    }

    fun getmudhafsurah(surah: Int): LiveData<List<NewMudhafEntity>> {
        mudhaf.value = this.newrepository.getmudhafsurah(surah)
        return mudhaf
    }

    fun getsifasurah(surah: Int): LiveData<List<SifaEntity>> {
        sifa.value = this.newrepository.getsifasurah(surah)
        return sifa
    }


    fun getVerbRootBySurahAyahWord(
        cid: Int,
        aid: Int,
        wid: Int,
    ): MutableLiveData<List<VerbCorpus>> {
        verbcorpuslist.value = this.newrepository.getVerbRootBySurahAyahWord(cid, aid, wid)
        return verbcorpuslist
    }

    fun getQuranCorpusWbw(cid: Int, aid: Int, wid: Int): MutableLiveData<List<QuranCorpusWbw>> {
        corpuswbwlist.value = this.newrepository.getQuranCorpusWbw(cid, aid, wid)
        return corpuswbwlist
    }

    fun getQuranCorpusWbwBysurah(cid: Int): MutableLiveData<List<QuranCorpusWbw>> {
        corpuswbwlist.value = this.newrepository.getQuranCorpusWbwBysurah(cid)
        return corpuswbwlist
    }


    fun getQuranRootBySurahAyahWord(
        cid: Int,
        aid: Int,
        wid: Int,
    ): MutableLiveData<List<QuranCorpusWbw>> {
        corpuswbwlist.value = this.newrepository.getQuranCorpusWbw(cid, aid, wid)
        return corpuswbwlist
    }


    fun gethalsurahayah(cid: Int, aid: Int): MutableLiveData<List<HalEnt>> {
        halword.value = this.newrepository.gethalsurahayah(cid, aid)
        return halword
    }

    fun getMafoolbihiword(surah: Int, ayah: Int, wordno: Int): MutableLiveData<List<MafoolBihi>> {
        mafoolBihi.value = this.newrepository.getMafoolbihi(surah, ayah, wordno)
        return mafoolBihi
    }


    fun getAjlihiword(surah: Int, ayah: Int, wordno: Int): MutableLiveData<List<LiajlihiEnt>> {
        ajlihiword.value = this.newrepository.getAjlihiword(surah, ayah, wordno)
        return ajlihiword
    }


    fun getMutlaqWOrd(surah: Int, ayah: Int, wordno: Int): MutableLiveData<List<MafoolMutlaqEnt>> {
        mutlaqword.value = this.newrepository.getMutlaqWOrd(surah, ayah, wordno)
        return mutlaqword
    }

    fun getTameezword(surah: Int, ayah: Int, wordno: Int): MutableLiveData<List<TameezEnt>> {
        tameez.value = this.newrepository.getTameezword(surah, ayah, wordno)
        return tameez

    }

    fun getNouncorpus(surah: Int, ayah: Int, wordno: Int): MutableLiveData<List<NounCorpus>> {

        nounbywordno.value = this.newrepository.getNouncorpus(surah, ayah, wordno)
        return nounbywordno
    }


    fun getHans(root: String): MutableLiveData<List<hanslexicon>> {
        viewModelScope.launch {
            hanslist.value = newrepository.getHansRoot(root)
        }
        return hanslist
    }

    fun getLanes(root: String): MutableLiveData<List<lanerootdictionary>> {
        viewModelScope.launch {
            laneslist.value = newrepository.getLanesRoot(root)
        }
        return laneslist
    }


    fun getBookmarks(): LiveData<List<BookMarks>> {
        viewModelScope.launch {
            bokmarks = newrepository.bookmarlist
        }
        return bokmarks
    }

    fun getBookmarksCollection(): LiveData<List<BookMarks>> {
        viewModelScope.launch {
            bokmarks = newrepository.bookmarckcollection
        }
        return bokmarks
    }


    fun getHalsurah(cid: Int): MutableLiveData<List<HalEnt>> {
        viewModelScope.launch {
            haliya.value = newrepository.gethalsurah(cid)
        }
        return haliya
    }

    fun getTameezsurah(cid: Int): MutableLiveData<List<TameezEnt>> {
        viewModelScope.launch {
            tameez.value = newrepository.gettameezsurah(cid)
        }
        return tameez
    }

    fun getMutlaqSurah(cid: Int): MutableLiveData<List<MafoolMutlaqEnt>> {
        viewModelScope.launch {
            mutlaq.value = newrepository.getmutlaqsura(cid)
        }
        return mutlaq
    }

    fun getLiajlihiSurah(cid: Int): MutableLiveData<List<LiajlihiEnt>> {
        viewModelScope.launch {
            liajlihi.value = newrepository.getliajlihsura(cid)
        }
        return liajlihi
    }


    fun getMafoolSurah(cid: Int): MutableLiveData<List<MafoolBihi>> {
        viewModelScope.launch {
            mafoolb.value = newrepository.getMafoolbihiSurah(cid)
        }
        return mafoolb
    }


    //  private val allUsers: LiveData<List<ChaptersAnaEntity>> get() = allchapters


    fun loadListschapter(): MutableLiveData<List<ChaptersAnaEntity>> {

        //delay is simulating network request delay
        //delay(1000)
        //listOf is simulating usersRepository.getUsers()
        chapterslist.value = this.newrepository.chaptersmutable as List<ChaptersAnaEntity>?
        return chapterslist
    }


    fun getAllChapters(): LiveData<List<ChaptersAnaEntity>> {


        viewModelScope.launch {
            chapters = newrepository.chapters
        }


        return chapters
    }

    fun getbadalSurah(cid: Int): MutableLiveData<List<BadalErabNotesEnt>> {
        viewModelScope.launch {
            badal.value = newrepository.getbadalnotes(cid)
        }
        return badal
    }

    fun getVersesBySurahLive(cid: Int): MutableLiveData<List<QuranEntity>> {


        viewModelScope.launch {
            allquran.value = newrepository.getsurahbychap(cid)
        }


        return allquran
    }

    fun getsurahayahVerses(cid: Int, ayid: Int): MutableLiveData<List<QuranEntity>> {


        viewModelScope.launch {
            allquran.value = newrepository.getsurahbyayah(cid, ayid)
        }


        return allquran
    }

    fun getquranbySUrah(cid: Int): LiveData<List<QuranEntity>> {


        viewModelScope.launch {
            allquran.value = newrepository.getsurahbychap(cid)
        }


        return allquran
    }

    fun getQuranCorpusWbwbysurah(cid: Int): LiveData<List<QuranCorpusWbw>> {


        viewModelScope.launch {
            qurancorpus.value = newrepository.getQuranCorpusWbwbysurah(cid)
        }


        return qurancorpus
    }

    fun getsurahayahVerseslist(cid: Int, ayid: Int): LiveData<List<QuranEntity>> {


        viewModelScope.launch {
            quranlist.value = newrepository.getsurahbyayahlist(cid, ayid)
        }


        return quranlist
    }


    fun getSurahSummary(cid: Int): LiveData<List<surahsummary>> {


        viewModelScope.launch {
            sursumm = newrepository.getSurahSummary(cid)
        }


        return sursumm
    }

    fun Insertbookmark(bookmar: BookMarks) = viewModelScope.launch {

        newrepository.insertlive(bookmar)
    }

    fun deletebookmark(bookmar: BookMarks) = viewModelScope.launch {

        newrepository.delete(bookmar)
    }

    fun deleteCollection(bookmar: String) = viewModelScope.launch {

        newrepository.deletecollection(bookmar)
    }


    /*    fun update(fav: Int, id: Int) = viewModelScope.launch {

            newrepository.updateFav(fav, id)
        }

        fun DuaItembyId(cat: String): LiveData<List<hduadetailsEnt>> {

            viewModelScope.launch {
                allduaitem = newrepository.getDuaitems(cat)
            }


            return allduaitem
        }*/


}

