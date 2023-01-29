package com.bignerdranch.android.photogallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.photogallery.databinding.FragmentPhotoPageBinding

// this class will be used for showing a web page in the app
class PhotoPageFragment : Fragment() {
    private val args: PhotoPageFragmentArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPhotoPageBinding.inflate(
            inflater,
            container,
            false
        )

        binding.apply {
            progressBar.max = 100

            webView.apply {
                // turn JavaScript on for the component
                // settings property will enable adding config details
                settings.javaScriptEnabled = true
                // create a WebViewClient object for responding to rendering events
                // this will ensure that the WebView itself will load the URL
                webViewClient = WebViewClient()
                // load the URL passed to the destination
                loadUrl(args.photoPageUri.toString())

                // WebChromeClient -> responsible for rendering the events that should change the
                // elements of chrome
                webChromeClient = object : WebChromeClient() {
                    // change the appearance of the ProgressBar depending on how much of the page
                    // has been loaded
                    override fun onProgressChanged(
                        webView: WebView,
                        newProgress: Int
                    ) {
                        if (newProgress == 100) {
                            progressBar.visibility = View.GONE
                        } else {
                            progressBar.visibility = View.VISIBLE
                            progressBar.progress = newProgress
                        }
                    }

                    // change the title that will appear in the app bar upon arriving
                    override fun onReceivedTitle(
                        view: WebView?,
                        title: String?
                    ) {
                        val parent = requireActivity() as AppCompatActivity
                        parent.supportActionBar?.subtitle = title
                    }
                }

            }
        }


        return binding.root
    }
}
