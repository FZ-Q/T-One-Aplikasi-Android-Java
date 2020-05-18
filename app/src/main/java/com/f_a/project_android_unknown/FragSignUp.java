package com.f_a.project_android_unknown;


import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.f_a.project_android_unknown.connection.DBConnection;
import com.f_a.project_android_unknown.model.ModelAccount;
import com.f_a.project_android_unknown.service.DAOAccount;
import com.f_a.project_android_unknown.session_manager.SessionManagerAccount;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class FragSignUp extends Fragment {

    ImageView ImgAva, ImgGallery;
    EditText ETanggal, EUser, EEmail, ENoP, EPass;
    Spinner SJK;
    String Jenis, Id_User;
    Button BtnSignUp;
    String JK[] = {"Laki-Laki", "Perempuan"};
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat DFormatter;

    final int REQUEST_CODE_GALLERY = 999;

    public FragSignUp() {
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
        View RootView = inflater.inflate(R.layout.fragment_frag_sign_up, container, false);

        ImgGallery = (ImageView) RootView.findViewById(R.id.IVGallery);
        ImgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        ImgAva = (ImageView) RootView.findViewById(R.id.IVAvatar);
        EUser = (EditText) RootView.findViewById(R.id.ETUsername);
        EEmail = (EditText) RootView.findViewById(R.id.ETEmail);
        ENoP = (EditText) RootView.findViewById(R.id.ETNoHp);
        ETanggal = (EditText) RootView.findViewById(R.id.ETTTL);
        ETanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromDatePickerDialog.show();
            }
        });
        SJK = (Spinner) RootView.findViewById(R.id.SJK);
        EPass = (EditText) RootView.findViewById(R.id.ETPass);

        ArrayAdapter<String> ArrayJK = new ArrayAdapter<> (getActivity(), R.layout.list_jenis_kelamin, R.id.TJenisK, JK);
        SJK.setAdapter(ArrayJK);

        SJK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SJK.getSelectedItem().toString();
                Jenis = SJK.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DFormatter = new SimpleDateFormat("dd MMMM, yyyy", Locale.US);
        setDateTimeField();

        BtnSignUp = (Button) RootView.findViewById(R.id.BSignUp);
        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(ETanggal.getText().toString()) || TextUtils.isEmpty(EUser.getText().toString())
                        || TextUtils.isEmpty(EEmail.getText().toString()) || TextUtils.isEmpty(ENoP.getText().toString())
                        || TextUtils.isEmpty(EPass.getText().toString())){
                    Snacbar();
                }else{
                    Intent SignUp = new Intent(getActivity(), Account.class);
                    DBConnection DB = DBConnection.getDatabase(getActivity().getApplicationContext());
                    DAOAccount DAOA = DB.DAOAccount();
                    ModelAccount MA = new ModelAccount();

                    MA.setPhoto(IVtoByte(ImgAva));
                    MA.setUser(EUser.getText().toString());
                    MA.setEmail(EEmail.getText().toString());
                    MA.setNo_Pon(ENoP.getText().toString());
                    MA.setTL(ETanggal.getText().toString());
                    MA.setJK(Jenis);
                    MA.setPass(EPass.getText().toString());
                    DAOA.save(MA);

                    SessionManagerAccount SMA = new SessionManagerAccount(getActivity());
                    ModelAccount MAUser = DAOA.FindOneByUser(EUser.getText().toString());
                    Id_User = String.valueOf(MAUser.getId());
                    SMA.createLoginSession(Id_User);

                    startActivity(SignUp);
                }
            }
        });
        return RootView;
    }


    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                ETanggal.setText(DFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private byte[] IVtoByte(ImageView IV) {
        Bitmap BM = ((BitmapDrawable)IV.getDrawable()).getBitmap();
        ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
        BM.compress(Bitmap.CompressFormat.JPEG, 50, BAOS);
        byte[] BArray = BAOS.toByteArray();
        return BArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent I = new Intent(Intent.ACTION_PICK);
                I.setType("image/*");
                startActivityForResult(I, REQUEST_CODE_GALLERY);
            }else {
                SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                snackbarText.append("You Do Not Have ");
                int Span1 = snackbarText.length();
                snackbarText.append("Permission To Access Gallery");
                snackbarText.setSpan(new ForegroundColorSpan(Color.RED), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                Snackbar snackbar = Snackbar
                        .make(getActivity().findViewById(android.R.id.content), snackbarText, Snackbar.LENGTH_LONG)
                        .setDuration(2000);
                snackbar.show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream IS = getActivity().getContentResolver().openInputStream(uri);
                Bitmap BM = BitmapFactory.decodeStream(IS);
                ImgAva.setImageBitmap(BM);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Snacbar(){
        SpannableStringBuilder snackbarText = new SpannableStringBuilder();
        snackbarText.append("Field ");
        int Span1 = snackbarText.length();
        snackbarText.append("Can Not Be Empty");
        snackbarText.setSpan(new ForegroundColorSpan(Color.RED), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        snackbarText.append(".");

        Snackbar snackbar = Snackbar
                .make(getActivity().findViewById(android.R.id.content), snackbarText, Snackbar.LENGTH_LONG)
                .setDuration(2000);
        snackbar.show();
    }
}
