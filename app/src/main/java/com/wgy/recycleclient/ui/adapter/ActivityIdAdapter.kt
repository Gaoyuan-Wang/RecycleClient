package com.wgy.recycleclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.wgy.recycleclient.R
import com.wgy.recycleclient.logic.model.CheckActivityByIdData
import com.wgy.recycleclient.logic.model.Sign
import com.wgy.recycleclient.ui.fragment.ActivityFragment
import kotlinx.android.synthetic.main.fragment_activity.*


class ActivityIdAdapter(
    private val fragment: ActivityFragment,
    private val activityList: ArrayList<CheckActivityByIdData>
):
    RecyclerView.Adapter<ActivityIdAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val activityName: TextView = view.findViewById(R.id.activityName)
        val activityPoint: TextView = view.findViewById(R.id.activityPoint)
        val activityLocation: TextView = view.findViewById(R.id.activityLocation)
        val activityBeginTime: TextView = view.findViewById(R.id.activityBeginTime)
        val activityEndTime: TextView = view.findViewById(R.id.activityEndTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_id_item,
            parent,
            false
        )
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            AlertDialog.Builder(parent.context).apply {
                setCancelable(false)
                setMessage("您确定报名此活动吗？：\n${activityList[position + 1].name}\n")
                setPositiveButton("确 定"){ _, _->
                    fragment.viewModel.sign = Sign(
                        activityList[position + 1].id,
                        fragment.viewModel.id
                    )
                    fragment.viewModel.sign(fragment.viewModel.sign)
                    fragment.idRecyclerView.visibility = View.GONE
                    fragment.searchActivity.setText("")
                }
                setNegativeButton("取 消"){ _, _->
                    fragment.idRecyclerView.visibility = View.GONE
                    fragment.searchActivity.setText("")
                }
                show()
            }
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = activityList[position]
        holder.activityName.text = activity.name
        holder.activityPoint.text = activity.point.toString()
        holder.activityLocation.text = activity.location
        holder.activityBeginTime.text = activity.startTime
        holder.activityEndTime.text = activity.endTime
    }

    override fun getItemCount(): Int = activityList.size
}