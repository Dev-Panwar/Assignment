package dev.panwar.assignment.model

import android.os.Parcel
import android.os.Parcelable

data class Review(
    val comment: String="",
    val date: String="",
    val rating: Int=1,
    val reviewerEmail: String="",
    val reviewerName: String=""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readString()?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(comment)
        parcel.writeString(date)
        parcel.writeInt(rating)
        parcel.writeString(reviewerEmail)
        parcel.writeString(reviewerName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}