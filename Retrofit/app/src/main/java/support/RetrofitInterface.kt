/*
 * *
 *  * Created by Vishal.Khanna on 13/12/21, 2:54 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 13/12/21, 11:21 AM
 *
 */

package dependencies

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("ESale/CBill/GetSetBookList")
    fun GetSetBookListReturn(
        @Query("SchoolId") SchoolId: String?,
        @Query("ClassId") ClassId: String?
    ): Call<ResponseBody?>?

    @GET("ESale/CBill/ReturnByStationByUser")
    fun ReturnByStationByUser(
        @Query("StartDate") StartDate: String?,
        @Query("EndDate") EndDate: String?,
        @Query("UserId") UserId: String?
    ): Call<ResponseBody?>?


    @GET("ESale/CBill/ReturnByStationByDate")
    fun ReturnByStationByDate(
        @Query("StartDate") StartDate: String?,
        @Query("EndDate") EndDate: String?,
        @Query("SchoolId") SchoolId: String?
    ): Call<ResponseBody?>?

    @POST("ESale/CBill/InsertItems")
    fun InsertItems(
        @Query("CBillId") CBillId: String?,
        @Query("PId") PId: String?,
        @Query("ClassId") ClassId: String?,
        @Query("ItemCode") ItemCode: String?,
        @Query("ItemName") ItemName: String?,
        @Query("Qty") Qty: String?,
        @Query("Rate") Rate: String?,
        @Query("GrossTotal") GrossTotal: String?,
        @Query("CGST") CGST: String?,
        @Query("SGST") SGST: String?,
        @Query("TotalAmount") TotalAmount: String?,
        @Query("Schoolid") Schoolid: String?,
        @Query("PublisherId") PublisherId: String?,
        @Query("UserId") UserId: String?
    ): Call<ResponseBody?>?

    @POST("ESale/CBill/GenerateBill")
    fun GenerateBill(
        @Query("SchoolId") SchoolId: String?,
        @Query("TotalPrice") TotalPrice: String?,
        @Query("TotalAmount") TotalAmount: String?,
        @Query("CGST") CGST: String?,
        @Query("SGST") SGST: String?,
        @Query("BookTypeId") BookTypeId: String?,
        @Query("UserId") UserId: String?,
        @Query("TabId") TabId: String?
    ): Call<ResponseBody?>?

    @POST("ESale/SO/SNV/DuplicateBill")
    fun DuplicateBill(@Query("BillId") id: String?): Call<ResponseBody?>?

    @GET("ESale/SIN/sN/SchoolListWithName")
    fun SchoolListWithName(): Call<ResponseBody?>?

    @GET("ESale/SIn/SN/ClassList")
    fun ClassList(): Call<ResponseBody?>?

    @GET("ESale/SIn/SN/GetSetId")
    fun GetSetId(
        @Query("SchoolId") SchoolId: String?,
        @Query("ClassId") ClassId: String?
    ): Call<ResponseBody?>?

    @GET("ESale/SIn/SN/GetSetBookList")
    fun GetSetBookList(
        @Query("SchoolId") SchoolId: String?,
        @Query("ClassId") ClassId: String?
    ): Call<ResponseBody?>?

    @GET("ESale/SO/SNV/CheckBillExist")
    fun CheckBillExist(
        @Query("Billno") Billno: String?,
        @Query("BookTypeid") BookTypeid: String?
    ): Call<ResponseBody?>?

    @POST("ESale/SO/SNV/ReturnSingleItem")
    fun ReturnSingleItem(
        @Query("pid") pid: String?,
        @Query("Qty") Qty: String?,
        @Query("BillNo") BillNo: String?,
        @Query("Schoolid") Schoolid: String?,
        @Query("ClassId") ClassId: String?,
        @Query("Price") Price: String?,
        @Query("UserId") UserId: String?,
        @Query("BillId") BillId: String?
    ): Call<ResponseBody?>?

    @GET("ESale/SIn/SN/BindProductType")
    fun BindProductType(): Call<ResponseBody?>?

    @GET("ESale/ES/Login/MLogin/")
    fun ManageLogin1(
        @Query("Phone") Phone: String?,
        @Query("password") password: String?
    ): Call<ResponseBody?>?

    @GET("ESale/SIn/SN/ClassByProductType")
    fun ClassByProductType(
        @Query("SchoolId") SchoolId: String?,
        @Query("ProductTypeId") ProductTypeId: String?
    ): Call<ResponseBody?>?

    @GET("ESale/SIn/SN/ProductListByType")
    fun ProductListByType(
        @Query("SchoolId") SchoolId: String?,
        @Query("ClassId") ClassId: String?,
        @Query("ProductTypeId") ProductTypeId: String?
    ): Call<ResponseBody?>?

    @GET("ESale/SOR/SNV/BillListByLocation")
    fun BillListByLocation(
        @Query("Schoolid") Schoolid: String?
    ): Call<ResponseBody?>?

    @POST("ESale/SIn/SN/ActiveSchool")
    fun ActiveSchool(@Query("id") id: String?): Call<ResponseBody?>?

    @POST("ESale/SIn/SN/InActiveSchool")
    fun InActiveSchool(@Query("id") id: String?): Call<ResponseBody?>?


    @GET("ESale/SO/SNV/SearchBillByNumber")
    fun SearchBillByNumber(
        @Query("BillNo") BillNo: String?
    ): Call<ResponseBody?>?

    @GET("ESale/SO/SNV/GetPaymentType")
    fun GetPaymentType(): Call<ResponseBody?>?

    @POST("ESale/SO/SNV/GenerateBill")
    fun GenerateBill_Books(
        @Query("SchoolId") SchoolId: String?,
        @Query("ClassId") ClassId: String?,
        @Query("SetId") SetId: String?,
        @Query("TotalAmount") TotalAmount: String?,
        @Query("PaymentType") PaymentType: String?,
        @Query("Discount") Discount: String?,
        @Query("IsDiscount") IsDiscount: String?,
        @Query("TabId") TabId: String?,
        @Query("UserId") UserId: String?,
        @Query("TotalPrice") TotalPrice: String?,
        @Query("NoOfSets") NoOfSets: String?,
        @Query("IscompleteSet") IscompleteSet: String?,
        @Query("CgstAmount") CgstAmount: String?,
        @Query("SgstAmount") SgstAmount: String?,
        @Query("DiscountAmount") DiscountAmount: String?,
        @Query("BookTypeid") BookTypeid: String?
    ): Call<ResponseBody?>?

    @POST("ESale/SO/SNV/InsertBillDetail")
    fun InsertBillDetail(
        @Query("BillId") BillId: String?,
        @Query("PID") PID: String?,
        @Query("PName") PName: String?,
        @Query("Qty") Qty: String?,
        @Query("Price") Price: String?,
        @Query("TotalSum") TotalSum: String?,
        @Query("Igst") Igst: String?,
        @Query("Cgst") Cgst: String?,
        @Query("Sgst") Sgst: String?,
        @Query("FinalAmount") FinalAmount: String?
    ): Call<ResponseBody?>?


    @POST("ESale/SO/SNV/InsertBillDetailNew")
    fun InsertBillDetailNew(
        @Query("BillId") BillId: String?,
        @Query("PID") PID: String?,
        @Query("PName") PName: String?,
        @Query("Qty") Qty: String?,
        @Query("Price") Price: String?,
        @Query("TotalSum") TotalSum: String?,
        @Query("Igst") Igst: String?,
        @Query("Cgst") Cgst: String?,
        @Query("Sgst") Sgst: String?,
        @Query("FinalAmount") FinalAmount: String?,
        @Query("BookTypeid") BookTypeid: String?
    ): Call<ResponseBody?>?


    @GET("ESale/SIn/SN/SchoolList")
    fun SchoolList(): Call<ResponseBody?>?

    @GET("ESale/SIn/SN/CheckAreaCode")
    fun CheckAreaCode(@Query("AreaCode") AreaCode: String?): Call<ResponseBody?>?

    @GET("ESale/SO/SNV/BillDetail")
    fun BillDetail(@Query("BillId") BillId: String?): Call<ResponseBody?>?

    @GET("ESale/SO/SNV/BillReportByDate")
    fun BillReportByDate(@Query("StartDate") StartDate: String?,
                         @Query("EndDate") EndDate: String?): Call<ResponseBody?>?

    @GET("ESale/SOR/SNV/TotalProductBillByLocation")
    fun TotalProductBillByLocation(
        @Query("Schoolid") Schoolid: String?,
        @Query("Producttypeid") Producttypeid: String?
    ): Call<ResponseBody?>?

    @GET("ESale/SOR/SNV/TotalProductBillByLocationDate")
    fun TotalProductBillByLocationDate(
        @Query("Schoolid") Schoolid: String?,
        @Query("Producttypeid") Producttypeid: String?,
        @Query("SDate") SDate: String?,
        @Query("EDate") EDate: String?
    ): Call<ResponseBody?>?

    @GET("/ESale/SO/SNV/GetUserBill")
    fun GetUserBill(
        @Query("UserId") UserId: String?,
        @Query("SchoolId") SchoolId: String?,
        @Query("StartDate") StartDate: String?,
        @Query("EndDate") EndDate: String?
    ): Call<ResponseBody?>?

    @GET("ESale/SOR/SNV/TodayProductBillByLocation")
    fun TodayProductBillByLocation(
        @Query("Schoolid") Schoolid: String?,
        @Query("Producttypeid") Producttypeid: String?
    ): Call<ResponseBody?>?

    @POST("ESale/SIn/SN/AddSchool/")
    fun AddSchool(
        @Query("SchoolName") SchoolName: String?,
        @Query("Address") Address: String?,
        @Query("BoardId") BoardId: String?,
        @Query("Area") Area: String?,
        @Query("PinCode") PinCode: String?,
        @Query("AreaCode") AreaCode: String?
    ): Call<ResponseBody?>?

    @POST("ESale/SIn/SN/ActiveSet")
    fun ActiveSet(@Query("SetId") SetId: String?): Call<ResponseBody?>?

    @POST("ESale/SIn/SN/InActiveSet")
    fun InActiveSet(@Query("SetId") SetId: String?): Call<ResponseBody?>?

    @POST("ESale/SIn/SN/ActivePublisher")
    fun ActivePublisher(@Query("id") id: String?): Call<ResponseBody?>?

    @POST("ESale/SIn/SN/InActivePublisher")
    fun InActivePublisher(@Query("id") id: String?): Call<ResponseBody?>?

    @POST("ESale/ES/Login/InActive/")
    fun InActive(
        @Query("UserId") UserId: String?,
        @Query("status") status: String?
    ): Call<ResponseBody?>?


    @POST("ESale/SIN/SN/UpdateProduct1")
    fun UpdateProduct(
        @Query("ProductId") ProductId: String?,
        @Query("StockId") StockId: String?,
        @Query("ProductCode") ProductCode: String?,
        @Query("ProductName") ProductName: String?,
        @Query("PublisherId") PublisherId: String?,
        @Query("SchoolId") SchoolId: String?,
        @Query("ClassId") ClassId: String?,
        @Query("UserId") UserId: String?,
        @Query("ProductType") ProductType: String?,
        @Query("MinQuantity") MinQuantity: String?,
        @Query("HSM") HSM: String?
    ): Call<ResponseBody?>?

    @GET("ESale/SIn/SN/GetSetList")
    fun GetSetList(): Call<ResponseBody?>?

    @POST("ESale/SIn/SN/ActiveProduct")
    fun ActiveProduct(@Query("id") id: String?): Call<ResponseBody?>?

    @POST("ESale/SIn/SN/InActiveProduct")
    fun InActiveProduct(@Query("id") id: String?): Call<ResponseBody?>?


    @GET("ESale/SIn/SN/ProductList")
    fun ProductList(): Call<ResponseBody?>?

    @Headers("Content-type: application/json")

    @GET("ESale/SIn/SN/PublisherList")
    fun PublisherList(): Call<ResponseBody?>?


    @GET("ESale/ES/Login/GetAllList")
    fun GetAllList(): Call<ResponseBody?>?

    @GET("ESale/SIN/SN/AvailableStock")
    fun AvailableStock(@Query("Schoolid") Schoolid: String?,
                       @Query("ClassId") ClassId: String?): Call<ResponseBody?>?

    @GET("ESale/SIN/SN/ProductListById1")
    fun ProductListById1(@Query("id") id: String?): Call<ResponseBody?>?

    @GET("api/Common/comm/getSubject")
    fun getSubject(@Query("gradeid") gradeid: String?): Call<ResponseBody?>?

    @GET("PDF/PDFAPI/App/getsubbyClassId")
    fun getsubbyClassId(@Query("gradeid") gradeid: String?): Call<ResponseBody?>?

    @GET("api/Common/comm/getgrade")
    fun getgrade(): Call<ResponseBody?>?

    @GET("SmartLearning/sl/comm/ResultListById")
    fun ResultListById(
        @Query("userid") userid: String?,
        @Query("chapterid") chapterid: String?,
        @Query("QuestionTypeId") QuestionTypeId: String?
    ): Call<ResponseBody?>?

    @GET("api/Common/comm/getTitle")
    fun getTitle(
        @Query("gradeid") gradeid: String?,
        @Query("subjectid") subjectid: String?
    ): Call<ResponseBody?>?

    @GET("PDF/PDFAPI/App/gettitle")
    fun gettitle(
        @Query("gradeid") gradeid: String?,
        @Query("subjectid") subjectid: String?
    ): Call<ResponseBody?>?

    @GET("SmartLearning/sl/comm/ChapterList/")
    fun ChapterList(@Query("id") id: String?): Call<ResponseBody?>?

    @GET("SmartLearning/sl/comm/ChapterSearch/")
    fun ChapterSearch(
        @Query("id") id: String?,
        @Query("query") query: String?
    ): Call<ResponseBody?>?

    @GET("SmartLearning/sl/comm/TitleSearch/")
    fun TitleSearch(
        @Query("subjectid") subjectid: String?,
        @Query("gradeid") gradeid: String?,
        @Query("query") query: String?
    ): Call<ResponseBody?>?

    @GET("api/eExpert/Expert/questionlist/")
    fun questionlist(
        @Query("sub") sub: String?,
        @Query("catid") catid: String?,
        @Query("qtypeid") qtypeid: String?
    ): Call<ResponseBody?>?

    @GET("SmartLearning/sl/comm/TopicList/")
    fun TopicList(@Query("id") id: String?): Call<ResponseBody?>?

    @POST("SmartLearning/sl/comm/SumbitChapterTest/")
    fun SumbitChapterTest(
        @Query("userid") userid: String?,
        @Query("totalques") totalques: String?,
        @Query("attemptques") attemptques: String?,
        @Query("correct") correct: String?,
        @Query("incorrect") incorrect: String?,
        @Query("subjectid") subjectid: String?,
        @Query("unattemp") unattemp: String?,
        @Query("email") email: String?,
        @Query("marks") marks: String?,
        @Query("chapterid") chapterid: String?,
        @Query("QuestionTypeId") QuestionTypeId: String?
    ): Call<ResponseBody?>?

    @POST("api/Common/comm/FeedbackRequest/")
    fun FeedbackRequest(
        @Query("Area") Area: String?,
        @Query("Userid") Userid: String?,
        @Query("Feedback") Feedback: String?,
        @Query("_email") _email: String?
    ): Call<ResponseBody?>?

    @POST("api/eExpert/Expert/PostQuery")
    fun PostQuery(
        @Query("TITLEID") TITLEID: String?,
        @Query("PAGE") PAGE: String?,
        @Query("QUERY") QUERY: String?,
        @Query("DIS") DIS: String?,
        @Query("userId") userid: String?,
        @Query("subjectId") subjectId: String?,
        @Query("gradeid") gradeid: String?,
        @Query("email") email: String?
    ): Call<ResponseBody?>?

    @POST("manageUser/User/secure/RegisterTeacher")
    fun RegisterTeacher(
        @Query("_email") _email: String?,
        @Query("_password") _password: String?,
        @Query("schoolName") schoolName: String?,
        @Query("schoolAddress") schoolAddress: String?,
        @Query("afilliationCode") afilliationCode: String?,
        @Query("phone") phone: String?,
        @Query("gradeid") gradeid: String?,
        @Query("subjectid") subjectid: String?
    ): Call<ResponseBody?>?
}