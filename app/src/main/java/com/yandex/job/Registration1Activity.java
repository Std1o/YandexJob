package com.yandex.job;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class Registration1Activity extends AppCompatActivity {

    EditText etName, etLastName, etMiddleName, etBirthDate, etUserPhone;
    public static String photo1;
    ImageView ivPhoto1;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);
        String title = "Регистрация: шаг №1";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        prefManager = new PrefManager(this);
        initViews();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpload:
                selectImage();
                break;
            case R.id.btnContinue:
                getDataFromEt();
                prefManager.setName(etName.getText().toString());
                prefManager.setLastName(etLastName.getText().toString());
                prefManager.setUserPhoto(photo1);
                startActivity(new Intent(this, Registration2Activity.class));
                break;
        }
    }

    public void selectImage() {
        Intent intent = new Intent(this, ImageSelectActivity.class);
        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
        startActivityForResult(intent, 1213);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            ivPhoto1.setImageBitmap(selectedImage);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] byteArrayImage = baos.toByteArray();
            photo1 = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
        }
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etUserPhone = findViewById(R.id.etUserPhone);
        ivPhoto1 = findViewById(R.id.ivPhoto1);
    }

    private void getDataFromEt() {
        etBirthDate.setText(etBirthDate.getText().toString().replace(".", "-"));
        etBirthDate.setText(etBirthDate.getText().toString().replace("/", "-"));

        RegistrationData.name = etName.getText().toString();
        RegistrationData.lastName = etLastName.getText().toString();
        RegistrationData.middleName = etMiddleName.getText().toString();
        RegistrationData.birthDate = etBirthDate.getText().toString();
        RegistrationData.phone = etUserPhone.getText().toString();
    }
}
