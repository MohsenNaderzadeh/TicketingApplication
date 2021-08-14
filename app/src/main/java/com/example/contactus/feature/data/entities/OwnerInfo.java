package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

public class OwnerInfo {
    
    @SerializedName("StudentNationalCode")
    private String studentNationalCode;
    
    @SerializedName("StudentLastName")
    private String studentLastName;
    
    @SerializedName("GradeName")
    private String gradeName;
    
    @SerializedName("MajorName")
    private String majorName;
    
    @SerializedName("StudentUniversityCode")
    private String studentUniversityCode;
    
    @SerializedName("StudentName")
    private String studentName;
    
    @SerializedName("StudentId")
    private int studentId;
    
    public String getStudentNationalCode() {
        return studentNationalCode;
    }
    
    public void setStudentNationalCode(String studentNationalCode) {
        this.studentNationalCode = studentNationalCode;
    }
    
    public String getStudentLastName() {
        return studentLastName;
    }
    
    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }
    
    public String getGradeName() {
        return gradeName;
    }
    
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
    
    public String getMajorName() {
        return majorName;
    }
    
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    
    public String getStudentUniversityCode() {
        return studentUniversityCode;
    }
    
    public void setStudentUniversityCode(String studentUniversityCode) {
        this.studentUniversityCode = studentUniversityCode;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    @Override
    public String toString() {
        return
                "OwnerInfo{" +
                        "studentNationalCode = '" + studentNationalCode + '\'' +
                        ",studentLastName = '" + studentLastName + '\'' +
                        ",gradeName = '" + gradeName + '\'' +
                        ",majorName = '" + majorName + '\'' +
                        ",studentUniversityCode = '" + studentUniversityCode + '\'' +
                        ",studentName = '" + studentName + '\'' +
                        ",studentId = '" + studentId + '\'' +
                        "}";
    }
}