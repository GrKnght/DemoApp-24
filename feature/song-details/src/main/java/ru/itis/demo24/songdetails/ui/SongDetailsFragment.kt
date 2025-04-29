package ru.itis.demo24.songdetails.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.androidbroadcast.vbpd.viewBinding
import ru.itis.demo24.basefeature.BaseFragment
import ru.itis.demo24.songdetails.R
import ru.itis.demo24.songdetails.databinding.FragmentSongDetailsBinding
import ru.itis.demo24.base.R as baseR

@AndroidEntryPoint
class SongDetailsFragment : BaseFragment(R.layout.fragment_song_details) {

    private val viewBinding by viewBinding(FragmentSongDetailsBinding::bind)

    private val viewModel: SongDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.headerTv.text = getString(baseR.string.song_details_header)
    }
}