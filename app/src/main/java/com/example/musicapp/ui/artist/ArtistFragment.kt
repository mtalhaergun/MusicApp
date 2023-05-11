package com.example.musicapp.ui.artist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentArtistBinding
import com.example.musicapp.ui.artist.adapter.ArtistRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistFragment : Fragment() {

    private lateinit var adapterArtist : ArtistRecyclerAdapter
    private var _binding : FragmentArtistBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ArtistViewModel>()
    private val args by navArgs<ArtistFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterArtist = ArtistRecyclerAdapter()
        binding.rvArtist.adapter = adapterArtist
        binding.textViewGenreName.setText(args.genreArg.name)
        viewModel.getArtist(args.genreArg.id.toString())
        observe()

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun observe(){
        viewModel.artistResponse.observe(viewLifecycleOwner, Observer {
            adapterArtist.setArtists(it.data)
        })
    }

}