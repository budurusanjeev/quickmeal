package com.yitsol.quickmeal.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.businessLayer.CommonBL;
import com.yitsol.quickmeal.businessLayer.DataListener;
import com.yitsol.quickmeal.domain.ContactUsDO;
import com.yitsol.quickmeal.webaccess.Response;
import com.yitsol.quickmeal.webaccess.ServiceMethods;

import java.util.ArrayList;

public class ContactUsActivity extends BaseNew implements DataListener {
    Context mContext;

    EditText edt_contactus_name, edt_contactus_email, edt_contactus_msg, edt_contactus_phone;
    Button btn_contactus_send, btn_contactus_ok;
    String subject, text;
    String custName, custEmail, custPhone, custMsg;
    String Message;
    int key;
    private ArrayList<ContactUsDO> arrContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("hahahh");
        setContentView(R.layout.activity_contact_us);
        mContext = ContactUsActivity.this;
        initialize();

        btn_contactus_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
                //generatemailbodycontent();

                if (custName.length() > 0 && custEmail.length() > 0 && custPhone.length() > 0 && custMsg.length() > 0) {
                    showLoaderNew();
                    new CommonBL(mContext, ContactUsActivity.this).contactUs(custName, custEmail, custPhone, custMsg);
                    // Toast.makeText(mContext, "Everything ok", Toast.LENGTH_SHORT).show();
                } else {
                    showAlertDialog("Please fill mandatory fields", "Ok");
                }
            }
        });

        btn_contactus_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Dashboard.class);
                startActivity(intent);
            }
        });

    }
/*String name, String email, String phone, String message*/

    private String generatemailbodycontent() {

        //hideSoftKeyboard(edt_mailbodycontent);

        StringBuilder mailcontentsb = new StringBuilder();

        mailcontentsb.append("Hi,");
        mailcontentsb.append(System.getProperty("line.separator"));
        mailcontentsb.append(System.getProperty("line.separator"));
        mailcontentsb.append(custMsg.trim());
        mailcontentsb.append(System.getProperty("line.separator"));
        mailcontentsb.append(System.getProperty("line.separator"));
        mailcontentsb.append("Thanks");
        mailcontentsb.append(System.getProperty("line.separator"));
        mailcontentsb.append(custName.trim());
        mailcontentsb.append(System.getProperty("line.separator"));
        //mailcontentsb.append(custEmail.trim());

        return text = mailcontentsb.toString();
    }

    public void getValues() {
        custName = edt_contactus_name.getText().toString();
        custEmail = edt_contactus_email.getText().toString();
        custPhone = edt_contactus_phone.getText().toString();
        custMsg = edt_contactus_msg.getText().toString();
    }

    public void initialize() {
        edt_contactus_name = (EditText) findViewById(R.id.edt_contactus_name);
        edt_contactus_email = (EditText) findViewById(R.id.edt_contactus_email);
        edt_contactus_msg = (EditText) findViewById(R.id.edt_contactus_msg);
        edt_contactus_phone = (EditText) findViewById(R.id.edt_contactus_phone);
        btn_contactus_ok = (Button) findViewById(R.id.btn_contactus_ok);
        btn_contactus_send = (Button) findViewById(R.id.btn_contactus_send);
    }

    @Override
    public void dataRetreived(Response data) {
        hideloader();
        if (data != null && data.method == ServiceMethods.WS_CONTACT_US) {
            if (!data.isError) {
                arrContactUs = (ArrayList<ContactUsDO>) data.data;
                if (arrContactUs != null && arrContactUs.size() > 0) {
                    for (int i = 0; i < arrContactUs.size(); i++) {
                        Message = arrContactUs.get(i).errorMessage;
                        // key = arrContactUs.get(i).key;
                    }
                    if (Message.equalsIgnoreCase("success")) {

                        showAlertDialog("Your message send successfully", "Ok");

                    } else {
                        showAlertDialog("Your message is not sent", "Ok");
                    }
                }
            }
        }
    }

}
