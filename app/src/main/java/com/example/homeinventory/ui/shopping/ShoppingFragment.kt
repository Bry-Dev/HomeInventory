package com.example.homeinventory.ui.shopping

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.homeinventory.R
import com.example.homeinventory.helper.SwipeHelper
import com.example.homeinventory.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShoppingFragment : Fragment() {

    private val shoppingViewModel: ShoppingViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_shopping, container, false)
        val recyclerShopping = root.findViewById<RecyclerView>(R.id.recycler_shopping)
        val adapter = ShoppingRecyclerViewAdapter()
        adapter.setHasStableIds(true)
        recyclerShopping.adapter = adapter
        shoppingViewModel.allShoppingItemForHome.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })
        val swipeToDeleteCallback = SwipeHelper { position ->
            val shoppingForHome = adapter.getShoppingAt(position)
            shoppingForHome?.let {
                val newTotal = it.shoppingItem.itemBuyQuantity + it.homeItem.itemQuantity
                homeViewModel.updateQuantity(newTotal, it.homeItem.itemId)
                shoppingViewModel.deleteShoppingItem(it.shoppingItem)
                Toast.makeText(
                    requireContext(),
                    "${it.homeItem.itemName} bought!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(recyclerShopping)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shopping_menu, menu)
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add_to_cart -> {
                findNavController().navigate(R.id.action_navigation_shopping_to_navigation_add_shopping)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}