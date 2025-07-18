package com.salmakhd.android.shahabseries.instagram;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salmakhd.android.shahabseries.R;
import com.salmakhd.android.shahabseries.instagram.fragments.LoginFragment;

public class MainInstagramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_instagram);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, new LoginFragment())
                .addToBackStack(null)
                .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                .commit();

//        regButton.setOnClickListener(new View.OnClickListener() {
//                                         @Override
//                                         public void onClick(View v) {
//                                             String nameText = name.getText().toString();
//                                             String usernameText = username.getText().toString();
//                                             String passwordText =  password.getText().toString();
//
//                                             if(!(nameText.isEmpty() && usernameText.isEmpty() && passwordText.isEmpty())) {
//                                                 RetrofitClient.getInstance().getApi().registerUser(
//                                                                 nameText,
//                                                                 usernameText,
//                                                                 passwordText
//                                                         )
//                                                         .enqueue(new Callback<JsonResponseModel>() {
//                                                             @Override
//                                                             public void onResponse(Call<JsonResponseModel> call, Response<JsonResponseModel> response) {
//                                                                 if(response.isSuccessful()) {
//                                                                     Log.i("Insta", "Operation successful"+ response.message());
//                                                                 } else {
//                                                                     switch (response.code()) {
//                                                                         case 409:
//                                                                             Log.i("Insta", "Error 409 encountered:" + response.message());
//
//                                                                         case 500:
//                                                                             Log.i("Insta", "Error encountered" + response.message());
//                                                                         default:
//                                                                             throw new UnknownError();
//                                                                     }
//                                                                 }
//                                                             }
//
//                                                             @Override
//                                                             public void onFailure(Call<JsonResponseModel> call, Throwable t) {
//                                                                 Log.i("Insta", "Network call failed.");
//                                                                 t.printStackTrace();
//                                                             }
//                                                         });
//
//                                             }
//                                         }
//                                     }
//        );
    }

    private void checkPermission() {
        boolean permissionRequestedBefore = true;
        if( ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showExplanatin();
            } else if(permissionRequestedBefore) {
                // direct to settings
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
            }
        } else {
            // do the rest of the operations
        }
    }

    public void showExplanatin() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("permission reqired");
        alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(MainInstagramActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // permission has been granted
        if(requestCode==123 && grantResults.length > 0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED) {

        } else {

        }
    }

}