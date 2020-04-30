package hyung.gwang.eyers2;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import hyung.gwang.eyers2.fragment.SlidingTabsBasicFragment;

public class MainActivity extends AppCompatActivity {

    String pagetest;
    public static final String TAG = "MainActivity";
    Button testbutton;
    // Whether the Log Fragment is currently shown
    private boolean mLogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsBasicFragment fragment = new SlidingTabsBasicFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        //상태바 색 변경
        actionbarcolor();


    }

    //상태바 색상 변경
    private void actionbarcolor() {
        //SoftDevelpmentKit 버전이 LOLLIPOP(21)과 같거나 더 높을때,
        //StatusBarColor 색상변경, 그밑에 버전들은 변경 안되는듯.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#8977ad"));
        }
    }
}
