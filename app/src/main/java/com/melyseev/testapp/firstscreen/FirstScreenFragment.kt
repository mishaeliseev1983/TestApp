package com.melyseev.testapp.firstscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        daggerApplicationComponent.inject(this)

        //Progress
        val progress = binding.progress
        val tvValueProgress = binding.tvValuePercent
        progress.max = ONE_HUNDRED_PERCENT
        progress.progress = START_PERCENT_PROGRESS

        viewModel.observeProgress(viewLifecycleOwner){
            progress.progress = it
            tvValueProgress.text = "$it %"
        }
        viewModel.fetchProgress()


        //Lottie image
        val lottie = binding.lavMain
        val staticImage = binding.staticImageView
        val stopBtn = binding.stop
        stopBtn.setOnClickListener {
            lottie.pauseAnimation()
        }
        val startBtn = binding.start
        startBtn.setOnClickListener {
            lottie.resumeAnimation()
        }
        val hideShowBtn = binding.hideShow
        hideShowBtn.setOnClickListener {
            if (lottie.isVisible) {
                lottie.visibility = View.GONE
                staticImage.visibility = View.VISIBLE
            } else {
                lottie.visibility = View.VISIBLE
                staticImage.visibility = View.GONE
            }
        }



        //Show custom view
        binding.showCustomAlert.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity(),R.style.CustomAlertDialog)
                .create()

            val view =
              layoutInflater.inflate(R.layout.custom_view_layout,null)
            val  button = view.findViewById<Button>(R.id.closeButton)

            builder.setView(view)
            button.setOnClickListener {
                builder.dismiss()
            }
            builder.setCanceledOnTouchOutside(true)
            builder.show()
        }

        //Next screen button
        val nextScreenBtn = binding.toSecondScreen
        nextScreenBtn.setOnClickListener {
            findNavController().navigate(R.id.action_firstScreenFragment_to_secondScreenFragment)
        }

    }

    override fun onPause() {
        super.onPause()
        viewModel.progressStarted = false
        viewModel.jobProgress.cancel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchProgress()
    }


}