package com.example.homeinventory.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homeinventory.R
import com.example.homeinventory.model.Category
import com.example.homeinventory.model.HomeItem
import com.example.homeinventory.ui.category.CategoryViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddHomeItemFragment : Fragment() {

    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var homeItemWithId: Map<Int, String>
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var category: List<Category>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_add_home_item, container, false)
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Notice!")
        alertDialog.setPositiveButton("Ok", null)
        val btnAddHomeItem = root.findViewById<Button>(R.id.btnAddHomeItem)
        val dropDownCategoryName = root.findViewById<Spinner>(R.id.dropDownCategoryName)
        val editTextHomeItemQty = root.findViewById<TextInputEditText>(R.id.editTextHomeItemQty)
        val editTextHomeItemName = root.findViewById<TextInputEditText>(R.id.editTextHomeItemName)

        homeViewModel.allHomeItemId.observe(
            viewLifecycleOwner,
            { homeItemId -> homeItemWithId = homeItemId })

        categoryViewModel.allCategory.observe(
            viewLifecycleOwner,
            { allCategory ->
                category = allCategory
                val categoryNames = allCategory.map { it.name }
                dropDownCategoryName.adapter =
                    ArrayAdapter(
                        requireContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        categoryNames
                    )
            })

        btnAddHomeItem.setOnClickListener {
            if (dropDownCategoryName.adapter.isEmpty) {
                alertDialog.setMessage("Home Item List is Blank. Please add under Home Item First.")
                alertDialog.show()
            } else {
                val key = category.first { it.name == dropDownCategoryName.selectedItem }.categoryId
                if (homeItemWithId.containsValue(editTextHomeItemName.text.toString().trim())) {
                    editTextHomeItemName.error = "Home Item already exists"
                }
                else {
                    homeViewModel.insertHomeItem(
                        HomeItem(
                            key,
                            editTextHomeItemName.text.toString(),
                            editTextHomeItemQty.text.toString().toInt()
                        )
                    )
                }
            }
        }
        return root
    }
}