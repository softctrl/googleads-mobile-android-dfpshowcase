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
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * This is a ListAdapter that holds a ListItem.
 *
 * @author api.eleichtenschl@gmail.com (Eric Leichtenschlag)
 */
public class ListAdapter extends BaseAdapter {
  private List<ListItem> listItems;
  private static LayoutInflater inflater = null;

  public ListAdapter(Activity activity, List<ListItem> listItems) {
    this.listItems = listItems;
    ListAdapter.inflater = (LayoutInflater) activity
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return listItems.size();
  }

  @Override
  public ListItem getItem(int position) {
    return listItems.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  private static class ViewHolder {
    public ImageView adIcon;
    public TextView adType;
    public TextView adSize;
    public TextView premiumFeature;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    ViewHolder holder;
    if (convertView == null) {
      view = inflater.inflate(R.layout.grid_list_layout, null);
      holder = new ViewHolder();
      holder.adIcon = (ImageView) view.findViewById(R.id.adIcon);
      holder.adType = (TextView) view.findViewById(R.id.adType);
      holder.adSize = (TextView) view.findViewById(R.id.adSize);
      holder.premiumFeature = (TextView) view.findViewById(R.id.premiumFeature);
      view.setTag(holder);
    } else {
      holder = (ViewHolder) view.getTag();
    }
    ListItem listItem = this.getItem(position);
    holder.adIcon.setImageResource(listItem.getAdIcon());
    holder.adType.setText(listItem.getAdType());
    holder.adSize.setText(listItem.getAdSize());
    if (listItem.getIsPremiumFeature()) {
      holder.premiumFeature.setText(Constants.PREMIUM);
    } else {
      holder.premiumFeature.setText("");
    }

    return view;
  }
}
