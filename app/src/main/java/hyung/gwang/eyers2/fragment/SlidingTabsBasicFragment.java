

package hyung.gwang.eyers2.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.view.FAQActivity;
import hyung.gwang.eyers2.view.MemberTest;
import hyung.gwang.eyers2.view.NoticeActivity;
import hyung.gwang.eyers2.view.MemberActivity;
import hyung.gwang.eyers2.view.FreeBoardActivity;


public class SlidingTabsBasicFragment extends Fragment {

    private String getuser;
    static final String LOG_TAG = "SlidingTabsBasicFragment";

    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private SlidingTabLayout mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;

    /**
     * Inflates the {@link View} which will be displayed by this {@link Fragment}, from the app's
     * resources.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if(getArguments() != null){
            getuser = getArguments().getString("key_id");
            Log.e("프래그먼트에 값전달됬나 확인",getuser);
        }


        return inflater.inflate(R.layout.fragment_sample, container, false);

    }

    // BEGIN_INCLUDE (fragment_onviewcreated)

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);


        mViewPager.setAdapter(new SamplePagerAdapter());
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // 페이지 어댑터 설정
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        // END_INCLUDE (setup_slidingtablayout)
    }


    // END_INCLUDE (fragment_onviewcreated)

    class SamplePagerAdapter extends PagerAdapter {
        String pagenumber;
        private LayoutInflater mInflater;
        /**
         * @return 페이지수
         */
        @Override
        public int getCount() {
            return 4;
        }


        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
                return "공지 사항";
            }
            else if (position == 1){
                return "회원 목록";
            }
            else if (position == 2){
                return "자유 게시판";
            }
            else if (position == 3){
                return "FAQ 게시판";
            }
            return "게시판 " + (position );
        }
        // END_INCLUDE (pageradapter_getpagetitle)

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // Inflate a new layout from our resources
            View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);
            // Add the newly created View to the ViewPager
            container.addView(view);
            Log.e("position!!!", String.valueOf(position));
            // Retrieve a TextView from the inflated View, and update it's text

            switch (position){
                case 0:
                    Button button = (Button) view.findViewById(R.id.testbutton);
                    button.setBackgroundResource(R.drawable.notice);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent;
                            switch (v.getId()) {
                                case R.id.testbutton:
                                        intent = new Intent(v.getContext(), NoticeActivity.class);
                                        intent.putExtra("key_id",getuser);
                                        Log.e("프래그먼트에서 다른데 인텐트될때 값 확인",getuser);
                                        startActivity(intent);
                                        Log.e("pagenumbertest0",pagenumber+"클릭");
                            }
                        }
                    });
                    break;
                case 1:
                    Button button2 = (Button) view.findViewById(R.id.testbutton);
                    button2.setBackgroundResource(R.drawable.member);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent;
                            switch (v.getId()) {
                                case R.id.testbutton:
                                    intent = new Intent(v.getContext(), MemberTest.class);
                                    intent.putExtra("key_id",getuser);
                                    Log.e("프래그먼트에서 다른데 인텐트될때 값 확인",getuser);
                                    startActivity(intent);
                                    Log.e("pagenumbertest1",pagenumber+"클릭");
                            }
                        }
                    });
                    break;
                case 2:
                    Button button3 = (Button) view.findViewById(R.id.testbutton);
                    button3.setBackgroundResource(R.drawable.freeboard);
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent;
                            switch (v.getId()) {
                                case R.id.testbutton:
                                    intent = new Intent(v.getContext(), FreeBoardActivity.class);
                                    intent.putExtra("key_id",getuser);
                                    Log.e("프래그먼트에서 다른데 인텐트될때 값 확인",getuser);
                                    startActivity(intent);
                                    Log.e("pagenumbertest2",pagenumber+"클릭");
                            }
                        }
                    });
                    break;
                case 3:
                    Button button4 = (Button) view.findViewById(R.id.testbutton);
                    button4.setBackgroundResource(R.drawable.faq);
                    button4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent;
                            switch (v.getId()) {
                                case R.id.testbutton:
                                    intent = new Intent(v.getContext(), FAQActivity.class);
                                    intent.putExtra("key_id",getuser);
                                    Log.e("프래그먼트에서 다른데 인텐트될때 값 확인",getuser);
                                    startActivity(intent);
                                    Log.e("pagenumbertest3",pagenumber+"클릭");
                            }
                        }
                    });
            }


            // Return the View
            return view;
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }
    }

}
