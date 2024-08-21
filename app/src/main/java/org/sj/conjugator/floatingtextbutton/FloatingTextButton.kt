package org.sj.conjugator.floatingtextbutton

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import ru.dimorinny.floatingtextbutton.R
import ru.dimorinny.floatingtextbutton.util.DimensionUtils

class FloatingTextButton(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs) {
    private var container: CardView? = null
    private var leftIconView: ImageView? = null
    private var rightIconView: ImageView? = null
    private var titleView: TextView? = null
    private var title: String? = null
    private var titleColor = 0
    private var leftIcon: Drawable? = null
    private var rightIcon: Drawable? = null
    private var background = 0

    init {
        inflateLayout(context)
        initAttributes(attrs)
        initView()
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(newTitle: String?) {
        title = newTitle
        if (newTitle == null || newTitle.isEmpty()) {
            titleView!!.visibility = GONE
        } else {
            titleView!!.visibility = VISIBLE
        }
        titleView!!.text = newTitle
    }

    @ColorInt
    fun getTitleColor(): Int {
        return titleColor
    }

    fun setTitleColor(@ColorInt color: Int) {
        titleColor = color
        titleView!!.setTextColor(color)
    }

    @ColorInt
    fun getBackgroundColor(): Int {
        return background
    }

    override fun setBackgroundColor(@ColorInt color: Int) {
        background = color
        container!!.setCardBackgroundColor(color)
    }

    var leftIconDrawable: Drawable?
        get() = leftIcon
        set(drawable) {
            leftIcon = drawable
            if (drawable != null) {
                leftIconView!!.visibility = VISIBLE
                leftIconView!!.setImageDrawable(drawable)
            } else {
                leftIconView!!.visibility = GONE
            }
        }
    var rightIconDrawable: Drawable?
        get() = rightIcon
        set(drawable) {
            rightIcon = drawable
            if (drawable != null) {
                rightIconView!!.visibility = VISIBLE
                rightIconView!!.setImageDrawable(drawable)
            } else {
                rightIconView!!.visibility = GONE
            }
        }

    override fun setOnClickListener(listener: OnClickListener?) {
        container!!.setOnClickListener(listener)
    }

    override fun hasOnClickListeners(): Boolean {
        return container!!.hasOnClickListeners()
    }

    override fun setOnLongClickListener(listener: OnLongClickListener?) {
        container!!.setOnLongClickListener(listener)
    }

    private fun inflateLayout(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.widget_floating_text_button, this, true)
        container = view.findViewById(R.id.layout_button_container)
        leftIconView = view.findViewById(R.id.layout_button_image_left)
        rightIconView = view.findViewById(R.id.layout_button_image_right)
        titleView = view.findViewById(R.id.layout_button_text)
    }

    private fun initAttributes(attrs: AttributeSet) {
        val styleable = context.obtainStyledAttributes(
            attrs,
            R.styleable.FloatingTextButton,
            0,
            0
        )
        title = styleable.getString(R.styleable.FloatingTextButton_floating_title)
        titleColor =
            styleable.getColor(R.styleable.FloatingTextButton_floating_title_color, Color.BLACK)
        leftIcon = styleable.getDrawable(R.styleable.FloatingTextButton_floating_left_icon)
        rightIcon = styleable.getDrawable(R.styleable.FloatingTextButton_floating_right_icon)
        background = styleable.getColor(
            R.styleable.FloatingTextButton_floating_background_color,
            Color.DKGRAY
        )
        styleable.recycle()
    }

    private fun initView() {
        setTitle(title)
        setTitleColor(titleColor)
        leftIconDrawable = leftIcon
        rightIconDrawable = rightIcon
        setBackgroundColor(background)
        container!!.setContentPadding(
            getHorizontalPaddingValue(16),
            getVerticalPaddingValue(8),
            getHorizontalPaddingValue(16),
            getVerticalPaddingValue(8)
        )
        initViewRadius()
    }

    private fun initViewRadius() {
        container!!.post { container!!.radius = (container!!.height / 2).toFloat() }
    }

    private fun getVerticalPaddingValue(dp: Int): Int {
        return DimensionUtils.dpToPx(context, dp)
    }

    private fun getHorizontalPaddingValue(dp: Int): Int {
        return DimensionUtils.dpToPx(context, dp)
    }
}