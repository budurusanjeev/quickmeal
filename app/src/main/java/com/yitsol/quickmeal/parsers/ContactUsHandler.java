package com.yitsol.quickmeal.parsers;

import com.yitsol.quickmeal.domain.ContactUsDO;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 26-05-2016.
 */
public class ContactUsHandler extends BaseHandler {

    ArrayList<ContactUsDO> arrContactUs;
    ContactUsDO contactUsDO;
    String errorMessage = "", Message = "", Error = "";

    public ContactUsHandler(String inputStream) {
        super();
        arrContactUs = new ArrayList<ContactUsDO>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {
            JSONObject jObject = new JSONObject(inputStream);
            Message = jObject.getString("status");

            if (Message.equalsIgnoreCase("success")) {

                JSONObject dataObject = jObject.getJSONObject("data");
                contactUsDO = new ContactUsDO();
                contactUsDO.custName = dataObject.getString("name");
                contactUsDO.custEmail = dataObject.getString("email");
                contactUsDO.custMobile = dataObject.getString("phone");
                contactUsDO.custMsg = dataObject.getString("message");
                arrContactUs.add(contactUsDO);
                errorMessage = "null";
                contactUsDO.message = Message;

            } else {
                JSONObject jsonObject = jObject.getJSONObject("error");
                Message = jsonObject.getString("name");
                contactUsDO = new ContactUsDO();
                contactUsDO.message = "Data not found";
                errorMessage = "";
                arrContactUs.add(contactUsDO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getData() {

        if (arrContactUs != null && arrContactUs.size() > 0)
            return arrContactUs;
        else
            return 0;
    }

    @Override
    public String getErrorData() {

        return errorMessage;
    }
}
