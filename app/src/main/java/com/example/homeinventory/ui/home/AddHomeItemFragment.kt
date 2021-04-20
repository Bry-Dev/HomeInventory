package com.example.homeinventory.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homeinventory.R
import com.example.homeinventory.model.Category
import com.example.homeinventory.model.HomeItem
import com.example.homeinventory.ui.category.CategoryViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


@AndroidEntryPoint
class AddHomeItemFragment : Fragment() {

    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var homeItemWithId: Map<Int, String>
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var category: List<Category>
    private val args: AddHomeItemFragmentArgs by navArgs()
    private lateinit var editTextHomeItemQty : TextInputEditText
    private lateinit var editTextHomeItemName : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_add_home_item, container, false)
        val btnAddHomeItem = root.findViewById<Button>(R.id.btnAddHomeItem)
        val btnDeleteHome = root.findViewById<Button>(R.id.btnDeleteHome)
        val btnEnableSpin = root.findViewById<ImageButton>(R.id.btnEnableSpin)
        val btnUpdateHomeItem = root.findViewById<Button>(R.id.btnUpdateHomeItem)
        val dropDownCategoryName = root.findViewById<Spinner>(R.id.dropDownCategoryName)
        editTextHomeItemQty = root.findViewById(R.id.editTextHomeItemQty)
        editTextHomeItemName = root.findViewById(R.id.editTextHomeItemName)
        val categoryName = root.findViewById<TextView>(R.id.categoryName)
        var isForEdit = false
        val editHomeItem = args.homeItem; var editName = ""
        var itemId = 0; var editQty = 0; var categoryId = 0
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.AlertDialogTheme)
        alertDialog.setTitle("Notice!")
        alertDialog.setPositiveButton("Ok", null)

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
        if (editHomeItem != null) {
            isForEdit = true
            itemId = editHomeItem.itemId
            editName = editHomeItem.itemName
            editQty = editHomeItem.itemQuantity
            categoryId = editHomeItem.categoryId
            editTextHomeItemName.setText(editName)
            editTextHomeItemQty.setText(editQty.toString())
            categoryViewModel.selectCategory(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { value -> categoryName.text = value },
                    {
                        Toast.makeText(requireContext(), "No Category Found! Contact Husband!", Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }
                )
            dropDownCategoryName.visibility = View.GONE
            btnAddHomeItem.visibility = View.GONE
            btnEnableSpin.setOnClickListener {
                categoryName.visibility = View.GONE
                btnEnableSpin.visibility = View.GONE
                dropDownCategoryName.visibility = View.VISIBLE
                for (i in 0..dropDownCategoryName.adapter.count) {
                    if (categoryName.text == dropDownCategoryName.adapter.getItem(i)) {
                        dropDownCategoryName.setSelection(i)
                        alertDialog.setMessage("Please be aware, changing category will delete shopping data of this item!")
                        alertDialog.show()
                        break
                    }
                }
            }
            btnUpdateHomeItem.setOnClickListener {
                val key = category.first { it.name == dropDownCategoryName.selectedItem }.categoryId
                val homeItemName = editTextHomeItemName.text.toString().trim()
                val homeItemQty = editTextHomeItemQty.text.toString().trim()
                val isVerified = checkValues(homeItemName, homeItemQty, isForEdit)
                if(isVerified) {
                    if (key != categoryId) {
                        homeViewModel.insertHomeItem(HomeItem(key, homeItemName, homeItemQty.toInt(), itemId))
                    }
                    else {
                        homeViewModel.updateHomeItem(HomeItem(categoryId, homeItemName, homeItemQty.toInt(), itemId))
                    }
                    findNavController().navigateUp()
                }
            }
        } else {
            categoryName.visibility = View.GONE
            btnEnableSpin.visibility = View.GONE
            btnDeleteHome.visibility = View.GONE
            btnUpdateHomeItem.visibility = View.GONE
        }

        btnDeleteHome.setOnClickListener {
            if(isForEdit) {
                homeViewModel.deleteHomeItem(HomeItem(categoryId, editName, editQty, itemId))
                findNavController().navigateUp()
            }
            else {
                alertDialog.setMessage("How did you get here??")
                alertDialog.show()
            }
        }

        btnAddHomeItem.setOnClickListener {
            if (dropDownCategoryName.adapter.isEmpty) {
                alertDialog.setMessage("Category is blank. Please add category first")
                alertDialog.show()
            } else {
                val key = category.first { it.name == dropDownCategoryName.selectedItem }.categoryId
                val homeItemName = editTextHomeItemName.text.toString().trim()
                val homeItemQty = editTextHomeItemQty.text.toString().trim()
                val isVerified = checkValues(homeItemName, homeItemQty, isForEdit)
                if(isVerified) {
                    homeViewModel.insertHomeItem(HomeItem(key, homeItemName, homeItemQty.toInt(), itemId))
                    findNavController().navigateUp()
                }
            }
        }
        return root
    }

    private fun checkValues(homeItemName : String, homeItemQty : String, isForEdit : Boolean) : Boolean {
        when {
            homeItemWithId.containsValue(homeItemName) && !isForEdit -> {
                editTextHomeItemName.error = "Home Item already exists"
                return false
            }
            homeItemName.isEmpty() -> {
                editTextHomeItemName.error = "Fill in Name"
                return false
            }
            homeItemQty.isEmpty() -> {
                editTextHomeItemQty.error = "Fill in Quantity"
                return false
            }
            else -> {
                return true
            }
        }
    }
}