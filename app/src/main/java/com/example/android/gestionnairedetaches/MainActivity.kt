package com.example.android.gestionnairedetaches

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //calendar
        val c= Calendar.getInstance()
        val year= c.get(Calendar.YEAR)
        val month= c.get(Calendar.MONTH)
        val day= c.get(Calendar.DAY_OF_MONTH)

        //la liste des taches
        var listView: ListView? = null
        listView = findViewById<ListView>(R.id.listView)

        var result = ArrayList<Tache>()
        var adapter = listAdapter(this, result)

        listView?.adapter = adapter
        adapter?.notifyDataSetChanged()

        //generate some rows
        for (i in 0..5) {
            var tache: Tache = Tache("Tache "+(i+1)+" ", "22-01-2020")
            result.add(tache)
        }

        // show calendar when click button
        showCalendarBtn.setOnClickListener {
            if (tacheName.text.toString()!=""){
                val dpd =DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val  val_month= month+1
                    val tacheName = tacheName.text.toString()
                    val  tacheDate=""+dayOfMonth+"/"+val_month+"/"+year
                    var tache: Tache = Tache(tacheName, tacheDate)
                    result.add(tache)

                    //refresh list of task
                    adapter?.notifyDataSetChanged()

                },year,month,day)
                dpd.show()
            }
        }

        //delete task when click on it
        listView.setOnItemClickListener { parent, view, position, id ->

            Toast.makeText(this, "Tache supprimee = "+position+"",Toast.LENGTH_SHORT).show()
            result.remove(result[position])
            adapter?.notifyDataSetChanged()
        }


        //Spinner here
        val spinner_values=arrayOf("Ce Jour", "Cette Semaine", "Tous Les Taches")

        spinner.adapter= ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinner_values)
        spinner.onItemSelectedListener= object :AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==1){

                }
                if (position==1){

                }
                else{

                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

    }
}
