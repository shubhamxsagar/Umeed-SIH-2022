package com.example.umeed.APIs;

import com.example.umeed.Chat.MessageModel;
import com.example.umeed.Models.AllListModel;
import com.example.umeed.Models.BookingModel;
import com.example.umeed.Models.ComplaintsModel;
import com.example.umeed.Models.LoginDataModel;
import com.example.umeed.Models.ProfileModel;
import com.example.umeed.Models.ReportsModel;
import com.example.umeed.Models.StatusErrorModel;
import com.example.umeed.Models.TablayoutModels.BloodAvailabilityModel;
import com.example.umeed.Models.TablayoutModels.HospitalAvailabilityModel;
import com.example.umeed.Models.TablayoutModels.HospitalTreatmentModel;
import com.example.umeed.Chat.ViewChatContactModel;
import com.example.umeed.Models.TotalBedModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

//    @GET("/v1/801f4111-ed01-4fb2-a7b9-a69f9e41834f")
//    Call<MapDataModel> mapList();

    @FormUrlEncoded
    @POST("patientregistration.php")
    Call<StatusErrorModel> patientregistration(@Field("firstname") String firstname, @Field("lastname") String lastname, @Field("mobileno") String mobileno, @Field("emailid") String emailid, @Field("age") String age, @Field("gender") String gender, @Field("state") String state, @Field("city") String city, @Field("bloodgroup") String bloodgroup, @Field("chronicdisease") String chronicdisease, @Field("allergy") String allergy, @Field("password") String password, @Field("confirmpassword") String confirmpassword, @Field("aadharno") String aadharno, @Field("ayushmancardno") String ayushmancardno, @Field("emergencycontactno1") String emergencycontactno1, @Field("emergencycontactno2") String emergencycontactno2);

    @FormUrlEncoded
    @POST("checkifaadharisnotregister.php")
    Call<StatusErrorModel> checkifaadharisnotregister(@Field("aadharno") String aadharno);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginDataModel> login(@Field("userid") String userid, @Field("password") String password);

    @FormUrlEncoded
    @POST("patientlogin.php")
    Call<StatusErrorModel> patientlogin(@Field("userid") String userid, @Field("password") String password);

    @FormUrlEncoded
    @POST("checkifaadharisregister.php")
    Call<StatusErrorModel> checkifaadharisregister(@Field("aadharno") String aadharno);

    @FormUrlEncoded
    @POST("verifyotp.php")
    Call<StatusErrorModel> verfiyotp(@Field("userid") String userid, @Field("otp") String otp);

    @FormUrlEncoded
    @POST("sendotp.php")
    Call<StatusErrorModel> sendotp(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("sendotpforaadharverification.php")
    Call<StatusErrorModel> sendotpforaadharverification(@Field("aadharno") String aadharno);

    @FormUrlEncoded
    @POST("listofhospital.php")
    Call<AllListModel> listofhospital(@Field("lattitude") String lattitude, @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("listofppd.php")
    Call<AllListModel> listofppd(@Field("lattitude") String lattitude, @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("listofbloodbank.php")
    Call<AllListModel> listofbloodbank(@Field("lattitude") String lattitude, @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("listoflab.php")
    Call<AllListModel> listoflab(@Field("lattitude") String lattitude, @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("listofroominhospital.php")
    Call<HospitalAvailabilityModel> listofroominhospital(@Field("medicalid") String medicalid);

    @FormUrlEncoded
    @POST("booktreatment.php")
    Call<StatusErrorModel> booktreatment(@Field("userid") String userid, @Field("medicalid") String medicalid, @Field("medicalusertype") String medicalusertype, @Field("serviceid") String serviceid, @Field("description") String description, @Field("sharemedicalhistory") String sharemedicalhistory, @Field("needambulance") String needambulance);

    @FormUrlEncoded
    @POST("listoftreatmentoffered.php")
    Call<HospitalTreatmentModel> listoftreatmentoffered(@Field("medicalid") String medicalid);

    @FormUrlEncoded
    @POST("viewchatcontacts.php")
    Call<ViewChatContactModel> viewchatcontacts(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("messages.php")
    Call<MessageModel> messages(@Field("userid1") String userid1, @Field("userid2") String userid2);

    @FormUrlEncoded
    @POST("sendmessages.php")
    Call<StatusErrorModel> sendmessages(@Field("sender") String sender, @Field("receiver") String receiver, @Field("message") String message);

    @FormUrlEncoded
    @POST("bloodstockinbloodbank.php")
    Call<BloodAvailabilityModel> bloodstockinbloodbank(@Field("medicalid") String medicalid);

    @FormUrlEncoded
    @POST("userdetails.php")
    Call<ProfileModel> userdetails(@Field("aadharno") String aadharno);

    @FormUrlEncoded
    @POST("userbookings.php")
    Call<BookingModel> userbookings(@Field("aadharno") String aadharno);

    @FormUrlEncoded
    @POST("userviewmedicalhistory.php")
    Call<ReportsModel> userviewmedicalhistory(@Field("aadharno") String aadharno);

    @FormUrlEncoded
    @POST("patientuploadmedicalhistory.php")
    Call<BookingModel> patientuploadmedicalhistory(@Field("aadharno") String aadharno, @Field("reportfile") String reportfile);

    @FormUrlEncoded
    @POST("viewreportforbooking.php")
    Call<ReportsModel> viewreportforbooking(@Field("bookingid") String bookingid);

    @FormUrlEncoded
    @POST("viewfinalbill.php")
    Call<ReportsModel> viewfinalbill(@Field("bookingid") String bookingid);

    @FormUrlEncoded
    @POST("addbillcomplain.php")
    Call<StatusErrorModel> addbillcomplain(@Field("aadharno") String aadharno, @Field("bookingid") String bookingid, @Field("description") String description);

    @FormUrlEncoded
    @POST("usercomplainlist.php")
    Call<ComplaintsModel> usercomplainlist(@Field("aadharno") String aadharno);

    @FormUrlEncoded
    @POST("totalbedinhospital.php")
    Call<TotalBedModel> totalbedinhospital(@Field("medicalid") String medicalid);

    @FormUrlEncoded
    @POST("sendsmstoemergencynumber.php")
    Call<StatusErrorModel> sendsmstoemergency(@Field("aadharno") String aadharno, @Field("location") String location);
}
