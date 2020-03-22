package com.example.androidhub.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androidhub.R
import kotlinx.android.synthetic.main.view_drawable_text.view.*

class DrawableTextView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet, defStyle:Int = 0) : ConstraintLayout(context, attributeSet, defStyle) {

    var textSelectedColor: Int? = null
    val textUnselectedColor: Int? = null

    init {
        View.inflate(context, R.layout.view_drawable_text, this)
    }

    override fun setSelected(isSelected: Boolean) {
        if (isSelected) {
            textSelectedColor?.let {
            }
        } else {
            textUnselectedColor?.let {
            }
        }
    }

}