package org.code5.code5push.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import org.code5.code5push.R;
import org.code5.code5push.api.rest.ListCallBack;
import org.code5.code5push.api.rest.RestCallBack;
import org.code5.code5push.api.services.UserService;
import org.code5.code5push.listeners.OnCompleteListener;
import org.code5.code5push.widgets.CustomSpinner;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by sony on 8/26/2017.
 */

public class SignUpFragment extends Fragment
{

    private static  SignUpFragment mInstance = null;
    private static OnCompleteListener onCompleteListener;
    public static SignUpFragment newInstance(OnCompleteListener listener)
    {
        if(mInstance==null)
        {
            mInstance = new SignUpFragment();
            onCompleteListener =listener;


        }
        return mInstance;
    }

    String[] states;
    private EditText etName;
    private EditText etEmail;
    private EditText etMobile;
    private EditText etAccount;
    private CheckBox ms;
    private String name;
    private String email;
    private String mobileNo;
    private String accountNo;
    int state ,discom,powerFeeder;
    private CustomSpinner stateSpinner,discomSpinner,powerFeederSpinner;
   // private CircularProgressButton signUp;
    private TextInputLayout tlName,tlEmail,tlMobile,tlAccount;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.form,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tlName =(TextInputLayout)view.findViewById(R.id.tlName) ;
        tlEmail =(TextInputLayout)view.findViewById(R.id.tlEmail) ;
        tlMobile =(TextInputLayout)view.findViewById(R.id.tlMobile) ;
        tlAccount =(TextInputLayout)view.findViewById(R.id.tlAccount) ;
        etName=(EditText)view.findViewById(R.id.et_firstname);
        etName.addTextChangedListener(new TextValidator(tlName) {
            @Override
            public void validate(TextInputLayout textView, String text) {
                if(text.isEmpty()){
                    tlName.setError("Name is required");
                    tlName.setErrorEnabled(true);}
                else {
                    tlName.setErrorEnabled(false);
                     }


                  }
        });
        etEmail =(EditText)view.findViewById(R.id.etEmail);
        etEmail.addTextChangedListener(new TextValidator(tlEmail) {
            @Override
            public void validate(TextInputLayout textView, String text) {
                EmailValidator validator =new EmailValidator();
                if(!validator.validate(text))
             {
                 tlEmail.setError("Invalid email ID");
                 tlEmail.setErrorEnabled(true);
             }
             else
                 {
                     tlEmail.setErrorEnabled(false);
                 }


            }
        });
        etMobile=(EditText)view.findViewById(R.id.etMobile);
        etMobile.addTextChangedListener(new TextValidator(tlMobile) {
            @Override
            public void validate(TextInputLayout textView, String text) {
                if(text.length()<10) {
                    tlMobile.setError("Please enter 10 digit mobile no");
                    tlMobile.setErrorEnabled(true);
                }
                else
                    {
                        tlMobile.setErrorEnabled(false);
                    }
            }
        });
        etAccount =(EditText)view.findViewById(R.id.etAccount);
        etAccount.addTextChangedListener(new TextValidator(tlAccount) {
            @Override
            public void validate(TextInputLayout textView, String text) {
                if(text.isEmpty()){
                    tlAccount.setError("Account no cannot be blank");
                    tlAccount.setErrorEnabled(true);}
                     else{
                    tlAccount.setErrorEnabled(false);
                }
            }

        });
        stateSpinner=(CustomSpinner)view.findViewById(R.id.state);

        UserService.getStateList(new ListCallBack() {
            @Override
            public void getList(String[] list) {

                states = list;
                stateSpinner.initializeStringValues(states,"Select State");

            }
        });
        String[] inialValues =new String[]{""};
        stateSpinner.setOnItemSelectedListener(stateSelectedListener);
        discomSpinner=(CustomSpinner)view.findViewById(R.id.discom);
        discomSpinner.setOnItemSelectedListener(discomSelectedListener);
        discomSpinner.initializeStringValues(inialValues,"Select Discom");
        powerFeederSpinner=(CustomSpinner)view.findViewById(R.id.power);
        powerFeederSpinner.setOnItemSelectedListener(powerFeederSelectedListener);
        powerFeederSpinner.initializeStringValues(inialValues,"Select Power Feeder");


    }

    private AdapterView.OnItemSelectedListener stateSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {


            Log.d("Position",String.valueOf(position));
            if(position>0){
                state =position;

                UserService.getCityList(position, new ListCallBack() {
                    @Override
                    public void getList(String[] list) {
                        discomSpinner.initializeStringValues(list,"Select Discom");


                    }
                });


            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {


        }
    };
    private AdapterView.OnItemSelectedListener discomSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
             if(position>0)
                 discom=position;


            UserService.getDivisionList(position, new ListCallBack() {
                @Override
                public void getList(String[] list) {

                    powerFeederSpinner.initializeStringValues(list,"Select Power Feeder");


                }
            });
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private AdapterView.OnItemSelectedListener powerFeederSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {

          if(position>0)
              powerFeeder =position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    private  final View.OnClickListener signUpClickeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            signUp();

        }
    };

    public void signUp()
    {

            String fakeRegId = "yddyfdssdfgggdagrfergerfgfgf";
            String password = "5522Difgkgga";
            String confirmpassword = "5522Difgkgga";
            name = etName.getText().toString();
            email = etName.getText().toString();
            mobileNo = etName.getText().toString();
            accountNo = etName.getText().toString();
            int area =4;


//api.registerUser(connectionNo,regId,name, email, password, passwordConfirmation, mobileNo, state, city, division, area);

               // try {
                   /* UserService.register(accountNo, fakeRegId, name, email, password, confirmpassword, mobileNo, state, discom, powerFeeder, area, new RestCallBack() {
                        @Override
                        public void onSuccess(boolean status) {
                            if (status) {
                                Log.d("Status",String.valueOf(status));

                            }
                        }

                        @Override
                        public void getStatusCode(int statusCode) {

                        }

                        @Override
                        public void getMessage(String message)
                        {
                            onCompleteListener.onComplete("Success");


                        }

                        @Override
                        public void getList(String[] list) {

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
*/

            }



    public abstract class TextValidator implements TextWatcher {
        private final TextInputLayout editText;

        public TextValidator(TextInputLayout editText) {
            this.editText = editText;
        }

        public abstract void validate(TextInputLayout textView, String text);

        @Override
        final public void afterTextChanged(Editable s) {
            String text = editText.getEditText().getText().toString();
            validate(editText, text);
        }

        @Override
        final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

        @Override
        final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
    }

    public static final class EmailValidator {

        private Pattern pattern;
        private Matcher matcher;

        private static final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        public EmailValidator() {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }

        /**
         * Validate hex with regular expression
         *
         * @param hex
         *            hex for validation
         * @return true valid hex, false invalid hex
         */
        public boolean validate(final String hex) {

            matcher = pattern.matcher(hex);
            return matcher.matches();

        }
    }

}
