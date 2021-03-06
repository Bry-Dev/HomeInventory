package com.example.homeinventory.ui.shopping

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homeinventory.R
import com.example.homeinventory.model.ShoppingItem
import com.example.homeinventory.ui.home.HomeViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate


@AndroidEntryPoint
class AddShoppingFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var editTextBuyDate: TextInputEditText
    private lateinit var homeItemWithId: Map<Int, String>
    private val shoppingViewModel: ShoppingViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.AlertDialogTheme)
        alertDialog.setTitle("Notice!")
        alertDialog.setPositiveButton("Ok", null)
        val root = inflater.inflate(R.layout.fargment_add_shopping, container, false)
        val btnAddToCart = root.findViewById<Button>(R.id.btnAddToCart)
        val autoCompleteNames = root.findViewById<AutoCompleteTextView>(R.id.autoCompleteNames)
        val editTextItemQty = root.findViewById<TextInputEditText>(R.id.editTextItemQty)
        editTextBuyDate = root.findViewById(R.id.editTextBuyDate)

        homeViewModel.allHomeItemId.observe(
            viewLifecycleOwner,
            { homeItemId ->
                homeItemWithId = homeItemId
                val homeItemNames = ArrayList(homeItemId.values)
                autoCompleteNames.setAdapter(
                    ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        homeItemNames
                    )
                )
            })

        autoCompleteNames.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                autoCompleteNames.showDropDown()
            }
        }

        editTextBuyDate.setOnClickListener {
            showDatePickerDialog()
        }
        btnAddToCart.setOnClickListener {
            if (autoCompleteNames.adapter.isEmpty) {
                alertDialog.setMessage("Home Item List is Blank. Please add under Home Item First.")
                alertDialog.show()
            } else if(editTextBuyDate.text.toString().trim().isEmpty()) {
                editTextBuyDate.error = "Please enter date."
            } else {
                val key = homeItemWithId.filterValues { it == autoCompleteNames.text.toString() }.keys
                if (key.isNullOrEmpty()) {
                    autoCompleteNames.error = "Please select a correct item!"
                }
                else {
                    shoppingViewModel.insertShoppingItem(
                        ShoppingItem(
                            key.first(), editTextItemQty.text.toString().toInt(), LocalDate.parse(
                                editTextBuyDate.text.toString().trim()
                            )
                        )
                    )
                    autoCompleteNames.setText("")
                    editTextItemQty.setText("")
                    autoCompleteNames.requestFocus()
                }
            }
        }
        return root
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            android.R.style.Theme_DeviceDefault_Dialog,
            this,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val monthFormat = month + 1
        var date = "$year-$monthFormat-$dayOfMonth"
        if (monthFormat < 10) date = "$year-0$monthFormat-$dayOfMonth"
        if (dayOfMonth < 10) date = "$year-0$monthFormat-0$dayOfMonth"
        editTextBuyDate.setText(date)

    }
}