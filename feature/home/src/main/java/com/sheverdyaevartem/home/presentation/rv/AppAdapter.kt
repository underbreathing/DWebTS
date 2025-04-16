package com.sheverdyaevartem.home.presentation.rv

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sheverdyaevartem.core.R.*
import com.sheverdyaevartem.home.databinding.RvAppBinding
import com.sheverdyaevartem.home.domain.model.AppMetaInfo

class AppAdapter(private val onCLick: ((app: AppMetaInfo) -> Unit)? = null) :
    ListAdapter<AppMetaInfo, AppViewHolder>(AppDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        return AppViewHolder(
            RvAppBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val current = getItem(position)
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
            .load(Uri.parse(model.appIconUri))
            .placeholder(drawable.ic_app_placeholder)
            .transform(RoundedCorners(12))
            .into(binding.ivAppIcon)
        binding.label.text = model.appName
    }
}

class AppDiffCallback : DiffUtil.ItemCallback<AppMetaInfo>() {
    override fun areItemsTheSame(oldItem: AppMetaInfo, newItem: AppMetaInfo): Boolean {
        return oldItem.packageName == newItem.packageName
    }

    override fun areContentsTheSame(oldItem: AppMetaInfo, newItem: AppMetaInfo): Boolean {
        return oldItem == newItem
    }
}