// Copyright 2011, Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.dfp.showcase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main activity class for application. Displays a list of bundled and premium DFP creative types.
 *
 * @author api.eleichtenschl@gmail.com (Eric Leichtenschlag)
 */
public class DfpShowcase extends Activity {
  private static final String TAB_TAG = "tab";
  private static final String INDICATOR_BUNDLED = "Bundled";
  private static final String INDICATOR_PREMIUM = "Premium";
  private TabHost tabHost;

  /** Called when the activity is first created. */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    this.tabHost = (TabHost) findViewById(android.R.id.tabhost);
    this.tabHost.setup();

    // Initialize the tabs.
    this.tabHost.addTab(this.tabHost.newTabSpec(INDICATOR_BUNDLED)
        .setIndicator(INDICATOR_BUNDLED)
        .setContent(new TabFactory(this)));
    this.tabHost.addTab(this.tabHost.newTabSpec(INDICATOR_PREMIUM)
        .setIndicator(INDICATOR_PREMIUM)
        .setContent(new TabFactory(this)));

    // Set the current tab if one exists.
    if (savedInstanceState != null) {
      this.tabHost.setCurrentTabByTag(savedInstanceState.getString(TAB_TAG));
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(TAB_TAG, this.tabHost.getCurrentTabTag());
  }

  /** Creates an empty tab. */
  static class TabFactory implements TabContentFactory {
    private final Activity activity;

    public TabFactory(Activity activity) {
      this.activity = activity;
    }

    @Override
    public View createTabContent(String tag) {
      ListView listView = new ListView(this.activity);
      if (INDICATOR_BUNDLED.equals(tag)) {
        listView.setAdapter(new ListAdapter(this.activity, AdFormat.BUNDLED_AD_FORMATS));
      } else if (INDICATOR_PREMIUM.equals(tag)) {
        listView.setAdapter(new ListAdapter(this.activity, AdFormat.PREMIUM_AD_FORMATS));
      }
      listView.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          TextView textView = (TextView) view.findViewById(R.id.adType);
          if (textView != null) {
            Toast.makeText(activity, textView.getText(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity, DisplayAdView.class);
            // Pass the ad type to the DisplayAdView activity.
            intent.putExtra(Constants.AD_FORMAT_KEY, textView.getText());
            activity.startActivity(intent);
          }
        }
      });
      return listView;
    }
  }
}
