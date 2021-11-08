package com.kdjj.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kdjj.presentation.R
import com.kdjj.presentation.databinding.ItemEditorAddStepBinding
import com.kdjj.presentation.databinding.ItemEditorRecipeMetaBinding
import com.kdjj.presentation.databinding.ItemEditorRecipeStepBinding
import com.kdjj.presentation.model.RecipeEditorItem
import com.kdjj.presentation.viewmodel.recipeeditor.RecipeEditorViewModel

class RecipeEditorListAdapter(private val viewModel: RecipeEditorViewModel) :
    ListAdapter<RecipeEditorItem, RecyclerView.ViewHolder>(RecipeEditorItemCallback()) {

    class RecipeMetaViewHolder(private val binding: ItemEditorRecipeMetaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeEditorItem.RecipeMetaModel) {
            binding.viewModel = item
            val adapter = ArrayAdapter(
                binding.root.context,
                R.layout.item_editor_recipe_type,
                arrayOf("한식", "양식", "중식", "일식")
            )
            binding.spinnerEditorRecipeType.adapter = adapter
        }
    }

    class RecipeStepViewHolder(private val binding: ItemEditorRecipeStepBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeEditorItem.RecipeStepModel) {
            binding.viewModel = item
        }
    }

    class AddStepViewHolder(private val binding: ItemEditorAddStepBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

        }
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is RecipeEditorItem.RecipeMetaModel -> TYPE_META
            is RecipeEditorItem.RecipeStepModel -> TYPE_STEP
            RecipeEditorItem.PlusButton -> TYPE_ADD
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_META -> {
                val binding = ItemEditorRecipeMetaBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                RecipeMetaViewHolder(binding)
            }
            TYPE_STEP -> {
                val binding = ItemEditorRecipeStepBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                RecipeStepViewHolder(binding)
            }
            else -> {
                val binding = ItemEditorAddStepBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AddStepViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (item as? RecipeEditorItem.RecipeMetaModel)?.let { (holder as RecipeMetaViewHolder).bind(item) }
            ?: (item as? RecipeEditorItem.RecipeStepModel)?.let { (holder as RecipeStepViewHolder).bind(item) }
            ?: (item as? RecipeEditorItem.PlusButton)?.let { (holder as AddStepViewHolder).bind() }
    }

    companion object {
        private const val TYPE_META = 0
        private const val TYPE_STEP = 1
        private const val TYPE_ADD = 2
    }
}