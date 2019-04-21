package com.myres.noban.mykotlinmvpproject.core.session


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myres.noban.mykotlinmvpproject.core.App
import com.myres.noban.mykotlinmvpproject.core.api.RetrofitAPIFactory
import com.myres.noban.mykotlinmvpproject.utility.Constant
import com.orhanobut.hawk.Hawk

import java.lang.reflect.Type
import java.util.ArrayList


/**
 * Created by Rafiqul Hasan Rony on 2/3/19.
 * Audacity It Solution.
 */
class SharedPreferenceSessionImpl : Session {

    internal var mGson: Gson

    override val isLoggedIn: Boolean
        get() = getBoolean("IS_LOGGED_IN")

    override val outletId: Int
        get() = getInt(Constant.OUTLET_ID_SESSION_KEY)

    override val areaId: Int
        get() = getInt(Constant.AREA_SESSION_KEY)

    override val subAreaId: Int
        get() = getInt(Constant.SUBAREA_SESSION_KEY)

    override val userId: Int
        get() = getInt("USER_ID")

    init {
        mGson = Gson()
    }

    override fun loggedOut() {
        //Hawk.deleteAll(); // to delete all shared preferences data
        try {
            RetrofitAPIFactory.getCache(App.getApp()).evictAll()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            saveLoggedIn(false)
        }
    }

    override fun saveLoggedIn(loginStatus: Boolean) {
        putBoolean("IS_LOGGED_IN", loginStatus)
    }

    override fun saveOutletId(outletId: Int) {
        putInt(Constant.OUTLET_ID_SESSION_KEY, outletId)
    }

    /**
     * LCOATION SELECTION
     */
    override fun saveAreaId(areaId: Int) {
        putInt(Constant.AREA_SESSION_KEY, areaId)
    }

    override fun saveSubAreaId(subAreaId: Int) {
        putInt(Constant.SUBAREA_SESSION_KEY, subAreaId)
    }

    /**
     * CART
     */
    //    @Override
    //    public boolean addToCart(CartProductsItem product) {
    //
    //        List<CartProductsItem> cartProductsList = getCartProducts();
    //
    //        /**
    //         * If any product already exists in cart then no need to add that product again
    //         *  on Successfully adding new product, return a BOOLEAN as response
    //         *  if return true; product added successfully
    //         *  if return false; product exists and not added
    //         * */
    //
    //        if (isThisProductExistInCart(product, cartProductsList)) {
    //            //product exists and not added in cart list
    //            return false;
    //        } else {
    //            cartProductsList.add(product);
    //
    //            Type listType = new TypeToken<List<CartProductsItem>>() {
    //            }.getType();
    //
    //            String cartList = new Gson().toJson(cartProductsList, listType);
    //            putString(Constant.CART_PRODUCT_LIST_KEY, cartList);
    //
    //            //product successfully added as return true;
    //            return true;
    //        }
    //
    //    }
    //
    //    @Override
    //    public boolean checkCartproduct(CartProductsItem product) {
    //        List<CartProductsItem> cartProductsList = getCartProducts();
    //        if (isThisProductExistInCart(product, cartProductsList)) {
    //            //product exists and not added in cart list
    //            return false;
    //        } else {
    //
    //            return true;
    //        }
    //    }

    //    private boolean isThisProductExistInCart(CartProductsItem product, List<CartProductsItem> cartProductsList) {
    //        boolean isFound = false;
    //
    //        for(CartProductsItem cartProductsItem : cartProductsList){
    //            if(cartProductsItem.getProductName().equals(product.getProductName())){
    //                isFound = true;
    //                break;
    //            }
    //        }
    //
    //        return isFound;
    //    }

    //    @Override
    //    public void removeProduct(CartProductsItem product) {
    //
    //        List<CartProductsItem> cartProductsList = getCartProducts();
    //        cartProductsList.remove(product);
    //
    //    }
    //
    //    @Override
    //    public List<CartProductsItem> getCartProducts() {
    //
    //        Type listType = new TypeToken<List<CartProductsItem>>() {
    //        }.getType();
    //
    //        String cartProductsListInString = getString(Constant.CART_PRODUCT_LIST_KEY);
    //
    //        List<CartProductsItem> productsItemsList = new ArrayList<>();
    //
    //        if (cartProductsListInString != null && !cartProductsListInString.isEmpty()) {
    //            productsItemsList = mGson.fromJson(cartProductsListInString, listType);
    //        }
    //
    //        return productsItemsList;
    //    }

    //    @Override
    //    public void deleteCart() {
    //        putString(Constant.CART_PRODUCT_LIST_KEY, "");
    //    }
    //
    //    @Override
    //    public void updateCartList(List<CartProductsItem> cartProductsItemList) {
    //
    //        Type listType = new TypeToken<List<CartProductsItem>>() {
    //        }.getType();
    //
    //        String cartList = new Gson().toJson(cartProductsItemList, listType);
    //
    //        putString(Constant.CART_PRODUCT_LIST_KEY, cartList);
    //
    //    }

    override fun saveUserId(id: Int) {
        putInt("USER_ID", id)
    }

    override fun removeUser() {
        removeKey("USER_ID")
    }


    private fun putString(key: String, value: String) {
        Hawk.put(key, value)
    }

    private fun putBoolean(key: String, value: Boolean) {
        Hawk.put(key, value)
    }

    private fun putInt(key: String, value: Int) {
        Hawk.put(key, value)
    }

    private fun putLong(key: String, value: Long) {
        Hawk.put(key, value)
    }

    private fun putDouble(key: String, value: Double) {
        Hawk.put(key, value)
    }


    private fun getString(key: String): String {
        return Hawk.get(key)
    }

    private fun getDouble(key: String): Double {
        return Hawk.get(key, 1.0)
    }

    private fun getInt(key: String): Int {
        return Hawk.get(key, -1)
    }

    private fun getLong(key: String): Long {
        return Hawk.get(key, 0).toLong()
    }

    private fun getBoolean(key: String): Boolean {
        return Hawk.get(key, false)
    }

    private fun removeKey(key: String) {
        Hawk.delete(key)
    }

    companion object {

        val TAG = "SharedPreferenceSessionImpl"
    }
}
