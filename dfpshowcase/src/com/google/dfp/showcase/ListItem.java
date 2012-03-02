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

/**
 * Represents an item in the list of the main activity.
 *
 * @author api.eleichtenschl@gmail.com (Eric Leichtenschlag)
 */
public class ListItem {
  private int adIcon;
  private String adType;
  private String adSize;
  private boolean isPremiumFeature;

  public ListItem() {
    // Empty constructor.
  }

  public int getAdIcon() {
    return this.adIcon;
  }

  public void setAdIcon(int adIcon) {
    this.adIcon = adIcon;
  }

  public String getAdType() {
    return this.adType;
  }

  public void setAdType(String adType) {
    this.adType = adType;
  }

  public String getAdSize() {
    return this.adSize;
  }

  public void setAdSize(String adSize) {
    this.adSize = adSize;
  }

  public boolean getIsPremiumFeature() {
    return this.isPremiumFeature;
  }

  public void setIsPremiumFeature(boolean isPremiumFeature) {
    this.isPremiumFeature = isPremiumFeature;
  }
}
