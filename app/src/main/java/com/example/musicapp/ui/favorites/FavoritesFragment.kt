package com.example.musicapp.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentFavoritesBinding
import com.example.musicapp.ui.favorites.adapter.FavoritesRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var adapterFavorites : FavoritesRecyclerAdapter
    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FavoritesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterFavorites = FavoritesRecyclerAdapter(viewModel)
        binding.rvFavorites.adapter = adapterFavorites
        adapterFavorites.getDao(viewModel.getFavoritesDao())
        viewModel.getFavorites()
        observe()
    }

    fun observe(){
        viewModel.favorites.observe(viewLifecycleOwner, Observer {
            adapterFavorites.setFavorites(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        adapterFavorites.stopMusic()
        _binding = null
    }

}