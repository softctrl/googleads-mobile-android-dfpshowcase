// Copyright 2013, Google Inc. All Rights Reserved.
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

import java.util.Arrays;
import java.util.List;

/**
 * An enumeration of bundled and premium ad formats shown in the DFP Showcase.
 *
 * @author api.eleichtenschl@gmail.com (Eric Leichtenschlag)
 */
public enum AdFormat {
  // Bundled formats.
  IMAGE("Image", "360x50", R.drawable.icon_banner, "/6253334/dfp_showcase/image"),
  ADMOB("AdMob", "320x50", R.drawable.icon_admob, "/6253334/dfp_showcase/admob"),
  MEDIATION("Mediation", "320x50", R.drawable.icon_admob, "/6253334/dfp_showcase/mediation"),
  TEXT_IMAGE("Text and Image", "320x50", R.drawable.icon_banner,
      "/6253334/dfp_showcase/text_image"),
  DFA_TAG("DoubleClick tag", "320x50", R.drawable.icon_rich_media, "/6253334/dfp_showcase/dfa"),
  MRAID_EXPANDABLE("MRAID Expandable", "360x50", R.drawable.icon_expandable,
      "/6253334/dfp_showcase/mraid_expand"),
  INTERSTITIAL("Interstitial", Constants.FULL_SCREEN, R.drawable.icon_rich_media,
      "/6253334/dfp_showcase/interstitial"),

  // Premium formats.
  IMAGE_CAROUSEL("Mobile Image Carousel", "300x250", R.drawable.icon_rich_media,
      "/6253334/dfp_showcase/carousel"),
  IMAGE_PLUS1("Mobile Image with Google +1", "300x250", R.drawable.icon_rich_media,
      "/6253334/dfp_showcase/googleplus"),
  INTERSTITIAL_AUTO_CLOSE("Mobile Interstitial with Autoclose", Constants.FULL_SCREEN,
      R.drawable.icon_rich_media, "/6253334/dfp_showcase/autoclose"),
  IMAGE_ANIMATION("Image Animation", Constants.FULL_SCREEN, R.drawable.icon_animation,
      "/6253334/dfp_showcase/image_animation"),
  VIDEO_INTERSTITIAL("Video Interstitial", Constants.FULL_SCREEN, R.drawable.icon_rich_media,
      "/6253334/dfp_showcase/video_interstitial"),
  CLICK_TO_DOWNLOAD("Click to Download", "360x50", R.drawable.icon_download,
      "/6253334/dfp_showcase/click2download"),
  CLICK_TO_CALL("Click to Call", "360x50", R.drawable.icon_rich_media,
      "/6253334/dfp_showcase/click2call"),
  CLICK_TO_MAP("Click to Map", "360x50", R.drawable.icon_rich_media,
      "/6253334/dfp_showcase/click2map");

  public static final List<AdFormat> BUNDLED_AD_FORMATS =
      Arrays.asList(IMAGE, ADMOB, MEDIATION, TEXT_IMAGE, DFA_TAG, MRAID_EXPANDABLE, INTERSTITIAL);

  public static final List<AdFormat> PREMIUM_AD_FORMATS = Arrays.asList(IMAGE_CAROUSEL, IMAGE_PLUS1,
      INTERSTITIAL_AUTO_CLOSE, IMAGE_ANIMATION, VIDEO_INTERSTITIAL, CLICK_TO_DOWNLOAD,
      CLICK_TO_CALL, CLICK_TO_MAP);

  /** The name of the format. */
  public final String name;

  /** The example size of the format. */
  public final String size;

  /** The icon representing the format. */
  public final int icon;

  /** The DFP ad unit associated with the format. */
  public final String adUnit;

  /**
   * Constructs a {@link AdFormat}.
   *
   * @param name the name of the format.
   * @param size the example size of the format.
   * @param icon the icon representing the format.
   * @param adUnit the DFP ad unit associated with the format.
   */
  private AdFormat(String name, String size, int icon, String adUnit) {
    this.name = name;
    this.size = size;
    this.icon = icon;
    this.adUnit = adUnit;
  }
}
