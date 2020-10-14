//package com.inspiration.lemoCard.login.loginbyphone.view
//
//import android.content.Context
//import android.text.*
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.inputmethod.InputMethodManager
//import android.widget.FrameLayout
//import android.widget.TextView
//import com.project.alan.frescolearningbykotlin.R
//import kotlinx.android.synthetic.main.verify_number_layout.view.*
//import kotlin.properties.Delegates
//
//
///**
// * Created by Alan on 2018/5/22.
// */
//class VerifyNumberView(context: Context, attributeSet: AttributeSet? = null) : FrameLayout(context, attributeSet) {
//
//    var listener: VerifyNumberViewListener? = null
//
//    interface VerifyNumberViewListener {
//        fun onVerifyNumberIsValid()
//    }
//
//    val VERIFY_NUMBER_LENGTH = 4
//    var textArray: Array<TextView> by Delegates.notNull()
//
//
//    init {
//        LayoutInflater.from(context).inflate(R.layout.verify_number_layout, this)
//        textArray = arrayOf(login_by_phone_view_code_number_text1,
//                login_by_phone_view_code_number_text2,
//                login_by_phone_view_code_number_text3,
//                login_by_phone_view_code_number_text4)
//        initEditText()
//    }
//
//    private fun initEditText() {
//        with(login_by_phone_view_code_number_edittext) {
//            inputType = InputType.TYPE_CLASS_NUMBER
//            filters = arrayOf<InputFilter>(InputFilter.LengthFilter(VERIFY_NUMBER_LENGTH))
//            addTextChangedListener(object : TextWatcher {
//                override fun afterTextChanged(s: Editable?) {
//                    setVerifyCode(s.toString().toCharArray())
//                    if (contentIsValid()) {
//                        listener?.onVerifyNumberIsValid()
//                    }
//                }
//
//                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                }
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                }
//            })
//        }
//
//    }
//
//    fun setVerifyCode(array: CharArray) {
//        for (i in textArray.indices) {
//            if (i <= array.size - 1 && !TextUtils.isEmpty(array[i].toString())) {
//                textArray[i].text = array[i].toString()
//                textArray[i].background = resources.getDrawable(R.drawable.login_by_phone_view_code_number_text_fill_bkg)
//            } else {
//                textArray[i].text = null
//                textArray[i].background = resources.getDrawable(R.drawable.login_by_phone_view_code_number_text_full_bkg)
//            }
//        }
//    }
//
//    private fun contentIsValid(): Boolean {
//        return login_by_phone_view_code_number_edittext.text.toString().length == VERIFY_NUMBER_LENGTH
//    }
//
//    fun clearNumber() {
//        login_by_phone_view_code_number_edittext.text = null
//    }
//
//    fun getVerifyNumber(): String = login_by_phone_view_code_number_edittext.editableText.toString()
//
//    //获取焦点并弹出键盘
//    fun focus() {
//        login_by_phone_view_code_number_edittext.requestFocus()
//        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(login_by_phone_view_code_number_edittext, InputMethodManager.RESULT_UNCHANGED_SHOWN)
//    }
//
//
//}