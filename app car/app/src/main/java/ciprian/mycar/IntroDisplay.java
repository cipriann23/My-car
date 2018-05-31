package ciprian.mycar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Cirpian on 5/31/2018.
 */

public class IntroDisplay extends Activity {
    private ViewPager viewPager;
    public IntroManager intromanager;
    private int[] layouts;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    private ViewPagerAdapter viewPagerAdapter;
    Button next,skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_display);
        intromanager = new IntroManager(IntroDisplay.this);
        /*
        if(Build.VERSION.SDK_INT>=21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        */


        viewPager = findViewById(R.id.viewpager);
        dotsLayout = findViewById(R.id.layoutDots);
        skip = findViewById(R.id.btn_skip);
        next = findViewById(R.id.btn_next);
        next.bringToFront();
        layouts =  new int[]{R.layout.intro_singin, R.layout.intro_setnumber,
                R.layout.intro_info1, R.layout.intro_info2};
        addBottomDots(0);
        viewPagerAdapter = new IntroDisplay.ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);
        skip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                intromanager.setFirst(false);
                Intent i = new Intent(IntroDisplay.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if(current<layouts.length){
                    viewPager.setCurrentItem(current);
                }else{
                    intromanager.setFirst(false);
                    Intent i = new Intent(IntroDisplay.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    private void addBottomDots(int position){

        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[position].setTextColor(colorActive[position]);
    }

    private int getItem(int i){
        return viewPager.getCurrentItem() + 1;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if(position==layouts.length-1){
                next.setText("PROCEED");
                skip.setVisibility(View.GONE);
            }else{
                next.setText("NEXT");
                skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        @Override
        public Object instantiateItem(ViewGroup container, int position){
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }
        @Override
        public int getCount(){
            return layouts.length;
        }
        @Override
        public boolean isViewFromObject(View view, Object object){
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }
    }
}
