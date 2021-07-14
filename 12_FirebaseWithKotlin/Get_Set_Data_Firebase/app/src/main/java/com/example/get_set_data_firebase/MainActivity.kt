package com.example.get_set_data_firebase

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    // Declare variables (initialize them later)
    private lateinit var editTextEmpNumber: EditText
    private lateinit var editTextEmpName: EditText
    private lateinit var editTextEmpSalary: EditText
    private lateinit var buttonRegister: Button
    private lateinit var textViewEmpInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("employee information")
        //myRef.setValue("Hello, World!!!")

        initUI()

        // Set data
        buttonRegister.setOnClickListener {
            var empNumber = editTextEmpNumber.text.toString().toInt()
            var empName = editTextEmpName.text.toString()
            var empSalary = editTextEmpSalary.text.toString().toInt()
            myRef.child(empNumber.toString()).setValue(EmployeeModel(empName, empSalary))
        }

        // Get data
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()    // Class that can change string
                for(i in snapshot.children){
                    var name = i.child("employeeName").value
                    var salary = i.child("employeeSalary").value
                    sb.append("${i.key} $name $salary \n")
                }
                textViewEmpInfo.text = sb
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    private fun initUI() {
        // finding the editText
        editTextEmpNumber = findViewById(R.id.etEmployeeID)
        editTextEmpName = findViewById(R.id.etEmployeeName)
        editTextEmpSalary = findViewById(R.id.etEmployeeSalary)
        // finding the button
        buttonRegister = findViewById(R.id.btnRegister)
        // finding the textView
        textViewEmpInfo = findViewById(R.id.textView)
    }
}