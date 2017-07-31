package com.cxp.mrrd.api;

import com.cxp.mrrd.model.ResponseModel;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 文 件 名: RxApi
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 4:59
 * 描    述: 数据请求 示例
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface RxApi {

    //前面不能带/，否则不出
    //正常连接
    @GET("getUserNameGet.jhtml")
    Observable<ResponseModel> getListUsers();

    //@Path 占位符连接 相当于在url后面/{accountId}   例如：http://192.168.1.109:8080/CxpWeb/user/{accountId}
    @GET("{accountId}")
    Observable<ResponseModel> getUserQuery(@Path("accountId") String accountId);

    //@Query 带参数连接 相当于在url后面跟上?userName={userName}  例如：http://192.168.1.109:8080/CxpWeb/user/getUserNameGetQuery.jhtml?userName={userName}
    @POST("getUserNameGetQuery.jhtml")
    Observable<ResponseModel> getUserQuerys(@Query("userName") String userName);

    //@QueryMap 传递多个参数  相当于在url后面跟上?key={key}&key1={key1}&key2={key2}...   例如：http://192.168.1.109:8080/CxpWeb/user/getUserNameGetQuery.jhtml?key={key}&key1={key1}&key2={key2}...
    @POST("getUserNamePost.jhtml")
    Observable<ResponseModel> getUserQueryMap(@QueryMap Map<String,Object> map);

    //@Field 相当于表单提交
    //@FormUrlEncoded 必须带上，防止乱码
    @FormUrlEncoded
    @POST("filedQuery.jhtml")
    Observable<ResponseModel> filedQuery(@Field("userName") String userName);

    //@FieldMap 相当于表单提交
    //@FormUrlEncoded 必须带上，防止乱码
    @FormUrlEncoded
    @POST("filedQueryMap.jhtml")
    Observable<ResponseModel> filedQueryMap(@FieldMap Map<String,Object> map);

    //@Multipart 单文件上传
    @Multipart
    @POST("upload.jhtml")
    Observable<String> uploadFile(@Part MultipartBody.Part file, @Query("data") String data);

    //@MultipartMap 多文件上传
    @Multipart
    @POST("uploads.jhtml")
    Observable<String> uploadFiles(@PartMap Map<String, RequestBody> uploadFiles, @Query("data") String data);
}
