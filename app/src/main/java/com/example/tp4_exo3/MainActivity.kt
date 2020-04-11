package com.example.tp4_exo3



import android.app.DatePickerDialog
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

/**
 *Created by Fedala Amira.
 */

class MainActivity : AppCompatActivity() {
    lateinit var layoutManager : LinearLayoutManager
    lateinit var list : MutableList<Int>
    var pst: Int = 0
    var itemList : MutableList<Item> = ArrayList()
    lateinit var adapter: recyclerAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager






      list = getTodaysTasks(itemList)
        Log.i("test", "${list.size}")
        adapter = recyclerAdapter(this)
        recyclerView.adapter = adapter


        val isTablet = resources.getBoolean(R.bool.big)
        val isLandscape = this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE
        Toast.makeText(this, "$isTablet", Toast.LENGTH_LONG).show()
        if (!isTablet){


        }else if (!isLandscape){
            val spinner: Spinner = findViewById(R.id.combobox)
            ArrayAdapter.createFromResource(
                this,
                R.array.options,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }

            combobox.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    pst= position
                    changeTasksViewing(position)

                }

            }

        } else {

            todayTaskBtnView.setOnClickListener {
                pst = 0
                changeTasksViewing(pst)
            }

            weekTaskBtnView.setOnClickListener{
                pst = 1
                changeTasksViewing(pst)
            }

            allTasksBtnView.setOnClickListener{
                pst = 2
                changeTasksViewing(pst)
            }


        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        addTacheBtnView.setOnClickListener {
            val tacheName = taskInputView.text.toString()
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, dayOfMonth ->
                val tache = Item(mYear, mMonth, dayOfMonth,tacheName)
                itemList.add(tache)
                Log.i("Length is ", "${itemList.size}")
                val list = itemList.distinct()
                Log.i("Length is ", "${list.size}")
                changeTasksViewing(pst)
            }, year, month, day)

            datePickerDialog.show()


        }


    }


    fun changeTasksViewing(position : Int){
        if (position == 0){
            list.clear()
            list = getTodaysTasks(itemList)
            adapter.notifyDataSetChanged()
        }

        if (position == 1){
           list.clear()
            list = getWeekTasks(itemList)
            adapter.notifyDataSetChanged()
        }

        if (position == 2){
            list.clear()
            list = getAllTasks(itemList)
            adapter.notifyDataSetChanged()
        }
    }



    fun getTodaysTasks(mainList : MutableList<Item>):MutableList<Int>{
        var list : MutableList<Int> = ArrayList()
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        for (i in 0 until mainList.size){
            if (year == mainList.get(i).annee && month == mainList.get(i).mois && day == mainList.get(i).jour){
                list.add(i)
            }
        }
        return list
    }

    fun getWeekTasks(mainList : MutableList<Item>):MutableList<Int>{
        var list : MutableList<Int> = ArrayList()
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val week = c.get(Calendar.WEEK_OF_YEAR)

        for (i in 0 until mainList.size){
            val cal = Calendar.getInstance()
            cal.set(mainList.get(i).annee, mainList.get(i).mois, mainList.get(i).jour)

            if (year == mainList.get(i).annee && week == cal.get(Calendar.WEEK_OF_YEAR)){
                list.add(i)
            }
        }
        return list
    }

    fun getAllTasks(mainList : MutableList<Item>):MutableList<Int>{
        var list : MutableList<Int> = ArrayList()
        for (i in 0 until mainList.size){
            list.add(i)
        }
        return list
    }



}
