package com.yitsol.quickmeal.parsers;

import com.yitsol.quickmeal.domain.RegistrationDO;

import org.json.JSONObject;

import java.util.ArrayList;

public class GetAllRegistrationHandler extends BaseHandler {
    ArrayList<RegistrationDO> arrRegistrationDO;
    RegistrationDO registrationDO;
    int opstatus;
    String httpStatus = "";
    String errorMessage = "", Message = "", Error = "";


    public GetAllRegistrationHandler(String inputStream) {
        arrRegistrationDO = new ArrayList<RegistrationDO>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {
            JSONObject jObject = new JSONObject(inputStream);
            //Message = jObject.getString("status");
            if (jObject.has("error")) {
                JSONObject jsonObject = jObject.getJSONObject("error");
                Message = jsonObject.getString("status");
                registrationDO = new RegistrationDO();
                registrationDO.errorMessage = Message;
                errorMessage = "null";
                arrRegistrationDO.add(registrationDO);
            } else {
                registrationDO = new RegistrationDO();
                registrationDO.CustomerName = jObject.getString("name");
                registrationDO.CustomerEmail = jObject.getString("email");
                registrationDO.Customerid = jObject.getString("id");
                registrationDO.CustomerMobile = jObject.getString("phone");
                errorMessage = "null";
                registrationDO.errorMessage = Message;
                arrRegistrationDO.add(registrationDO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getData() {
        if (arrRegistrationDO != null && arrRegistrationDO.size() > 0)
            return arrRegistrationDO;
        else
            return 0;
    }

    @Override
    public String getErrorData() {
        return errorMessage;
    }

}
