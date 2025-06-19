package com.example.imc

import android.os.Parcel
import android.os.Parcelable

class IMC(var nome: String?, var peso: Float, var altura: Float, var imc: Float) :Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat()
    ) {
    }

    constructor(name: String, peso: Float, altura: Float) : this(name,peso,altura,0.0f)

    fun calcular() : String{

        val alt =altura/100
            val calc = peso / (alt*alt)
            val msg = when {
                calc < 16f -> "Magreza grave"
                calc < 17f -> "Magreza moderada"
                calc < 19f -> "Magreza leve"
                calc < 25f -> "Saudável"
                calc < 30f -> "Sobrepeso"
                calc < 35f -> "Obesidade I"
                calc < 40f -> "Obesidade II"
                else -> "Obesidade Morbida."
            }


        imc = calc
        return msg
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeFloat(peso)
        parcel.writeFloat(altura)
        parcel.writeFloat(imc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IMC> {
        override fun createFromParcel(parcel: Parcel): IMC {
            return IMC(parcel)
        }

        override fun newArray(size: Int): Array<IMC?> {
            return arrayOfNulls(size)
        }
    }


}