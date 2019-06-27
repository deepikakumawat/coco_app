package Network;

import com.ws.design.coco_ecommerce_ui_kit.address.AddressListResponse;
import com.ws.design.coco_ecommerce_ui_kit.address.DeleteAddressResponse;
import com.ws.design.coco_ecommerce_ui_kit.login.ForgotPasswordResponse;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderResponse;
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
}
