package com.example.qr_scanner

import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.qr_scanner.databinding.ActivityMainBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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