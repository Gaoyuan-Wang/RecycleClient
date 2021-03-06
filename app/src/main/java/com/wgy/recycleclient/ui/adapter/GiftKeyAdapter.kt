package com.wgy.recycleclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckGiftByKeyData
import com.wgy.recycleclient.logic.model.ExchangeGift
import com.wgy.recycleclient.ui.fragment.IntegralFragment
import kotlinx.android.synthetic.main.fragment_integral.*

class GiftKeyAdapter (private val fragment: IntegralFragment, private val giftList: List<CheckGiftByKeyData>):
    RecyclerView.Adapter<GiftKeyAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val giftName: TextView = view.findViewById(R.id.giftName)
        val giftPoint: TextView = view.findViewById(R.id.giftPoint)
        val giftStock: TextView = view.findViewById(R.id.giftStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_gift_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition

            if (fragment.activity?.getSharedPreferences(
                    "MailingInformation",
                    AppCompatActivity.MODE_PRIVATE
                )?.getString("isSaved", null).toString() == "true"
            ) {
                fragment.viewModel.contact = fragment.activity?.getSharedPreferences(
                    "MailingInformation",
                    AppCompatActivity.MODE_PRIVATE
                )?.getString("contact", null).toString()
                fragment.viewModel.location = fragment.activity?.getSharedPreferences(
                    "MailingInformation",
                    AppCompatActivity.MODE_PRIVATE
                )?.getString("location", null).toString()
                fragment.viewModel.phone = fragment.activity?.getSharedPreferences(
                    "MailingInformation",
                    AppCompatActivity.MODE_PRIVATE
                )?.getString("phone", null).toString()
                AlertDialog.Builder(parent.context).apply {
                    setCancelable(false)
                    setMessage(
                        "?????????????????????????????????\n" +
                                "???????????????${giftList[position].name}\n" +
                                "?????????????????????${giftList[position].point}"
                    )
                    setPositiveButton("??? ???") { _, _ ->
                        fragment.viewModel.exchangeGift = ExchangeGift(
                            fragment.viewModel.id,
                            fragment.viewModel.id,
                            giftList[position].id,
                            fragment.viewModel.contact,
                            fragment.viewModel.phone,
                            fragment.viewModel.location
                        )
                        fragment.viewModel.exchangeGift(fragment.viewModel.exchangeGift)
                        fragment.searchRecyclerView.visibility = View.GONE
                        fragment.viewModel.checkResidentById(fragment.viewModel.checkResidentById)
                        fragment.searchGift.setText("")
                    }
                    setNegativeButton("??? ???") { _, _ ->
                        fragment.searchRecyclerView.visibility = View.GONE
                        fragment.searchGift.setText("")
                    }
                    show()
                }
            } else {
                Toast.makeText(fragment.activity, "??????????????????????????????", Toast.LENGTH_SHORT).show()
                fragment.searchRecyclerView.visibility = View.GONE
                fragment.searchGift.setText("")
            }

        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gift = giftList[position]
        holder.giftName.text = gift.name
        holder.giftPoint.text = gift.point.toString()
        holder.giftStock.text = gift.stock.toString()
    }

    override fun getItemCount(): Int = giftList.size
}