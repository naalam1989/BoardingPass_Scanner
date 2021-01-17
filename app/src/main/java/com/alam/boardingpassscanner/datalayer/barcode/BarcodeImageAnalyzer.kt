package com.alam.boardingpassscanner.datalayer.barcode

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.lifecycle.MutableLiveData
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class BarcodeImageAnalyzer : ImageAnalysis.Analyzer {

    val liveDataAnalyser = MutableLiveData<String>()

    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            // Pass image to an ML Kit Vision API
            val scanner = BarcodeScanning.getClient()
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    // Task completed successfully
                    if (barcodes.size > 0) {
                        Log.i("Nadeem", barcodes[0].rawValue!!)

                        //Finish Activity
                        liveDataAnalyser.postValue(barcodes[0].rawValue)
                    } else {
                        imageProxy.close()
                    }
                }
                .addOnFailureListener {
                    // Task failed with an exception
                    Log.e("Nadeem", "addOnFailureListener")
                }
        }
    }
}