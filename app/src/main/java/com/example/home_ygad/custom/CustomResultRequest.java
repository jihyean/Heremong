package com.example.home_ygad.custom;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CustomResultRequest extends StringRequest {


    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://heremong.dothome.co.kr/Custom.php";
    private Map<String, String> map;


    public CustomResultRequest( String SB, String Dog_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();

        map.put("SB",SB);
        map.put("Dog_id",Dog_id);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
