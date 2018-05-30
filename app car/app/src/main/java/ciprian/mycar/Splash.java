package ciprian.mycar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Splash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    private ViewPager viewPager;
    public IntroManager intromanager;
    private int[] layouts;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    private ViewPagerAdapter viewPagerAdapter;
    Button next,skip;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.intro);


        ImageView logo = findViewById(R.id.imageView4);

        logo.bringToFront();

        Animation animSlide_start = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_start);

        // Start the animation like this
        logo.startAnimation(animSlide_start);


        Handler handler = new Handler();


        handler.postDelayed(new Runnable() {
            public void run() {
                // Start the animation like this
                TextView moto = findViewById(R.id.textView2);
                Animation anim_fadein = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade_in);
                moto.startAnimation(anim_fadein);
                moto.setVisibility(View.VISIBLE);
            }
        }, 600);   //2,5 seconds

        handler.postDelayed(new Runnable() {
            public void run() {
                // Start the animation like this
                TextView moto = findViewById(R.id.textView2);
                moto.setVisibility(View.VISIBLE);
            }
        }, 1600);

        handler.postDelayed(new Runnable() {
            public void run() {
                // Start the animation like this
                TextView moto = findViewById(R.id.textView2);
                Animation anim_fadeout = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade_out);
                moto.startAnimation(anim_fadeout);
            }
        }, 4500);

        handler.postDelayed(new Runnable() {
            public void run() {
                // Start the animation like this
                TextView moto = findViewById(R.id.textView2);
                moto.setVisibility(View.INVISIBLE);
            }
        }, 4950);


        handler.postDelayed(new Runnable() {
            public void run() {
                // Start the animation like this
                ImageView logo = findViewById(R.id.imageView4);
                Animation animSlide_end = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slide);
                logo.startAnimation(animSlide_end);
            }
        }, 4500);





        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                /*
                 //Create an Intent that will start the Menu-Activity.
                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
                //runFadeOutAnimation();
                */

                intromanager = new IntroManager(Splash.this);
                if(!intromanager.Check()){
                    intromanager.setFirst(false);
                    Intent i = new Intent(Splash.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }
                if(Build.VERSION.SDK_INT>=21){
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                }


                viewPager = (ViewPager) findViewById(R.id.viewpager);
                dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
                skip = findViewById(R.id.btn_skip);
                next = findViewById(R.id.btn_next);
                layouts =  new int[]{R.layout.intro_singin, R.layout.intro_setnumber,
                        R.layout.intro_info1, R.layout.intro_info2};
                addBottomDots(0);
                viewPagerAdapter = new ViewPagerAdapter();
                viewPager.setAdapter(viewPagerAdapter);
                viewPager.addOnPageChangeListener(viewListener);
                skip.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        intromanager.setFirst(false);
                        Intent i = new Intent(Splash.this,MainActivity.class);
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
                            Intent i = new Intent(Splash.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    private void runFadeOutAnimation() {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        a.reset();
        ConstraintLayout ll = findViewById(R.id.linearLayout);
        ll.clearAnimation();
        ll.startAnimation(a);
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

    public class ViewPagerAdapter extends PagerAdapter{
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