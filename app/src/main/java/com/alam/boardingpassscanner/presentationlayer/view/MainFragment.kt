package com.alam.boardingpassscanner.presentationlayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alam.boardingpassscanner.R
import com.alam.boardingpassscanner.presentationlayer.view.adapter.RecyclerViewAdapter
import com.alam.boardingpassscanner.presentationlayer.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        view?.findViewById<RecyclerView>(R.id.recyclerView)?.run {
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewAdapter(context)
            viewModel.getAllBoardingPass().observe(viewLifecycleOwner, { result ->
                if (result.isNullOrEmpty()) {
                    //Show Add Boarding Pass Layout
                } else {
                    //Update Recycler View
                    (adapter as RecyclerViewAdapter).setData(result)
                }
            })
        }
    }

}