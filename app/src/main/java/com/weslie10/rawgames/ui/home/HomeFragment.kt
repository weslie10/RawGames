package com.weslie10.rawgames.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.weslie10.rawgames.core.data.source.remote.response.ResultsItem
import com.weslie10.rawgames.core.ui.GamesAdapter
import com.weslie10.rawgames.core.utils.Utility.loading
import com.weslie10.rawgames.databinding.FragmentHomeBinding
import com.weslie10.rawgames.ui.detail.DetailActivity
import com.weslie10.rawgames.ui.detail.DetailActivity.Companion.ID
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var gamesAdapter: GamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            gamesAdapter = GamesAdapter()

            gamesAdapter.onItemClick = { data ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(ID, data.id)
                startActivity(intent)
            }

            binding.progressBar.loading(true)
            homeViewModel.listGames.observe(viewLifecycleOwner, gameObserver)

            with(binding.rvGames) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gamesAdapter
            }

            setUpSearch()
            homeViewModel.getSearch().observe(viewLifecycleOwner, { search ->
                allLoading(true)
                if (search.isNotEmpty()) {
                    homeViewModel.searchGames.observe(viewLifecycleOwner, gameObserver)
                } else {
                    homeViewModel.listGames.observe(viewLifecycleOwner, gameObserver)
                }
            })
        }
    }

    private val gameObserver = Observer<List<ResultsItem>> { game ->
        if (game.isNotEmpty()) {
            allLoading(false)
            gamesAdapter.setData(game)
            binding.notFound.loading(false)
        } else {
            binding.notFound.loading(true)
        }
    }

    private fun setUpSearch() {
        binding.searchView.apply {
            setOnClickListener {
                onActionViewExpanded()
                allLoading(false)
            }
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean = search(query)
                override fun onQueryTextChange(newText: String): Boolean = search(newText)
            })
        }
    }

    private fun search(query: String): Boolean {
        allLoading(true)
        homeViewModel.setSearch(query)
        return true
    }

    private fun allLoading(state: Boolean) {
        binding.progressBar.loading(state)
        binding.rvGames.visibility = if (!state) View.VISIBLE else View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}