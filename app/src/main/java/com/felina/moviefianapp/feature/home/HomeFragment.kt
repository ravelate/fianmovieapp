package com.felina.moviefianapp.feature.home

import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.GridLayoutManager
import com.felina.moviefianapp.R
import com.felina.moviefianapp.core.data.Resource
import com.felina.moviefianapp.core.domain.model.Movie
import com.felina.moviefianapp.core.service.MovieUpdateService
import com.felina.moviefianapp.core.ui.MovieAdapter
import com.felina.moviefianapp.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()
    private val binding get() = _binding!!
    private var listmovie: List<Movie>? = null

    private val alarmManager: AlarmManager by lazy {
        activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
    val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "custom_action") {
                // Handle the broadcast message here
                val message = intent.getStringExtra("message")
                Log.e("data Updated", message.toString())
                // Do something with the message
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val alarmIntent = Intent(context, MovieUpdateService()::class.java)
        val pendingIntent = PendingIntent.getService(
            requireContext(),
            0,
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val intervalMillis = 10 * 100 // 1 minute
        val firstTriggerMillis = SystemClock.elapsedRealtime() + intervalMillis

        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            firstTriggerMillis,
            intervalMillis.toLong(),
            pendingIntent
        )
        requireContext().startService(alarmIntent)

        val localBroadcastManager = LocalBroadcastManager.getInstance(requireContext())
        val intentFilter = IntentFilter("custom_action")
        localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter)

        val movieAdapter = MovieAdapter()

        homeViewModel.movie.observeForever { movie ->
            if (listmovie == null){
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            listmovie = movie.data
                            movieAdapter.setData(movie.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = getString(R.string.something_wrong)
                        }
                    }
                }
            }else {
                if (movie != null){
                    when (movie) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            if ((movie.data as List<Movie>).isNotEmpty()){
                                listmovie = movie.data
                                showDialog(movie.data)
                            }
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = getString(R.string.something_wrong)
                        }
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
        val localBroadcastManager = LocalBroadcastManager.getInstance(requireContext())
        localBroadcastManager.unregisterReceiver(broadcastReceiver)
    }
    private fun showDialog(data: List<Movie>?) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setGravity(Gravity.BOTTOM)

        val yesBtn = dialog.findViewById(R.id.btn_load) as Button
        yesBtn.setOnClickListener {
            val movieAdapter = MovieAdapter()
            movieAdapter.setData(data)
            with(binding.rvTourism) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
            dialog.dismiss()
        }

        dialog.show()
    }

}