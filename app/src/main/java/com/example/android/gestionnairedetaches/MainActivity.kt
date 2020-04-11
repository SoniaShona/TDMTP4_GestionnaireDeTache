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
        val monthValue=month+1
        val today= "$day/$monthValue/$year"

        //la liste des taches
        var listView: ListView?
        listView = findViewById(R.id.listView)

        var section = 0
        var result = ArrayList<Tache>()
        var result1 = ArrayList<Tache>()
        var adapter = listAdapter(this, result)
        listView?.adapter = adapter
        adapter?.notifyDataSetChanged()

        //generer des taches initials
        for (i in 0..4) {
            var tache: Tache = Tache("Tache Test "+(i+1)+" ", ""+(i+12)+"/04/2020")
            result.add(tache)
            result.sort()
        }

        // visualiser calendrier
        showCalendarBtn.setOnClickListener {
            if (tacheName.text.toString()!=""){
                val dpd =DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val tacheName = tacheName.text.toString()
                    val  tacheDate=""+dayOfMonth+"/"+(month+1)+"/"+year
                    var tache = Tache(tacheName, tacheDate)
                    result.add(tache)
                    result.sort()
                    if (tacheDate.equals(today,true)){
                        result1.add(tache)
                        result1.sort()
                    }

                    //refresh list of task
                    adapter?.notifyDataSetChanged()

                },year,month,day)
                dpd.show()
            }
        }

        // Supprimer la teche cliquee
        listView.setOnItemClickListener { parent, view, position, id ->
            if (section==0){
                result.remove(result[position])
                result.sort()
                adapter?.notifyDataSetChanged()
            }
            Toast.makeText(this, "Tache supprimee = ",Toast.LENGTH_SHORT).show()
        }



        // fonction de filtrage de la liste des taches
        fun showTodayTasks(){
            for (i in 0 until result.size){
                if (result[i].date.equals(today,true)){
                    result1.add(result[i])
                    result1.sort()
                    adapter = listAdapter(this@MainActivity,result1)
                    listView?.adapter = adapter
                    adapter?.notifyDataSetChanged()
                    section=1
                    Toast.makeText(this@MainActivity, today,Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun showWeekTasks(){
            result1.clear()
            adapter = listAdapter(this@MainActivity,result)
            listView?.adapter = adapter
            adapter?.notifyDataSetChanged()
            section=0
            Toast.makeText(this@MainActivity, "API 26 AND HIGHER",Toast.LENGTH_SHORT).show()
        }

        fun showAllTasks(){
            result1.clear()
            adapter = listAdapter(this@MainActivity,result)
            listView?.adapter = adapter
            adapter?.notifyDataSetChanged()
            section=0
            Toast.makeText(this@MainActivity, "All",Toast.LENGTH_SHORT).show()
        }


        // Partie combobox pour ecran grand en mode portrait

        val spinner_values=arrayOf("Tous Les Taches","Ce Jour", "Cette Semaine")
        spinner.adapter= ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinner_values)
        spinner.onItemSelectedListener= object :AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position==0){ showAllTasks() }
                else{
                    if (position==1){ showTodayTasks() }
                    else{ showWeekTasks() }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }


        // Partie menu pour ecran grand en mode paysage

        todayBtn.setOnClickListener {
            showTodayTasks()
        }

        thisWeekBtn.setOnClickListener {
            showWeekTasks()
        }

        allTasksBtn.setOnClickListener {
            showAllTasks()
        }

    }
}
