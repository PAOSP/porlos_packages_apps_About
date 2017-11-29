package paosp.about;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.joaquimley.faboptions.FabOptions;
//import com.sdsmdg.tastytoast.TastyToast;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FabOptions fabOptions = (FabOptions) findViewById(R.id.fab_options);
        fabOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.faboptions_home: {
                        Snackbar.make(view, "Opening paosp Google+ Community", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(getString(R.string.paosp_url)));
                        startActivity(intent);
                        break;
                    }
                    case R.id.faboptions_tg: {
                        Snackbar.make(view, "Opening Telegram Channel for paosp-CAF", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(getString(R.string.paosp_channel)));
                        startActivity(intent);
                        break;
                    }
                    case R.id.faboptions_git: {
                        Snackbar.make(view, "Opening paosp Source Github", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(getString(R.string.paosp_github)));
                        startActivity(intent);
                        break;
                    }
                    case R.id.faboptions_log: {
                        boolean appPresent = appCheck("com.nolanlawson.logcat");

                        if (appPresent) {
                            Snackbar.make(view, "Opening Logcat Reader", Snackbar.LENGTH_LONG).show();
                            Intent intent = new Intent("com.nolanlawson.logcat.intents.LAUNCH");
                            startActivity(intent);
                        } else {
                            Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                            i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.nolanlawson.logcat"));
                            startActivity(i);
                        }

                    }
                    default:
                }
            }
        });

    }

    private boolean appCheck(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_present;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_present = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_present = false;
        }
        return app_present;
    }

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    //#1
    public static class AboutFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public AboutFragment() {
        }

        public static AboutFragment newInstance(int sectionNumber) {
            AboutFragment fragment = new AboutFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.content_about, container, false);
            return rootView;
        }
    }

    //#2
    public static class TCFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public TCFragment() {
        }

        public static TCFragment newInstance(int sectionNumber) {
            TCFragment fragment = new TCFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            String listArray1[] = new String[]{
                    "edi194",
                    "LuckShadow",
                    "MattRoot",

            };

            String listArray2[] = new String[]{
                    "kutas",
                    "większy kutas",
                    "jeszcze większy kutas",

            };

            int bgimageArray[] = new int[]{
                    R.drawable.edi194_bg,
                    R.drawable.luck_bg,
                    R.drawable.matt_bg,
            };

            int imageArray[] = new int[]{
                    R.drawable.edi194,
                    R.drawable.luck,
                    R.drawable.matt,
            };

            ListView listView = (ListView) rootView.findViewById(R.id.list);
            listView.setAdapter(new TCAdapter(getActivity(), listArray1, listArray2, bgimageArray, imageArray));
            listView.setNestedScrollingEnabled(true);
            return rootView;
        }
    }

    //#3
    public static class MaintainerFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public MaintainerFragment() {
        }

        public static MaintainerFragment newInstance(int sectionNumber) {
            MaintainerFragment fragment = new MaintainerFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            String listArray1[] = new String[]{
                    "edi194",
                    "LuckShadow",
                    "MattRoot",
            };

            String listArray2[] = new String[]{
                    "mido (nougat)",
                    "mido (oreo)",
                    "cancro (nougat)",
            };

            ListView listView = (ListView) rootView.findViewById(R.id.list);
            listView.setAdapter(new MaintainerAdapter(getActivity(), listArray1, listArray2));
            listView.setNestedScrollingEnabled(true);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AboutFragment.newInstance(position + 1);
                case 1:
                    return TCFragment.newInstance(position + 1);
                case 2:
                    return MaintainerFragment.newInstance(position + 1);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "About";
                case 1:
                    return "Team";
                case 2:
                    return "Maintainers";
            }
            return null;
        }
    }
}
