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
  private List<AdFormat> adFormats;
  private static LayoutInflater inflater = null;

  public ListAdapter(Activity activity, List<AdFormat> adFormats) {
    this.adFormats = adFormats;
    ListAdapter.inflater = (LayoutInflater) activity
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return adFormats.size();
  }

  @Override
  public AdFormat getItem(int position) {
    return adFormats.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  private static class ViewHolder {
    public ImageView adIcon;
    public TextView adType;
    public TextView adSize;
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
      view.setTag(holder);
    } else {
      holder = (ViewHolder) view.getTag();
    }
    AdFormat adFormat = this.getItem(position);
    holder.adIcon.setImageResource(adFormat.icon);
    holder.adType.setText(adFormat.name);
    holder.adSize.setText(adFormat.size);

    return view;
  }
}
