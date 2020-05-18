package com.f_a.project_android_unknown;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.f_a.project_android_unknown.connection.DBConnection;
import com.f_a.project_android_unknown.model.ModelAccount;
import com.f_a.project_android_unknown.model.ModelLogin;
import com.f_a.project_android_unknown.service.DAOAccount;
import com.f_a.project_android_unknown.session_manager.SessionManagerAccount;
import com.google.android.material.snackbar.Snackbar;


public class FragLogin extends Fragment {

    TextView TSignUp;
    Button BtnLogin;
    Intent intent;
    EditText IUser, IPass;

    String Id_User;


    public FragLogin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_frag_login, container, false);
        IUser = (EditText) RootView.findViewById(R.id.ETUsername);
        IPass = (EditText) RootView.findViewById(R.id.ETPass);

        BtnLogin = (Button) RootView.findViewById(R.id.BSLogin);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if(TextUtils.isEmpty(IUser.getText().toString()) && TextUtils.isEmpty(IPass.getText().toString())){
                    SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                    int Span1 = snackbarText.length();
                    snackbarText.append("Username ");
                    snackbarText.setSpan(new ForegroundColorSpan(Color.RED), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    snackbarText.append(" and ");
                    int Span2 = snackbarText.length();
                    snackbarText.append("Password ");
                    snackbarText.setSpan(new ForegroundColorSpan(Color.RED), Span2, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span2, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    snackbarText.append("Can Not Be Empty");
                    snackbarText.append(".");

                    Snackbar snackbar = Snackbar
                            .make(getActivity().findViewById(android.R.id.content), snackbarText, Snackbar.LENGTH_LONG)
                            .setDuration(2000);
                    snackbar.setAction("Sign Up", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                            FragSignUp FSignUp = new FragSignUp();
                            //buat object fragmentkedua

                            getFragmentManager().beginTransaction()
                                    .replace(R.id.Frame, FSignUp)
                                    //menggant fragment
                                    .addToBackStack(null)
                                    //menyimpan fragment
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    //transisi fragment
                                    .commit();
                        }
                    });
                    view = snackbar.getView();
                    FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    snackbar.show();
                }else if(TextUtils.isEmpty(IUser.getText().toString())){
                    SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                    int Span1 = snackbarText.length();
                    snackbarText.append("Username ");
                    snackbarText.setSpan(new ForegroundColorSpan(Color.RED), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    snackbarText.append("Can Not Be Empty");
                    snackbarText.append(".");

                    Snackbar snackbar = Snackbar
                            .make(getActivity().findViewById(android.R.id.content), snackbarText, Snackbar.LENGTH_LONG)
                            .setDuration(2000);
                    view = snackbar.getView();
                    FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    snackbar.show();
                }else if(TextUtils.isEmpty(IPass.getText().toString())){
                    SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                    int Span1 = snackbarText.length();
                    snackbarText.append("Password ");
                    snackbarText.setSpan(new ForegroundColorSpan(Color.RED), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    snackbarText.append("Can Not Be Empty");
                    snackbarText.append(".");

                    Snackbar snackbar = Snackbar
                            .make(getActivity().findViewById(android.R.id.content), snackbarText, Snackbar.LENGTH_LONG)
                            .setDuration(2000);
                    view = snackbar.getView();
                    FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    snackbar.show();
                }else{
                    DBConnection DB = DBConnection.getDatabase(getActivity().getApplicationContext());
                    DAOAccount DAOA = DB.DAOAccount();

                    ModelLogin ML = DAOA.Login(IUser.getText().toString(), IPass.getText().toString());

                    if(ML.getCUser() == 1 && ML.getCPass() == 1){
                        SessionManagerAccount SMA = new SessionManagerAccount(getActivity());
                        Id_User = String.valueOf(ML.getId());
                        SMA.createLoginSession(Id_User);
                        intent = new Intent(getActivity(), Dashboard.class);
                        startActivity(intent);
                    }else{
                        SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                        int Span1 = snackbarText.length();
                        snackbarText.append("Username ");
                        snackbarText.setSpan(new ForegroundColorSpan(Color.RED), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        snackbarText.append("or ");
                        int Span2 = snackbarText.length();
                        snackbarText.append("Password ");
                        snackbarText.setSpan(new ForegroundColorSpan(Color.RED), Span2, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span2, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        snackbarText.append("Incorrect.");
                        Snackbar snackbar = Snackbar
                                .make(getActivity().findViewById(android.R.id.content), snackbarText, Snackbar.LENGTH_LONG)
                                .setDuration(2000);
                        snackbar.setAction("Sign Up", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                                FragSignUp FSignUp = new FragSignUp();
                                //buat object fragmentkedua

                                getFragmentManager().beginTransaction()
                                        .replace(R.id.Frame, FSignUp)
                                        //menggant fragment
                                        .addToBackStack(null)
                                        //menyimpan fragment
                                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                        //transisi fragment
                                        .commit();
                            }
                        });
                        view = snackbar.getView();
                        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                        params.gravity = Gravity.TOP;
                        snackbar.show();
                    }
                }
            }

        });


        TSignUp = (TextView) RootView.findViewById(R.id.TVSignUp);
        TSignUp.setClickable(true);
        TSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                FragSignUp FSignUp = new FragSignUp();
                //buat object fragmentkedua

                getFragmentManager().beginTransaction()
                        .replace(R.id.Frame, FSignUp)
                        //menggant fragment
                        .addToBackStack(null)
                        //menyimpan fragment
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        //transisi fragment
                        .commit();
            }
        });
        return RootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
