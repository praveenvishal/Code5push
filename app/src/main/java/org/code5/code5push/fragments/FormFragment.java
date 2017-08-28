package org.code5.code5push.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.code5.code5push.R;

/**
 * Created by sony on 8/26/2017.
 */

public class FormFragment extends Fragment {

    private EditText etName;
    private EditText etEmail;
    private EditText etMobile;
    private EditText etAccount;
    private String name;
    private String email;
    private String mobileNo;
    private String accountNo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.form, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        etName = (EditText) view.findViewById(R.id.et_firstname);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        etAccount = (EditText) view.findViewById(R.id.etAccount);


    }






}
