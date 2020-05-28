package com.josep.ootmplanner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TodoFragmentAdapter extends FragmentPagerAdapter {

    public TodoFragmentAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        if(position == 0)
            return new IncompleteTodoFragment();
        else
            return new CompleteTodoFragment();
    }

    @Override
    public int getCount(){
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position == 0)
            return "Incomplete";
        else
            return "Complete";
    }
}
