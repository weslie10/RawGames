package com.weslie10.rawgames.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.weslie10.rawgames.core.ui.FavoriteAdapter
import com.weslie10.rawgames.core.utils.Utility.loading
import com.weslie10.rawgames.databinding.FragmentFavoriteBinding
import com.weslie10.rawgames.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteAdapter = FavoriteAdapter()
        favoriteViewModel.listFavorite.observe(viewLifecycleOwner) { favorite ->
            if (favorite.isNotEmpty()) {
                favoriteAdapter.setData(favorite)
                binding.notFound.loading(false)

                favoriteAdapter.onItemClick = { data ->
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.ID, data.id)
                    startActivity(intent)
                }
                with(binding.rvGames) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = favoriteAdapter
                }
            } else {
                binding.notFound.loading(true)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}