package com.felina.moviefianapp.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.felina.moviefianapp.R
import com.felina.moviefianapp.core.data.Resource
import com.felina.moviefianapp.core.ui.MovieAdapter
import com.felina.moviefianapp.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter()
        homeViewModel.movie.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = getString(R.string.something_wrong)
                    }
                }
            }
        }

        with(binding.rvTourism) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}