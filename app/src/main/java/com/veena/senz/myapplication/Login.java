package com.veena.senz.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private Button button_login_login;
    private EditText editText_login_username;
    private EditText editText_login_password;
    private String username;
    private String password;
    public String baseUrl;

    RequestQueue MyRequestQueue;
    private boolean isLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        MyRequestQueue = Volley.newRequestQueue(this);

        baseUrl = "http://10.91.150.78:8080/api/auth/signin";


        editText_login_username = (EditText) findViewById(R.id.editText_login_username);
        editText_login_password = (EditText) findViewById(R.id.editText_login_password);

        button_login_login = (Button) findViewById(R.id.button_login_login);

//        button_login_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//
//                    username = editText_login_username.getText().toString();
//                    password = editText_login_password.getText().toString();
//
//                        ApiAuthenticationClient apiAuthenticationClient = new ApiAuthenticationClient(baseUrl, username, password);
//
//                        AsyncTask<Void, Void, String> execute = new ExecuteNetworkOperation(apiAuthenticationClient);
//                        execute.execute();
//
//
//
//                } catch (
//                        Exception ex)
//
//                {
//                }
//            }
//        });
//




    }


    public void login(View view) {
        //ragulan
        username = editText_login_username.getText().toString();
        password = editText_login_password.getText().toString();
        // Optional Parameters to pass as POST request
        JSONObject js = new JSONObject();
        try {
            js.put("usernameOrEmail", username);
            js.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, baseUrl, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

//                        Log.d("Responce", response.toString());

                        try {
                            String accessToken = response.getString("accessToken");
                            Log.i("token", accessToken);
                            savePreferences("token", accessToken);

                            String savedToken = loadPreferences("token");
                            isLogin = true;
                            Log.i("savedToken", savedToken);

                        } catch (Exception e) {
                            Log.e("Responce", response.toString());
                            // Toast.makeText(new Login(), "login unsucces", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(), "login unsucces", Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("error response", "Error: " + error.getMessage());
                        //Toast.makeText(getBaseContext(), "login unsucces", Toast.LENGTH_LONG).show();

                    }
                }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };


        if (validateLogin(username, password)) {
//                    //do login
            // Adding request to request queue
            Volley.newRequestQueue(this).add(jsonObjReq);

        }

        if (isLogin) {
            findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
            Toast.makeText(this, "login succes", Toast.LENGTH_SHORT).show();
            goPhotopage();
        } else {
            Toast.makeText(this, "login unsucces", Toast.LENGTH_SHORT).show();
        }

//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            // Display the progress bar.
//            findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
//        }
    }

    private void goPhotopage() {
        Intent i = new Intent(getApplicationContext(), Photo.class);
        startActivity(i);
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private String loadPreferences(String key) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String score = sharedPreferences.getString(key, "");
        return score;
    }

    private boolean validateLogin(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "userName is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}


    /**
     * This subclass handles the network operations in a new thread.
     * It starts the progress bar, makes the API call, and ends the progress bar.
     */






//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//            // Hide the progress bar.
//            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
//
//            // Login Success
//            if (isValidCredentials.equals("true")) {
//                goToSecondActivity();
//            }
//            // Login Failure
//            else {
//                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
//            }
//        }



    /**
     * Open a new activity window.
     */
//    private void goToSecondActivity() {
//        Bundle bundle = new Bundle();
//        bundle.putString("username", username);
//        bundle.putString("password", password);
//        bundle.putString("baseUrl", baseUrl);
//
//        Intent intent = new Intent(this, Photo.class);
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }
//

//
//
//}


//UserService userService;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        final EditText edtuserName;
//        final EditText edtPassword;
//        Button btnLogin;
//
//
//        edtuserName = (EditText) findViewById(R.id.editText3);
//        edtPassword = (EditText) findViewById(R.id.editText4);
//        btnLogin = (Button) findViewById(R.id.button);
//        //userService = ApiUtils.getUserService();
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//
//
//
//            @Override
//            public void onClick(View v) {
//                String userName = edtuserName.getText().toString();
//                String password = edtPassword.getText().toString();
//                //validate form
//                if(validateLogin(userName, password)) {
//                    //do login
//
//                    Connection obj = new Connection();
//                    obj.execute();
//
//
//                  //  go();
////                    btnLogin.setOnClickListener(new View.OnClickListener() {
////                        //@Override
////                        public void onClick(View view)
////                        {
////                            Intent i = new Intent(getApplicationContext(), aaa.class);
////                            startActivity(i);
////
////                        }
////                    });
//                }
//
//                else {
//                    Toast.makeText(new Login(), "login unsucces", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
//
//
//
//    public JSONObject buidJsonObject() throws JSONException {
//
//        final EditText edtuserName;
//        final EditText edtPassword;
//        Button btnLogin;
//
//        edtuserName = (EditText) findViewById(R.id.editText5);
//        edtPassword = (EditText) findViewById(R.id.editText6);
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.accumulate("userName", edtuserName.getText().toString());
//        jsonObject.accumulate("password",  edtPassword.getText().toString());
//
//        return jsonObject;
//    }
//
//    public void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException {
//
//        OutputStream os = conn.getOutputStream();
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//        writer.write(jsonObject.toString());
//        Log.i(Login.class.toString(), jsonObject.toString());
//        writer.flush();
//        writer.close();
//        os.close();
//    }
//
//
//    private boolean validateLogin(String userName, String password){
//        if(userName == null || userName.trim().length() == 0){
//            Toast.makeText(this, "userName is required", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if(password == null || password.trim().length() == 0){
//            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }
//
//
//    public void go()
//    {
//        Intent intent = new Intent(Login.this, Photo.class);
//        startActivity(intent);
//    }
//
//}
//
//
//
//


