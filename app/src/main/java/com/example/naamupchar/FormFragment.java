package com.example.naamupchar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Message;
import android.se.omapi.Session;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private EditText etName, etFatherName, etGotra, etPhone, etAddress, etCity, etProblem;
    private Button btnRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        etName = (EditText) view.findViewById(R.id.etName);
        etFatherName = (EditText) view.findViewById(R.id.etFatherName);
        etGotra = (EditText) view.findViewById(R.id.etGotra);
        etPhone = (EditText) view.findViewById(R.id.etPhone);
        etAddress = (EditText) view.findViewById(R.id.etAddress);
        etCity = (EditText) view.findViewById(R.id.etCity);
        etProblem = (EditText) view.findViewById(R.id.etProblem);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name="", fatherName="", gotra="", phone="", address="", city="", problem = "";
                name = etName.getText().toString().trim();
                fatherName = etFatherName.getText().toString().trim();
                gotra = etGotra.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                address = etAddress.getText().toString().trim();
                city = etCity.getText().toString().trim();
                problem = etProblem.getText().toString().trim();

                if (name.isEmpty() || fatherName.isEmpty() || gotra.isEmpty() || phone.isEmpty()
                        || address.isEmpty() || city.isEmpty() || problem.isEmpty()){
                    Toast.makeText(getContext(), "All fields are necessary", Toast.LENGTH_SHORT).show();
                }
                else {
                    String[] TO = {"naamupchar@gmail.com"};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    String message = "Naam Upchar Registration Entry -\n\n" +
                            "Name: " + name + "\n" +
                            "Father's Name: " + fatherName + "\n" +
                            "Gotra: " + gotra + "\n" +
                            "Phone: " + phone + "\n" +
                            "Address: " + address + "\n" +
                            "City: " + city + "\n\n" +
                            "Problem Description: " + problem;

                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Naam Upchar Registration");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, message);

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send details..."));
                        getActivity().finish();
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}