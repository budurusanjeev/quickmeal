package com.yitsol.quickmeal.parsers;

import com.yitsol.quickmeal.domain.AddressDO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetAddressDetailHandler extends BaseHandler {
    ArrayList<AddressDO> arrAddressDO;
    AddressDO addressDO;
    int opstatus;
    int key;
    String httpStatus = "", status = "";
    String errorMessage = "", Message = "", Error = "";
    StringBuilder stringBuilder;


    public GetAddressDetailHandler(String inputStream) {
        arrAddressDO = new ArrayList<AddressDO>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {
            JSONObject jObject = new JSONObject(inputStream);
            status = jObject.getString("status");
            Message = jObject.getString("message");

            if (status.equalsIgnoreCase("success")) {
                JSONArray jsonArray = jObject.getJSONArray("data");
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        {
                            stringBuilder = new StringBuilder();
                            addressDO = new AddressDO();
                            addressDO.addressId = jsonObject.getString("id");
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
                        }
                    }
                    arrAddressDO.add(addressDO);
                } else {
                    errorMessage = "no address";
                }
            } else {
                errorMessage = "error";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getData() {
        if (arrAddressDO != null && arrAddressDO.size() > 0)
            return arrAddressDO;
        else
            return 0;
    }

    @Override
    public String getErrorData() {
        return errorMessage;
    }

}
