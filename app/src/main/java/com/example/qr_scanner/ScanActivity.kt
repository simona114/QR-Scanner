package com.example.qr_scanner

import android.os.Bundle
import com.journeyapps.barcodescanner.CaptureActivity

class ScanActivity : CaptureActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
    }
}