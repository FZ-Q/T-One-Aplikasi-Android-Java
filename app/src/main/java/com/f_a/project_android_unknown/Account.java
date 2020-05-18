package com.f_a.project_android_unknown;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import java.util.HashMap;
import java.util.Locale;

public class Account extends AppCompatActivity {

    EditText EUser, EEmail, ENoP, ETTL, EPass;
    Spinner SJK;
    ImageView ImgAva, ImgUpdate, ImgDelete, ImgGallery;

    int Id;
    String JK[] = {"Laki-Laki", "Perempuan"};
    String Jenis;

    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("My Account"); // set the top title

        dateFormatter = new SimpleDateFormat("dd MMMM, yyyy", Locale.US);
        setDateTimeField();

        ImgAva = (ImageView) findViewById(R.id.IVAvatar);
        EUser = (EditText) findViewById(R.id.ETUsername);
        EEmail = (EditText) findViewById(R.id.ETEmail);
        ENoP = (EditText) findViewById(R.id.ETNoHp);
        ETTL = (EditText) findViewById(R.id.ETTTL);
        ETTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromDatePickerDialog.show();
            }
        });
        SJK = (Spinner) findViewById(R.id.SJK);
        EPass = (EditText) findViewById(R.id.ETPass);

        spinner();

        SessionManagerAccount SMA = new SessionManagerAccount(getApplicationContext());
        HashMap<String, String> User = SMA.getUser();

        Id = Integer.parseInt(User.get(SessionManagerAccount.KEY_ID_USER));

        DBConnection DB = DBConnection.getDatabase(getApplicationContext());
        DAOAccount DAOA = DB.DAOAccount();
        ModelAccount MA = DAOA.FindOneById(Id);

        EUser.setText(MA.getUser());
        EEmail.setText(MA.getEmail());
        ENoP.setText(MA.getNo_Pon());
        ETTL.setText(MA.getTL());
        EPass.setText(MA.getPass());
        ImgAva.setImageBitmap(BitmapFactory.decodeByteArray(MA.getPhoto(), 0, MA.getPhoto().length));

        ImgGallery = (ImageView) findViewById(R.id.IVGallery);
        ImgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        ImgDelete = findViewById(R.id.IVDelete);
        ImgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                snackbarText.append("Do You Want to ");
                int Span1 = snackbarText.length();
                snackbarText.append("Delete");
                snackbarText.setSpan(new ForegroundColorSpan(Color.RED), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                snackbarText.append(" Your Account.");
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), snackbarText, Snackbar.LENGTH_LONG)
                        .setDuration(2000);
                snackbar.setAction("Delete", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                        ModelAccount MAD = new ModelAccount();
                        MAD.setId(Id);
                        DAOA.delete(MAD);

                        Intent intent = new Intent(Account.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });

        ImgUpdate = findViewById(R.id.IVEdit);
        ImgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                snackbarText.append("Do You Want to ");
                int Span1 = snackbarText.length();
                snackbarText.append("Update ");
                snackbarText.setSpan(new ForegroundColorSpan(Color.GREEN), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                snackbarText.append("Your Account.");
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), snackbarText, Snackbar.LENGTH_LONG)
                        .setDuration(2000);
                snackbar.setAction("Update", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();

                        ModelAccount MAUpdate = new ModelAccount();

                        MAUpdate.setId(Id);
                        MAUpdate.setPhoto(IVtoByte(ImgAva));
                        MAUpdate.setUser(EUser.getText().toString());
                        MAUpdate.setEmail(EEmail.getText().toString());
                        MAUpdate.setNo_Pon(ENoP.getText().toString());
                        MAUpdate.setTL(ETTL.getText().toString());
                        MAUpdate.setJK(Jenis);
                        MAUpdate.setPass(EPass.getText().toString());
                        DAOA.update(MAUpdate);

                        SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                        int Span1 = snackbarText.length();
                        snackbarText.append("Update ");
                        snackbarText.setSpan(new ForegroundColorSpan(Color.GREEN), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        snackbarText.append("Account ");
                        int Span2 = snackbarText.length();
                        snackbarText.append("Success");
                        snackbarText.setSpan(new ForegroundColorSpan(Color.GREEN), Span2, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span2, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        snackbarText.append(".");

                        Snackbar snackbar = Snackbar
                                .make(findViewById(android.R.id.content), snackbarText, Snackbar.LENGTH_LONG)
                                .setDuration(2000);
                        snackbar.show();

                    }
                });
                snackbar.show();
            }
        });

    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(Account.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                ETTL.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int Id = item.getItemId();
        if(Id == R.id.Back){
            Intent Back = new Intent(Account.this, Dashboard.class);
            startActivity(Back);
        }
        return super.onOptionsItemSelected(item);
    }

    public void spinner(){
        ArrayAdapter<String> ArrayJK = new ArrayAdapter<> (this, R.layout.list_jenis_kelamin, R.id.TJenisK, JK);
        SJK.setAdapter(ArrayJK);

        SessionManagerAccount SMA = new SessionManagerAccount(getApplicationContext());
        HashMap<String, String> User = SMA.getUser();

        Id = Integer.parseInt(User.get(SessionManagerAccount.KEY_ID_USER));

        DBConnection DB = DBConnection.getDatabase(getApplicationContext());
        DAOAccount DAOA = DB.DAOAccount();
        ModelAccount MA = DAOA.FindOneById(Id);

        int DP = ArrayJK.getPosition(MA.getJK());
        SJK.setSelection(DP);

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
                Toast.makeText(Account.this, "Anda Tidak Memiliki Izin untuk Mengakses Lokasi File", Toast.LENGTH_SHORT).show();
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
                InputStream IS = getContentResolver().openInputStream(uri);
                Bitmap BM = BitmapFactory.decodeStream(IS);
                ImgAva.setImageBitmap(BM);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
