package ciprian.mycar;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_white);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        ListView lv = findViewById(R.id.list_number);
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        final String[] items_numbers = new String[]{"SV-13-ANA", "IS-32-ISU", "B-999-WLL"};

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items_numbers);
        lv.setAdapter(itemsAdapter);

        int ratio = getScreenWidth()/ (5/2);
        //setMeasuredDimension(ratio);
    }
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    void setMeasuredDimension(int height) {

        ConstraintLayout one = findViewById(R.id.status_one);
        ViewGroup.LayoutParams params_one = one.getLayoutParams();
        params_one.height = height;
        params_one.width = 0;
        one.setLayoutParams(params_one);

        ConstraintLayout two = findViewById(R.id.status_two);
        ViewGroup.LayoutParams params_two = one.getLayoutParams();
        params_two.height = height;
        params_two.width = 0;
        two.setLayoutParams(params_two);

        ConstraintLayout tree = findViewById(R.id.status_tree);
        ViewGroup.LayoutParams params_tree = one.getLayoutParams();
        params_tree.height = height;
        params_tree.width = 0;
        tree.setLayoutParams(params_tree);

        ConstraintLayout four = findViewById(R.id.status_four);
        ViewGroup.LayoutParams params_four = one.getLayoutParams();
        params_four.height = height;
        params_four.width = 0;
        four.setLayoutParams(params_four);
    }
}
