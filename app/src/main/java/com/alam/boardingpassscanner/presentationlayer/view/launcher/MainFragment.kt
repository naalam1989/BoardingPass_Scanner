package com.alam.boardingpassscanner.presentationlayer.view.launcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alam.boardingpassscanner.R
import com.alam.boardingpassscanner.databinding.MainFragmentBinding
import com.alam.boardingpassscanner.presentationlayer.view.launcher.adapter.RecyclerViewAdapter
import com.alam.boardingpassscanner.presentationlayer.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        view?.findViewById<RecyclerView>(R.id.recyclerView)?.run {
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewAdapter(context, viewModel)
            viewModel.getAllBoardingPass().observe(viewLifecycleOwner, { result ->
                if (result.isNullOrEmpty()) {
                    //Clear Recycler View
                    removeAllViewsInLayout()
                    visibility = View.GONE

                    //Show Add Boarding Pass
                    binding.addCueView.visibility = View.VISIBLE

                } else {
                    //Update Recycler View
                    visibility = View.VISIBLE

                    //Hide Add Boarding Pass
                    binding.addCueView.visibility = View.GONE

                    //Update Adapter
                    (adapter as RecyclerViewAdapter).setData(result)

                }
            })
        }
    }

}