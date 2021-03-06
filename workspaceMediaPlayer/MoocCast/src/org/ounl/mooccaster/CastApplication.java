/*
 * Copyright (C) 2013 Google Inc. All Rights Reserved.
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

package org.ounl.mooccaster;

import org.ounl.mooccaster.R;
import org.ounl.mooccaster.settings.CastPreference;

import com.google.sample.castcompanionlibrary.cast.VideoCastManager;
import com.google.sample.castcompanionlibrary.utils.Utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * The {@link Application} for this demo application.
 */
public class CastApplication extends Application {
    private static String APPLICATION_ID;
    private static VideoCastManager mCastMgr = null;   
    public static final double VOLUME_INCREMENT = 0.05;
    public static final String COLOR_ORANGE = "#FCC668";
    
    // Default playlist
    //public static final String DEFAUT_CATALOG_URL = "https://dl.dropboxusercontent.com/u/49435539/cast/documentB5.json";
    //public static final String DEFAUT_CATALOG_URL = "https://www.dropbox.com/s/2fzq6bn61lieved/documentB5.json?dl=0";
    //public static final String DEFAUT_CATALOG_URL = "https://drive.upm.es/index.php/s/YOli0aHydrpi47w/download";
    //public static final String DEFAUT_CATALOG_URL = "https://github.com/btabuenca/Android/blob/master/workspaceNFCMediaPlayer/lifelong-learning-hub.mediaplayer/video_list_en.json";
    public static final String DEFAUT_CATALOG_URL = "https://raw.githubusercontent.com/btabuenca/Android/master/workspaceNFCMediaPlayer/lifelong-learning-hub.mediaplayer/video_list_en.json";
    
    private String mUrlPlayList;
    
//    public static final String CATALOG_URL =
//           "https://dl.dropboxusercontent.com/u/49435539/cast/document.json";    
//    public static final String CATALOG_URL =
//            "https://dl.dropboxusercontent.com/u/49435539/video_list_es.json";    
//    public static final String CATALOG_URL =
//            "https://dl.dropboxusercontent.com/u/49435539/video_list_es.json";    
//    public static final String CATALOG_URL =
//            "https://dl.dropboxusercontent.com/u/49435539/mobile_list_ou.json";    
        
    
    
    /*
     * (non-Javadoc)
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        APPLICATION_ID = getString(R.string.app_id);
        Utils.saveFloatToPreference(getApplicationContext(),
                VideoCastManager.PREFS_KEY_VOLUME_INCREMENT, (float) VOLUME_INCREMENT);
        
        
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);        
        mUrlPlayList = sp.getString(CastPreference.KEY_URLPLAYLIST, DEFAUT_CATALOG_URL);
        		

    }

    public static VideoCastManager getCastManager(Context context) {
        if (null == mCastMgr) {
            mCastMgr = VideoCastManager.initialize(context, APPLICATION_ID, null, null);
            mCastMgr.enableFeatures(
                    VideoCastManager.FEATURE_NOTIFICATION |
                            VideoCastManager.FEATURE_LOCKSCREEN |
                            VideoCastManager.FEATURE_WIFI_RECONNECT |
                            VideoCastManager.FEATURE_CAPTIONS_PREFERENCE |
                            VideoCastManager.FEATURE_DEBUGGING);

        }
        mCastMgr.setContext(context);
        String destroyOnExitStr = Utils.getStringFromPreference(context,
                CastPreference.TERMINATION_POLICY_KEY);
        mCastMgr.setStopOnDisconnect(null != destroyOnExitStr
                && CastPreference.STOP_ON_DISCONNECT.equals(destroyOnExitStr));
        return mCastMgr;
    }
    
	public String getUrlPlayList() {		
		return mUrlPlayList;
	}

	public void setUrlPlayList(String mUrlPlayList) {
		this.mUrlPlayList = mUrlPlayList;
	}    
    

}
