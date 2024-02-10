package edu.utem.ftmk.webservicesapi;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import edu.utem.ftmk.webservicesapi.ui.main.GetJokeActivityFrag;
import edu.utem.ftmk.webservicesapi.ui.main.GetUniversityFrag;

public class SectionsPagerAdapter extends FragmentPagerAdapter{
    private final Context mContext;
    private int totalTabs;

    public SectionsPagerAdapter(Context context, FragmentManager fm, int totalTabs){
        super(fm);
        mContext = context;
        this.totalTabs=totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
    switch (position)
    {
        case 0:
            GetJokeActivityFrag jokeActivityFrag = new GetJokeActivityFrag();
            return jokeActivityFrag;
        case 1:
            GetUniversityFrag getUniversityFrag = new GetUniversityFrag();
            return getUniversityFrag;
    }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
