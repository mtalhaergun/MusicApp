package com.example.musicapp.ui.artistdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentArtistDetailBinding
import com.example.musicapp.ui.artistdetail.adapter.AlbumRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistDetailFragment : Fragment() {

    private lateinit var adapterAlbum : AlbumRecyclerAdapter
    private var _binding : FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ArtistDetailViewModel>()
    private val args by navArgs<ArtistDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterAlbum = AlbumRecyclerAdapter()
        binding.rvAlbum.adapter = adapterAlbum
        binding.artist = args.artistArg
        viewModel.getAlbums(args.artistArg.id.toString(),0)
        observe()
    }

    fun observe(){
        viewModel.albumResponse.observe(viewLifecycleOwner, Observer {
            adapterAlbum.setAlbums(it)
        })
    }

}