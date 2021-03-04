package com.qchemmo.ktplayer.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.qchemmo.ktplayer.R
import com.qchemmo.ktplayer.ui.activity.AboutActivity


class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.setting)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        when(preference?.key){
            "about"->{
                startActivity(Intent(activity,AboutActivity::class.java))
            }
        }
        return super.onPreferenceTreeClick(preference)
    }
}