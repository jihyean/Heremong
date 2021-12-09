package com.example.home_ygad.search;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest_filter extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://heremong.dothome.co.kr/SearchF.php";
    private Map<String, String> map;

    public SearchRequest_filter(String ID, String SB, String Dkg, String Grade, String Dsav, String Cate, String Paddress, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();

        map.put("ID",ID);
        map.put("SB",SB);
        map.put("Dkg",Dkg);
        map.put("Grade",Grade);
        map.put("Dsav",Dsav);
        map.put("Cate",Cate);
        map.put("Paddress",Paddress);
        // map.put("D_sav",D_sav);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
