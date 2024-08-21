package com.example.utility

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout
import com.example.Constant.QURAN_VERB_ROOT
import com.example.Constant.QURAN_VERB_WAZAN
import com.example.mushafconsolidated.R

import com.google.android.material.button.MaterialButton


class OvalCustomKeyboard : LinearLayout, View.OnClickListener {
    private val keyValues = SparseArray<String>()

    // --Commented out by Inspection (18/08/23, 6:13 am):private final String LogTag = "Keyboard";
    var mycontext: Context? = null
    private var keyboard: OvalCustomKeyboard? = null

    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key1;
    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key2;
    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key3;
    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key4;
    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key5;
    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key6;
    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key7;
    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key8;
    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key9;
    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key00;
    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key_dot;
    private var key_enter: MaterialButton? = null

    // --Commented out by Inspection (18/08/23, 6:13 am):private MaterialButton key_exit;
    // --Commented out by Inspection (18/08/23, 6:12 am):private RadioGroup formone, formtwo;
    private val inputtext: String? = null
    private val inputConnection: InputConnection? = null

    // --Commented out by Inspection START (18/08/23, 6:12 am):
    //    public String getInputtext() {
    //        return inputtext;
    //    }
    // --Commented out by Inspection STOP (18/08/23, 6:12 am)
    // --Commented out by Inspection START (18/08/23, 6:13 am):
    //    public void setInputtext(String inputtext) {
    //        this.inputtext = inputtext;
    //    }
    // --Commented out by Inspection STOP (18/08/23, 6:13 am)
    //  private com.sjconjugatortwo.keyboard.KeyBoardInitActivity InitActivity;
    private val radioText: String? = null

    /*
        constructor(keyBoardInitActivity: ConjugatorAct?) : super(keyBoardInitActivity) {
            mycontext = keyBoardInitActivity
        }
    */

    //  constructor(context: ConjugatorAct?, s: String?) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    // --Commented out by Inspection START (18/08/23, 6:13 am):
    //    public void setRadioText(String radioText) {
    //        this.radioText = radioText;
    //    }
    // --Commented out by Inspection STOP (18/08/23, 6:13 am)
    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.round_arabic_keyboard, this, true)
        //  LayoutInflater.from(context).inflate(R.layout.back_round_arabic_keyboard, this, true);
        val key_delete = findViewById<MaterialButton>(R.id.key_delete)
        val key_AC = findViewById<MaterialButton>(R.id.key_AC)
        key_enter = findViewById(R.id.key_enter)
        keyboard = findViewById(R.id.arabic_keyboard)
        //   key_exit=findViewById(R.id.key_exit);
        val dhad = findViewById<MaterialButton>(R.id.dhad)
        val suwad = findViewById<MaterialButton>(R.id.suwad)
        val qaf = findViewById<MaterialButton>(R.id.qaf)
        val fa = findViewById<MaterialButton>(R.id.fa)
        val ghain = findViewById<MaterialButton>(R.id.ghain)
        val ayn = findViewById<MaterialButton>(R.id.ayn)
        val haaa = findViewById<MaterialButton>(R.id.haaa)
        val kha = findViewById<MaterialButton>(R.id.kha)
        val ha = findViewById<MaterialButton>(R.id.ha)
        val jeem = findViewById<MaterialButton>(R.id.jeem)
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
        val sheen = findViewById<MaterialButton>(R.id.sheen)
        val seen = findViewById<MaterialButton>(R.id.seen)
        val ya = findViewById<MaterialButton>(R.id.ya)
        val ba = findViewById<MaterialButton>(R.id.ba)
        val lam = findViewById<MaterialButton>(R.id.lam)
        val alif = findViewById<MaterialButton>(R.id.hamza)
        val ta = findViewById<MaterialButton>(R.id.ta)
        val nun = findViewById<MaterialButton>(R.id.nun)
        val meem = findViewById<MaterialButton>(R.id.meem)
        val kaf = findViewById<MaterialButton>(R.id.kaf)
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
        key_enter!!.setOnClickListener(this)
        //  key_exit.setOnClickListener(this);
        //  key_dot.setOnClickListener(this);
        keyValues.put(R.id.sheen, "ش")
        keyValues.put(R.id.seen, "س")
        keyValues.put(R.id.ya, "ي")
        keyValues.put(R.id.ba, "ب")
        keyValues.put(R.id.lam, "ل")
        keyValues.put(R.id.hamza, "ا")
        keyValues.put(R.id.ta, "ت")
        keyValues.put(R.id.nun, "ن")
        keyValues.put(R.id.meem, "م")
        keyValues.put(R.id.kaf, "ك")
        val zoay = findViewById<MaterialButton>(R.id.zoay)
        val toay = findViewById<MaterialButton>(R.id.toay)
        val dhal = findViewById<MaterialButton>(R.id.dhal)
        val dal = findViewById<MaterialButton>(R.id.dal)
        val zaa = findViewById<MaterialButton>(R.id.zaa)
        val raa = findViewById<MaterialButton>(R.id.raa)
        val waw = findViewById<MaterialButton>(R.id.waw)
        val tamarboot = findViewById<MaterialButton>(R.id.tamarboota)
        val tha = findViewById<MaterialButton>(R.id.tha)
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
        //    keyValues.put(R.id.key_00, "00");
        //    keyValues.put(R.id.key_dot, ".");
    }

    fun getKey_enter(): Button? {
        return key_enter
    }

    // --Commented out by Inspection START (18/08/23, 6:13 am):
    //    public void setInputConnection(InputConnection ic) {
    //        inputConnection = ic;
    //    }
    // --Commented out by Inspection STOP (18/08/23, 6:13 am)
    private fun InitDiaalog(root: String) {
        // val applicationContext: Context = ConjugatorAct.getContextOfApplication()
        //   GRadioGroup gr=new GRadioGroup(nasara,zaraba,samia,fatha,karuma,hasiba);
        val sp = QuranGrammarApplication.context!!.getSharedPreferences("key", 0)
        val babs = sp.getString("bab", "")
        val dataBundle = Bundle()
        dataBundle.putString(QURAN_VERB_WAZAN, radioText)
        dataBundle.putString(QURAN_VERB_WAZAN, babs)
        dataBundle.putString(QURAN_VERB_ROOT, root)
        //   QuranVerbConjDialog dialog = new QuranVerbConjDialog(getContext());
        //  dialog.setArguments(dataBundle);
        //   Intent i = new Intent(getContext(), VerbQueryActivity.class);
        //   i.putExtra(QURAN_VERB_ROOT,root!!);
        //   i.putExtra(QURAN_VERB_FORM,babs);
        //   getContext().startActivity(i);
        //      FragmentActivity activity = (FragmentActivity) getContext();
        //      final FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        //     transaction.add(R.id.frame_container ,dialog,VERSEFRAGMENT);
        //     transaction.commit();
    }

    private fun inputConnectionCommitText(view: View) {
        val value = keyValues[view.id]
        inputConnection!!.commitText(value, 1)
    }

    override fun onClick(view: View) {}
}