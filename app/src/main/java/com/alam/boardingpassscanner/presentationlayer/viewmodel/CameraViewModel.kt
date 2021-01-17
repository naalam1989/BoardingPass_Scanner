package com.alam.boardingpassscanner.presentationlayer.viewmodel

import android.app.Activity
import android.util.Log
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alam.boardingpassscanner.R
import com.alam.boardingpassscanner.datalayer.barcode.BarcodeImageAnalyzer
import com.alam.boardingpassscanner.domainlayer.usecase.AddBoardingPass
import com.alam.boardingpassscanner.domainlayer.usecase.ParseBarcode
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class CameraViewModel : ViewModel() {

    val liveDataActivity = MutableLiveData<Boolean>()
    private var cameraExecutor = Executors.newSingleThreadExecutor()

    override fun onCleared() {
        super.onCleared()
        cameraExecutor.shutdown()
    }

    fun startCamera(activity: Activity) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(activity)

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider((activity.findViewById<PreviewView>(R.id.viewFinder)).createSurfaceProvider())
                }

            val imageAnalysis = ImageAnalysis.Builder()
                .setTargetResolution(Size(1280, 720))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .apply {
                    setAnalyzer(cameraExecutor, BarcodeImageAnalyzer().also {
                        it.liveDataAnalyser.observeForever{
                                barcode -> processBarcode(barcode)
                        }
                    })
                }

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    activity as LifecycleOwner, cameraSelector, imageAnalysis, preview)

            } catch(exc: Exception) {
                Log.e("Nadeem", "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(activity))
    }

    private fun processBarcode(barcode: String) {
        //Parse barcode
        ParseBarcode().use(barcode)?.also {
            //Add to DB
            viewModelScope.launch {
                AddBoardingPass().use(it)
            }
            //Finish Activity
            liveDataActivity.postValue(true)
        } ?: liveDataActivity.postValue(false)
    }

}