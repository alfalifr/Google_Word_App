package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.wordsapp.Util.changeLayout
import com.example.wordsapp.Util.setLayoutItem
import com.example.wordsapp.databinding.FragmentRecyclerviewBinding

class LetterListFragment: Fragment() {
  private lateinit var binding: FragmentRecyclerviewBinding
  private lateinit var navController: NavController
  private var isLinear = true

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
    navController = findNavController()
  }

  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    inflater.inflate(R.menu.layout_menu, menu)
    menu.findItem(R.id.action_switch_layout)?.setLayoutItem(requireContext(), isLinear)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean =
    if(item.itemId == R.id.action_switch_layout) {
      isLinear = !isLinear
      item.setLayoutItem(requireContext(), isLinear)
      binding.recyclerView.changeLayout(isLinear)
      true
    } else {
      super.onOptionsItemSelected(item)
    }


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentRecyclerviewBinding.inflate(inflater)
    return binding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    binding.apply {
      recyclerView.apply {
        adapter = LetterAdapter {
          val action = LetterListFragmentDirections.actionFragmentLetterToFragmentWord(
            it.toString()
          )
          navController.navigate(action)
        }
        changeLayout(isLinear)
      }
    }
  }
}