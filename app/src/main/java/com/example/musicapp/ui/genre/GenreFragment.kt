package com.example.musicapp.ui.genre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentGenreBinding
import com.example.musicapp.model.genre.Data
import com.example.musicapp.ui.genre.adapter.GenreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : Fragment() {

    private lateinit var adapterGenre : GenreAdapter
    private var _binding : FragmentGenreBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<GenreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterGenre = GenreAdapter()
        binding.rvGenre.adapter = adapterGenre
        viewModel.getGenres()
        observe()
    }

    fun observe(){
        viewModel.genreResponse.observe(viewLifecycleOwner, Observer {
            adapterGenre.setGenres(it.data)
        })
    }

}