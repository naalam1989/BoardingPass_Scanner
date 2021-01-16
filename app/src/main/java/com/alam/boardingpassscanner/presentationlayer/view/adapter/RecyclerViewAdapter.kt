package com.alam.boardingpassscanner.presentationlayer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alam.boardingpassscanner.R
import com.alam.boardingpassscanner.databinding.BoardingItemLayoutBinding
import com.alam.boardingpassscanner.datalayer.database.room.BoardingPassEntity
import com.alam.boardingpassscanner.presentationlayer.viewmodel.MainViewModel


class RecyclerViewAdapter(context: Context, val viewModel: MainViewModel) : RecyclerView.Adapter<RecyclerViewAdapter.RepositoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val boardingPasses = mutableListOf<BoardingPassEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            DataBindingUtil.inflate(inflater, R.layout.boarding_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindView(boardingPasses[position])
    }

    override fun getItemCount(): Int {
        return boardingPasses.size
    }

    fun setData(list: List<BoardingPassEntity>) {
        //TODO: Can be optimised to remove the item deleted using #notifyItemRemoved
        boardingPasses.clear()
        boardingPasses.addAll(list)
        notifyDataSetChanged()
    }

    inner class RepositoryViewHolder(private val binding: BoardingItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: BoardingPassEntity) {
            binding.boardingPass = item
            binding.viewModel = viewModel
        }

    }
}