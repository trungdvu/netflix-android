package com.trungdvu.netflix.ui.screens.detail.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.trungdvu.netflix.data.constant.ApiConstant.SAMPLE_VIDEO_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor() : ViewModel() {

    private val _extractedVideoUrl = MutableLiveData("")
    val extractedVideoUrl: LiveData<String> = _extractedVideoUrl

    @SuppressLint("StaticFieldLeak")
    fun extract(context: Context, youtubeLink: String) {

        object : YouTubeExtractor(context) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                vMeta: VideoMeta?
            ) {
                if (ytFiles != null) {
                    val iTag = 22
                    val streamUrl = ytFiles[iTag]?.url

                    streamUrl?.let { safeStreamUrl ->
                        _extractedVideoUrl.value = safeStreamUrl
                    }
                } else {
                    _extractedVideoUrl.value = SAMPLE_VIDEO_URL
                }
            }
        }.extract(youtubeLink)
    }
}