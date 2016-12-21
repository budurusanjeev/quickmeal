package com.yitsol.quickmeal.parsers;

import com.yitsol.quickmeal.domain.ListOfMealPlanDO;
import com.yitsol.quickmeal.domain.OrderNowDO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OneDayOrderHandler extends BaseHandler {


    ArrayList<OrderNowDO> arrOrderNowDOList;
    ArrayList<ListOfMealPlanDO> arrListofMealPlan;
    OrderNowDO orderNowDO;
    ListOfMealPlanDO listOfMealPlanDO;
    int opstatus;
    String httpStatus = "", pkg_name, categoryId;
    String errorMessage = "", Message = "", Error = "";


    public OneDayOrderHandler(String inputStream) {
        arrOrderNowDOList = new ArrayList<OrderNowDO>();
        getInputStream(inputStream);
    }

    private void getInputStream(String inputStream) {
        try {

            JSONArray mealPlanArray = new JSONArray(inputStream);
            for (int i = 0; i < mealPlanArray.length(); i++) {

                JSONObject jsonObject = mealPlanArray.getJSONObject(i);
                if (jsonObject.has("error")) {
                    JSONObject jObj = jsonObject.getJSONObject("error");
                    Message = jObj.getString("status");
                    orderNowDO = new OrderNowDO();
                    orderNowDO.errorMessage = "NO Internet";
                    errorMessage = "null";
                    arrOrderNowDOList.add(orderNowDO);
                } else if (jsonObject.has("name")) {
                    pkg_name = jsonObject.getString("name");
                    //  orderNowDO.pkg_Name = jsonObject.getString("name");
                    if (pkg_name.equals("Silver")) {
                        orderNowDO = new OrderNowDO();
                        orderNowDO.pkgTpye_G_S_D = "silver";
                        orderNowDO.pkg_Name = jsonObject.getString("desc");
                        orderNowDO.pkgId = jsonObject.getString("id");
                        orderNowDO.pkg_Image = jsonObject.getString("imgURL");
                        orderNowDO.pkg_Price = jsonObject.getDouble("price");
                        orderNowDO.oneWeekPrice = jsonObject.getDouble("oneWeekPrice");
                        orderNowDO.oneMonthPrice = jsonObject.getDouble("oneMonthPrice");
                        orderNowDO.threeMonthPrice = jsonObject.getDouble("threeMonthPrice");
                        orderNowDO.sixMonthPrice = jsonObject.getDouble("sixMonthPrice");
                        orderNowDO.oneYearPrice = jsonObject.getDouble("oneYearPrice");

                        //===================Categories List================================
                        JSONArray categoryArray = jsonObject.getJSONArray("categories");
                        for (int j = 0; j < categoryArray.length(); j++) {
                            categoryId = (String) categoryArray.get(j);
                            orderNowDO.categories.add(categoryId);
                        }
                        JSONArray food_Item_Array = jsonObject.getJSONArray("categoriesList");
                        for (int k = 0; k < categoryArray.length(); k++) {
                            JSONObject jObj = food_Item_Array.getJSONObject(k);
                            //==================Category List with Item Names===================

                            ListOfMealPlanDO silver_pkg_desc = new ListOfMealPlanDO();

                            silver_pkg_desc.food_ItemName = jObj.getString("name");
                            silver_pkg_desc.food_ItemId = jObj.getString("id");
                            orderNowDO.listOfMealPlanDOs.add(silver_pkg_desc);
                        }
                        arrOrderNowDOList.add(orderNowDO);

                    }

                    //===============================Gold============================================

                    if (pkg_name.equals("Gold")) {
                        orderNowDO = new OrderNowDO();
                        orderNowDO.pkgTpye_G_S_D = "gold";
                        orderNowDO.pkg_Name = jsonObject.getString("desc");
                        orderNowDO.pkgId = jsonObject.getString("id");
                        orderNowDO.pkg_Image = jsonObject.getString("imgURL");
                        orderNowDO.pkg_Price = jsonObject.getDouble("price");
                        orderNowDO.oneWeekPrice = jsonObject.getDouble("oneWeekPrice");
                        orderNowDO.oneMonthPrice = jsonObject.getDouble("oneMonthPrice");
                        orderNowDO.threeMonthPrice = jsonObject.getDouble("threeMonthPrice");
                        orderNowDO.sixMonthPrice = jsonObject.getDouble("sixMonthPrice");
                        orderNowDO.oneYearPrice = jsonObject.getDouble("oneYearPrice");
                        //===================Categories List================================
                        JSONArray categoryArray = jsonObject.getJSONArray("categories");
                        for (int j = 0; j < categoryArray.length(); j++) {
                            categoryId = categoryArray.getString(j);
                            orderNowDO.categories.add(categoryId);
                        }
                        JSONArray food_Item_Array = jsonObject.getJSONArray("categoriesList");
                        for (int k = 0; k < categoryArray.length(); k++) {
                            JSONObject jObj = food_Item_Array.getJSONObject(k);
                            //==================Category List with Item Names===================

                            ListOfMealPlanDO silver_pkg_desc = new ListOfMealPlanDO();

                            silver_pkg_desc.food_ItemName = jObj.getString("name");
                            silver_pkg_desc.food_ItemId = jObj.getString("id");
                            orderNowDO.listOfMealPlanDOs.add(silver_pkg_desc);
                        }
                        arrOrderNowDOList.add(orderNowDO);

                    }


                    //===============================Diamond========================================

                    if (pkg_name.equals("Diamond")) {
                        orderNowDO = new OrderNowDO();
                        orderNowDO.pkgTpye_G_S_D = "diamond";
                        orderNowDO.pkg_Name = jsonObject.getString("desc");
                        orderNowDO.pkgId = jsonObject.getString("id");
                        orderNowDO.pkg_Image = jsonObject.getString("imgURL");
                        orderNowDO.pkg_Price = jsonObject.getDouble("price");
                        orderNowDO.oneWeekPrice = jsonObject.getDouble("oneWeekPrice");
                        orderNowDO.oneMonthPrice = jsonObject.getDouble("oneMonthPrice");
                        orderNowDO.threeMonthPrice = jsonObject.getDouble("threeMonthPrice");
                        orderNowDO.sixMonthPrice = jsonObject.getDouble("sixMonthPrice");
                        orderNowDO.oneYearPrice = jsonObject.getDouble("oneYearPrice");
                        //===================Categories List================================
                        JSONArray categoryArray = jsonObject.getJSONArray("categories");
                        for (int j = 0; j < categoryArray.length(); j++) {
                            categoryId = categoryArray.getString(j);
                            orderNowDO.categories.add(categoryId);
                        }
                        JSONArray food_Item_Array = jsonObject.getJSONArray("categoriesList");
                        for (int k = 0; k < categoryArray.length(); k++) {
                            JSONObject jObj = food_Item_Array.getJSONObject(k);
                            //==================Category List with Item Names===================

                            ListOfMealPlanDO silver_pkg_desc = new ListOfMealPlanDO();

                            silver_pkg_desc.food_ItemName = jObj.getString("name");
                            silver_pkg_desc.food_ItemId = jObj.getString("id");
                            orderNowDO.listOfMealPlanDOs.add(silver_pkg_desc);


                        }
                        errorMessage = "null";
                        arrOrderNowDOList.add(orderNowDO);

                    }
                } else {
                    errorMessage = "null";
                    arrOrderNowDOList.add(orderNowDO);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getData() {
        if (arrOrderNowDOList != null && arrOrderNowDOList.size() > 0)
            return arrOrderNowDOList;
        else
            return 0;
    }

    @Override
    public String getErrorData() {
        return errorMessage;
    }

}
