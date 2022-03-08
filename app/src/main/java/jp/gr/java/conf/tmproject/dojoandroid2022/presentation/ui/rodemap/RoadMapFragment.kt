package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.model.RoadMapModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader

class RoadMapFragment : Fragment(R.layout.roadmap_fragment) {

    private var _binding: RoadmapFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapFragmentBinding.bind(view)
        parseRodeMap()
    }

    private fun parseRodeMap(): RoadMapModel = Json.decodeFromString(getJsonData())

    private fun getJsonData(): String {
        val assetManager = resources.assets
        val inputStream = assetManager.open("android-developer-roadmap2022.json")
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        return bufferedReader.readText()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
