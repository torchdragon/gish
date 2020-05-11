package com.torchdragon.gish.ui.repositories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.torchdragon.gish.GishActivity
import com.torchdragon.gish.R
import com.torchdragon.gish.databinding.RepositoriesFragmentBinding
import com.torchdragon.gish.ui.issues.IssuesFragment

class RepositoriesFragment : Fragment(), IssueNavigationHandler {

    private var _binding: RepositoriesFragmentBinding? = null
    private val binding: RepositoriesFragmentBinding get() = _binding!!

    companion object {
        fun newInstance() = RepositoriesFragment()
        const val TAG = "com.torchdragon.gish.ui.repositories.repositories_fragment"
    }

    private lateinit var viewModel: RepositoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = RepositoriesViewModelFactory((activity as GishActivity).gitHubApi)
        viewModel = ViewModelProvider(this, factory).get(RepositoriesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.repositories_fragment, container, false)
        binding.viewAdapter = RepositoriesAdapter(this)


        viewModel.repositoriesData.observe(viewLifecycleOwner,
            Observer {
                binding.viewAdapter?.submitList(it)
            })

        return binding.root
    }

    override fun navigateTo(destination: String) {
        Log.v("Gish", "Attempting to navigate to $destination")
        val bundle = Bundle().apply {
            putString(IssuesFragment.URL_EXTRA, destination)
        }

        parentFragmentManager
            .beginTransaction()
            .addToBackStack(IssuesFragment.TAG)
            .replace(R.id.container, IssuesFragment::class.java, bundle, IssuesFragment.TAG)
            .commit()
    }
}
