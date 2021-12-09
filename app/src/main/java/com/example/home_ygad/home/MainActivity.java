package com.example.home_ygad.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.home_ygad.R;
import com.example.home_ygad.map.Fragment_Map;
import com.example.home_ygad.mypage.Fragment_Mypage;
import com.example.home_ygad.shoplist.Fragment_Like;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNV;

    private Toolbar toolbar;

   // private static final String TAG_PARENT = "TAG_PARENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar=findViewById(R.id.MyToolBar); //상단 툴바 id
        setSupportActionBar(toolbar); // appbar xml 레이아웃이 툴바여야 set 할수있음

        mBottomNV = findViewById(R.id.nav_view);//하단 네비바 id
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId()); //하단 네비바 아이콘이 셀렉트 됐을 때 호출되는 함수

                return true;
            }
        });

        mBottomNV.setSelectedItemId(R.id.navigation_Home); //기본 네비1아이콘이 선택되어있다




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {    //res menu 상단 바
        MenuInflater inflater=getMenuInflater(); //인플레이터로 menu xml을 호출
        inflater.inflate(R.menu.menu,menu);

        MenuItem.OnActionExpandListener onActionExpandListener=new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) { //매뉴가 열렸을 때
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                return true; //현재는 토스트 창을 띄우는걸로설정해놓았음
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) { //닫혔을 때
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                return true;
            }
        };
        menu.findItem(R.id.search).setOnActionExpandListener(onActionExpandListener);
        SearchView searchView=(SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("이곳에서 검색하세요"); //검색창 힌트
        return true;

    }





    //BottomNavigation 프라그먼트 변경
    private void BottomNavigate(int id) {
        String tag = String.valueOf(id); //tag는 고유명사로 id의 string 값

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //이곳에서 기본 프라그먼트 매니저와 트랜젝션을 설정함.


        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);

        //  findFragmentByTag 를 이용해 해당 태그인지 찾아 주는걸로.
        if (fragment == null) {
            if (id == R.id.navigation_Home) {  //홈화면 아이콘 터치
                fragment = new Fragment_Home();
            } else if (id == R.id.navigation_Map){ //지도화면
                fragment = new Fragment_Map();}
//             else if (id == R.id.navigation_Customized) {
//                fragment = new Fragment_Customized();
//            }

            else if (id == R.id.navigation_Like){ //찜 화면

                fragment = new Fragment_Like();
            }else if (id == R.id.navigation_MyPage){ //마이페이지 화면

                fragment = new Fragment_Mypage();
            }

//            else if (id == R.id.navigation_Customized){ // 맞춤 화면
//
//
//            }
            fragmentTransaction.add(R.id.content_layout, fragment, tag);
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        //  우리는 네비게이션바 라는 임플리먼트를 사용하니까. 이 함수는 현재 활성 프래그먼트를 설정하는 함수임.
        //
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();


    }

    @Override
    public void onBackPressed() { //뒤로가기 버튼 처리법. 이중 프라그먼트를 사용하면 처리해주는것이 좋다는데 아직 건들기는 어려운데..

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 아..이 onBackPressed는 메인에 있는게 맞는거같은데...
}