package com.myres.noban.mykotlinmvpproject.core.session

/**
 * Created by Rafiqul Hasan Rony on 2/3/19.
 * Audacity It Solution.
 */
interface Session {

    val isLoggedIn: Boolean

    val outletId: Int

    val areaId: Int

    val subAreaId: Int

    val userId: Int

    fun loggedOut()

    fun saveLoggedIn(loginStatus: Boolean)

    /**
     * LOCATION ACTIVITY
     */
    fun saveOutletId(outletId: Int)

    fun saveAreaId(areaId: Int)

    fun saveSubAreaId(subAreaId: Int)

    /**
     * CART PRODUCTS
     */

    //    boolean addToCart(CartProductsItem product);
    //
    //    boolean checkCartproduct(CartProductsItem product);
    //
    //    void removeProduct(CartProductsItem product);
    //
    //    List<CartProductsItem> getCartProducts();
    //
    //    void deleteCart();
    //
    //    void updateCartList(List<CartProductsItem> cartProductsItemList);

    /**
     * CUSTOMER INFORMATION
     */

    fun saveUserId(id: Int)

    fun removeUser()

    //    void saveCustomerData(CustomerData customerData);
    //
    //    CustomerData getSavedCustomerData();
}
