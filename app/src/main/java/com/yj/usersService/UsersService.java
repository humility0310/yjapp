package com.yj.usersService;

import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yj.vo.UsersVo;
import com.yj.network.JSONResult;

import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by BIT on 2016-12-16.
 */

public class UsersService {

    public UsersVo fetchUsersVo() {

        String url = "http://192.168.1.5:8088/yjsite/test3";
        HttpRequest httpRequest = HttpRequest.get(url);
        httpRequest.contentType(HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.accept(HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.connectTimeout(3000);
        httpRequest.readTimeout(3000);

        int responseCode = httpRequest.code();

        if (responseCode != HttpURLConnection.HTTP_OK) {
            //에러처리
            throw new RuntimeException("Http Response: " + responseCode);
        }

        JSONResultUsersVo jSONResultUsersVo = fromJSON(httpRequest, JSONResultUsersVo.class);

        return jSONResultUsersVo.getData();
    }

    //----------------------------------------input-----------------------------------------------------
    public int inputUsersVo() {
        String url = "http://192.168.1.5:8088/yjsite/test1";

        HttpRequest httpRequest = HttpRequest.post(url);
        httpRequest.contentType(HttpRequest.CONTENT_TYPE_FORM);
        httpRequest.accept(HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.connectTimeout(3000);
        httpRequest.readTimeout(3000);

        int responseCode = httpRequest.send("id=android&password=android1&usersImage=android3").code();
        Log.d("int responseCode ", ""+responseCode);

        if (responseCode != HttpURLConnection.HTTP_OK) {
            //에러처리
            throw new RuntimeException("Http Response: " + responseCode);
        }

        JSONResultNumber jSONResultNumber = fromJSON(httpRequest, JSONResultNumber.class);

        return jSONResultNumber.getData();
    }


    public List<UsersVo> fetchUserList() {
        String url = "http://192.168.1.5:8088/yjsite/testlist";
        HttpRequest httpRequest = HttpRequest.post(url);

        httpRequest.contentType(HttpRequest.CONTENT_TYPE_FORM);
        httpRequest.accept(HttpRequest.CONTENT_TYPE_JSON);
        httpRequest.connectTimeout(3000);
        httpRequest.readTimeout(3000);

        int responseCode = httpRequest.code();

        if (responseCode != HttpURLConnection.HTTP_OK) {

            //에러처리
            throw new RuntimeException("Http Response: " + responseCode);
        }

        JSONResultUserList jsonResultUserList = fromJSON(httpRequest, JSONResultUserList.class);
        return jsonResultUserList.getData();
    }


    protected <V> V fromJSON(HttpRequest request, Class<V> target) {
        V v = null;

        try {
            Gson gson = new GsonBuilder().create();

            Reader reader = request.bufferedReader();
            v = gson.fromJson(reader, target);
            reader.close();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return v;
    }
/*
    public List<UsersVo> fetchUserMockList() {

        //Mock Data
        List<UsersVo> list = new ArrayList<UsersVo>();

        UsersVo user = new UsersVo();
        user.setId("");
        user.setName("안대혁");
        user.setPhone("010-4761-6934");
        user.setEmail("kickscar@gmail.com");
        user.setProfilePic("https://avatars1.githubusercontent.com/u/482271?v=3&s=460");
        user.setStatus(1);
        list.add(user);

        return list;
    }*/

    private class JSONResultUsersVo extends JSONResult<UsersVo> {

    }


    private class JSONResultNumber extends JSONResult<Integer> {

    }


    private class JSONResultUserList extends JSONResult<List<UsersVo>> {

    /*
    JSON 문자열을 자바 객체로 변환
    @param request
    @param target
    @param <V>
    @return
    * */
    }

}
