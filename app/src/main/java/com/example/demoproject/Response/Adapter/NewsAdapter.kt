package com.example.demoproject.Response.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.demoproject.R
import com.example.demoproject.Response.Article
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder
import javax.inject.Inject


class NewsAdapter @Inject constructor():  Adapter<NewsAdapter.ViewHolder>() {

    private var list : MutableList<Article> = mutableListOf()

    fun updateList(newList : List<Article>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val newsItem = list[position]
        holder.bind(newsItem)

        holder.itemView.setOnClickListener {

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tv_heading)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tv_desc)
        private val logoImageView: SimpleDraweeView = itemView.findViewById(R.id.iv_logo)


        fun bind(newsItem: Article) {
            if (!newsItem.title.isNullOrEmpty()) {
                titleTextView.text = newsItem.title
            } else {
                titleTextView.text = "No Title"
            }

            if (!newsItem.description.isNullOrEmpty()) {
                descriptionTextView.text = newsItem.description
            } else {
                descriptionTextView.text = "No Description"
            }

            val imageurl = newsItem.urlToImage
            if (!imageurl.isNullOrEmpty()) {
                val uri = Uri.parse(imageurl)
                val request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setProgressiveRenderingEnabled(true)
                    .build()
                val controller: DraweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(logoImageView.controller)
                    .build()
                logoImageView.controller = controller
            } else {
                // If the URL is null or empty, you may want to set a placeholder image or hide the image view
                // logoImageView.setImageURI(null) // Set a placeholder image
                 logoImageView.visibility = View.GONE // Hide the image view
            }
        }
    }

}