package com.example.umeed.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDataModel {

    @SerializedName("firstname")
    @Expose
    private String firstname;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("mobileno")
    @Expose
    private String mobileno;

    @SerializedName("emailid")
    @Expose
    private String emailid;

    @SerializedName("aadharno")
    @Expose
    private String aadharno;

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("bloodgroup")
    @Expose
    private String bloodgroup;

    @SerializedName("chronicdisease")
    @Expose
    private String chronicdisease;

    @SerializedName("allergy")
    @Expose
    private String allergy;

    @SerializedName("ayushmancardno")
    @Expose
    private String ayushmancardno;

    @SerializedName("emergencycontact1")
    @Expose
    private String emergencycontact1;

    @SerializedName("emergencycontact2")
    @Expose
    private String emergencycontact2;

    public ProfileDataModel(String firstname, String lastname, String mobileno, String emailid, String aadharno, String age, String gender, String state, String city, String bloodgroup, String chronicdisease, String allergy, String ayushmancardno, String emergencycontact1, String emergencycontact2) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobileno = mobileno;
        this.emailid = emailid;
        this.aadharno = aadharno;
        this.age = age;
        this.gender = gender;
        this.state = state;
        this.city = city;
        this.bloodgroup = bloodgroup;
        this.chronicdisease = chronicdisease;
        this.allergy = allergy;
        this.ayushmancardno = ayushmancardno;
        this.emergencycontact1 = emergencycontact1;
        this.emergencycontact2 = emergencycontact2;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getAadharno() {
        return aadharno;
    }

    public void setAadharno(String aadharno) {
        this.aadharno = aadharno;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getChronicdisease() {
        return chronicdisease;
    }

    public void setChronicdisease(String chronicdisease) {
        this.chronicdisease = chronicdisease;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getAyushmancardno() {
        return ayushmancardno;
    }

    public void setAyushmancardno(String ayushmancardno) {
        this.ayushmancardno = ayushmancardno;
    }

    public String getEmergencycontact1() {
        return emergencycontact1;
    }

    public void setEmergencycontact1(String emergencycontact1) {
        this.emergencycontact1 = emergencycontact1;
    }

    public String getEmergencycontact2() {
        return emergencycontact2;
    }

    public void setEmergencycontact2(String emergencycontact2) {
        this.emergencycontact2 = emergencycontact2;
    }
}
