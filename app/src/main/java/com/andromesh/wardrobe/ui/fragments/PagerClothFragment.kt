package com.andromesh.wardrobe.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andromesh.wardrobe.databinding.PagerClothFragmentBinding
import java.io.File

class PagerClothFragment : Fragment() {

    private lateinit var binding: PagerClothFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = PagerClothFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val message = arguments!!.getString(EXTRA_MESSAGE)

        binding.imageView.setImageURI(Uri.fromFile(File(message!!)))

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {
        fun newInstance(message: String): PagerClothFragment {

            val f = PagerClothFragment()
            val bdl = Bundle(1)
            bdl.putString(EXTRA_MESSAGE, message)
            f.arguments = bdl

            return f
        }

    }
}