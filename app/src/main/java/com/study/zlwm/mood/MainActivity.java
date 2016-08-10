package com.study.zlwm.mood;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.study.zlwm.mood.fragment.FragmentMoodPlan;
import com.study.zlwm.mood.fragment.FragmentMyMood;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<String> fragmentTag=new ArrayList<String>();
    private MoodFragmentAdapter moodFragmentAdapter;
    private ViewPager viewPager;
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

        //this.setContentView(R.layout.activity_main);

        /*setContentView(R.layout.main);
        // 以findViewById()取得Button对象并添加事件onClickLisener
        Button button1=(Button)findViewById(R.id.bt1);
        button1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToLayout2();


            }});*/
    }
 /*   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 以findViewById()取得Button对象并添加事件onClickLisener
        Button button1=(Button)findViewById(R.id.bt1);
        button1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToLayout2();


            }});

    }*/
    /*public void goToLayout2() {
        // 将layout改成mylayout
        setContentView(R.layout.mylayout);
        Button b2 = (Button) findViewById(R.id.bt2);
        b2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                goToLayout1();
            }
        });
    }
    // 将layout由mylayout.xml切换成main.xml
    public void goToLayout1() {
        setContentView(R.layout.main);
        Button bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                goToLayout2();
            }
        });
    }*/

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

           /* FragmentTransaction mytransaction=showFragment("my_mood");
            if( mytransaction!=null ){

                Fragment fragment1=new FragmentMyMood();
                mytransaction.add(R.id.fragment_container,fragment1,"my_mood");
                fragmentTag.add("my_mood");
                mytransaction.commit();
            }*/
            getSupportActionBar().setTitle("我的心情");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            FragmentMyMood fragment1=new FragmentMyMood();
            System.out.println("刘炜99999"+fragment1.getView());//为空为啥
            transaction.replace(R.id.fragment_container,fragment1,"my_mood");
            transaction.commit();

            if(moodFragmentAdapter==null)
            {
                moodFragmentAdapter=new MoodFragmentAdapter(getSupportFragmentManager());
            }
            System.out.println("刘炜99999"+fragment1.getView());//为空为啥
            viewPager = (ViewPager) fragment1.getView().findViewById(R.id.view_pager);
            viewPager.setAdapter(moodFragmentAdapter);
            viewPager.setCurrentItem(0);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


        } else if (id == R.id.mood_road) {
            getSupportActionBar().setTitle("心历路程");

        } else if (id == R.id.mood_plan) {

           /* FragmentTransaction mytransaction=showFragment("mood_plan");
            if( mytransaction!=null ){

                Fragment fragment1=new FragmentMoodPlan();
                mytransaction.add(R.id.fragment_container,fragment1,"mood_plan");
                fragmentTag.add("mood_plan");
                mytransaction.commit();
            }*/
            getSupportActionBar().setTitle("心情计划");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            FragmentMoodPlan fragment1=new FragmentMoodPlan();
            transaction.replace(R.id.fragment_container,fragment1,"mood_plan");
            transaction.commit();

        } else if (id == R.id.good_mood) {
            getSupportActionBar().setTitle("心情圈");

        } else if (id == R.id.night) {

        } else if (id == R.id.setting) {

        }
        //原来是这里的才是问题，我草
        //选了之后关闭
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*private FragmentTransaction showFragment(String tag)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        for (String tmp:fragmentTag)
        {
            Fragment fragment1=manager.findFragmentByTag(tmp);
            transaction.hide(fragment1);
        }

        if(fragmentTag.contains(tag))
        {
            Fragment fragment1=manager.findFragmentByTag(tag);
            transaction.show(fragment1);
            transaction.commit();
            return null;
        }
        else
        {
            return transaction;
        }

    }*/
}
