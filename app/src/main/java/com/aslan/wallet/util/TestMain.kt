package com.aslan.wallet.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.min


fun main(){
    // 1.000.000.000.000.000.000.000
    val b = "750085647470000.5050"
    val x = b.toDouble()
//    val balance: Double = String.format("%.2f", b).toDouble()
    val separate = b.split(".")
    if(separate[0].length in 7..10)
        println(separate[0]+"."+separate[1]+"K")
    else if (separate[0].length >= 10)
        println(separate[0]+"."+separate[1]+"M")
    else
        println(separate[0]+"."+separate[1])
    println(separate[0])
    println(separate[1])
    println(x)
//    walletAmount(balance)


}

fun walletAmount(amount: Double){
    if (!amount.isNaN()){
        val a: Double = String.format("%.2f", amount).toDouble()
        val separate = a.toString().split(".")
        println(separate[0])
        println(separate[1])
    }else{
        println("1")
        println("0")
    }
}