package com.example.homeinventory.helper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.homeinventory.R
import com.example.homeinventory.model.HomeItem
import com.example.homeinventory.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditDialog(private val homeItem: HomeItem) : DialogFragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setTitle("Please select quantity")
        requireContext().theme.applyStyle(R.style.EditDialog, true)
        return inflater.inflate(R.layout.edit_item_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numberOfItems = homeItem.itemQuantity
        val numPicker = view.findViewById<NumberPicker>(R.id.numPickQty)
        val btnOK = view.findViewById<Button>(R.id.btnOk)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)
        val itemQty = Array(numberOfItems) { (it + 1).toString() }
        numPicker?.maxValue = numberOfItems
        numPicker?.minValue = 1
        numPicker?.displayedValues = itemQty

        btnOK.setOnClickListener {
            val difference = homeItem.itemQuantity - numPicker.value
            homeViewModel.updateHomeItem(HomeItem(homeItem.categoryId, homeItem.itemName, difference, homeItem.itemId))
            dialog?.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog?.dismiss()
        }

    }
}