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
    public IntroManager intromanager;
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

                //runFadeOutAnimation();
                */

                intromanager = new IntroManager(Splash.this);
                if(!intromanager.Check()){
                    intromanager.setFirst(false);
                    Intent i = new Intent(Splash.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent mainIntent = new Intent(Splash.this,IntroDisplay.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }

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


}