package com.example.demo.domain;

/**
 * @author Qiang
 */
public class PhoneNumber {
    private String mobilePhoneNumber;
    private String template;

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public PhoneNumber(String phonenumber) {
        this.mobilePhoneNumber = phonenumber;
    }

    public String getPhonenumber() {
        return mobilePhoneNumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.mobilePhoneNumber = phonenumber;
    }
}
