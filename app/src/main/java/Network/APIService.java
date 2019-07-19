package Network;

import com.ws.design.coco_ecommerce_ui_kit.address.AddUpdateAddressResponse;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListResponse;
import com.ws.design.coco_ecommerce_ui_kit.address.DeleteAddressResponse;
import com.ws.design.coco_ecommerce_ui_kit.checkout.CheckoutPaymentResponse;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.HomeResponse;
import com.ws.design.coco_ecommerce_ui_kit.login.ForgotPasswordResponse;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.EmptyCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.RemoveCartByCrossResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.RemoveCartOneByOneResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.RemoveWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_rating_list.AddRatingResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_rating_list.product_rating_response.ProductRatingResponse;
import com.ws.design.coco_ecommerce_ui_kit.profile.UpdateProfileResponse;
import com.ws.design.coco_ecommerce_ui_kit.signup.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
                                  @Field("password") String password,
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
                                      @Field("quantity") String quantity);

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
                                                      @Field("phone") String phone);


    @POST("product-detail")
    @FormUrlEncoded
    Call<ProductDetailsResponse> getProductDetails(@Field("slug") String slug);

    @POST("product-ratings")
    @FormUrlEncoded
    Call<ProductRatingResponse> getProductRating(@Field("productid") String productId);


    //    @Headers("user-key: 9900a9720d31dfd5fdb4352700c")
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
    Call<ProductByCategoryResponse> getProductByCategory(@Field("cat_id") String catId);

}

