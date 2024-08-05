package dev.panwar.assignment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import dev.panwar.assignment.adapters.ProductAdapter
import dev.panwar.assignment.api.RetrofitInstance
import dev.panwar.assignment.databinding.ActivityProductsBinding
import dev.panwar.assignment.model.Product
import dev.panwar.assignment.model.ProductApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Products : AppCompatActivity() {
    private var binding:ActivityProductsBinding?=null
    private lateinit var mProgressDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupProducts()

    }

    private fun setupProducts() {
        showProgressDialog("Please wait")
//        Toast.makeText(this@HomeActivity,authToken,Toast.LENGTH_SHORT).show()
        val call: Call<ProductApiResponse> = RetrofitInstance.api.getProducts()
        call.enqueue(object : Callback<ProductApiResponse> {
            override fun onResponse(call: Call<ProductApiResponse>, response: Response<ProductApiResponse>) {
                if (response.isSuccessful) {
                    hideProgressDialogue()
                    val productResponse = response.body()
                    if (productResponse!=null){
                        val productList : List<Product> = productResponse.products
                        Log.d("error",productList.get(0).sku)
                        Log.d("error",productList.get(0).warrantyInformation)
                        Log.d("error",productList.get(0).shippingInformation)
                        Log.d("error",productList.get(0).weight.toString())
                        setupRecyclerview(productList)
                    }
                } else {
                    hideProgressDialogue()
                    // Handle non-successful response (e.g., HTTP error)
                    if (response.code()==401){

                    }else{

                    }

                }
            }

            override fun onFailure(call: Call<ProductApiResponse>, t: Throwable) {
                hideProgressDialogue()
                // Handle failure (e.g., network issues)
                Toast.makeText(this@Products, "Request failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerview(productList: List<Product>) {
        val layoutManager= LinearLayoutManager(this)
        val adapter=ProductAdapter(productList)
        binding?.rvProducts?.layoutManager=layoutManager
        binding?.rvProducts?.adapter=adapter

    }

    fun showProgressDialog(text:String){
        mProgressDialog= Dialog(this)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.setContentView(R.layout.dialog_progress)
//        findViewById<TextView>(R.id.tv_progress_text).text= text
        mProgressDialog.show()
    }
    // for hiding progress Dialogue
    fun hideProgressDialogue(){
        mProgressDialog.dismiss()
    }


}