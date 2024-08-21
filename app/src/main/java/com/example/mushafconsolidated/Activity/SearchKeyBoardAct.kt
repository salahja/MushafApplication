package com.example.mushafconsolidated.Activity

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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RelativeLayout
import com.example.Constant.QURAN_VERB_ROOT
import com.example.mushafconsolidated.Entities.qurandictionary
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.sj.conjugator.activity.BaseActivity
import org.sj.conjugator.activity.KeyboardUtil.hideKeyboard
import ru.dimorinny.floatingtextbutton.FloatingTextButton


class SearchKeyBoardAct : BaseActivity(), View.OnClickListener {
    private val keyValues = SparseArray<String>()
    private lateinit var quranbtn: Button
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var layoutBottomSheet: RelativeLayout
    private var sheetBehavior: BottomSheetBehavior<RelativeLayout?>? = null
    private var tlist: ListView? = null
    private var mlist: ListView? = null
    private lateinit var qurandictionaryArrayList: ArrayList<qurandictionary>
    private lateinit var keyboard: View
    private lateinit var inputConnection: InputConnection
    private lateinit var actv: AutoCompleteTextView

    // --Commented out by Inspection START (22/08/23, 5:56 pm):
    //    public void setInputtext(String inputtext) {
    //    }
    // --Commented out by Inspection STOP (22/08/23, 5:56 pm)
    override fun onBackPressed() {
        Log.d("CDA", "onBackPressed Called")
        super@SearchKeyBoardAct.finish()
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_key_activity_autocomplete)
        hideKeyboard(this@SearchKeyBoardAct)
        keyboard = findViewById(R.id.arabic_keyboard)
        val callButton = findViewById<FloatingTextButton>(R.id.action_buttons)
        callButton.setOnClickListener { view: View? ->
            super@SearchKeyBoardAct.finish()
            super@SearchKeyBoardAct.finish()
            super.onBackPressed()
        }
        //    hideKeyboardSoft();
        SetUpAutoComplete()
        val ic: InputConnection = actv.onCreateInputConnection(EditorInfo())
        // InputConnection ic = editTextAuto.onCreateInputConnection(new EditorInfo());
        setInputConnection(ic)
        init()
        // kb. getCharSequence();
    }

    private fun SetUpAutoComplete() {
        hideKeyboard(this@SearchKeyBoardAct)
        val root: Array<String?>
        val util = Utils(this@SearchKeyBoardAct)
        //  ArrayList<MujarradVerbs> verbAll = util.getMujarradAall();
        qurandictionaryArrayList = util.quranDictionary as ArrayList<qurandictionary>
        val size = qurandictionaryArrayList.size
        root = arrayOfNulls(size)
        for ((i, entity) in qurandictionaryArrayList.withIndex()) {
            val roots = entity.rootarabic
            root[i] = roots
        }
        val h = HashSet(listOf(*root))
        val aList2: ArrayList<String?> = ArrayList(h)
        val adapters = ArrayAdapter(this, R.layout.my_simple_list_item, aList2)
        val listadapters = ArrayAdapter(this, R.layout.my_simple_list_item, root)
        //Getting the instance of AutoCompleteTextView
        actv = findViewById(R.id.autoCompleteTextView)
        val listdisp = findViewById<ListView>(R.id.list_item)
        listdisp.adapter = listadapters
        listdisp.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView: AdapterView<*>?, view: View?, position: Int, l: Long ->
                // here you code
                val qurandictionary = qurandictionaryArrayList[position]
                launchActivity(qurandictionary.rootarabic)
            }
        val sizes = 1300
        actv.dropDownHeight = sizes
        actv.threshold = 1 //will start working from first character
        actv.setAdapter(adapters) //setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED)
        actv.textSize = 50.00.toFloat()
        //   editTextAuto = findViewById(R.id.autoCompleteTextView);
        actv.setRawInputType(InputType.TYPE_CLASS_TEXT)
        actv.setTextIsSelectable(true)
        //   KeyboardUtil.hideKeyboard(this);
        actv.showSoftInputOnFocus = false
        actv.setOnFocusChangeListener { view: View?, hasFocus: Boolean ->
            if (hasFocus) {
                keyboard.visibility = LinearLayout.VISIBLE
                //     actv.showDropDown();
                if (tlist != null) tlist!!.adapter = null
                if (mlist != null) mlist!!.adapter = null
            } //   keyboard.setVisibility(LinearLayout.GONE);
        }
    }

    private fun init() {
        quranbtn = findViewById(R.id.qurangrammar)
        layoutBottomSheet = findViewById(R.id.bottom_sheet)
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet)
        floatingActionButton = findViewById(R.id.fab)
        keyboard = findViewById(R.id.arabic_keyboard)
        val key_delete = findViewById<Button>(R.id.key_delete)
        val key_AC = findViewById<Button>(R.id.key_AC)
        val key_enter = findViewById<Button>(R.id.key_enter)
        keyboard = findViewById(R.id.arabic_keyboard)
        val dhad = findViewById<Button>(R.id.dhad)
        val suwad = findViewById<Button>(R.id.suwad)
        val qaf = findViewById<Button>(R.id.qaf)
        val fa = findViewById<Button>(R.id.fa)
        val ghain = findViewById<Button>(R.id.ghain)
        val ayn = findViewById<Button>(R.id.ayn)
        val haaa = findViewById<Button>(R.id.haaa)
        val kha = findViewById<Button>(R.id.kha)
        val ha = findViewById<Button>(R.id.ha)
        val jeem = findViewById<Button>(R.id.jeem)
        floatingActionButton.setOnClickListener(this)
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
        val sheen = findViewById<Button>(R.id.sheen)
        val seen = findViewById<Button>(R.id.seen)
        val ya = findViewById<Button>(R.id.ya)
        val ba = findViewById<Button>(R.id.ba)
        val lam = findViewById<Button>(R.id.lam)
        val alif = findViewById<Button>(R.id.hamza)
        val ta = findViewById<Button>(R.id.ta)
        val nun = findViewById<Button>(R.id.nun)
        val meem = findViewById<Button>(R.id.meem)
        val kaf = findViewById<Button>(R.id.kaf)
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
        val zoay = findViewById<Button>(R.id.zoay)
        val toay = findViewById<Button>(R.id.toay)
        val dhal = findViewById<Button>(R.id.dhal)
        val dal = findViewById<Button>(R.id.dal)
        val zaa = findViewById<Button>(R.id.zaa)
        val raa = findViewById<Button>(R.id.raa)
        val waw = findViewById<Button>(R.id.waw)
        val tamarboot = findViewById<Button>(R.id.tamarboota)
        val tha = findViewById<Button>(R.id.tha)
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
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(view: View) {
        //   hideKeyboardSoft();
        sheetBehavior!!.setBottomSheetCallback(object : BottomSheetCallback() {
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
        val currentText = inputConnection.getExtractedText(ExtractedTextRequest(), 0).text
        val beforeCursorText = inputConnection.getTextBeforeCursor(currentText.length, 0)
        val afterCursorText = inputConnection.getTextAfterCursor(currentText.length, 0)
        when (view.id) {
            R.id.fab -> {
                toggleBottomSheet()
                val charSequence = inputConnection.getTextBeforeCursor(currentText.length, 0)
                if (charSequence.toString().length == 3) {
                    //  setInputtext(charSequence.toString());
                    InitSelecton(charSequence.toString())
                }
            }

            R.id.key_enter -> {
                val charSequence = inputConnection.getTextBeforeCursor(currentText.length, 0)
                if (charSequence.toString().length == 3) {
                    InitSelecton(charSequence.toString())
                }
            }

            R.id.key_delete -> {
                val selectedText = inputConnection.getSelectedText(0)
                if (TextUtils.isEmpty(selectedText)) inputConnection.deleteSurroundingText(
                    1,
                    0
                ) else inputConnection.commitText("", 1)
            }

            R.id.key_AC -> inputConnection.deleteSurroundingText(
                beforeCursorText!!.length,
                afterCursorText!!.length
            )

            else -> inputConnectionCommitText(view)
        }
    }

    private fun toggleBottomSheet() {
        if (sheetBehavior!!.state != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior!!.setState(BottomSheetBehavior.STATE_EXPANDED)
            //    btnBottomSheet.setText("Close sheet");
        } else {
            sheetBehavior!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
            //    btnBottomSheet.setText("Expand sheet");
        }
    }

    private fun setInputConnection(ic: InputConnection?) {
        if (ic != null) {
            inputConnection = ic
        }
    }

    private fun inputConnectionCommitText(view: View) {
        val value = keyValues[view.id]
        inputConnection.commitText(value, 1)
    }

    private fun InitSelecton(roots: String) {
        keyboard.visibility = LinearLayout.GONE
        actv.clearFocus()
        val split = roots.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val root = split[0]
        tlist = ListView(this@SearchKeyBoardAct)
        mlist = ListView(this@SearchKeyBoardAct)
        val util = Utils(this@SearchKeyBoardAct)
        util.quranDictionary
        launchActivity(root)
    }

    private fun launchActivity(root: String) {
        val bundle = Bundle()
        //   Intent intent = new Intent(getActivity(), NounOccuranceAsynKAct.class);
        //   Intent intent = new Intent(getActivity(), NounOccuranceAsynKAct.class);
        //   Intent intent = new Intent(getActivity(), NounOccuranceAsynKAct.class);


        val intent = Intent(this@SearchKeyBoardAct, KeyboardSearchResult::class.java)
        //   getTypedValues();
        bundle.putString(QURAN_VERB_ROOT, root)
        intent.putExtras(bundle)
        //   intent.putExtra(QURAN_VERB_ROOT,vb.getRoot());
        startActivity(intent)
    }
}