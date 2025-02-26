package com.torchdragon.gish.ui.issues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.torchdragon.gish.GishActivity
import com.torchdragon.gish.R
import com.torchdragon.gish.databinding.IssuesFragmentBinding

class IssuesFragment : Fragment() {

    private var _binding: IssuesFragmentBinding? = null
    private val binding: IssuesFragmentBinding get() = _binding!!

    companion object {
        const val URL_EXTRA = "com.torchdragon.gish.ui.issues.issues_fragment.url_extra"
        const val TITLE_EXTRA = "com.torchdragon.gish.ui.issues.issues_fragment.title_extra"
        const val TAG = "com.torchdragon.ui.issues.issue_fragment"
    }

    private lateinit var viewModel: IssuesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = IssuesViewModelFactory(
            (activity as GishActivity).gitHubApi,
            arguments ?: throw IllegalArgumentException("No arguments passed to IssuesFragment"))

        viewModel = ViewModelProvider(this, factory).get(IssuesViewModel::class.java)

        arguments?.getString(TITLE_EXTRA)?.let {
            requireActivity().title = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.issues_fragment, container, false)
        binding.issuesAdapter = IssuesAdapter()
        binding.viewModel = viewModel

        viewModel.issuesApiData.observe(viewLifecycleOwner, Observer {
            binding.issuesAdapter?.submitList(it)
        })

        return binding.root
    }
}
