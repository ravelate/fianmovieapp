package com.felina.moviefianapp.feature.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.felina.moviefianapp.core.data.Resource
import com.felina.moviefianapp.databinding.FragmentSplashBinding
import com.felina.moviefianapp.feature.home.HomeFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        splashViewModel.movie.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        parentFragmentManager.beginTransaction().replace(this.id, HomeFragment())
                            .commit()
                    }

                    is Resource.Error -> {
                        Toast.makeText(
                            activity,
                            "Sambungkan ke internet: tidak ada data di penyimpanan lokal",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBar.visibility = View.GONE
                        binding.button.visibility = View.VISIBLE
                    }
                }
            } else {
                binding.progressBar.visibility = View.GONE
                binding.button.visibility = View.VISIBLE
            }
        }
        binding.button.setOnClickListener {
            activity?.recreate()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}