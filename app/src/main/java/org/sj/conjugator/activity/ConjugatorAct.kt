package org.sj.conjugator.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import com.example.Constant.QURAN_VERB_ROOT
import com.example.Constant.QURAN_VERB_WAZAN
import com.example.Constant.SARFKABEER
import com.example.Constant.VERBMOOD
import com.example.Constant.VERBTYPE
import com.example.mushafconsolidated.Activity.QuranGrammarAct
import com.example.mushafconsolidated.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import database.VerbDatabaseUtils
import database.entity.MazeedEntity
import database.entity.MujarradVerbs
import database.verbrepo.VerbModel
import org.sj.conjugator.fragments.SettingsFragmentVerb
import org.sj.conjugator.utilities.SharedPref
import ru.dimorinny.floatingtextbutton.FloatingTextButton

class ConjugatorAct : BaseActivity(), View.OnClickListener {
    private val keyValues: SparseArray<String> = SparseArray<String>()
    private lateinit var quranbtn: Button
    private lateinit var settingbtn: Button
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var layoutBottomSheet: RelativeLayout
    private lateinit var sheetBehavior: BottomSheetBehavior<*>
    private var tlist: ListView? = null
    private var mlist: ListView? = null
    lateinit var nasara: Chip
    private lateinit var zaraba: Chip
    private lateinit var samia: Chip
    private lateinit var fataha: Chip
    private lateinit var karuma: Chip
    private lateinit var haseeba: Chip
    private lateinit var tafeel: Chip
    private lateinit var mufala: Chip
    private lateinit var ifal: Chip
    private lateinit var tafaul: Chip
    private lateinit var tafaaul: Chip
    private lateinit var infala: Chip
    private lateinit var iftiala: Chip
    private lateinit var istifala: Chip
    private lateinit var mujarradbtn: MaterialButton
    private lateinit var mazeedbtn: MaterialButton
    private var isSarfKabeed = false
    private var mujarradVerbs: ArrayList<MujarradVerbs> = ArrayList()
    private lateinit var editTextAuto: EditText
    private lateinit var verbmood: RadioGroup
    private lateinit var indicative: RadioButton
    private lateinit var subjunctive: RadioButton
    private lateinit var inputtext: String
    private lateinit var keyboard: View

    private lateinit var inputConnection: InputConnection
    private var mazeedEntityVerbs: ArrayList<MazeedEntity> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conjugator_key_activity_autocomplete)
        KeyboardUtil.hideKeyboard(this@ConjugatorAct)
        keyboard = findViewById(R.id.arabic_keyboard)
        val callButton: FloatingTextButton = findViewById(R.id.action_buttons)
        callButton.setOnClickListener { view: View? ->
            super@ConjugatorAct.finish()
            super.onBackPressed()
        }
        //    hideKeyboardSoft();
     //   contextOfApplication = getApplicationContext()
        SetUpAutoComplete()
        val ic: InputConnection = editTextAuto.onCreateInputConnection(EditorInfo())
        // InputConnection ic = editTextAuto.onCreateInputConnection(new EditorInfo());
        setInputConnection(ic)
        init()
        // kb. getCharSequence();
    }

    @SuppressLint("CutPasteId")
    private fun SetUpAutoComplete() {
        KeyboardUtil.hideKeyboard(this@ConjugatorAct)
        val viewmodel: VerbModel by viewModels()
        var root: Array<String?> = emptyArray()
        val util = VerbDatabaseUtils(this@ConjugatorAct)
        //   val verbAll: ArrayList<MujarradVerbs?>? = util.mujarradAall
        // val size = verbAll?.size
        //   root = arrayOfNulls(size!!)
        val actv: AutoCompleteTextView =
            findViewById(R.id.autoCompleteTextView)
        editTextAuto = findViewById(R.id.autoCompleteTextView)
        var i = 0
        viewmodel.getMujarradll().observe(this) {
            val len = it.size
            root = arrayOfNulls(len)
            val arr = mutableListOf<String>()
            for (entity in it) {
                arr.add(entity.root)


            }


            val autoadapter: ArrayAdapter<String> =
                ArrayAdapter<String>(this, R.layout.dropdown_item_list, arr)
            //Getting the instance of AutoCompleteTextView

            val sizes = 500
            actv.dropDownHeight = sizes
            actv.threshold = 1 //will start working from first character
            actv.setAdapter(autoadapter) //setting the adapter data into the AutoCompleteTextView
            //  actv.adapter=autoadapter
            actv.setTextColor(Color.RED)
            //   actv.setTextSize((float) 50.00);

            actv.setRawInputType(InputType.TYPE_CLASS_TEXT)
            actv.setTextIsSelectable(true)
            //   KeyboardUtil.hideKeyboard(this);
            actv.showSoftInputOnFocus = false
            actv.setOnFocusChangeListener({ view: View?, hasFocus: Boolean ->
                if (hasFocus) {
                    keyboard.visibility = LinearLayout.VISIBLE
                    if (tlist != null) tlist!!.adapter = null
                    if (mlist != null) mlist!!.adapter = null
                } //   keyboard.setVisibility(LinearLayout.GONE);
            })
        }


    }

    private fun init() {
        quranbtn = findViewById(R.id.qurangrammar)
        settingbtn = findViewById(R.id.conjugatorsetting)
        layoutBottomSheet = findViewById(R.id.bottom_sheet)
        sheetBehavior = BottomSheetBehavior.from<View>(layoutBottomSheet)
        floatingActionButton = findViewById(R.id.fab)
        mujarradbtn = findViewById(R.id.mujarradbtn)
        mazeedbtn = findViewById(R.id.mazeedbtn)
        nasara = findViewById(R.id.nasara)
        zaraba = findViewById(R.id.zaraba)
        samia = findViewById(R.id.samia)
        fataha = findViewById(R.id.fataha)
        karuma = findViewById(R.id.karuma)
        haseeba = findViewById(R.id.hasiba)
        tafeel = findViewById(R.id.tafeel)
        mufala = findViewById(R.id.mufala)
        ifal = findViewById(R.id.ifal)
        tafaul = findViewById(R.id.tafaul)
        tafaaul = findViewById(R.id.tafaaaul)
        infala = findViewById(R.id.infala)
        iftiala = findViewById(R.id.iftiala)
        istifala = findViewById(R.id.istifala)
        verbmood = findViewById(R.id.verbcase)
        indicative = findViewById(R.id.rdindicative)
        subjunctive = findViewById(R.id.rdsubjunctive)
        val jussive: RadioButton = findViewById(R.id.rdjussive)
        val emphasized: RadioButton = findViewById(R.id.emphasized)
        keyboard = findViewById(R.id.arabic_keyboard)
        val key_delete: Button = findViewById(R.id.key_delete)
        val key_AC: Button = findViewById(R.id.key_AC)
        val key_enter: Button = findViewById(R.id.key_enter)
        keyboard = findViewById(R.id.arabic_keyboard)
        val dhad: Button = findViewById(R.id.dhad)
        val suwad: Button = findViewById(R.id.suwad)
        val qaf: Button = findViewById(R.id.qaf)
        val fa: Button = findViewById(R.id.fa)
        val ghain: Button = findViewById(R.id.ghain)
        val ayn: Button = findViewById(R.id.ayn)
        val haaa: Button = findViewById(R.id.haaa)
        val kha: Button = findViewById(R.id.kha)
        val ha: Button = findViewById(R.id.ha)
        val jeem: Button = findViewById(R.id.jeem)
        //     GRadioGroup gr = new GRadioGroup(nasara,zaraba,samia,fatha,karuma,hasiba,two,three,four,five,six,seven,eight,ten);
        quranbtn.setOnClickListener(this)
        settingbtn.setOnClickListener(this)
        floatingActionButton.setOnClickListener(this)
        mujarradbtn.setOnClickListener(this)
        mazeedbtn.setOnClickListener(this)
        nasara.setOnClickListener(this)
        zaraba.setOnClickListener(this)
        samia.setOnClickListener(this)
        fataha.setOnClickListener(this)
        karuma.setOnClickListener(this)
        haseeba.setOnClickListener(this)
        tafeel.setOnClickListener(this)
        tafaul.setOnClickListener(this)
        tafaaul.setOnClickListener(this)
        mufala.setOnClickListener(this)
        infala.setOnClickListener(this)
        istifala.setOnClickListener(this)
        iftiala.setOnClickListener(this)
        ifal.setOnClickListener(this)
        dhad.setOnClickListener(this)
        suwad.setOnClickListener(this)
        qaf.setOnClickListener(this)
        fa.setOnClickListener(this)
        ghain.setOnClickListener(this)
        ayn.setOnClickListener(this)
        haaa.setOnClickListener(this)
        kha.setOnClickListener(this)
        ha.setOnClickListener(this)
        jeem.setOnClickListener(this)
        keyValues.put(R.id.dhad, "ض")
        keyValues.put(R.id.suwad, "ص")
        keyValues.put(R.id.qaf, "ق")
        keyValues.put(R.id.fa, "ف")
        keyValues.put(R.id.ghain, "غ")
        keyValues.put(R.id.ayn, "ع")
        keyValues.put(R.id.haaa, "ه")
        keyValues.put(R.id.kha, "خ")
        keyValues.put(R.id.ha, "ح")
        keyValues.put(R.id.jeem, "ج")
        val sheen: Button = findViewById(R.id.sheen)
        val seen: Button = findViewById(R.id.seen)
        val ya: Button = findViewById(R.id.ya)
        val ba: Button = findViewById(R.id.ba)
        val lam: Button = findViewById(R.id.lam)
        val alif: Button = findViewById(R.id.hamza)
        val ta: Button = findViewById(R.id.ta)
        val nun: Button = findViewById(R.id.nun)
        val meem: Button = findViewById(R.id.meem)
        val kaf: Button = findViewById(R.id.kaf)
        sheen.setOnClickListener(this)
        seen.setOnClickListener(this)
        ya.setOnClickListener(this)
        ba.setOnClickListener(this)
        lam.setOnClickListener(this)
        alif.setOnClickListener(this)
        ta.setOnClickListener(this)
        nun.setOnClickListener(this)
        meem.setOnClickListener(this)
        kaf.setOnClickListener(this)
        //   key00.setOnClickListener(this);
        key_delete.setOnClickListener(this)
        key_AC.setOnClickListener(this)
        key_enter.setOnClickListener(this)
        //  key_dot.setOnClickListener(this);
        keyValues.put(R.id.sheen, "ش")
        keyValues.put(R.id.seen, "س")
        keyValues.put(R.id.ya, "ي")
        keyValues.put(R.id.ba, "ب")
        keyValues.put(R.id.lam, "ل")
        keyValues.put(R.id.hamza, "ء")
        keyValues.put(R.id.ta, "ت")
        keyValues.put(R.id.nun, "ن")
        keyValues.put(R.id.meem, "م")
        keyValues.put(R.id.kaf, "ك")
        val zoay: Button = findViewById(R.id.zoay)
        val toay: Button = findViewById(R.id.toay)
        val dhal: Button = findViewById(R.id.dhal)
        val dal: Button = findViewById(R.id.dal)
        val zaa: Button = findViewById(R.id.zaa)
        val raa: Button = findViewById(R.id.raa)
        val waw: Button = findViewById(R.id.waw)
        val tamarboot: Button = findViewById(R.id.tamarboota)
        val tha: Button = findViewById(R.id.tha)
        zoay.setOnClickListener(this)
        toay.setOnClickListener(this)
        dhal.setOnClickListener(this)
        dal.setOnClickListener(this)
        zaa.setOnClickListener(this)
        raa.setOnClickListener(this)
        waw.setOnClickListener(this)
        tamarboot.setOnClickListener(this)
        tha.setOnClickListener(this)
        keyValues.put(R.id.zoay, "ظ")
        keyValues.put(R.id.toay, "ط")
        keyValues.put(R.id.dhal, "ذ")
        keyValues.put(R.id.dal, "د")
        keyValues.put(R.id.zaa, "ز")
        keyValues.put(R.id.raa, "ر")
        keyValues.put(R.id.waw, "و")
        keyValues.put(R.id.tamarboota, "ة")
        keyValues.put(R.id.tha, "ث")
        clearParameters()
    }

    private fun clearParameters() {
        nasara.visibility = View.GONE
        zaraba.visibility = View.GONE
        fataha.visibility = View.GONE
        samia.visibility = View.GONE
        karuma.visibility = View.GONE
        haseeba.visibility = View.GONE
        ifal.visibility = View.GONE
        tafeel.visibility = View.GONE
        tafaul.visibility = View.GONE
        tafaaul.visibility = View.GONE
        infala.visibility = View.GONE
        iftiala.visibility = View.GONE
        istifala.visibility = View.GONE
        mufala.visibility = View.GONE
    }

    override fun onClick(view: View) {
        //   hideKeyboardSoft();
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN, BottomSheetBehavior.STATE_DRAGGING, BottomSheetBehavior.STATE_SETTLING, BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_COLLAPSED -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
        if (inputConnection == null) {
            val logTag = "Keyboard"
            Log.i(logTag, "Input connection == null")
            return
        }
        val currentText: CharSequence =
            inputConnection.getExtractedText(ExtractedTextRequest(), 0).text
        val beforeCursorText: CharSequence? =
            inputConnection.getTextBeforeCursor(currentText.length, 0)
        val afterCursorText: CharSequence? =
            inputConnection.getTextAfterCursor(currentText.length, 0)
        when (view.id) {
            R.id.conjugatorsetting -> {
                val fragment = SettingsFragmentVerb()
                val transaction: FragmentTransaction =
                    supportFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                transaction.replace(R.id.frame_container, fragment)
                transaction.addToBackStack("setting")
                transaction.commit()
            }

            R.id.qurangrammar -> {
                val intent = Intent(this@ConjugatorAct, QuranGrammarAct::class.java)
                finish()
                startActivity(intent)
            }

            R.id.fab -> {
                toggleBottomSheet()
                val charSequence: CharSequence? =
                    inputConnection.getTextBeforeCursor(currentText.length, 0)
                if (charSequence.toString().length == 3) {
                    inputtext = charSequence.toString()
                    InitSelecton(charSequence.toString())
                }
            }

            R.id.key_enter -> {
                val charSequence: CharSequence? =
                    inputConnection.getTextBeforeCursor(currentText.length, 0)
                if (charSequence.toString().length == 3) {
                    inputtext = charSequence.toString()
                    InitSelecton(charSequence.toString())
                }
            }

            R.id.key_delete -> {
                val selectedText: CharSequence = inputConnection.getSelectedText(0)
                val charSequences: CharSequence? = inputConnection.getTextBeforeCursor(10, 10)
                if (TextUtils.isEmpty(selectedText)) inputConnection.deleteSurroundingText(
                    1,
                    0
                ) else inputConnection.commitText("", 1)
            }

            R.id.key_AC -> if (beforeCursorText != null) {
                if (afterCursorText != null) {
                    inputConnection.deleteSurroundingText(
                        beforeCursorText.length,
                        afterCursorText.length
                    )
                }
            }

            R.id.tafeel -> {
                typedValues
                //   rivate void InitDiaalog(String root, String wazan, String verbtype) {
                InitDiaalog(mazeedEntityVerbs[0].root, "2", "mazeed")
            }

            R.id.mujarradbtn -> {
                val dataBundle = Bundle()
                dataBundle.putString(VERBTYPE, "mujarrad")
                val intents = Intent(this@ConjugatorAct, SarfSagheerActivity::class.java)
                intents.putExtras(dataBundle)
                finish()
                startActivity(intents)
            }

            R.id.mazeedbtn -> {
                val mdataBundle = Bundle()
                mdataBundle.putString(VERBTYPE, "mazeed")
                val mintent = Intent(this@ConjugatorAct, SarfSagheerActivity::class.java)
                mintent.putExtras(mdataBundle)
                finish()
                startActivity(mintent)
            }

            R.id.mufala -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "3", "mazeed")
            }

            R.id.ifal -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "1", "mazeed")
            }

            R.id.tafaul -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "7", "mazeed")
            }

            R.id.tafaaaul -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "8", "mazeed")
            }

            R.id.infala -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "4", "mazeed")
            }

            R.id.iftiala -> {
                typedValues
                InitDiaalog(mazeedEntityVerbs[0].root, "5", "mazeed")
            }

            R.id.istifala -> {
                typedValues
                //   rivate void InitDiaalog(String root, String wazan, String verbtype) {
                InitDiaalog(mazeedEntityVerbs[0].root, "9", "mazeed")
            }

            R.id.nasara -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "1", "mujarrad")
            }

            R.id.zaraba -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "2", "mujarrad")
            }

            R.id.samia -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "4", "mujarrad")
            }

            R.id.fataha -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "3", "mujarrad")
            }

            R.id.karuma -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "5", "mujarrad")
            }

            R.id.hasiba -> {
                typedValues
                InitDiaalog(mujarradVerbs[0].root, "6", "mujarrad")
            }

            else -> inputConnectionCommitText(view)
        }
    }

    private fun toggleBottomSheet() {
        if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            //    btnBottomSheet.setText("Close sheet");
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            //    btnBottomSheet.setText("Expand sheet");
        }
    }

    // inputConnectionCommitText(view);
    private val typedValues: Unit
        private get() {
            val charSequence: CharSequence
            val beforeCursorText: CharSequence
            val afterCursorText: CharSequence
            val currentText: CharSequence =
                inputConnection.getExtractedText(ExtractedTextRequest(), 0).text
            beforeCursorText = inputConnection.getTextBeforeCursor(currentText.length, 0)!!
            afterCursorText = inputConnection.getTextAfterCursor(currentText.length, 0)!!
            // inputConnectionCommitText(view);
            charSequence = inputConnection.getTextBeforeCursor(currentText.length, 0)!!
            if (charSequence.toString().length == 3) {
                inputtext = charSequence.toString()
                InitSelecton(charSequence.toString())
            }
        }

    private fun setInputConnection(ic: InputConnection?) {
        if (ic != null) {
            inputConnection = ic
        }
    }

    private fun inputConnectionCommitText(view: View) {
        val value: String = keyValues.get(view.id)
        inputConnection.commitText(value, 1)
    }

    private fun InitSelecton(roots: String) {
        val thulathia = ArrayList<String>()
        val mazeed = ArrayList<String>()
        keyboard.visibility = LinearLayout.GONE
        val viewmodel: VerbModel by viewModels()
        editTextAuto.clearFocus()
        val split = roots.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val root = split[0]
        val pref = SharedPref(this)
        tlist = ListView(this@ConjugatorAct)
        mlist = ListView(this@ConjugatorAct)
        val utils = VerbDatabaseUtils(this@ConjugatorAct)
     //   mujarradVerbs = utils.getMujarradVerbs(root) as ArrayList<MujarradVerbs>
        viewmodel.getMujarradRoot(root).observe(this){
            mujarradVerbs = it as ArrayList<MujarradVerbs>
            for (entity in it) {
                thulathia.add(entity.babname)
            }

            for (s in it) {
                when (s.bab) {
                    "1" -> {
                        nasara.text = s.babname
                        nasara.isEnabled = true
                        nasara.visibility = View.VISIBLE
                    }

                    "2" -> {
                        zaraba.visibility = View.VISIBLE
                        zaraba.isEnabled = true
                        zaraba.text = s.babname
                    }

                    "3" -> {
                        fataha.visibility = View.VISIBLE
                        fataha.isEnabled = true
                        fataha.text = s.babname
                    }

                    "4" -> {
                        samia.visibility = View.VISIBLE
                        samia.isEnabled = true
                        samia.text = s.babname
                    }

                    "5" -> {
                        karuma.visibility = View.VISIBLE
                        karuma.isEnabled = true
                        karuma.text = s.babname
                    }

                    "6" -> {
                        haseeba.visibility = View.VISIBLE
                        haseeba.isEnabled = true
                        haseeba.text = s.babname
                    }
                }
            }
        }

     //   mazeedEntityVerbs = utils.getMazeedRoot(root)
        viewmodel.getMazeedRoot(root).observe(this){
            mazeedEntityVerbs= it as ArrayList<MazeedEntity>
            for (dict in it) {
                mazeed.add(dict.babname + "," + dict.form)
            }

            for (s in it) {
                when (s.form) {
                    "1" -> {
                        ifal.text = s.babname
                        ifal.isEnabled = true
                        ifal.visibility = View.VISIBLE
                    }

                    "2" -> {
                        tafeel.isEnabled = true
                        tafeel.text = s.babname
                        tafeel.visibility = View.VISIBLE
                    }

                    "3" -> {
                        mufala.isEnabled = true
                        mufala.text = s.babname
                        mufala.visibility = View.VISIBLE
                    }

                    "4" -> {
                        infala.isEnabled = true
                        infala.text = s.babname
                        infala.visibility = View.VISIBLE
                    }

                    "5" -> {
                        iftiala.isEnabled = true
                        iftiala.text = s.babname
                        iftiala.visibility = View.VISIBLE
                    }

                    "9" -> {
                        istifala.isEnabled = true
                        istifala.text = s.babname
                        istifala.visibility = View.VISIBLE
                    }

                    "7" -> {
                        tafaul.isEnabled = true
                        tafaul.text = s.babname
                        tafaul.visibility = View.VISIBLE
                    }

                    "8" -> {
                        tafaaul.isEnabled = true
                        tafaaul.text = s.babname
                        tafaaul.visibility = View.VISIBLE
                    }
                }
            }
        }



        if ((thulathia.size == 0) and (mazeed.size == 0)) {
            editTextAuto.setText(R.string.notfound)
        }
    }

    private fun InitDiaalog(root: String, wazan: String, verbtype: String) {
        val dataBundle = Bundle()
        val selectedRadioButton: RadioButton =
            findViewById(verbmood.getCheckedRadioButtonId())
        //get RadioButton text
        val selected: String = selectedRadioButton.text.toString()
        // display it as Toast to the user
        val checked: Boolean = indicative.isChecked
        val accusativeChecked: Boolean = subjunctive.isChecked
        when (selected) {
            "Indicative" -> dataBundle.putString(VERBMOOD, "Indicative")
            "Subjunctive" -> dataBundle.putString(VERBMOOD, "Subjunctive")
            "Jussive" -> dataBundle.putString(VERBMOOD, "Jussive")
            "Emphasized" -> dataBundle.putString(VERBMOOD, "Emphasized")
        }
        //  ninitThulathiAdapter(root);
        dataBundle.putString(QURAN_VERB_WAZAN, wazan)
        dataBundle.putString(QURAN_VERB_ROOT, root)
        dataBundle.putString(VERBTYPE, verbtype)
        dataBundle.putBoolean(SARFKABEER, isSarfKabeed)
        isSarfKabeed = false
        val intent = Intent(this@ConjugatorAct, ConjugatorTabsActivity::class.java)
        intent.putExtras(dataBundle)
        startActivity(intent)
    }

    companion object
}