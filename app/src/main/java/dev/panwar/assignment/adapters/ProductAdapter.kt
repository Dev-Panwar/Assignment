package dev.panwar.assignment.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import dev.panwar.assignment.adapters.ProductAdapter.ViewHolder
import android.widget.TextView
import com.bumptech.glide.Glide
import dev.panwar.assignment.ProductDetailsActivity
import dev.panwar.assignment.R
import dev.panwar.assignment.model.Product

class ProductAdapter(private var productList:List<Product>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct=productList[position]
        holder.tvProductDescription.text = truncateDescription(currentProduct.description, 100)
        holder.tvProductTitle.setText(currentProduct.title)

        // Use Glide to load the image into ivThumbnail
        Glide.with(holder.itemView.context)
            .load(currentProduct.thumbnail) // Make sure to replace `imageUrl` with your actual image URL property
            .placeholder(R.drawable.placeholder) // Optional placeholder
            .error(R.drawable.placeholder) // Optional error image
            .into(holder.ivThumbnail)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("PRODUCT", currentProduct) // Pass the entire product object
            context.startActivity(intent)
        }
    }

    private fun truncateDescription(description: String, maxLength: Int): String {
        return if (description.length > maxLength) {
            description.substring(0, maxLength) + "..."
        } else {
            description
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val tvProductTitle: TextView = itemView.findViewById(R.id.tv_product_title)
		val tvProductDescription: TextView = itemView.findViewById(R.id.tv_Product_description)
        val ivThumbnail:ImageView=itemView.findViewById(R.id.iv_thumnail)
    }
}
