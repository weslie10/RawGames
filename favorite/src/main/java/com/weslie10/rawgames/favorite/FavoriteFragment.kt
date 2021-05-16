package com.weslie10.rawgames.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.weslie10.rawgames.core.domain.model.Games
import com.weslie10.rawgames.core.utils.Utility.loading
import com.weslie10.rawgames.favorite.databinding.FragmentFavoriteBinding
import com.weslie10.rawgames.favorite.di.favoriteModule
import com.weslie10.rawgames.favorite.utils.ItemSwipeHelper
import com.weslie10.rawgames.favorite.utils.OnItemSwiped
import com.weslie10.rawgames.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemSwipeHelper = ItemSwipeHelper(object : OnItemSwiped {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder) {
                val swipedPosition = viewHolder.bindingAdapterPosition
                val gameEntity = favoriteAdapter.getSwipedData(swipedPosition)
                setFavorite(gameEntity)
                Snackbar.make(view, R.string.message_undo, Snackbar.LENGTH_LONG).apply {
                    setAction(R.string.message_ok) { setFavorite(gameEntity) }
                    show()
                }
            }
        })
        itemSwipeHelper.attachToRecyclerView(binding.rvGames)

        loadKoinModules(favoriteModule)

        favoriteAdapter = FavoriteAdapter()
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

    private fun setFavorite(games: Games) {
        games.isFavorite = !games.isFavorite
        favoriteViewModel.setFavoriteGames(games, games.isFavorite)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}