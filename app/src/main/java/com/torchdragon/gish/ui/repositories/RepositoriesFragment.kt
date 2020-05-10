package com.torchdragon.gish.ui.repositories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.torchdragon.gish.R
import com.torchdragon.gish.databinding.RepositoriesFragmentBinding

class RepositoriesFragment : Fragment() {

    private var _binding: RepositoriesFragmentBinding? = null
    private val binding: RepositoriesFragmentBinding get() = _binding!!

    companion object {
        fun newInstance() = RepositoriesFragment()
    }

    private lateinit var viewModel: RepositoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(RepositoriesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.repositories_fragment, container, false)
        binding.viewAdapter = RepositoriesAdapter()

        viewModel.repositoriesData.observe(viewLifecycleOwner,
            Observer {
                binding.viewAdapter?.submitList(it)
            })

        return binding.root
    }
}
