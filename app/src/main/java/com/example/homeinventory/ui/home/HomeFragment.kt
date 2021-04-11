package com.example.homeinventory.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.homeinventory.R
import com.example.homeinventory.model.HomeItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickItem {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val categoryRecyclerView = root.findViewById<RecyclerView>(R.id.recycler_category)
        val adapter = CategoryRecyclerViewAdapter(this)
        categoryRecyclerView.adapter = adapter
        homeViewModel.allHomeItem.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })
        return root
    }

    override fun onEditClick(homeItem: HomeItem) {
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationAddHomeItem()
        action.homeItem = homeItem
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.category_menu, menu)
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add_category -> {
                findNavController().navigate(R.id.action_navigation_home_to_navigation_add_category)
                return true
            }
            R.id.menu_add_homeItem -> {
                findNavController().navigate(R.id.action_navigation_home_to_navigation_add_home_item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TAG = "HomeFragment"
    }


}