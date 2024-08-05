package dev.panwar.assignment

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import dev.panwar.assignment.model.Product

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var product: Product
    private companion object{
        const val TAG="error"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        product = intent.getParcelableExtra("PRODUCT") ?: return

        // Initialize views
        val ivProductImage = findViewById<ImageView>(R.id.iv_product_image)
        val tvProductTitle = findViewById<TextView>(R.id.tv_product_title)
        val tvProductCategory = findViewById<TextView>(R.id.tv_product_category)
        val tvProductPrice = findViewById<TextView>(R.id.tv_product_price)
        val tvProductDescription = findViewById<TextView>(R.id.tv_product_description)
        val tvProductRating = findViewById<TextView>(R.id.tv_product_rating)
        val tvProductStock = findViewById<TextView>(R.id.tv_product_stock)
        val tvProductBrand = findViewById<TextView>(R.id.tv_product_brand)
        val tvProductSku = findViewById<TextView>(R.id.tv_product_sku)
        val tvProductWeight = findViewById<TextView>(R.id.tv_product_weight)
        val tvProductDimensions = findViewById<TextView>(R.id.tv_product_dimensions)
        val tvProductWarranty = findViewById<TextView>(R.id.tv_product_warranty)
        val tvProductShipping = findViewById<TextView>(R.id.tv_product_shipping)
        val tvProductReturnPolicy = findViewById<TextView>(R.id.tv_product_return_policy)
        val tvReview1 = findViewById<TextView>(R.id.tv_review_1)
        val tvReview2 = findViewById<TextView>(R.id.tv_review_2)
        val tvReview3 = findViewById<TextView>(R.id.tv_review_3)

        // Load image using Glide
        Glide.with(this)
            .load(product.images.firstOrNull() ?: "")
            .into(ivProductImage)

        // Set text to TextViews
        tvProductTitle.text = product.title
        tvProductCategory.text = "Category: ${product.category}"
        tvProductPrice.text = "Price: $${product.price} (${product.discountPercentage}% off)"
        tvProductDescription.text = product.description
        tvProductRating.text = "Rating: ${product.rating}"
        tvProductStock.text = "Availability: ${product.availabilityStatus}"
        tvProductBrand.text = "Brand: ${product.brand}"
        tvProductSku.text = "SKU: ${product.sku}"
        Log.d(TAG,product.sku)
        Log.d(TAG,product.weight.toString())
        Log.d(TAG,product.warrantyInformation)
        Log.d(TAG,product.shippingInformation)
        tvProductWeight.text = "Weight: ${product.weight}g"
        tvProductDimensions.text = "Dimensions: ${product.dimensions.width} x ${product.dimensions.height} x ${product.dimensions.depth}"
        tvProductWarranty.text = "Warranty: ${product.warrantyInformation}"
        tvProductShipping.text = "Shipping: ${product.shippingInformation}"
        tvProductReturnPolicy.text = "Return Policy: ${product.returnPolicy}"

        // Set reviews
        val reviews = product.reviews
        Log.d(TAG,reviews.toString())
        if (reviews.isNotEmpty()) {
            tvReview1.text = "${reviews[0].reviewerName} (${reviews[0].reviewerEmail})\nRating: ${reviews[0].rating}\n${reviews[0].comment}"
        }
        if (reviews.size > 1) {
            tvReview2.text = "${reviews[1].reviewerName} (${reviews[1].reviewerEmail})\nRating: ${reviews[1].rating}\n${reviews[1].comment}"
        }
        if (reviews.size > 2) {
            tvReview3.text = "${reviews[2].reviewerName} (${reviews[2].reviewerEmail})\nRating: ${reviews[2].rating}\n${reviews[2].comment}"
        }
    }
}