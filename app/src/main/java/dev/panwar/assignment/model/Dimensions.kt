package dev.panwar.assignment.model

import android.os.Parcel
import android.os.Parcelable

data class Dimensions(
    val depth: Double=0.0,
    val height: Double=0.0,
    val width: Double=0.0
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(depth)
        parcel.writeDouble(height)
        parcel.writeDouble(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dimensions> {
        override fun createFromParcel(parcel: Parcel): Dimensions {
            return Dimensions(parcel)
        }

        override fun newArray(size: Int): Array<Dimensions?> {
            return arrayOfNulls(size)
        }
    }
}