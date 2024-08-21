package org.sj.conjugator.CustomKeyboard

import android.content.Context
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout


import com.example.mushafconsolidated.R

class CustomKeyboard : LinearLayout, View.OnClickListener {
    private val keyValues = SparseArray<String>()
    lateinit var keyboard: CustomKeyboard;
     lateinit var key_enter: Button;

    // --Commented out by Inspection (20/08/23, 10:49 pm):private CharSequence charSequence;
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

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.round_arabic_keyboard, this, true)
        val key_delete = findViewById<Button>(R.id.key_delete)
        val key_AC = findViewById<Button>(R.id.key_AC)
        key_enter = findViewById(R.id.key_enter)
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
        keyValues.put(R.id.hamza, "ا")
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

    override fun onClick(view: View) {}
}