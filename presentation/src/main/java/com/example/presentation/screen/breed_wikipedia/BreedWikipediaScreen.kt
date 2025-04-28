package com.example.presentation.screen.breed_wikipedia

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BreedWikipediaScreen(
    modifier: Modifier = Modifier,
    viewModel: BreedWikipediaViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AndroidView(
        factory = { context ->
            WebView(context).apply {

                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        view?.loadUrl(request?.url.toString())
                        return true
                    }
                }
                loadUrl(uiState.url)
            }
        },
        update = { webView ->
            webView.loadUrl(uiState.url)
        }
    )
}