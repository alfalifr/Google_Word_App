package com.example.wordsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.wordsapp.Util.changeLayout
import com.example.wordsapp.Util.setLayoutItem
import com.example.wordsapp.databinding.FragmentRecyclerviewBinding

class WordListFragment: Fragment() {
  companion object {
    const val LETTER = "letter"
    const val SEARCH_PREFIX = "https://www.google.com/search?q="
  }

  private lateinit var binding: FragmentRecyclerviewBinding
  private val arg by lazy {
    WordListFragmentArgs.fromBundle(requireArguments())
  }
  private var isLinear = true

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)

    //title = getString(R.string.detail_prefix) + " " + letterId
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
        adapter = WordAdapter(
          arg.letter,
          requireContext()
        ) {
          val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("$SEARCH_PREFIX$it")
          )
          startActivity(intent)
        }
        changeLayout(isLinear)
      }
    }
  }
}