package com.example.financemonitoring.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.EXTRA_ITEM_ID
import com.example.financemonitoring.domain.EXTRA_MODE
import com.example.financemonitoring.domain.MODE_ADD
import com.example.financemonitoring.domain.MODE_EDIT
import com.example.financemonitoring.domain.MODE_UNDEF
import com.example.financemonitoring.domain.UNDEFINED_ID
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDate

class RecordActivity : AppCompatActivity() {
    private lateinit var til_name: TextInputLayout
    private lateinit var til_change: TextInputLayout
    private lateinit var til_category: TextInputLayout
    private lateinit var til_date: TextInputLayout

    private lateinit var editName: TextInputEditText
    private lateinit var editChange: TextInputEditText
    private lateinit var editCategory: TextInputEditText
    private lateinit var editDate: TextInputEditText

    private lateinit var buttonSave: Button

    private lateinit var viewModel: RecordViewModel

    private var mode: String = MODE_UNDEF
    private var recordId: Long = UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        viewModel = ViewModelProvider(this)[RecordViewModel::class.java]

        initViews()
        parseIntent()
        registerLiveData()
    }

    fun parseIntent(){
        if (intent.hasExtra(EXTRA_MODE)){
            mode = intent.getStringExtra(EXTRA_MODE) ?: MODE_UNDEF
            Log.d(TAG, "parseIntent: mode = $mode")
            if (mode == MODE_ADD){
                lunchActivityForAdd()
            }
            else if (mode == MODE_EDIT && intent.hasExtra(EXTRA_ITEM_ID)){
                recordId = intent.getLongExtra(EXTRA_ITEM_ID, UNDEFINED_ID)
                Log.d(TAG, "parseIntent: itemId = $recordId")
                lunchActivityForEdit()
            } else {
                throw IllegalArgumentException("Item Id is not defined")
            }
        } else {
            throw IllegalArgumentException("Mode is not defined")
        }
    }

    fun initViews(){
        til_name = findViewById(R.id.nameTIL)
        til_change = findViewById(R.id.changeTIL)
        til_category = findViewById(R.id.categoryTIL)
        til_date = findViewById(R.id.dateTIL)

        editName = findViewById(R.id.editName)
        editName.addTextChangedListener {
            til_name.error = null
        }
        editCategory = findViewById(R.id.editCategory)
        editCategory.addTextChangedListener {
            til_category.error = null
        }
        editChange = findViewById(R.id.editChange)
        editChange.addTextChangedListener {
            til_change.error = null
        }
        editDate = findViewById(R.id.editDate)
        editDate.addTextChangedListener {
            til_date.error = null
        }

        buttonSave = findViewById(R.id.saveButton)

    }

    fun registerLiveData(){
        viewModel.recordLiveData.observe(this){
            Toast.makeText(this,"Record Id: ${it.id}",Toast.LENGTH_SHORT).show()
            editName.setText(it.name)
            editChange.setText(it.change.toString())
            editCategory.setText(it.category)
            editDate.setText(it.date.toString())
        }

        viewModel.errorNameLD.observe(this){
            til_name.error = if (it){
                null
            } else {
                "Name error"
            }
        }
        viewModel.errorChangeLD.observe(this){
            til_change.error = if (it){
                null
            } else {
                "Change error"
            }
        }
        viewModel.errorCategoryLD.observe(this){
            til_category.error = if(it){
                null
            }else{
                "Category error"
            }
        }
        viewModel.errorDateLD.observe(this){
            til_date.error = if(it){
                null
            }else{
                "Date error"
            }
        }

        viewModel.finishActivityLD.observe(this){
            finish()
//            onBackPressed()
        }
    }


    private fun lunchActivityForAdd(){
        editDate.setText(LocalDate.now().toString())
        buttonSave.setOnClickListener {
            Log.d(TAG, "lunchActivityForAdd: ")
            viewModel.addRecord(editName.text, editChange.text,
                editCategory.text,editDate.text)
        }
    }
    private fun lunchActivityForEdit(){
        viewModel.getRecord(recordId)

        buttonSave.setOnClickListener {
            Log.d(TAG, "lunchActivityForEdit: ")
            viewModel.editRecord(editName.text, editChange.text,
                editCategory.text,editDate.text)
        }
    }

    companion object{
        val TAG = "XXXX"


    }
}