package com.example.homeinventory.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homeinventory.R
import com.example.homeinventory.model.Category
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCategoryFragment : Fragment() {

    private val categoryViewModel : CategoryViewModel by viewModels()
    private val args: AddCategoryFragmentArgs by navArgs()
    private lateinit var compareCategory: List<Category>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_add_category, container, false)
        val btnAdd = root.findViewById<Button>(R.id.btnAddCategory)
        val newCategoryText = root.findViewById<TextInputEditText>(R.id.edtNewCategory)
        var isForEdit = false
        var itemId = 0
        categoryViewModel.allCategory.observe(viewLifecycleOwner,
            { category -> compareCategory = category})
        val editCategory = args.category
        if (editCategory != null) {
            isForEdit = true
            newCategoryText.setText(editCategory.name)
            itemId = editCategory.categoryId
        }
        btnAdd.setOnClickListener {
            val newCategory = newCategoryText.text.toString().trim()
            if(newCategory.isEmpty()) {
                newCategoryText.error = "Can't be blank"
            }
            else {
                if(compareCategory.any{it.name == newCategory} && !isForEdit) {
                    newCategoryText.error = "Category already exists"
                }
                else {
                    if (isForEdit) {
                        categoryViewModel.updateCategory(Category(newCategory, itemId))
                    }
                    else {
                        categoryViewModel.insertCategoryData(Category(newCategory, itemId))
                    }
                    findNavController().navigateUp()
                }
            }
        }
        return root
    }
}