package com.example.loginandsignupwithfragments.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.loginandsignupwithfragments.R;
import com.example.loginandsignupwithfragments.listeners.OnFragmentChangeListener;

public class SignupFragment extends Fragment {

    OnFragmentChangeListener mOnFragmentChangeListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView loginLink = view.findViewById(R.id.login_link_text_id);

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnFragmentChangeListener.change(new LoginFragment(), "loginFragment", false);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnFragmentChangeListener = (OnFragmentChangeListener) context;
    }
}
