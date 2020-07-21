package com.obiangetfils.weatherapp.city

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.obiangetfils.weatherapp.R

class CreateCityDialogFragment : DialogFragment() {

    interface CreateCityDialogListener {
        fun onDialogPositiveClick(cityName : String)
        fun onDialogNegativeClick()
    }

    var listener : CreateCityDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(this!!.context!!)

        val editText = EditText(context)
        with(editText){
            inputType = InputType.TYPE_CLASS_TEXT
            hint = context.getString(R.string.default_edittext_hint)
        }

        builder.setTitle(getString(R.string.create_city_name))
            .setView(editText)
            .setPositiveButton(getString(R.string.create_city),
                   DialogInterface.OnClickListener { _, _ ->
                       listener?.onDialogPositiveClick(editText.text.toString())
                   })
            .setNegativeButton(getString(R.string.cancel),
                   DialogInterface.OnClickListener { dialog, _ ->
                       dialog.cancel()
                       listener?.onDialogNegativeClick()
                   })

        return builder.create()
    }     

}