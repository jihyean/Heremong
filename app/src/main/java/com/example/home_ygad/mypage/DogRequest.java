package com.example.home_ygad.mypage;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//로그인 요청을 함
public class DogRequest extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://heremong.dothome.co.kr/DogInsert2.php"; //강아지 등록 php
    private Map<String, String> map;

    public DogRequest(String ID, String namei, String Kg, String Sav, String Sex, String agei, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("ID",ID);
        map.put("namei",namei);
        map.put("Kg",Kg);
        map.put("Sav",Sav);
        map.put("Sex",Sex);
        map.put("agei",agei);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
