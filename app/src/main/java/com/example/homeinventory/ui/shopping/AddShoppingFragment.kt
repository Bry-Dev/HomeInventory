package com.example.homeinventory.ui.shopping

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homeinventory.R
import com.example.homeinventory.model.ShoppingItem
import com.example.homeinventory.ui.home.HomeViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import kotlin.collections.ArrayList


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
        val dropDownItemName = root.findViewById<Spinner>(R.id.dropDownItemName)
        val editTextItemQty = root.findViewById<TextInputEditText>(R.id.editTextItemQty)
        editTextBuyDate = root.findViewById(R.id.editTextBuyDate)

        homeViewModel.allHomeItemId.observe(
            viewLifecycleOwner,
            { homeItemId ->
                homeItemWithId = homeItemId
                val homeItemNames = ArrayList(homeItemId.values)
                dropDownItemName.adapter =
                    ArrayAdapter(
                        requireContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        homeItemNames
                    )})

        editTextBuyDate.setOnClickListener {
            showDatePickerDialog()
        }
        btnAddToCart.setOnClickListener {
            if (dropDownItemName.adapter.isEmpty) {
                alertDialog.setMessage("Home Item List is Blank. Please add under Home Item First.")
                alertDialog.show()
            } else {
                val key = homeItemWithId.filterValues { it == dropDownItemName.selectedItem }.keys
                shoppingViewModel.insertShoppingItem(ShoppingItem(key.first(), editTextItemQty.text.toString().toInt(), LocalDate.parse(editTextBuyDate.text.toString().trim())))
                findNavController().navigateUp()
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