package com.example.tokosahabat.API;

import com.example.tokosahabat.model.Cart;
import com.example.tokosahabat.model.Order;
import com.example.tokosahabat.model.ResponseModel;
import com.example.tokosahabat.model.keranjang.Keranjang;
import com.example.tokosahabat.model.login.Login;
import com.example.tokosahabat.model.login.LoginAdmin;
import com.example.tokosahabat.model.price.Price;
import com.example.tokosahabat.model.register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login_admin.php")
    Call<LoginAdmin> loginAdminResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama") String nama,
            @Field("telepon") String telepon
    );


    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(
            @Field("gambar_item") String gambar_item,
            @Field("kode_item") String kode_item,
            @Field("barcode") String barcode,
            @Field("nama_item") String nama_item,
            @Field("stok_item") String stok_item,
            @Field("jenis_item") String jenis_item,
            @Field("konversi") String konversi,
            @Field("tipe_item") String tipe_item,
            @Field("satuan") String satuan,
            @Field("harga_pokok") String harga_pokok,
            @Field("harga_level") String harga_level
    );

    @GET("retrieve.php")
    Call<ResponseModel> ardRertrieveData();

    @GET("retrieve_order.php")
    Call<Order> ardRertrieveOderData();

//    @GET("retrieve_keranjang.php")
//    Call<Cart> ardRertrieveKeranjangData();

    @FormUrlEncoded
    @POST("retrieve_keranjang.php")
    Call<Cart> ardRertrieveKeranjangData(
          @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("cari_produk.php")
    Call<ResponseModel> ardCariProdukData(
            @Field("nama_item") String nama_item
    );

    @FormUrlEncoded
    @POST("order.php")
    Call<Order>ardOrderData(
            @Field("id_user") int id_user,
            @Field("nama_user") String nama_user
    );


    @FormUrlEncoded
    @POST("total_price.php")
    Call<Price> ardTotalPrice(
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("total_price_order.php")
    Call<Price> ardTotalOrderPrice(
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseModel> ardDeleteData(
            @Field("id_item") int id_item
    );

    @FormUrlEncoded
    @POST("checkout.php")
    Call<Cart> ardCheckoutData(
            @Field("id_user") int id_user
    );



    @FormUrlEncoded
    @POST("edit.php")
    Call<ResponseModel> ardEditData(
            @Field("id_item") int id_item
    );

    @FormUrlEncoded
    @POST("detail_produk.php")
    Call<ResponseModel> ardDetailData(
            @Field("id_item") int id_item
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseModel> ardUpdateData(
            @Field("id_item") int id_item,
            @Field("gambar_item") String gambar_item,
            @Field("kode_item") String kode_item,
            @Field("barcode") String barcode,
            @Field("nama_item") String nama_item,
            @Field("stok_item") String stok_item,
            @Field("jenis_item") String jenis_item,
            @Field("konversi") String konversi,
            @Field("tipe_item") String tipe_item,
            @Field("satuan") String satuan,
            @Field("harga_pokok") String harga_pokok,
            @Field("harga_level") String harga_level
    );

    @FormUrlEncoded
    @POST("approve.php")
    Call<Order> ardApproveData(
            @Field("id_transaksi") int id_transaksi,
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("data_order.php")
    Call<Cart> ardDataOrder(
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("keranjang.php")
    Call<Keranjang> ardKeranjangData(
            @Field("id_user") int id_user,
            @Field("id_item") int id_item,
            @Field("gambar_item") String gambar_item,
            @Field("nama_item") String nama_item,
            @Field("harga_pokok") String harga_pokok
    );
}
