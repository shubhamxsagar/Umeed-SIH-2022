package com.example.umeed.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllListDataModel {

    public static final int LayoutOne = 0;
    public static final int LayoutTwo = 1;

    int viewType;

    @SerializedName("organizationname")
    @Expose
    String organizationname;

    @SerializedName("ownername")
    @Expose
    String ownername;

    @SerializedName("medicalusertype")
    @Expose
    String medicalusertype;

    @SerializedName("emailid")
    @Expose
    String emailid;

    @SerializedName("website")
    @Expose
    String website;

    @SerializedName("mobileno")
    @Expose
    String mobileno;

    @SerializedName("landlineno")
    @Expose
    String landlineno;

    @SerializedName("licensenumber")
    @Expose
    String licensenumber;

    @SerializedName("licensefile")
    @Expose
    String licensefile;

    @SerializedName("medicalid")
    @Expose
    String medicalid;

    @SerializedName("password")
    @Expose
    String password;

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("lattitude")
    @Expose
    String lattitude;

    @SerializedName("longitude")
    @Expose
    String longitude;

    @SerializedName("experience")
    @Expose
    String experience;

    @SerializedName("specialization")
    @Expose
    String specialization;

    @SerializedName("covidpatientaccepted")
    @Expose
    String covidpatientaccepted;

    @SerializedName("ayushmancardaccepted")
    @Expose
    String ayushmancardaccepted;

    @SerializedName("mediclaimaccepted")
    @Expose
    String mediclaimaccepted;

    @SerializedName("emergencypatientaccepted")
    @Expose
    String emergencypatientaccepted;

    @SerializedName("info")
    @Expose
    String info;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("qualification")
    @Expose
    String qualification;

    @SerializedName("bloodbank")
    @Expose
    String bloodbank;

    @SerializedName("distance")
    @Expose
    String distance;

    public AllListDataModel(int viewType, String organizationname, String ownername, String medicalusertype, String emailid, String website, String mobileno, String landlineno, String licensenumber, String licensefile, String medicalid, String password, String address, String lattitude, String longitude, String experience, String specialization, String covidpatientaccepted, String ayushmancardaccepted, String mediclaimaccepted, String emergencypatientaccepted, String info, String status, String type, String qualification, String bloodbank, String distance) {
        this.viewType = viewType;
        this.organizationname = organizationname;
        this.ownername = ownername;
        this.medicalusertype = medicalusertype;
        this.emailid = emailid;
        this.website = website;
        this.mobileno = mobileno;
        this.landlineno = landlineno;
        this.licensenumber = licensenumber;
        this.licensefile = licensefile;
        this.medicalid = medicalid;
        this.password = password;
        this.address = address;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.experience = experience;
        this.specialization = specialization;
        this.covidpatientaccepted = covidpatientaccepted;
        this.ayushmancardaccepted = ayushmancardaccepted;
        this.mediclaimaccepted = mediclaimaccepted;
        this.emergencypatientaccepted = emergencypatientaccepted;
        this.info = info;
        this.status = status;
        this.type = type;
        this.qualification = qualification;
        this.bloodbank = bloodbank;
        this.distance = distance;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getMedicalusertype() {
        return medicalusertype;
    }

    public void setMedicalusertype(String medicalusertype) {
        this.medicalusertype = medicalusertype;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getLandlineno() {
        return landlineno;
    }

    public void setLandlineno(String landlineno) {
        this.landlineno = landlineno;
    }

    public String getLicensenumber() {
        return licensenumber;
    }

    public void setLicensenumber(String licensenumber) {
        this.licensenumber = licensenumber;
    }

    public String getLicensefile() {
        return licensefile;
    }

    public void setLicensefile(String licensefile) {
        this.licensefile = licensefile;
    }

    public String getMedicalid() {
        return medicalid;
    }

    public void setMedicalid(String medicalid) {
        this.medicalid = medicalid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getCovidpatientaccepted() {
        return covidpatientaccepted;
    }

    public void setCovidpatientaccepted(String covidpatientaccepted) {
        this.covidpatientaccepted = covidpatientaccepted;
    }

    public String getAyushmancardaccepted() {
        return ayushmancardaccepted;
    }

    public void setAyushmancardaccepted(String ayushmancardaccepted) {
        this.ayushmancardaccepted = ayushmancardaccepted;
    }

    public String getMediclaimaccepted() {
        return mediclaimaccepted;
    }

    public void setMediclaimaccepted(String mediclaimaccepted) {
        this.mediclaimaccepted = mediclaimaccepted;
    }

    public String getEmergencypatientaccepted() {
        return emergencypatientaccepted;
    }

    public void setEmergencypatientaccepted(String emergencypatientaccepted) {
        this.emergencypatientaccepted = emergencypatientaccepted;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getBloodbank() {
        return bloodbank;
    }

    public void setBloodbank(String bloodbank) {
        this.bloodbank = bloodbank;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
