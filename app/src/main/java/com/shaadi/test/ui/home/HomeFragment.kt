package com.shaadi.test.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.shaadi.test.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dispatcher = requireActivity().onBackPressedDispatcher
        dispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitDialog()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btCard.setOnClickListener {
            findNavController().navigate(R.id.userCardFragment)
        }
        btList.setOnClickListener {
            findNavController().navigate(R.id.userListFragment)
        }
    }

    private fun OnBackPressedCallback.showExitDialog() {
        val dialog = MaterialDialog(requireContext(), BottomSheet(LayoutMode.WRAP_CONTENT))
        dialog.show {
            title(text = "Are you sure?")
            message(text = "Are you sure you want to exit?")
            positiveButton(text = "Yes") { dialog ->
                isEnabled = false
                dialog.dismiss()
                requireActivity().onBackPressed()
            }
            negativeButton(text = "No") { dialog ->
                dialog.dismiss()
            }
        }
    }
}