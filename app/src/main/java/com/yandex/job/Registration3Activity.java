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
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class Registration3Activity extends AppCompatActivity implements View.OnClickListener {

    public static String photo2, photo3, photo4, photo5;
    String currentPhoto = "photo2";
    ImageView ivPhoto2, ivPhoto3, ivPhoto4, ivPhoto5;
    Button upload2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration3);
        String title = "Регистрация: шаг №3";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        initViews();
        if (RegistrationData.workType.equals("rent")) {
            upload2.setEnabled(false);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnContinue:
                startActivity(new Intent(this, Registration4Activity.class));
                break;
            case R.id.upload1:
            case R.id.upload2:
                selectImage();
                break;
        }
    }

    private void initViews() {
        ivPhoto2 = findViewById(R.id.ivPhoto2);
        ivPhoto3 = findViewById(R.id.ivPhoto3);
        ivPhoto4 = findViewById(R.id.ivPhoto4);
        ivPhoto5 = findViewById(R.id.ivPhoto5);
        upload2 = findViewById(R.id.upload2);
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

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] byteArrayImage = baos.toByteArray();
            System.out.println(currentPhoto);
            if (currentPhoto.equals("photo2")) {
                ivPhoto2.setImageBitmap(selectedImage);
                photo2 = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                currentPhoto = "photo3";
            }
            else if (currentPhoto.equals("photo3")) {
                ivPhoto3.setImageBitmap(selectedImage);
                photo3 = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                currentPhoto = "photo4";
            }
            else if (currentPhoto.equals("photo4")) {
                ivPhoto4.setImageBitmap(selectedImage);
                photo4 = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                currentPhoto = "photo5";
            }
            else if (currentPhoto.equals("photo5")) {
                ivPhoto5.setImageBitmap(selectedImage);
                photo5 = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
            }
        }
    }
}
