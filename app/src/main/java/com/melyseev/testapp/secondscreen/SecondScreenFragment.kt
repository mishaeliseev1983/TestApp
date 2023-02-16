package com.melyseev.testapp.secondscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.melyseev.testapp.App
import com.melyseev.testapp.R
import com.melyseev.testapp.ViewModuleFactory
import com.melyseev.testapp.common.ONE_HUNDRED_PERCENT
import com.melyseev.testapp.common.START_PERCENT_PROGRESS
import com.melyseev.testapp.databinding.FragmentSecondScreenBinding
import com.melyseev.testapp.secondscreen.recyclerview.RaitingsAdapter
import javax.inject.Inject

class SecondScreenFragment : Fragment() {

    private var _binding: FragmentSecondScreenBinding? = null
    private val binding: FragmentSecondScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentSecondScreenBinding is null ")


    @Inject
    lateinit var viewModelFactory: ViewModuleFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[SecondScreenViewModel::class.java]
    }

    private val daggerApplicationComponent by lazy {
        (requireActivity().application as App).component
    }

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
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        daggerApplicationComponent.inject(this)

        viewModel.observeDataClock(viewLifecycleOwner){
            var minutes = 0
            var seconds = 0
            var hours = it/3600
            val reminderHours = it % 3600
            if(reminderHours != 0){
                minutes = reminderHours / 60
                val reminderMinutes = reminderHours % 60
                if(reminderMinutes != 0){
                    seconds = reminderMinutes
                }
            }

            binding.tvHours.text = String.format("%02d", hours)
            binding.tvMinutes.text =  String.format("%02d", minutes)
            binding.tvSeconds.text = String.format("%02d", seconds)
        }
        viewModel.startTimer()


        //Progress1
        val progress1 = binding.progress1
        val tvValueProgress1 = binding.tvValueProgress1
        progress1.max = ONE_HUNDRED_PERCENT
        progress1.progress = START_PERCENT_PROGRESS
        viewModel.observeProgress1(viewLifecycleOwner) {
            progress1.progress = it
            tvValueProgress1.text = "$it %"
        }


        //Progress2
        val progress2 = binding.progress2
        val tvValueProgress2 = binding.tvValueProgress2
        progress2.max = ONE_HUNDRED_PERCENT
        progress2.progress = START_PERCENT_PROGRESS
        viewModel.observeProgress2(viewLifecycleOwner) {
            progress2.progress = it
            tvValueProgress2.text = "$it %"
        }



        binding.randomizeValues.setOnClickListener {
            viewModel.goProgress1()
            viewModel.goProgress2()
        }


        binding.toFirstScreenFragment.setOnClickListener {
            findNavController().navigate(R.id.action_secondScreenFragment_to_firstScreenFragment)
        }


        val raitingsListAdapter = RaitingsAdapter()
        binding.recyclerViewRaitings.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewRaitings.adapter = raitingsListAdapter


        viewModel.observeListRaitings(viewLifecycleOwner) {
            binding.progressBarRaitings.visibility = View.GONE
            raitingsListAdapter.change(it)
        }

        viewModel.observeDataError(viewLifecycleOwner) {
            if(it) {
                binding.progressBarRaitings.visibility = View.GONE
                val text = R.string.cantLoadData
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(requireContext(), text, duration)
                toast.show()
            }
        }
        viewModel.getRatings()
    }

}