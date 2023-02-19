package com.melyseev.testapp.firstscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.melyseev.testapp.App
import com.melyseev.testapp.R
import com.melyseev.testapp.ViewModuleFactory
import com.melyseev.testapp.common.ONE_HUNDRED_PERCENT
import com.melyseev.testapp.common.START_PERCENT_PROGRESS
import com.melyseev.testapp.databinding.FragmentFirstScreenBinding
import javax.inject.Inject

class FirstScreenFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModuleFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FirstScreenViewModel::class.java]
    }

    private val daggerApplicationComponent by lazy {
        (requireActivity().application as App).component
    }

    private var _binding: FragmentFirstScreenBinding? = null
    private val binding: FragmentFirstScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstScreenBinding is null ")


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        daggerApplicationComponent.inject(this)

        //Progress
        binding.progress.max = ONE_HUNDRED_PERCENT
        binding.progress.progress = START_PERCENT_PROGRESS

        viewModel.observeProgress(viewLifecycleOwner) {
            binding.progress.progress = it
            binding.tvValuePercent.text = String.format(
                getString(R.string.percent_show),
                it.toString())
        }

        //Lottie image
        val staticImage = binding.staticImageView
        binding.stopLottie.setOnClickListener {
            binding.lottie.pauseAnimation()
        }
        binding.startLottie.setOnClickListener {
            binding.lottie.resumeAnimation()
        }
        binding.hideShowLottie.setOnClickListener {
            if (binding.lottie.isVisible) {
                binding.lottie.visibility = View.GONE
                staticImage.visibility = View.VISIBLE
            } else {
                binding.lottie.visibility = View.VISIBLE
                staticImage.visibility = View.GONE
            }
        }


        //Show custom view
        binding.showCustomAlert.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialog)
                .create()

            val view =
                layoutInflater.inflate(R.layout.custom_view_layout, null)
            val button = view.findViewById<Button>(R.id.closeButton)

            builder.setView(view)
            button.setOnClickListener {
                builder.dismiss()
            }
            builder.setCanceledOnTouchOutside(true)
            builder.show()
        }

        //Next screen button
        binding.btnSecondScreen.setOnClickListener {
            findNavController().navigate(R.id.action_firstScreenFragment_to_secondScreenFragment)
        }

    }

    override fun onPause() {
        super.onPause()
        viewModel.jobProgress.cancel()
        binding.lottie.pauseAnimation()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchProgress()
        binding.lottie.resumeAnimation()
    }

}