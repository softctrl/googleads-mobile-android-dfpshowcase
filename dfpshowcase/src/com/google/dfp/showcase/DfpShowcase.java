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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity class for application.  Displays a list of different DFP ad
 * examples.
 *
 * @author api.eleichtenschl@gmail.com (Eric Leichtenschlag)
 */
public class DfpShowcase extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    ListView listView = (ListView) findViewById(R.id.listView);
    if (listView != null) {
      ListAdapter listAdapter = new ListAdapter(DfpShowcase.this, generateListItems());
      listView.setAdapter(listAdapter);
      listView.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapt, View view, int position, long id) {
          TextView textView = (TextView) view.findViewById(R.id.adType);
          if (textView != null) {
            Toast.makeText(getApplicationContext(), textView.getText(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DfpShowcase.this, DisplayAdView.class);
            // Pass the ad type to the DisplayAdView activity.
            intent.putExtra("adType", textView.getText());
            startActivity(intent);
          }
        }
      });
    }
  }

  /**
   * Generates a list of ListItem objects from the ad icons, types, sizes,
   * and premium features constants arrays.  This list will become the
   * input to the ListAdapter.
   */
  private List<ListItem> generateListItems() {
    List<ListItem> listItems = new ArrayList<ListItem>();
    // All four arrays must have the same length to continue.
    if ((Constants.AD_ICONS.length == Constants.AD_TYPES.length)
        && (Constants.AD_ICONS.length == Constants.AD_SIZES.length)
        && (Constants.AD_ICONS.length == Constants.PREMIUM_FEATURES.length)) {
      for (int index = 0; index < Constants.AD_ICONS.length; index++) {
        ListItem listItem = new ListItem();
        listItem.setAdIcon(Constants.AD_ICONS[index]);
        listItem.setAdType(Constants.AD_TYPES[index]);
        listItem.setAdSize(Constants.AD_SIZES[index]);
        listItem.setIsPremiumFeature(Constants.PREMIUM_FEATURES[index]);
        listItems.add(listItem);
      }
    }
    return listItems;
  }
}
