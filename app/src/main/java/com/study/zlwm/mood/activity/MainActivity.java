package com.study.zlwm.mood.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.fragment.FragmentMoodPlan;
import com.study.zlwm.mood.fragment.FragmentMyMood;
import com.study.zlwm.mood.fragment.MoodRouteFragment;
import com.study.zlwm.mood.global.GlobalInfo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //private ArrayList<String> fragmentTag=new ArrayList<String>();
    //private MoodFragmentAdapter moodFragmentAdapter;
    //private ViewPager viewPager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //R为类，id为类，toolbar成员变量；JAVA静态内部类访问成员，R.id.toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);//AppCompatActivity中的一个方法，包含bar
        toolbar.setTitle("我的心情");
        setSupportActionBar(toolbar);

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
       //     @Override
        //    public void onClick(View view) {
                /**
                 * 在屏幕底部显示提示信息
                 */
           //     Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
           //             .setAction("Action", null).show();
          //  }
        //});
        /**
         * Note that you can only have one drawer view for each vertical edge of the window.
         * If your layout configures more than one drawer view per vertical edge of the window,
         * an exception will be thrown at runtime.
         * 抽屉布局
         */
        //点击的按钮
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();/*实现左上角那个点击按钮与抽屉同步*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void setUser() {
        SharedPreferences preferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        //第二个参数为默认值
        String user_id=preferences.getString("user_id", "指路为码");
        String name=preferences.getString("name", "华为比赛");
        TextView headUserId= (TextView) findViewById(R.id.head_user_id);
        TextView headUsername= (TextView) findViewById(R.id.head_user_name);
        //System.out.println("uuuuu"+user_id+"|"+headUserId+"|"+headUsername);
        headUserId.setText(user_id);
        headUsername.setText(name);

        //设置全局变量
        GlobalInfo globalInfo= (GlobalInfo) getApplication();
        globalInfo.setTel_id(user_id);
        globalInfo.setUsername(name);
    }


    @Override
    //当按下返回键时执行程序
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // void函数要写return,否则会失效，例如这个函数。
        // 点击返回建时，如果抽屉菜单是开着的就关闭
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            Log.d("lw","onbackpressed_open");
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.d("lw","onbackpressed_open_else");
            super.onBackPressed();
        }
    }

    @Override
    //加载menu  sdk3.0以后menu包含在actionbar中
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        setUser();//当menu被加载时才能获取到相应侧栏的view
        Log.d("lw","加载menu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d("lw","进入onOptions");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Log.d("lw","进入onNavigation");
        if (id == R.id.my_mood) {

            //为什么不行，可能因为切换后这个类又设置监听去了，但又没有找到设置监听的对象
            Log.d("lw","R.id.my_mood");

            getSupportActionBar().setTitle("我的心情");
            FragmentManager fragmentManager= getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentMyMood fragmentMyMood=new FragmentMyMood();
            fragmentTransaction.replace(R.id.fragment_container,fragmentMyMood,"my_mood");
            fragmentTransaction.commit();

        } else if (id == R.id.mood_road) {
            getSupportActionBar().setTitle("心历路程");
            MoodRouteFragment moodRouteFragment=new MoodRouteFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,moodRouteFragment,"mood_road").commit();

        } else if (id == R.id.mood_plan) {

            getSupportActionBar().setTitle("心情计划");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            FragmentMoodPlan fragment1=new FragmentMoodPlan();
            transaction.replace(R.id.fragment_container,fragment1,"mood_plan");
            transaction.commit();

        } else if (id == R.id.good_mood) {
            getSupportActionBar().setTitle("心情圈");

        } else if (id == R.id.login) {
            Intent intent = new Intent();
            intent.setClass(this,LoginActivity.class);
            // 启动需要监听返回值的Activity，并设置请求码：requestCode
            startActivityForResult(intent,1);
//            getSupportActionBar().setTitle("心情计划");
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            FragmentMoodPlan fragment1=new FragmentMoodPlan();
//            transaction.replace(R.id.fragment_container,fragment1,"mood_plan");
//            transaction.commit();

        }else if(id==R.id.register) {
            Intent intent = new Intent();
            intent.setClass(this,RegisterActivity.class);
            startActivity(intent);
        }
        //原来是这里的才是问题，我草
        //选了之后关闭
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 当otherActivity中返回数据的时候，会响应此方法
        // requestCode和resultCode必须与请求startActivityForResult()和返回setResult()的时候传入的值一致。
        //请求码结果码来区别来自哪的响应
        if (requestCode == 1 && resultCode == 1) {
            Bundle bundle = data.getExtras();
            String user_id = bundle.getString("user_id");
            String name = bundle.getString("name");
            //Log.i(TAG,"onActivityResult: "+ strResult);
            TextView headUserId= (TextView) findViewById(R.id.head_user_id);
            TextView headUsername= (TextView) findViewById(R.id.head_user_name);
            headUserId.setText(user_id);
            headUsername.setText(name);
            Toast.makeText(MainActivity.this, user_id + ":" + name, Toast.LENGTH_SHORT).show();
        }
    }

}
