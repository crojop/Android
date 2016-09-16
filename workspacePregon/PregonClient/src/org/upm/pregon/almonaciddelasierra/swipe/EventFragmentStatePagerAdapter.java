 /*******************************************************************************
 * Copyright (C) 2014 Open University of The Netherlands
 * Author: Bernardo Tabuenca Archilla
 * LearnTracker project 
 * 
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.upm.pregon.almonaciddelasierra.swipe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import org.upm.pregon.almonaciddelasierra.db.tables.EventDb;

import java.util.List;

/**
 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
 * representing an object in the collection.
 */
public class EventFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

	private String CLASSNAME = this.getClass().getName();
	
	private List<EventFragment> mFragments;
	private List<EventDb> mEvents;
	

    public EventFragmentStatePagerAdapter(FragmentManager fm, List<EventFragment> fragments, List<EventDb> events) {
        super(fm);        
        mFragments = fragments;
        mEvents = events;
    }
    


    @Override
    public Fragment getItem(int i) {
    	
    	Log.d(CLASSNAME, "getItem EventFragmentStatePagerAdapter position["+i+"].");
    	
    	
//        Fragment fragment = mFragments.get(i);        
//        Bundle args = new Bundle();
//        args.putInt(EventFragment.ARG_POSITION, i);
//        fragment.setArguments(args);
        
        
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    
    /**
     * This is the header on top of the screen
     */
    @Override
    public CharSequence getPageTitle(int position) {
    	
    	Log.d(CLASSNAME, "getPageTitle EventFragmentStatePagerAdapter position["+position+"].");
    	
    	if (position >= getCount()){
    		// Index out of bounds        	
            return "Index out of bounds. There is something wrong here :(";    		
    	}else{
    		return ""+mEvents.get(position).getFormattedTaskDateStart();
    	}
    	
    }

  
    @Override
    public int getItemPosition(Object item) {
    	EventFragment dayF = (EventFragment)item;
    	
    	Log.d(CLASSNAME, "getItemPosition EventFragmentStatePagerAdapter super.position["+super.getItemPosition(item)+"] positon["+dayF.getPosition()+"].");
    	
        return POSITION_NONE;
    }
    
    
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        Log.d(CLASSNAME, "notifyDataSetChanged EventFragmentStatePagerAdapter.");
        
    }
    
    

	
}