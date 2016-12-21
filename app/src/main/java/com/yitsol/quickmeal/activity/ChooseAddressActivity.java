package com.yitsol.quickmeal.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.businessLayer.CommonBL;
import com.yitsol.quickmeal.businessLayer.DataListener;
import com.yitsol.quickmeal.domain.AddressDO;
import com.yitsol.quickmeal.domain.OrderNowDO;
import com.yitsol.quickmeal.utilities.AppConstants;
import com.yitsol.quickmeal.utilities.CartDatabase;
import com.yitsol.quickmeal.utilities.PreferenceUtils;
import com.yitsol.quickmeal.webaccess.Response;
import com.yitsol.quickmeal.webaccess.ServiceMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChooseAddressActivity extends BaseNew implements DataListener {


    public HashMap<String, String> Userdata;
    FrameLayout fl_chooseAddress;
    LinearLayout ll_address_spinner, ll_addAddress, ll_PayOnline, ll_CashOnDelivery, ll_addAddressLayout, add_address;
    Spinner spin_address;
    TextView tv_addressView, tv_totalAmt, tv_addressType;
    EditText edt_hno, edt_address, edt_landmark, edt_city, edt_zipcode;
    ArrayList<OrderNowDO> arrorderList;
    String pkgDesc, pkgName, houseNo, address, addressType = "", zipcode = "", landmark, city, customerId, addressId, custDeliveryAddress = "Address", Message;
    double pkg_price;
    boolean loginstatus;
    PreferenceUtils preferenceUtils;
    ArrayList<AddressDO> arrAddressList;
    Dialog useraddresdialog;
    AddressDO addressDO;
    OrderNowDO orderNowDO;
    int quantity;
    double grandtotal = 0;
    StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_addrss);
        mContext = ChooseAddressActivity.this;
        stringBuilder = new StringBuilder();
        initializeControls();
        arrorderList = CartDatabase.getAllCartList();
        if (arrorderList.size() > 0) {
            for (int i = 0; i < arrorderList.size(); i++) {
                OrderNowDO orderNowDO = arrorderList.get(i);
                pkgName = orderNowDO.pkg_Name;
                pkg_price = orderNowDO.pkg_Price;
                pkgDesc = orderNowDO.pkg_Desc;
                quantity = orderNowDO.quantity;
                setValues();
            }

        } else {
            showAlertDialog("No Order Found", "OK");
        }

        ll_CashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (custDeliveryAddress.equalsIgnoreCase("Address")) {
                    showAlertDialog("Please Enter Addres", "OK");
                } else {
                    Intent intent = new Intent(mContext, OrderCompleteActivity.class);
                    startActivity(intent);
                }
            }
        });


        ll_addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showdialogforaddressentry();
                tv_addressView.setText(custDeliveryAddress);

            }
        });

        List<String> addressType = new ArrayList<String>();
        addressType.add("Home");
        addressType.add("Office");
        addressType.add("Other");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, addressType);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_address.setAdapter(dataAdapter);
    }

    private void setValues() {

        tv_totalAmt.setText(String.valueOf(pkg_price));
        tv_addressView.setText(custDeliveryAddress);

    }

    private void showdialogforaddressentry() {

        useraddresdialog = new Dialog(mContext, R.style.Theme_Dialog_Translucent);

        useraddresdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        useraddresdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        useraddresdialog.setCanceledOnTouchOutside(false);

        useraddresdialog.setContentView(R.layout.popup_address);


        ll_addAddressLayout = (LinearLayout) useraddresdialog.findViewById(R.id.ll_addAddressLayout);

        tv_addressType = (TextView) useraddresdialog.findViewById(R.id.tv_addressType);

        edt_hno = (EditText) useraddresdialog.findViewById(R.id.edt_hno);
        edt_address = (EditText) useraddresdialog.findViewById(R.id.edt_address);
        add_address = (LinearLayout) useraddresdialog.findViewById(R.id.add_address);
        edt_landmark = (EditText) useraddresdialog.findViewById(R.id.edt_landmark);
        edt_city = (EditText) useraddresdialog.findViewById(R.id.edt_city);
        edt_zipcode = (EditText) useraddresdialog.findViewById(R.id.edt_zipcode);


        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
                getID();
                if (houseNo.length() > 0 && address.length() > 0 && landmark.length() > 0 && city.length() > 0 && zipcode.length() > 0) {
                    addressDO = new AddressDO();
                    addressDO.customerId = customerId;
                    addressDO.addressType = addressType;
                    addressDO.city = city;
                    addressDO.zipcode = zipcode;
                    addressDO.houseNo = houseNo;
                    addressDO.landmark = landmark;
                    addressDO.address = address;
                    showLoaderNew();
                    new CommonBL(mContext, ChooseAddressActivity.this).addAddress(customerId, addressType, houseNo, address, landmark, city, zipcode);

                    stringBuilder.append(houseNo);
                    stringBuilder.append(",");
                    stringBuilder.append(address);
                    stringBuilder.append(",");
                    stringBuilder.append(landmark);
                    stringBuilder.append(",");
                    stringBuilder.append(city);
                    stringBuilder.append(",");
                    stringBuilder.append(zipcode);

                    addressDO.custDelAddress = stringBuilder.toString();
                    custDeliveryAddress = stringBuilder.toString();
                    CartDatabase.addUserAddress(addressDO);
                    // updateAddress();
                    int i = CartDatabase.getAddressListCount();
                    // Toast.makeText(mContext, "Success" + i, Toast.LENGTH_SHORT).show();
                    setValues();
                    useraddresdialog.dismiss();


                } else {
                    showAlertDialog("Please Fill All Fields", "OK");
                }
            }
        });

        useraddresdialog.show();
    }

    public void getID() {
        if (loginstatus) {
            Userdata = preferenceUtils.getUserDetails();
            customerId = Userdata.get(AppConstants.KEY_CUSTOMER_ID);
        }
    }

    private void getValues() {
        houseNo = edt_hno.getText().toString();
        address = edt_address.getText().toString();
        landmark = edt_landmark.getText().toString();
        city = edt_city.getText().toString();
        zipcode = edt_zipcode.getText().toString();
    }

    private void initializeControls() {

        spin_address = (Spinner) findViewById(R.id.spin_address);

        fl_chooseAddress = (FrameLayout) findViewById(R.id.fl_chooseAddress);

        tv_addressView = (TextView) findViewById(R.id.tv_addressView);
        tv_totalAmt = (TextView) findViewById(R.id.tv_totalAmt);

        ll_address_spinner = (LinearLayout) findViewById(R.id.ll_address_spinner);
        ll_addAddress = (LinearLayout) findViewById(R.id.ll_addAddress);
        ll_PayOnline = (LinearLayout) findViewById(R.id.ll_PayOnline);
        ll_CashOnDelivery = (LinearLayout) findViewById(R.id.ll_CashOnDelivery);

    }

    @Override
    protected void onResume() {

        super.onResume();
        tv_addressView.setText(custDeliveryAddress);
    }

    @Override
    public void dataRetreived(Response data) {

        hideloader();

        if (data != null && data.method == ServiceMethods.WS_SAVE_ADDRESS) {

            if (!data.isError) {
                arrAddressList = (ArrayList<AddressDO>) data.data;
                if (arrAddressList != null && arrAddressList.size() > 0) {
                    for (int i = 0; i < arrAddressList.size(); i++) {
                        Message = arrAddressList.get(i).errorMessage;
                    }

                    if (Message.equalsIgnoreCase("success")) {
                        useraddresdialog.dismiss();
                        Toast.makeText(mContext, "Address stored", Toast.LENGTH_SHORT).show();
                    } else {
                        useraddresdialog.dismiss();
                        Toast.makeText(mContext, "Address not stored", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }


    }
}
