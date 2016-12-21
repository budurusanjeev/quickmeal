package com.yitsol.quickmeal.parsers;

import com.yitsol.quickmeal.domain.ListOfMealPlanDO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 26-05-2016.
 */
public class GetSubItemListDataHandler extends BaseHandler {

    ArrayList<ListOfMealPlanDO> arrListOfSubItems;
    ListOfMealPlanDO listOfMealPlanDO;
    String errorMessage = "", Message = "", Error = "";

    public GetSubItemListDataHandler(String inputStream) {
        super();
        arrListOfSubItems = new ArrayList<ListOfMealPlanDO>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {

            JSONObject jObject = new JSONObject(inputStream);
            Message = jObject.getString("status");

            if (Message.equalsIgnoreCase("success")) {
                JSONArray dataArray = jObject.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject subItemsObj = dataArray.getJSONObject(i);
                    listOfMealPlanDO = new ListOfMealPlanDO();
                    listOfMealPlanDO.sbItemName = subItemsObj.getString("name");
                    listOfMealPlanDO.sbItemCategoryId = subItemsObj.getString("id");
                    listOfMealPlanDO.sbItemPrice = subItemsObj.getDouble("price");
                    arrListOfSubItems.add(listOfMealPlanDO);
                }
                errorMessage = "null";
                listOfMealPlanDO.Message = Message;

            } else {
                JSONObject jsonObject = jObject.getJSONObject("error");
                Message = jsonObject.getString("name");
                listOfMealPlanDO = new ListOfMealPlanDO();
                listOfMealPlanDO.Message = "Email id/ Mobile number or password incorrect";
                errorMessage = "";
                arrListOfSubItems.add(listOfMealPlanDO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getData() {

        if (arrListOfSubItems != null && arrListOfSubItems.size() > 0)
            return arrListOfSubItems;
        else
            return 0;
    }

    @Override
    public String getErrorData() {

        return errorMessage;
    }
}
