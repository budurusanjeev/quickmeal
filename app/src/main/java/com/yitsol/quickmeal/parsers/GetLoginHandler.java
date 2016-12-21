package com.yitsol.quickmeal.parsers;


import com.yitsol.quickmeal.domain.LoginDO;

import org.json.JSONObject;

import java.util.ArrayList;

public class GetLoginHandler extends BaseHandler {


    ArrayList<LoginDO> arrLoginDO;
    LoginDO loginDO;
    int opstatus;
    String httpStatus = "";
    String errorMessage = "", Message = "", Error = "";


    public GetLoginHandler(String inputStream) {
        arrLoginDO = new ArrayList<LoginDO>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {

            JSONObject jObject = new JSONObject(inputStream);
            //Message = jObject.getString("status");
            if (jObject.has("error")) {
                JSONObject jsonObject = jObject.getJSONObject("error");
                Message = jsonObject.getString("status");
                loginDO = new LoginDO();
                loginDO.errorMessage = "Email id/ Mobile number or password incorrect";
                errorMessage = "null";
                arrLoginDO.add(loginDO);
            } else {
                JSONObject jsonObject = jObject.getJSONObject("data");
                JSONObject dataObj = jsonObject.getJSONObject("customer");
                loginDO = new LoginDO();
                loginDO.CustomerName = dataObj.getString("name");
                loginDO.CustomerEmail = dataObj.getString("email");
                loginDO.CustomerMobile = dataObj.getString("phone");
                loginDO.Customerid = dataObj.getString("id");

                //loginDO.walletAmount = jObject.getDouble("walletAmount");

                errorMessage = "null";
                loginDO.loginMessage = Message;
                arrLoginDO.add(loginDO);
            }
           /* else
            {
                JSONObject jsonObject = jObject.getJSONObject("error");


                loginDO = new LoginDO();
                loginDO.loginMessage=Message;
                loginDO.CustomerKey=jObject.getString("key");
                errorMessage="null";
                arrLoginDO.add(loginDO);
            }
*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getData() {
        if (arrLoginDO != null && arrLoginDO.size() > 0)
            return arrLoginDO;
        else
            return 0;
    }

    @Override
    public String getErrorData() {
        return errorMessage;
    }


}
