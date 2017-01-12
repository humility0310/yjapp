package com.yj.yjapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yj.listformat.UsersListArrayAdapter;
import com.yj.network.SafeAsyncTask;
import com.yj.usersService.UsersService;
import com.yj.vo.UsersVo;

import java.util.List;

public class MyListActivity extends ListActivity {

    private UsersListArrayAdapter usersListArrayAdapter = null;

    private UsersService usersService = new UsersService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        usersListArrayAdapter = new UsersListArrayAdapter(this);
        setListAdapter(usersListArrayAdapter);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        findViewById(R.id.button_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchUserListAsyncTask().execute();
            }
        });
    }

    private class FetchUserListAsyncTask extends SafeAsyncTask<List<UsersVo>> {
        @Override
        public List<UsersVo> call() throws Exception {
            List<UsersVo> list = usersService.fetchUserList();
            return list;
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {
            Log.d("------->", "fail:" + e);
            super.onException(e);
        }

        @Override
        protected void onSuccess(List<UsersVo> usersVos) throws Exception {
            for (int i = 0; i < usersVos.size(); i++) {

                UsersVo myUsersVo = usersVos.get(i);
                String temp = "http://192.168.1.5:8088/yjsite/gallery/assets/" + usersVos.get(i).getUsersImage();
                myUsersVo.setUsersImage(temp);
                usersVos.set(i, myUsersVo);

                Log.d("--usersVo[" + (i + 1) + "번째]-->", "" + usersVos.get(i));
            }

            usersListArrayAdapter.add(usersVos);
        }
    }
}
