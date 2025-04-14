package com.sheverdyaevartem.home.ui.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sheverdyaevartem.core.R.*
import com.sheverdyaevartem.home.R
import com.sheverdyaevartem.home.databinding.RvAppBinding

class AppAdapter(private val onCLick: ((app: AppMetaInfo) -> Unit)? = null) : Adapter<AppViewHolder>() {

    private val apps: MutableList<AppMetaInfo> = mutableListOf()

    fun setNewItems(apps: List<AppMetaInfo>) {
        this.apps.clear()
        this.apps.addAll(apps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        return AppViewHolder(
            RvAppBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val current = apps[position]
        holder.bind(current)
        onCLick?.let {
            holder.itemView.setOnClickListener {
                onCLick.invoke(current)
            }
        }
    }


}

class AppViewHolder(private val binding: RvAppBinding) : ViewHolder(binding.root) {

    fun bind(model: AppMetaInfo) {
        Glide.with(itemView)
            .load(model.appIcon)
            .placeholder(drawable.ic_app_placeholder)
            .transform(RoundedCorners(12))
            .into(binding.ivAppIcon)
        binding.label.text = model.appName
    }

}