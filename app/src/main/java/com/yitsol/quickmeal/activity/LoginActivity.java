package com.yitsol.quickmeal.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.businessLayer.CommonBL;
import com.yitsol.quickmeal.businessLayer.DataListener;
import com.yitsol.quickmeal.domain.LoginDO;
import com.yitsol.quickmeal.domain.RegistrationDO;
import com.yitsol.quickmeal.utilities.FontType;
import com.yitsol.quickmeal.utilities.PreferenceUtils;
import com.yitsol.quickmeal.webaccess.Response;
import com.yitsol.quickmeal.webaccess.ServiceMethods;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseNew implements DataListener {
    RelativeLayout rl_signup, rl_login;
    LinearLayout login_layout, registration_layout, ll_fb_login;
    LinearLayout ll_addCart_subscribe, ll_addCart_oneDay;
    EditText edt_userid, edt_pwd, edt_userName, edt_email, edt_mobile, edt_userpwd, edt_cnfrmpwd;
    Button btn_login, btn_signup;
    int pincode, addressId, addressKey, key;
    Context mContext;
    char mDigit;
    Typeface custom_font;
    FontType fontType;
    double walletAmount = 0;
    String LoginId, UserId, UserLoginPwd, UserName, UserEmailId, EmailId, MobileNum, UserPass, UserCnfmPass, cityName, houseNo, address, custid, pinSelect;
    String source = "CUSTOM", role = "USER", Message, Status, userId_forget;
    PreferenceUtils preferenceUtils;
    String Error = "";
    Dialog useraddresdialog;
    Button btn_forget_password;
    EditText edt_userId_forget;
    TextView txt_login, txt_signup, btn_forgot, tv_fb_txt, tv_or;
    private Pattern EMAIL_PATTERN = Patterns.EMAIL_ADDRESS;
    private Pattern MOBILE_PATTEN = Pattern.compile("^[789]\\d{9}$");
    private ArrayList<RegistrationDO> arrRegistration;
    private ArrayList<LoginDO> arrLogin;
    private String login_userid;
    private String loginusername;
    private String login_user_email;

    public static boolean isValidEmail(String string) {
        final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );
        Matcher matcher = EMAIL_ADDRESS_PATTERN.matcher(string);
        boolean value = matcher.matches();
        return value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);
        mContext = LoginActivity.this;

        initializeControls();
        //custom_font = Typeface.createFromAsset(getAssets(),"fonts/futubk_.ttf");
        ViewGroup root = (ViewGroup) findViewById(R.id.login_root);

        fontType = new FontType(mContext, root);
        setFontToAll();
        preferenceUtils = new PreferenceUtils(mContext);

        registration_layout.setVisibility(View.GONE);


        rl_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login_layout.setVisibility(View.GONE);
                //rl_signup.setBackgroundColor(getResources().getColor(R.color.App_blue));
                txt_signup.setTextColor(getResources().getColor(R.color.App_Orange));
                //rl_login.setBackgroundColor(getResources().getColor(R.color.App_black_40));
                txt_login.setTextColor(Color.WHITE);
                registration_layout.setVisibility(View.VISIBLE);

            }
        });

        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_layout.setVisibility(View.VISIBLE);
                //rl_login.setBackgroundColor(getResources().getColor(R.color.App_blue));
                txt_login.setTextColor(getResources().getColor(R.color.App_Orange));
                //rl_signup.setBackgroundColor(getResources().getColor(R.color.App_black_40));
                txt_signup.setTextColor(Color.WHITE);
                registration_layout.setVisibility(View.GONE);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTextLogin();

                if (LoginId.length() > 0) {
                    //  validate_email_addres_methodand_key_setter(LoginId);
                    if (validate_email_addres_methodand_key_setter(LoginId) == 1) {
                        //===================Email_Id============================
                        if (LoginId.length() > 0) {
                            if (isValidEmail(LoginId)) {
                                if (UserLoginPwd.length() > 0) {
                                    showLoaderNew();
                                    if (new CommonBL(mContext, LoginActivity.this).checkLoginByMail(LoginId, UserLoginPwd))
                                        ;
                                } else {
                                    showAlertDialog("Please enter Password .", "Ok");
                                }
                            } else {
                                showAlertDialog("Please enter valid Email", "Ok");
                            }
                        } else {
                            showAlertDialog("Please enter Email", "Ok");
                        }

                    } else if (validate_email_addres_methodand_key_setter(LoginId) == 2) {
                        if (LoginId.length() > 0 && UserLoginPwd.length() > 0) {
                            mDigit = LoginId.charAt(0);
                            if (LoginId.length() == 10) {
                                if (mDigit == '7' || mDigit == '8' || mDigit == '9') {
                                    showLoaderNew();
                                    new CommonBL(mContext, LoginActivity.this).checkLoginByMobile(LoginId, UserLoginPwd);
                                } else {
                                    showAlertDialog("Enter a 10 digit mobile number", "Ok");
                                }
                            } else {
                                showAlertDialog("Enter Valid Mobile number", "Ok");
                            }
                        } else {
                            showAlertDialog("Enter Mobile number and Password", "Ok");
                        }
                    } else {
                        showAlertDialog("You have entered wrong UserId or Password", "Ok");
                    }
                }
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextSignup();

                if (UserName.length() > 0 && MobileNum.length() > 0 && EmailId.length() > 0 && UserPass.length() > 0 && UserCnfmPass.length() > 0) {
                    if (isValidEmail(EmailId)) {
                        mDigit = MobileNum.charAt(0);
                        if (MobileNum.length() == 10) {
                            if (mDigit == '7' || mDigit == '8' || mDigit == '9') {
                                if (isAlphaNumeric(UserPass)) {
                                    if (UserPass.equals(UserCnfmPass)) {
                                        showLoaderNew();
                                        new CommonBL(mContext, LoginActivity.this).doRegistration(UserName, EmailId, MobileNum, UserPass);
                                        // Toast.makeText(mContext, "Everything ok", Toast.LENGTH_SHORT).show();
                                    } else {
                                        showAlertDialog("Passwords do not match", "Ok");
                                    }
                                } else {
                                    showAlertDialog("Enter minimum 4 character password", "Ok");
                                }
                            } else {
                                showAlertDialog("Enter a valid mobile number", "Ok");
                            }
                        } else {
                            showAlertDialog("Enter a valid mobile number", "Ok");
                        }
                    } else {
                        showAlertDialog("Enter a valid email", "Ok");
                    }

                } else {
                    showAlertDialog("All fields are mandatory", "Ok");
                }

            }
        });

        btn_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogforForgetPass();
            }
        });
    }

    private void setFontToAll() {
        edt_userid.setTypeface(custom_font);
        edt_pwd.setTypeface(custom_font);
        edt_userName.setTypeface(custom_font);
        edt_email.setTypeface(custom_font);
        edt_mobile.setTypeface(custom_font);
        edt_userpwd.setTypeface(custom_font);
        edt_cnfrmpwd.setTypeface(custom_font);
        btn_login.setTypeface(custom_font);
        btn_signup.setTypeface(custom_font);
        txt_login.setTypeface(custom_font);
        txt_signup.setTypeface(custom_font);
        btn_forgot.setTypeface(custom_font);
    }

    public void clearEditText() {

        edt_userName.setText("");
        edt_email.setText("");
        edt_mobile.setText("");
        edt_userpwd.setText("");
        edt_cnfrmpwd.setText("");

    }

    private void getTextLogin() {
        LoginId = edt_userid.getText().toString();
        UserLoginPwd = edt_pwd.getText().toString();
    }

    private void getTextSignup() {
        UserName = edt_userName.getText().toString();
        EmailId = edt_email.getText().toString();
        MobileNum = edt_mobile.getText().toString();
        UserPass = edt_userpwd.getText().toString();
        UserCnfmPass = edt_cnfrmpwd.getText().toString();
    }

    public boolean isAlphaNumeric(String pwd) {
        //String pattern = "^[a-zA-Z0-9]*$";
        //String pattern ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        /*if (pwd.matches(pattern))
        {
			return true;
		}
		return false;*/

        return pwd.length() >= 4;
    }


    private void showdialogforForgetPass() {

        useraddresdialog = new Dialog(mContext, R.style.Theme_Dialog_Translucent);

        useraddresdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        useraddresdialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(
                        Color.TRANSPARENT));

        useraddresdialog.setCanceledOnTouchOutside(false);

        useraddresdialog.setContentView(R.layout.forget_password);

        edt_userId_forget = (EditText) useraddresdialog.findViewById(R.id.userId_forget);
        btn_forget_password = (Button) useraddresdialog.findViewById(R.id.btn_forget_password);

        // userId_forget= edt_userId_forget.getText().toString();

        btn_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId_forget = edt_userId_forget.getText().toString();
/*
                if (userId_forget.length() > 0) {
                    showLoaderNew();
                    new CommonBL(mContext, LoginActivity.this).forgetPassword(userId_forget);
                    showAlertDialog("Password sent", "Ok");
                }
                else
                {
                    showAlertDialog("Please enter register mobile number", "Ok");
                }*/

                if (userId_forget.length() > 0) {
                    mDigit = userId_forget.charAt(0);
                    if (userId_forget.length() == 10) {
                        if (mDigit == '7' || mDigit == '8' || mDigit == '9') {
                            showLoaderNew();
                            //       new CommonBL(mContext, LoginActivity.this).forgetPassword(userId_forget);
                            //  showAlertDialog("Password sent", "Ok");
                        } else {
                            showAlertDialog("Enter a 10 digit mobile number", "Ok");
                        }
                    } else {
                        showAlertDialog("Enter Mobile number", "Ok");
                    }
                }

            }
        });
        useraddresdialog.show();
    }


    private void initializeControls() {

        edt_userid = (EditText) findViewById(R.id.edt_userid);
        edt_pwd = (EditText) findViewById(R.id.edt_pwd);
        edt_userName = (EditText) findViewById(R.id.edt_userName);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_mobile = (EditText) findViewById(R.id.edt_mobile);
        edt_userpwd = (EditText) findViewById(R.id.edt_userpwd);
        edt_cnfrmpwd = (EditText) findViewById(R.id.edt_cnfrmpwd);

        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_forgot = (TextView) findViewById(R.id.btn_forgot);
        btn_login = (Button) findViewById(R.id.btn_login);
        rl_signup = (RelativeLayout) findViewById(R.id.rl_signup);
        rl_login = (RelativeLayout) findViewById(R.id.rl_login);
        login_layout = (LinearLayout) findViewById(R.id.login_layout);
        registration_layout = (LinearLayout) findViewById(R.id.registration_layout);

        //ll_pkgType = (LinearLayout) findViewById(R.id.ll_pkgType);
        ll_addCart_subscribe = (LinearLayout) findViewById(R.id.ll_addCart_subscribe);
        ll_addCart_oneDay = (LinearLayout) findViewById(R.id.ll_addCart_oneDay);

        txt_login = (TextView) findViewById(R.id.txt_login);
        txt_signup = (TextView) findViewById(R.id.txt_signup);
    }


    private int validate_email_addres_methodand_key_setter(String emailaddress) {


        Matcher emailmatcher = EMAIL_PATTERN.matcher(emailaddress);
        Matcher mobilematcher = MOBILE_PATTEN.matcher(emailaddress);
        if (emailmatcher.matches()) {
            return 1;

        } else if (mobilematcher.matches()) {
            return 2;
        }

        return 0;

    }


    @Override
    @SuppressWarnings("unchecked")
    public void dataRetreived(Response data) {


        hideloader();
        if (data != null && data.method == ServiceMethods.WS_DO_REGISTRATION) {
            if (!data.isError) {
                arrRegistration = (ArrayList<RegistrationDO>) data.data;
                if (arrRegistration != null && arrRegistration.size() > 0) {
                    for (int i = 0; i < arrRegistration.size(); i++) {
                        Message = arrRegistration.get(i).errorMessage;
                    }
                    if (Message.equalsIgnoreCase("422")) {
                        showAlertDialog("Mobile Number or Email Id already registered", "Ok");
                    } else {
                        for (int i = 0; i < arrRegistration.size(); i++) {
                            UserId = arrRegistration.get(i).Customerid;
                            UserName = arrRegistration.get(i).CustomerName;
                            UserEmailId = arrRegistration.get(i).CustomerEmail;
                            MobileNum = arrRegistration.get(i).CustomerMobile;
                            showAlertDialog("Registered Successfully", "Ok");
                            //clearEditText();
                            Intent intent = new Intent(mContext, Dashboard.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        } else if (data != null && data.method == ServiceMethods.WS_CHECK_LOGIN) {
            if (!data.isError) {
                arrLogin = (ArrayList<LoginDO>) data.data;
                if (arrLogin != null && arrLogin.size() > 0) {

                    for (int i = 0; i < arrLogin.size(); i++) {

                        Message = arrLogin.get(i).loginMessage;
                    }

                    if (Message.equalsIgnoreCase("422")) {
                        showAlertDialog("Mobile Number or Email Id already registered", "Ok");
                    } else {

                        for (int i = 0; i < arrLogin.size(); i++) {
                            UserId = arrLogin.get(i).Customerid;
                            UserName = arrLogin.get(i).CustomerName;
                            UserEmailId = arrLogin.get(i).CustomerEmail;
                            MobileNum = arrLogin.get(i).CustomerMobile;
                            preferenceUtils.createLoginSession(UserId, UserName, UserEmailId, MobileNum, String.valueOf(walletAmount));
                            showAlertDialog("Login Successfully", "Ok");
                            //clearEditText();
                            Intent intent = new Intent(mContext, Dashboard.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        }
    }

    private void gotocheckemailexistedornot() {


    }

}
