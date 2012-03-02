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
 * This class holds constant values for Ad Units, Ad Types and ListView Data.
 *
 * @author api.eleichtenschl@gmail.com (Eric Leichtenschlag)
 */
public final class Constants {
  /* ListView Resources. */
  public static final int[] AD_ICONS = {
      R.drawable.icon_banner, R.drawable.icon_expandable, R.drawable.icon_admob,
      R.drawable.icon_rich_media, R.drawable.icon_animation, R.drawable.icon_download,
      R.drawable.icon_rich_media, R.drawable.icon_rich_media
  };

  public static final String[] AD_TYPES = {
      AdTypes.BANNER, AdTypes.EXPANDABLE, AdTypes.ADMOB_BACKFILL, AdTypes.RICH_MEDIA_INTERSTITIAL,
      AdTypes.IMAGE_ANIMATION, AdTypes.CLICK_TO_DOWNLOAD, AdTypes.CLICK_TO_CALL,
      AdTypes.CLICK_TO_MAP
  };

  public static final String[] AD_SIZES = {
      "320x50", "320x50", "320x50", "320x480", "320x533", "320x50", "320x50", "320x50"
  };

  public static final boolean[] PREMIUM_FEATURES = {
      false, false, false, false, true, false, true, true
  };

  public static final String PREMIUM = "(Premium)";
  public static final String SHOWCASE = "Showcase";
  public static final int IMAGE_ANIMATION_WIDTH = 320;
  public static final int IMAGE_ANIMATION_HEIGHT = 533;

  /** This subclass holds constant values for the different Ad Units. */
  public final class AdUnits {
    public static final String BANNER = "/6253334/demo_android_app/banner";
    public static final String EXPANDABLE = "/6253334/demo_android_app/banner";
    public static final String ADMOB_BACKFILL = "/6253334/demo_android_app/banner";
    public static final String RICH_MEDIA_INTERSTITIAL = "/6253334/demo_android_app/interstitial";
    public static final String IMAGE_ANIMATION = "/6253334/demo_android_app/interstitial";
    public static final String CLICK_TO_DOWNLOAD = "/6253334/demo_android_app/banner";
    public static final String CLICK_TO_CALL = "/6253334/demo_android_app/banner";
    public static final String CLICK_TO_MAP = "/6253334/demo_android_app/banner";

    private AdUnits() {
      // This will never be called.
    }
  }

  /** This subclass holds constant values for the different Ad Types. */
  public final class AdTypes {
    public static final String BANNER = "Banner";
    public static final String EXPANDABLE = "Expandable";
    public static final String ADMOB_BACKFILL = "AdMob Backfill";
    public static final String RICH_MEDIA_INTERSTITIAL = "Rich Media Interstitial";
    public static final String IMAGE_ANIMATION = "Image Animation";
    public static final String CLICK_TO_DOWNLOAD = "Click to Download";
    public static final String CLICK_TO_CALL = "Click to Call";
    public static final String CLICK_TO_MAP = "Click to Map";

    private AdTypes() {
      // This will never be called.
    }
  }

  /** Private constructor so that the class cannot be instantiated. */
  private Constants() {
    // This will never be called.
  }
}
