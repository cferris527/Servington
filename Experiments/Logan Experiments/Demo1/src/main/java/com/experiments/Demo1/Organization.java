package com.experiments.Demo1;
public class Organization {
    private String organizationName;
    private String organizationType;
    private int organizationStatus;
    private String organizationAddress;
    private int organizationPhoneNumber;
    private int organizationNumMembers;
    private int organizationID;

    public Organization(String name, String organizationType, int numMembers, int ID) {
        organizationName = name;
        this.organizationType = organizationType;
        organizationNumMembers = numMembers;
        organizationStatus = 0;
        organizationAddress = "";
        organizationPhoneNumber = -1;
        organizationID = ID;
    }
    public String getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(String name) {
        organizationName = name;
    }
    public String getOrganizationType() {
        return organizationType;
    }
    public void setOrganizationType(String type) {
        organizationType = type;
    }
    public int getOrganizationStatus() {
        return organizationStatus;
    }
    public void setOrganizationStatus(int status) {
        organizationStatus = status;
    }
    public String getOrganizationAddress() {
        return organizationAddress;
    }
    public void setOrganizationAddress(String address) {
        organizationAddress = address;
    }
    public int getOrganizationPhoneNumber() {
        return organizationPhoneNumber;
    }
    public void setOrganizationPhoneNumber(int phoneNumber) {
        organizationPhoneNumber = phoneNumber;
    }
    public int getOrganizationNumMembers() {
        return organizationNumMembers;
    }
    public void setOrganizationNumMembers(int numMembers) {
        organizationNumMembers = numMembers;
    }
    public int getOrganizationID() {
        return organizationID;
    }
    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }
    @Override
    public String toString() {
        return organizationName;
    }
}
