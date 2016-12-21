package com.yitsol.quickmeal.parsers;

import com.yitsol.quickmeal.domain.UserProfileDO;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 26-05-2016.
 */
public class GetUserProfileHandler extends BaseHandler {

    ArrayList<UserProfileDO> arrListUserDetails;
    UserProfileDO userProfileDO;
    String errorMessage = "", Message = "", Error = "";

    public GetUserProfileHandler(String inputStream) {
        super();
        arrListUserDetails = new ArrayList<UserProfileDO>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {
            JSONObject jObject = new JSONObject(inputStream);
            Message = jObject.getString("status");

            if (Message.equalsIgnoreCase("success")) {

                JSONObject dataObject = jObject.getJSONObject("data");
                userProfileDO = new UserProfileDO();
                userProfileDO.name = dataObject.getString("name");
                userProfileDO.phone = dataObject.getString("phone");
                userProfileDO.email = dataObject.getString("email");
                arrListUserDetails.add(userProfileDO);
                errorMessage = "null";
                userProfileDO.statusMessage = Message;

            } else {
                JSONObject jsonObject = jObject.getJSONObject("error");
                Message = jsonObject.getString("name");
                userProfileDO = new UserProfileDO();
                userProfileDO.statusMessage = "Data not found";
                errorMessage = "";
                arrListUserDetails.add(userProfileDO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getData() {

        if (arrListUserDetails != null && arrListUserDetails.size() > 0)
            return arrListUserDetails;
        else
            return 0;
    }

    @Override
    public String getErrorData() {

        return errorMessage;
    }
}
