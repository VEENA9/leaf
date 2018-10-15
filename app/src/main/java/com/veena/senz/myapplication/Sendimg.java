package com.veena.senz.myapplication;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Sendimg {

    public static void uploadImage() throws IOException {

        String  url = "http://10.91.150.250:5000/api/auth/signin";

// Example data
        String lname = "leaf1";
        String description = "xxxyyyy zzz";
        File image = Photo4.createImageFile();
// Create an HTTP client to execute the request
        OkHttpClient client = new OkHttpClient();

// Create a multipart request body. Add metadata and files as 'data parts'.
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", lname)
                .addFormDataPart("description", description)
                .addFormDataPart("image", image.getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), image))
                .build();

// Create a POST request to send the data to UPLOAD_URL
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

// Execute the request and get the response from the server
        okhttp3.Response response = null;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

// Check the response to see if the upload succeeded
        if (response == null || !response.isSuccessful()) {
            Log.w("Example", "Unable to upload to server.");
        } else {
            Log.v("Example", "Upload was successful.");
        }
    }
}
