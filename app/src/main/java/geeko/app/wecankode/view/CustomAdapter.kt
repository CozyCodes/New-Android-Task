package geeko.app.wecankode.view

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import geeko.app.wecankode.databinding.AppItemBinding
import geeko.app.wecankode.model.DataInfo


class CustomAdapter(private var listener: OnCategoryClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var filterList = listOf<DataInfo>()
    var originalList = listOf<DataInfo>()

    fun setAppList(modelList: List<DataInfo>) {
        this.filterList = modelList
        this.originalList = modelList

        //notifyItemRangeInserted(0, categoryModel.size)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        Log.d("LIST_SIZE","" + this.filterList.size)
        return this.filterList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val appInfo = this.filterList[position]
        (holder as RecyclerHolderCatIcon).bind(appInfo, listener)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val applicationBinding = AppItemBinding.inflate(layoutInflater, parent, false)
        return RecyclerHolderCatIcon(applicationBinding)
    }

    interface OnCategoryClickListener {
        fun onCategoryClick(position: Int)
    }


    inner class RecyclerHolderCatIcon(private var applicationBinding: AppItemBinding) : RecyclerView.ViewHolder(applicationBinding.root) {

        fun bind(appInfo: DataInfo, listener: OnCategoryClickListener?) {
            applicationBinding.dataModel  = appInfo

        }


    }
}