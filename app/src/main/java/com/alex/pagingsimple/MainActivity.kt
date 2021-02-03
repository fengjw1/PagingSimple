package com.alex.pagingsimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<CheeseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cheeseAdapter = CheeseAdapter()
        cheeseList.adapter = cheeseAdapter

        lifecycleScope.launch {
            viewModel.allCheeses.collectLatest {
                cheeseAdapter.submitData(it) }
        }

        //左右滑动删除操作
        initSwipeToDelete()
        //add 操作
        initAddButtonListener()

    }

    private fun initAddButtonListener(){
        addButton.setOnClickListener {
            addCheese()
        }
    }

    private fun addCheese(){
        val cheeseName = inputText.text.trim()
        if (cheeseName.isNotEmpty()){
            viewModel.insert(cheeseName)
            inputText.setText("")
        }
    }

    private fun initSwipeToDelete(){
        ItemTouchHelper(object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
               return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as CheeseViewHolder).cheese?.let {
                    viewModel.remove(it)
                }
            }
        }).attachToRecyclerView(cheeseList)
    }

}