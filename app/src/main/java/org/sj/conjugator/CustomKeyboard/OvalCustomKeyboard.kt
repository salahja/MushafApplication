package org.sj.conjugator.CustomKeyboard

import android.content.Context
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.mushafconsolidated.R
import com.google.android.material.button.MaterialButton

class OvalCustomKeyboard : LinearLayout, View.OnClickListener {
    private val keyValues = SparseArray<String>()
    var mycontext: Context? = null
    var keyboard: OvalCustomKeyboard? = null
    private val radioText: String? = null

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
        val key_delete = findViewById<MaterialButton>(R.id.key_delete)
        val key_AC = findViewById<MaterialButton>(R.id.key_AC)
        val key_enter = findViewById<MaterialButton>(R.id.key_enter)
        keyboard = findViewById(R.id.arabic_keyboard)
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
        key_enter.setOnClickListener(this)
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
    }

    override fun onClick(view: View) {}
}