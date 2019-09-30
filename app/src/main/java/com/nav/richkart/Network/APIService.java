package com.nav.richkart.Network;

import com.nav.richkart.address.AddUpdateAddressResponse;
import com.nav.richkart.address.AddressListResponse;
import com.nav.richkart.address.DeleteAddressResponse;
import com.nav.richkart.deals.seller.DealsResponse;
import com.nav.richkart.departments.CategoriesResponse;
import com.nav.richkart.checkout.CheckoutPaymentResponse;
import com.nav.richkart.home.home_response.HomeResponse;
import com.nav.richkart.login.ForgotPasswordResponse;
import com.nav.richkart.login.LoginResponse;
import com.nav.richkart.my_order.CancelOrderResponse;
import com.nav.richkart.my_order_details.MyOrderDetailsResponse;
import com.nav.richkart.product_by_category.ProductByCategoryResponse;
import com.nav.richkart.product_details.AddToCartResponse;
import com.nav.richkart.my_cart.CartListResponse;
import com.nav.richkart.my_cart.EmptyCartResponse;
import com.nav.richkart.my_cart.RemoveCartByCrossResponse;
import com.nav.richkart.my_cart.RemoveCartOneByOneResponse;
import com.nav.richkart.my_order.MyOrderResponse;
import com.nav.richkart.product_details.AddToWishListResponse;
import com.nav.richkart.my_wishlist.MyWishListResponse;
import com.nav.richkart.my_wishlist.RemoveWishListResponse;
import com.nav.richkart.product_by_category.ProductByCategoryRequest;
import com.nav.richkart.product_details.project_details_response.CheckPincodeResponse;
import com.nav.richkart.product_details.project_details_response.ProductDetailsResponse;
import com.nav.richkart.product_rating_list.AddRatingResponse;
import com.nav.richkart.product_rating_list.product_rating_response.ProductRatingResponse;
import com.nav.richkart.profile.ChanePasswordResponse;
import com.nav.richkart.profile.ChangeProfileImageResponse;
import com.nav.richkart.profile.UpdateProfileResponse;
import com.nav.richkart.search.SearchResponse;
import com.nav.richkart.search.TrendingResponse;
import com.nav.richkart.seller.SellerResponse;
import com.nav.richkart.signup.GetOTPResponse;
import com.nav.richkart.signup.SignUpResponse;
import com.nav.richkart.sub_sub_category.SubSubCategoriesResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {

  /*  @POST("login")
    @FormUrlEncoded
    Observable<LoginResponse> doLogin(@Field("email") String email,
                                      @Field("password") String password);*/

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> doLogin(@Field("email") String email,
                                @Field("password") String password);


  /*  @POST("saveuserdata")
    @FormUrlEncoded
    Observable<SignUpResponse> doSignUp(@Field("email") String email,
                                        @Field("fname") String fName,
                                        @Field("lname") String lName,
                                        @Field("mobileno") String phone,
                                        @Field("password") String password,
                                        @Field("cn_password") String confirmPassword);*/

    @POST("saveuserdata")
    @FormUrlEncoded
    Call<SignUpResponse> doSignUp(@Field("email") String email,
                                  @Field("fname") String fName,
                                  @Field("lname") String lName,
                                  @Field("mobileno") String phone,
                                  @Field("otp") String otp,
                                  @Field("vctoken") String VCToken,
                                  @Field("password") String password,
                                  @Field("gender") String gender,
                                  @Field("cn_password") String confirmPassword);


    @POST("passwordreset")
    @FormUrlEncoded
    Call<ForgotPasswordResponse> doPasswordReset(@Field("email") String email);


/*    @POST("my-order")
    @FormUrlEncoded
    Observable<MyOrderResponse> getMyOrder(@Field("userid") String userID);*/

    @POST("my-order")
    @FormUrlEncoded
    Call<MyOrderResponse> getMyOrder(@Field("userid") String userID);

  /*  @POST("user-address-list")
    @FormUrlEncoded
    Observable<AddressListResponse> getAddressList(@Field("userid") String userID);*/

    @POST("user-address-list")
    @FormUrlEncoded
    Call<AddressListResponse> getAddressList(@Field("userid") String userID);


    @POST("delete-address")
    @FormUrlEncoded
    Call<DeleteAddressResponse> deleteAddress(@Field("id") String addressid);


    /*@POST("update-profile")
    @FormUrlEncoded
    Observable<UpdateProfileResponse> doUpdateProfile(@Field("fname") String fName,
                                                      @Field("lname") String lName,
                                                      @Field("password") String password,
                                                      @Field("cn_password") String confirmPassword);*/

    @POST("update-profile")
    @FormUrlEncoded
    Call<UpdateProfileResponse> doUpdateProfile(@Field("id") String userId,
                                                @Field("name") String fName,
                                                @Field("lastname") String lName,
                                                @Field("mobileno") String phone,
                                                @Field("password") String password);

    @POST("wishlist")
    @FormUrlEncoded
    Call<MyWishListResponse> getMyWishList(@Field("user_id") String userID);


    @POST("add-to-wishlist")
    @FormUrlEncoded
    Call<AddToWishListResponse> addToWishList(@Field("user_id") String userID,
                                              @Field("product_id") String productId);

    @POST("remove-wishlist")
    @FormUrlEncoded
    Call<RemoveWishListResponse> removeWishList(@Field("wishlist_id") String wishlistId);


    @POST("add-to-cart")
    @FormUrlEncoded
    Call<AddToCartResponse> addToCart(@Field("user_id") String userID,
                                      @Field("product_id") String productId,
                                      @Field("quantity") String quantity,
                                      @Field("attributes") String attributes);

    @POST("cartlist")
    @FormUrlEncoded
    Call<CartListResponse> getCartList(@Field("user_id") String userID);

    @POST("empty-cart")
    @FormUrlEncoded
    Call<EmptyCartResponse> emptyCart(@Field("user_id") String userID);

    @POST("remove-from-cart-one-by-one")
    @FormUrlEncoded
    Call<RemoveCartOneByOneResponse> removeCartOneByOne(@Field("user_id") String userID,
                                                        @Field("product_id") String productId,
                                                        @Field("quantity") String quantity);


    @POST("remove-cart-cross-button")
    @FormUrlEncoded
    Call<RemoveCartByCrossResponse> removeCartByCross(@Field("cart_id") String cartId);

    @POST("update-address")
    @FormUrlEncoded
    Call<AddUpdateAddressResponse> doAddUpdateAddress(@Field("id") String id,
                                                      @Field("name") String name,
                                                      @Field("userid") String userId,
                                                      @Field("address1") String address1,
                                                      @Field("address2") String address2,
                                                      @Field("city") String city,
                                                      @Field("state") String state,
                                                      @Field("country") String country,
                                                      @Field("zipcode") String zipcode,
                                                      @Field("addresstype") String addressType,
                                                      @Field("landmark") String landmark,
                                                      @Field("phone") String phone);


    @POST("product-detail")
    @FormUrlEncoded
    Call<ProductDetailsResponse> getProductDetails(@Field("productid") String slug, @Field("user_id") String userId);

    @POST("product-ratings")
    @FormUrlEncoded
    Call<ProductRatingResponse> getProductRating(@Field("productid") String productId);


    @POST("homepage")
    Call<HomeResponse> getHomeData();


    @POST("add-product-rating")
    @FormUrlEncoded
    Call<AddRatingResponse> addReview(@Field("userid") String userId,
                                      @Field("usercommnet") String userComment,
                                      @Field("productid") String productId,
                                      @Field("prod_rate") String productRate
    );

    @POST("checkout-payment")
    @FormUrlEncoded
    Call<CheckoutPaymentResponse> getCheckoutPayment(@Field("user_id") String userId,
                                                     @Field("razorpay_payment_id") String razorPaymentId,
                                                     @Field("shipcharge") String shipCharge,
                                                     @Field("amount") String amount,
                                                     @Field("sameinfo") String sameInfo,
                                                     @Field("rfname") String rFname,
                                                     @Field("rlname") String rLname,
                                                     @Field("remail") String rEmail,
                                                     @Field("rlandmark") String rLandmark,
                                                     @Field("rnumber") String rNumber,
                                                     @Field("radress") String rAddress,
                                                     @Field("rzip") String rZip,
                                                     @Field("rstate") String rState,
                                                     @Field("rcity") String rCity,
                                                     @Field("sfname") String sFname,
                                                     @Field("slname") String sLname,
                                                     @Field("semail") String sEmail,
                                                     @Field("slandmark") String sLandmark,
                                                     @Field("snumber") String sNumber,
                                                     @Field("sadress") String sAddress,
                                                     @Field("szip") String sZip,
                                                     @Field("sstate") String sState,
                                                     @Field("scity") String sCity
    );


    @POST("get-products-by-category")
    @FormUrlEncoded
    Call<ProductByCategoryResponse> getProductByCategory(@Field("cat_id") String catId,
                                                         @Field("fattributes") String[] fattributes);

    @POST("get-products-by-category")
    Call<ProductByCategoryResponse> getProductByCategory(@Body ProductByCategoryRequest jsonObject);

    @POST("cancel_order")
    @FormUrlEncoded
    Call<CancelOrderResponse> cancelOrder(@Field("order_id") String orderId,
                                          @Field("reason") String reason);

    @POST("vendordata")
    @FormUrlEncoded
    Call<SellerResponse> getSellerProduct(@Field("vendor_id") String venderId);

    @POST("deals_of_the_day")
    Call<DealsResponse> getDealsProduct();

    @POST("vieworder")
    @FormUrlEncoded
    Call<MyOrderDetailsResponse> getOrderDetails(@Field("orderid") String orderId);

    @POST("all_categories")
    Call<CategoriesResponse> getCategories();

    @POST("generateotp")
    @FormUrlEncoded
    Call<GetOTPResponse> getOTP(@Field("mobile_number") String phone);

    @POST("get_sub_cats_with_products")
    @FormUrlEncoded
    Call<SubSubCategoriesResponse> getSubCateProductByCategory(@Field("cat_id") String catId);


    @POST("search")
    @FormUrlEncoded
    Call<SearchResponse> getSearchItem(@Field("term") String term);

    @POST("checkpincode")
    @FormUrlEncoded
    Call<CheckPincodeResponse> checkPincode(@Field("pincode") String pincode);

    @Multipart
    @POST("change-user-pic")
    Call<ChangeProfileImageResponse> changeProfilePic(@Part("user_id") RequestBody userId,
                                                      @Part MultipartBody.Part imageFile);


    @POST("update-password")
    @FormUrlEncoded
    Call<ChanePasswordResponse> changePassword(@Field("user_id") String userId,
                                                @Field("old_pass") String oldPass,
                                                @Field("new_pass") String NewPass);

    @POST("get_all_trending")
    Call<TrendingResponse> getTrendings();

}

