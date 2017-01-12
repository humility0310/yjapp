package com.yj.yjapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yj.usersService.UsersService;
import com.yj.vo.UsersVo;
import com.yj.network.SafeAsyncTask;

public class MainActivity extends AppCompatActivity {

    private UsersService userService = new UsersService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_joinform).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JoinForm.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchUserListAsyncTask().execute();
            }
        });
        findViewById(R.id.button_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchUserListAsyncTask2().execute();
            }
        });
        findViewById(R.id.button_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyListActivity.class);
                startActivity(intent);
            }
        });
    }
//------------------------------Input-----------------------------
    private class FetchUserListAsyncTask2 extends SafeAsyncTask<Integer> {
        @Override
        public Integer call() throws Exception {
            return userService.inputUsersVo();
            //return true;
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {
            Log.d( "------->", "fail:" + e );
            super.onException(e);
        }

        @Override
        protected void onSuccess(Integer a) throws Exception {
            Log.d( "------->", "" + a );
        }
    }
//-----------------------------------------------------------------------------
    private class FetchUserListAsyncTask extends SafeAsyncTask<UsersVo> {
        @Override
        public UsersVo call() throws Exception {
            UsersVo usersVo = userService.fetchUsersVo();
            return usersVo;
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {
            Log.d( "------->", "fail:" + e );
            super.onException(e);
        }

        @Override
        protected void onSuccess(UsersVo usersVo) throws Exception {
            Log.d( "------->", "" + usersVo );
        }
    }

}