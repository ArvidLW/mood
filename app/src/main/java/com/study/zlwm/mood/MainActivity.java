package com.study.zlwm.mood;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //R为类，id为类，toolbar成员变量；JAVA静态内部类访问成员，R.id.toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);//AppCompatActivity中的一个方法，包含bar
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 在屏幕底部显示提示信息
                 */
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /**
         * Note that you can only have one drawer view for each vertical edge of the window.
         * If your layout configures more than one drawer view per vertical edge of the window,
         * an exception will be thrown at runtime.
         * 抽屉布局
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //this.setContentView(R.layout.mood_plan);
    }

    @Override
    //当按下返回键时执行程序
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // void函数要写return,否则会失效，例如这个函数。
        // 如果抽屉菜单是开着的就关闭
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
            Log.d("lw","R.id.my_mood");
            //View myView = findViewById(R.id.mood_plan_layout);
            setContentView(R.layout.mood_plan);

        } else if (id == R.id.mood_road) {
            setContentView(R.layout.activity_main);
            //LayoutInflater inflator =getLayoutInflater();
           // View view11=inflator.inflate(R.layout.mood_plan, null, false);
            //setContentView(R.layout.test);
            //setContentView(view11);

        } else if (id == R.id.mood_plan) {

            LayoutInflater myInflate=LayoutInflater.from(this);
            View myView=myInflate.inflate(R.layout.mood_plan,null);
           ///setContentView(R.layout.activity_main);
            //View my1=myView.findViewById(R.id.mood_tv);
           this.setContentView(myView);

        } else if (id == R.id.good_mood) {
            //this.setContentView(R.layout.mood_plan);

        } else if (id == R.id.night) {
            //setContentView(R.layout.content_main);

        } else if (id == R.id.setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
