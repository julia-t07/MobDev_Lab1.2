package ua.kpi.comsys.io8223.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ua.kpi.comsys.io8223.FirstFragment;
import ua.kpi.comsys.io8223.SecondFragment;
import ua.kpi.comsys.io8223.R;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    Drawable picture;
    String name;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FirstFragment();
                break;
            case 1:
                fragment = new SecondFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                picture = mContext.getResources().
                        getDrawable(R.drawable.ic_action_name);
                name = mContext.getResources().getString(TAB_TITLES[0]);
                break;
            case 1:
                picture = mContext.getResources().
                        getDrawable(R.drawable.ic_action_name2);
                name = mContext.getResources().getString(TAB_TITLES[1]);
                break;
            default:
                //TODO: handle default selection
                break;
        }

        SpannableStringBuilder sb = new SpannableStringBuilder(" \n" + name); // space added before text for convenience

        picture.setBounds(5, 5, picture.getIntrinsicWidth(), picture.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(picture, DynamicDrawableSpan.ALIGN_BASELINE);
        sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }


    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}