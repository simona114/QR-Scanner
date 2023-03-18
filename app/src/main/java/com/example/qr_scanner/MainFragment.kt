package com.example.qr_scanner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import com.example.qr_scanner.databinding.FragmentMainBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions


class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnScan.setOnClickListener {
            scanQRCode()
        }
    }
    private fun scanQRCode() {
        try {
            val scanOptions = ScanOptions()
                .setOrientationLocked(true)
            barLauncher.launch(scanOptions)
        } catch (e: Exception) {
            Log.e("scan", "scan: ${e.message} ")
            e.printStackTrace()
        }
    }

    private val barLauncher: ActivityResultLauncher<ScanOptions> =
        registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                Log.d("scan_result", "result.contents is ${result.contents} ")
                binding.tvScanResult.text = "result:" + result.contents
            } else {
                Log.d("scan_result", "result.contents is null")

            }
        }
}