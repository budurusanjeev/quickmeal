package com.yitsol.quickmeal.parsers;

import com.yitsol.quickmeal.domain.AddressDO;

import org.json.JSONObject;

import java.util.ArrayList;

public class SaveAddressDetailHandler extends BaseHandler {
    ArrayList<AddressDO> arrSaveAddressDO;
    AddressDO addressDO;
    int opstatus;
    int key;
    String httpStatus = "", status = "";
    String errorMessage = "", Message = "", Error = "";
    StringBuilder stringBuilder;


    public SaveAddressDetailHandler(String inputStream) {
        arrSaveAddressDO = new ArrayList<AddressDO>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {
            JSONObject jObject = new JSONObject(inputStream);
            Message = jObject.getString("status");
            //key = jObject.getInt("key");
            if (Message.equalsIgnoreCase("success")) {

                JSONObject jsonObject = jObject.getJSONObject("data");
                {
                    stringBuilder = new StringBuilder();
                    addressDO = new AddressDO();
                    addressDO.customerId = jsonObject.getString("customerId");
                    addressDO.houseNo = jsonObject.getString("houseNumber");
                    addressDO.addressType = jsonObject.getString("addressType");
                    addressDO.address = jsonObject.getString("address");
                    addressDO.landmark = jsonObject.getString("landMark");
                    addressDO.city = jsonObject.getString("city");
                    addressDO.zipcode = jsonObject.getString("zipcode");


                    stringBuilder = new StringBuilder();
                    stringBuilder.append(addressDO.houseNo);
                    stringBuilder.append(",");
                    stringBuilder.append(addressDO.address);
                    stringBuilder.append(",");
                    stringBuilder.append(addressDO.landmark);
                    stringBuilder.append(",");
                    stringBuilder.append(addressDO.city);
                    stringBuilder.append(",");
                    stringBuilder.append(addressDO.zipcode);

                    addressDO.custDelAddress = stringBuilder.toString();

                    addressDO.errorMessage = "null";
                    errorMessage = "null";
                    arrSaveAddressDO.add(addressDO);
                }

            } else {
                JSONObject jsonObject = jObject.getJSONObject("error");
                Message = jsonObject.getString("name");
                addressDO = new AddressDO();
                addressDO.errorMessage = "Data not found";
                errorMessage = "";
                arrSaveAddressDO.add(addressDO);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getData() {
        if (arrSaveAddressDO != null && arrSaveAddressDO.size() > 0)
            return arrSaveAddressDO;
        else
            return 0;
    }

    @Override
    public String getErrorData() {
        return errorMessage;
    }

}
