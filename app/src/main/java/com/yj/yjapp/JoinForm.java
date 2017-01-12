package com.yj.yjapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.net.CookieManager;
import java.net.HttpURLConnection;

public class JoinForm extends AppCompatActivity {

    EditText joinId, joinPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_form);

        joinId = (EditText) findViewById(R.id.Text_id);
        joinPw = (EditText) findViewById(R.id.Text_pw);
        findViewById(R.id.button_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        findViewById(R.id.button_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jId = joinId.getText().toString();
                String jPw = joinPw.getText().toString();



                String url = "http://localhost:8088/yjsite.androidMain.jsp";

                HttpRequest request = HttpRequest.get(url);

                request.accept(HttpRequest.CONTENT_TYPE_JSON);
                request.connectTimeout(1000);
                request.readTimeout(3000);

                int responseCode = request.code();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    /* 에러 처리 */
                    //return null;
                }



            }
        });

        findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("들어가나욤?","궁금하네요");
                finish();
            }
        });
    }


    protected <V> V fromJSON( HttpRequest request, Class<V> target ) throws IOException {

        Gson gson = new GsonBuilder().create();

        Reader reader = request.bufferedReader();
        V v = gson.fromJson( reader, target );
        reader.close();

        return v;
    }

}


