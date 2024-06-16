package com.example.imagesearchserper

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchserper.model.Image
import com.example.imagesearchserper.view.ImageAdapter
import com.example.imagesearchserper.viewModel.ImageViewModel

class MainActivity : ComponentActivity() {
    private lateinit var query: String
    private lateinit var searchField: EditText
    private val imageViewModel = ImageViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchField = findViewById(R.id.searchFieldET)

        val imagesList = listOf(
            Image(
                title = "A Strategic Analysis of Apple Inc.",
                imageUrl = "https://media.licdn.com/dms/image/C4D12AQFNv_KSo_VCwQ/article-cover_image-shrink_600_2000/0/1638142508773?e=2147483647&v=beta&t=SoxCwfG_3-FF8YnKRQNmBv0k0zOPe26PI6-1Nda-GrE",
                imageWidth = 740,
                imageHeight = 415,
                thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpOBvEgzRNu284eO7Mw-_IKukYnD2CXhGMTs1rjPcJj45uRiyr&s",
                thumbnailWidth = 300,
                thumbnailHeight = 168,
                source = "LinkedIn",
                domain = "www.linkedin.com",
                link = "https://www.linkedin.com/pulse/strategic-analysis-apple-inc-bidemi-ogedengbe",
                googleUrl = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.licdn.com%2Fdms%2Fimage%2FC4D12AQFNv_KSo_VCwQ%2Farticle-cover_image-shrink_600_2000%2F0%2F1638142508773%3Fe%3D2147483647%26v%3Dbeta%26t%3DSoxCwfG_3-FF8YnKRQNmBv0k0zOPe26PI6-1Nda-GrE&tbnid=E8hnCY8LIxTZ3M&imgrefurl=https%3A%2F%2Fwww.linkedin.com%2Fpulse%2Fstrategic-analysis-apple-inc-bidemi-ogedengbe&docid=gP0JwewjX407kM&w=740&h=415&ved=0ahUKEwjs5rXpwOCGAxVytokEHV1zA9kQvFcIAigA",
                position = 1
            ),
            Image(
                title = "Innovate, Integrate, Dominate: Success Factors of Apple Inc.",
                imageUrl = "https://thebrandhopper.com/wp-content/uploads/2023/11/apple-success-story-1024x576.jpg",
                imageWidth = 1024,
                imageHeight = 576,
                thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMAnrZ5yjVCWU2pqUeQg1DMDfLjYQQSXSPm434nNIbjSl07Sdk&s",
                thumbnailWidth = 300,
                thumbnailHeight = 168,
                source = "The Brand Hopper",
                domain = "thebrandhopper.com",
                link = "https://thebrandhopper.com/2023/11/27/innovate-integrate-dominate-success-factors-of-apple-inc/",
                googleUrl = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fthebrandhopper.com%2Fwp-content%2Fuploads%2F2023%2F11%2Fapple-success-story-1024x576.jpg&tbnid=dBWVKtsWMnMJWM&imgrefurl=https%3A%2F%2Fthebrandhopper.com%2F2023%2F11%2F27%2Finnovate-integrate-dominate-success-factors-of-apple-inc%2F&docid=KlOfuwGNeFnNPM&w=1024&h=576&ved=0ahUKEwjs5rXpwOCGAxVytokEHV1zA9kQvFcIAygB",
                position = 2
            ),
            Image(
                title = "Company Overview of Apple & Intel: Philosophy, Portfolio, etc",
                imageUrl = "https://akm-img-a-in.tosshub.com/indiatoday/apple_647_040116105516.jpg?.xccL1E_SxXCNt9K1Jjgg.vKCdo0Mf0u",
                imageWidth = 647,
                imageHeight = 404,
                thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcLsYkwuPnA4XE5yssV_NEbFf2BXqLXtb3mi-rnC95Htyssxg&s",
                thumbnailWidth = 284,
                thumbnailHeight = 177,
                source = "www.toppr.com",
                domain = "www.toppr.com",
                link = "https://www.toppr.com/guides/commercial-knowledge/business-organizations/company-overview-of-apple-intel/",
                googleUrl = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fakm-img-a-in.tosshub.com%2Findiatoday%2Fapple_647_040116105516.jpg%3F.xccL1E_SxXCNt9K1Jjgg.vKCdo0Mf0u&tbnid=oLut_EKUPhhwyM&imgrefurl=https%3A%2F%2Fwww.toppr.com%2Fguides%2Fcommercial-knowledge%2Fbusiness-organizations%2Fcompany-overview-of-apple-intel%2F&docid=bM9CtTTSOxggsM&w=647&h=404&ved=0ahUKEwjs5rXpwOCGAxVytokEHV1zA9kQvFcIBCgC",
                position = 3
            ),
            Image(
                title = "Apple Inc. History, Impacts & Facts | Study.com",
                imageUrl = "https://study.com/cimages/multimages/16/untitled_design_454963738386560630025.png",
                imageWidth = 400,
                imageHeight = 400,
                thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbmcdEWkuwrE2VM5jVPB9llDbiSFqIwgFtuAgTdtFZouIpM8s&s",
                thumbnailWidth = 225,
                thumbnailHeight = 225,
                source = "Study.com",
                domain = "study.com",
                link = "https://study.com/academy/lesson/apple-inc-history-products-facts-company.html",
                googleUrl = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fstudy.com%2Fcimages%2Fmultimages%2F16%2Funtitled_design_454963738386560630025.png&tbnid=-MJlalWh44sqgM&imgrefurl=https%3A%2F%2Fstudy.com%2Facademy%2Flesson%2Fapple-inc-history-products-facts-company.html&docid=k9FyGGXCOOK96M&w=400&h=400&ved=0ahUKEwjs5rXpwOCGAxVytokEHV1zA9kQvFcIBSgD",
                position = 4
            ),
            Image(
                title = "Apple Inc. - Wikipedia",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Aerial_view_of_Apple_Park_dllu.jpg/250px-Aerial_view_of_Apple_Park_dllu.jpg",
                imageWidth = 250,
                imageHeight = 167,
                thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8wM0CNXqylajpTGCnMWjjhVsTHc_ZHPzawkO2jaG0MxqMG0g&s",
                thumbnailWidth = 250,
                thumbnailHeight = 167,
                source = "Wikipedia",
                domain = "en.wikipedia.org",
                link = "https://en.wikipedia.org/wiki/Apple_Inc.",
                googleUrl = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2F5%2F5a%2FAerial_view_of_Apple_Park_dllu.jpg%2F250px-Aerial_view_of_Apple_Park_dllu.jpg&tbnid=epDDR_fqfM0z8M&imgrefurl=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FApple_Inc.&docid=gk5eHYqeOTZu-M&w=250&h=167&ved=0ahUKEwjs5rXpwOCGAxVytokEHV1zA9kQvFcIBigE",
                position = 5
            ),
            Image(
                title = "Apple SWOT Analysis 2021 - Business Strategy Hub",
                imageUrl = "https://www.business-strategy-hub.com/wp-content/uploads/2021/06/apple-swot-analysis.jpg",
                imageWidth = 1920,
                imageHeight = 1080,
                thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTA5w2ydc22sm_Y3V2OCE9ipxDLcUuwp4RzS8cq4kfgJoqn4Bg&s",
                thumbnailWidth = 300,
                thumbnailHeight = 168,
                source = "Business Strategy Hub",
                domain = "www.business-strategy-hub.com",
                link = "https://www.business-strategy-hub.com/apple-swot-analysis-2021/",
                googleUrl = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.business-strategy-hub.com%2Fwp-content%2Fuploads%2F2021%2F06%2Fapple-swot-analysis.jpg&tbnid=Me_8leUT3g_wOM&imgrefurl=https%3A%2F%2Fwww.business-strategy-hub.com%2Fapple-swot-analysis-2021%2F&docid=qQ1fXWh8yNZTkM&w=1920&h=1080&ved=0ahUKEwjs5rXpwOCGAxVytokEHV1zA9kQvFcICygF",
                position = 6
            )
        )

        // Initialize the RecyclerView and the ImageAdapter with an empty list
        val recyclerView: RecyclerView = findViewById(R.id.imageListRV)
        val imageAdapter = ImageAdapter(imagesList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = imageAdapter

        // Observe the LiveData object in the ImageViewModel
        imageViewModel.imagesLiveData.observe(this) { images ->
            // Update the adapter's data when the LiveData changes
            imageAdapter.updateImages(images)
        }

        searchField.setOnEditorActionListener { _, _, _ ->
            query = searchField.text.toString()
//            searchImages(query)
            true
        }
    }

    private fun searchImages(query: String) {
        imageViewModel.getImages(query)
    }
}