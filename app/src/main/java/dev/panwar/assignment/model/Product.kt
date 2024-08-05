package dev.panwar.assignment.model

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val availabilityStatus: String = "",
    val brand: String = "",
    val category: String = "",
    val description: String = "",
    val dimensions: Dimensions,
    val discountPercentage: Double = 0.0,
    val id: Int = 1,
    val images: List<String> = ArrayList(),
    val meta: Meta,
    val minimumOrderQuantity: Int = 1,
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val returnPolicy: String = "",
    val reviews: List<Review> = ArrayList(),
    val shippingInformation: String = "",
    val sku: String = "",
    val stock: Int = 1,
    val tags: List<String> = ArrayList(),
    val thumbnail: String = "",
    val title: String = "",
    val warrantyInformation: String = "",
    val weight: Int = 1
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(Dimensions::class.java.classLoader) ?: Dimensions(), // Provide a default value if necessary
        parcel.readDouble(),
        parcel.readInt(),
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readParcelable(Meta::class.java.classLoader) ?: Meta(), // Provide a default value if necessary
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.createTypedArrayList(Review.CREATOR) ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(availabilityStatus)
        parcel.writeString(brand)
        parcel.writeString(category)
        parcel.writeString(description)
        parcel.writeParcelable(dimensions, flags)
        parcel.writeDouble(discountPercentage)
        parcel.writeInt(id)
        parcel.writeStringList(images)
        parcel.writeParcelable(meta, flags)
        parcel.writeInt(minimumOrderQuantity)
        parcel.writeDouble(price)
        parcel.writeDouble(rating)
        parcel.writeString(returnPolicy)
        parcel.writeTypedList(reviews) // Ensure the correct method for writing a list of Parcelable
        parcel.writeString(shippingInformation)
        parcel.writeString(sku)
        parcel.writeInt(stock)
        parcel.writeStringList(tags)
        parcel.writeString(thumbnail)
        parcel.writeString(title)
        parcel.writeString(warrantyInformation)
        parcel.writeInt(weight)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
