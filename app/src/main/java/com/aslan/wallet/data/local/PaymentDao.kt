package com.aslan.wallet.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aslan.wallet.domain.model.Category
import com.aslan.wallet.domain.model.CategoryAmount
import com.aslan.wallet.domain.model.Payment

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPayment(payment: Payment)

    @Query("SELECT * FROM payment_table")
    fun getAllPayment(): List<Payment>

    @Query("SELECT * FROM payment_table WHERE id =:idPayment")
    fun selectPayment(idPayment: Int): Payment

    @Query("SELECT * FROM payment_table WHERE title LIKE '%' || :searchQuery || '%' ORDER BY id DESC")
    fun searchPayment(searchQuery: String): LiveData<List<Payment>>

    @Query("SELECT SUM(amount) FROM payment_table WHERE type =:type")
    fun getWalletBalanceByType(type: String): Double

    @Query("SELECT * FROM payment_table WHERE type =:type")
    fun getAllPaymentByType(type: String): List<Payment>

    @Query("select categoryName as name, categoryIcon as icon, COUNT(categoryName) as count, SUM(amount) as amount from payment_table group by categoryName")
    fun getAllCategory(): List<Category>

    @Update
    fun updatePayment(payment: Payment)

    @Query("DELETE FROM payment_table")
    fun deleteAllPayment()

    @Query("DELETE FROM payment_table WHERE id =:idPayment")
    fun deletePayment(idPayment: Int)

}