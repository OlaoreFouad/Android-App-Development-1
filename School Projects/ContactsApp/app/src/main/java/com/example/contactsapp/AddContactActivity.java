package com.example.contactsapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactsapp.models.Contact;
import com.example.contactsapp.utils.Util;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, organizationEditText, phoneNumberEditText, emailEditText, groupsEditText;
    private EditText addressEditText;
    private Button cancelButton, saveButton;

    private android.widget.TextView addContactGroupsText;

    private Contact contact;
    private Intent intent;

    private static final int REQUEST_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

//        EditTexts
        nameEditText = findViewById(R.id.addContactNameId);
        organizationEditText = findViewById(R.id.addContactOrgId);
        phoneNumberEditText = findViewById(R.id.addContactPhoneNumberId);
        emailEditText = findViewById(R.id.addContactEmailId);
        groupsEditText = findViewById(R.id.addContactGroupsId);
        addressEditText = findViewById(R.id.addContactAddressId);

//        Buttons
        cancelButton = findViewById(R.id.cancelButtonId);
        saveButton = findViewById(R.id.saveButtonId);

//        TextViews
        addContactGroupsText = findViewById(R.id.addContactGroupsTextId);

        cancelButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        addContactGroupsText.setOnClickListener(this);

        intent = getIntent();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButtonId: {
                setResult(RESULT_CANCELED);
                finish();
            }
            break;
            case R.id.saveButtonId: {
                int success = save();
                if (success == 1) {
                    intent.putExtra("contact", contact);
                    setResult(RESULT_OK, intent);
                    finish();
                } else if (success == -1) {
                    Toast.makeText(this, "Empty fields!. Fill necessary fields!", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.addContactGroupsTextId: {
                startActivityForResult(new Intent(AddContactActivity.this, SelectGroupsActivity.class), REQUEST_CODE);
            }
            break;
        }
    }

    private int save() {
        contact = new Contact();
        if (!TextUtils.isEmpty(nameEditText.getText())) {
            contact.setName(nameEditText.getText().toString());
            if (!TextUtils.isEmpty(organizationEditText.getText())) {
                contact.setOrganization(organizationEditText.getText().toString());
            } else {
                contact.setOrganization(null);
            }
            if (TextUtils.isEmpty(phoneNumberEditText.getText())) {
                return -1;
            } else {
                contact.setPhoneNumber(phoneNumberEditText.getText().toString());
            }
            if (!TextUtils.isEmpty(emailEditText.getText())) {
                contact.setEmail(emailEditText.getText().toString());
            } else {
                contact.setEmail(null);
            }
            if (!TextUtils.isEmpty(groupsEditText.getText())) {
                contact.setGroups(groupsEditText.getText().toString().split(", "));
            } else {
                contact.setGroups(null);
            }
            if (!TextUtils.isEmpty(addressEditText.getText())) {
                contact.setAddress(addressEditText.getText().toString());
            } else {
                contact.setAddress(null);
            }
        } else {
            return -1;
        }

        Log.i(Util.TAG, "save: successful!");
        return 1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(Util.TAG, "onActivityResult: " + REQUEST_CODE);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                if (data != null && data.getStringExtra("groups") != null) {
                    Log.i(Util.TAG, "onActivityResult: data is not null oo!");
                    Log.i(Util.TAG, "onActivityResult: " + data.getStringExtra("groups"));
                    groupsEditText.setText(data.getStringExtra("groups"));
                    addContactGroupsText.setVisibility(View.GONE);
                }
            }
        }
    }
}
