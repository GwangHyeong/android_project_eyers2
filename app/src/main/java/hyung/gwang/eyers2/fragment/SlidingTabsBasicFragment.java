/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hyung.gwang.eyers2.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.view.PageTestFour;
import hyung.gwang.eyers2.view.PageTestOne;
import hyung.gwang.eyers2.view.PageTestThree;
import hyung.gwang.eyers2.view.PageTestTwo;


public class SlidingTabsBasicFragment extends Fragment {


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

//        super.onCreateView(inflater, container, savedInstanceState);


        return inflater.inflate(R.layout.fragment_sample, container, false);

    }

    // BEGIN_INCLUDE (fragment_onviewcreated)
    /**
     * This is called after the {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has finished.
     * Here we can pick out the {@link View}s we need to configure from the content view.
     *
     * We set the {@link ViewPager}'s adapter to be an instance of {@link SamplePagerAdapter}. The
     * {@link SlidingTabLayout} is then given the {@link ViewPager} so that it can populate itself.
     *
     * @param view View created in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);


        mViewPager.setAdapter(new SamplePagerAdapter());
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
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

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)
        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
                return "공지 사항";
            }
            else if (position == 1){
                return "족보 게시판";
            }
            else if (position == 2){
                return "자유 게시판";
            }
            else if (position == 3){
                return "QnA 게시판";
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
            TextView title = (TextView) view.findViewById(R.id.item_title);

            switch (position){
                case 0:
                    Button button = (Button) view.findViewById(R.id.testbutton);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent;
                            switch (v.getId()) {
                                case R.id.testbutton:
                                        intent = new Intent(v.getContext(), PageTestOne.class);
                                        startActivity(intent);
                                        Log.e("pagenumbertest0",pagenumber+"클릭");
                            }
                        }
                    });
                    break;
                case 1:
                    Button button2 = (Button) view.findViewById(R.id.testbutton);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent;
                            switch (v.getId()) {
                                case R.id.testbutton:
                                    intent = new Intent(v.getContext(), PageTestTwo.class);
                                    startActivity(intent);
                                    Log.e("pagenumbertest1",pagenumber+"클릭");
                            }
                        }
                    });
                    break;
                case 2:
                    Button button3 = (Button) view.findViewById(R.id.testbutton);
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent;
                            switch (v.getId()) {
                                case R.id.testbutton:
                                    intent = new Intent(v.getContext(), PageTestThree.class);
                                    startActivity(intent);
                                    Log.e("pagenumbertest2",pagenumber+"클릭");
                            }
                        }
                    });
                    break;
                case 3:
                    Button button4 = (Button) view.findViewById(R.id.testbutton);
                    button4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent;
                            switch (v.getId()) {
                                case R.id.testbutton:
                                    intent = new Intent(v.getContext(), PageTestFour.class);
                                    startActivity(intent);
                                    Log.e("pagenumbertest3",pagenumber+"클릭");
                            }
                        }
                    });
            }

            title.setText(String.valueOf(position ));
            pagenumber =  title.getText().toString();


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
