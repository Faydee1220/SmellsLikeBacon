package com.rq.smellslikebacon;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends LoggingActivity implements ListFragment.OnRecipeSelectedInterface {

    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 修正轉向時重複產生 Fragment
        // 找不到 fragment 時會回傳 null（第一次建立時）
        // 因為兩個 fragment 是用同一個 id ，在第二個 fragment 轉向時會出錯，可改用 Tag 區分
//        ListFragment savedFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.placeholder);
        ListFragment savedFragment = (ListFragment) getFragmentManager().findFragmentByTag(LIST_FRAGMENT);

        if (savedFragment == null) {
            ListFragment fragment = new ListFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // 第三個參數是 tag
            fragmentTransaction.add(R.id.placeholder, fragment, LIST_FRAGMENT);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onListRecipeSelected(int index) {
//        Toast.makeText(this, Recipes.names[index], Toast.LENGTH_SHORT).show();

        ViewPagerFragment fragment = new ViewPagerFragment();

        // 傳遞參數
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 用 replace 更換新的 fragment
        fragmentTransaction.replace(R.id.placeholder, fragment, VIEWPAGER_FRAGMENT);
        // 修正返回鍵，預設會跳出 App，此處改成回上一頁
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
