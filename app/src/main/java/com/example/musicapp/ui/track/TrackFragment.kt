package com.example.musicapp.ui.track

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentTrackBinding
import com.example.musicapp.ui.track.adapter.TrackRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackFragment : Fragment() {

    private lateinit var adapterTrack : TrackRecyclerAdapter
    private var _binding : FragmentTrackBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<TrackViewModel>()
    private val args by navArgs<TrackFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrackBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterTrack = TrackRecyclerAdapter()
        binding.rvTrack.adapter = adapterTrack
        binding.albumName.text = args.albumArg.title
        viewModel.getTracks(args.albumArg.id.toString())
        observe()
    }

    fun observe(){
        viewModel.trackResponse.observe(viewLifecycleOwner, Observer {
            adapterTrack.setTracks(it.data)
        })
    }

}