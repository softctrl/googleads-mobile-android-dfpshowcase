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

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.doubleclick.DfpExtras;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * This activity showcases the ad type that the user selected.
 *
 * @author api.eleichtenschl@gmail.com (Eric Leichtenschlag)
 */
public class DisplayAdView extends Activity implements OnClickListener, AdListener {
  private AdView adView;
  private InterstitialAd interstitial;
  private String adFormat;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Remove title and status bar.
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                              WindowManager.LayoutParams.FLAG_FULLSCREEN);

    this.adView = null;
    this.interstitial = null;
    this.adFormat = getIntent().getStringExtra(Constants.AD_FORMAT_KEY);
    // Define the adView or interstitial based on the ad type.
    if (AdFormat.IMAGE.name.equals(adFormat)) {
      this.adView = new AdView(this, getWidestBannerSize(), AdFormat.IMAGE.adUnit);
    } else if (AdFormat.ADMOB.name.equals(adFormat)) {
      this.adView = new AdView(this, AdSize.BANNER, AdFormat.ADMOB.adUnit);
    } else if (AdFormat.MEDIATION.name.equals(adFormat)) {
      this.adView = new AdView(this, AdSize.BANNER, AdFormat.MEDIATION.adUnit);
    } else if (AdFormat.TEXT_IMAGE.name.equals(adFormat)) {
      this.adView = new AdView(this, AdSize.BANNER, AdFormat.TEXT_IMAGE.adUnit);
    } else if (AdFormat.DFA_TAG.name.equals(adFormat)) {
      this.adView = new AdView(this, AdSize.BANNER, AdFormat.DFA_TAG.adUnit);
    } else if (AdFormat.MRAID_EXPANDABLE.name.equals(adFormat)) {
      this.adView = new AdView(this, getWidestBannerSize(), AdFormat.MRAID_EXPANDABLE.adUnit);
    } else if (AdFormat.INTERSTITIAL.name.equals(adFormat)) {
      this.interstitial = new InterstitialAd(this, AdFormat.INTERSTITIAL.adUnit);
    } else if (AdFormat.IMAGE_CAROUSEL.name.equals(adFormat)) {
      this.adView = new AdView(this, AdSize.IAB_MRECT, AdFormat.IMAGE_CAROUSEL.adUnit);
    } else if (AdFormat.IMAGE_PLUS1.name.equals(adFormat)) {
      this.adView = new AdView(this, AdSize.IAB_MRECT, AdFormat.IMAGE_PLUS1.adUnit);
    } else if (AdFormat.INTERSTITIAL_AUTO_CLOSE.name.equals(adFormat)) {
      this.interstitial = new InterstitialAd(this, AdFormat.INTERSTITIAL_AUTO_CLOSE.adUnit);
    } else if (AdFormat.IMAGE_ANIMATION.name.equals(adFormat)) {
      this.interstitial = new InterstitialAd(this, AdFormat.IMAGE_ANIMATION.adUnit);
    } else if (AdFormat.VIDEO_INTERSTITIAL.name.equals(adFormat)) {
      this.interstitial = new InterstitialAd(this, AdFormat.VIDEO_INTERSTITIAL.adUnit);
    } else if (AdFormat.CLICK_TO_DOWNLOAD.name.equals(adFormat)) {
      this.adView = new AdView(this, getWidestBannerSize(), AdFormat.CLICK_TO_DOWNLOAD.adUnit);
    } else if (AdFormat.CLICK_TO_CALL.name.equals(adFormat)) {
      this.adView = new AdView(this, getWidestBannerSize(), AdFormat.CLICK_TO_CALL.adUnit);
    } else if (AdFormat.CLICK_TO_MAP.name.equals(adFormat)) {
      this.adView = new AdView(this, getWidestBannerSize(), AdFormat.CLICK_TO_MAP.adUnit);
    }

    if (this.interstitial != null) {
      setContentView(R.layout.display_interstitial);
      this.interstitial.setAdListener(this);
    } else if (adView != null) {
      setContentView(R.layout.display_ad_view);
      this.adView.setAdListener(this);

      // Set AdView to bottom of screen.
      RelativeLayout layout = (RelativeLayout) findViewById(R.id.adViewDisplay);
      if (layout != null) {
        RelativeLayout.LayoutParams params = new
            RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                                        LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layout.addView(adView);
        this.adView.setLayoutParams(params);
      }
      this.adView.loadAd(new AdRequest());
    }
  }

  /**
   * Gets a 360x50 {@link AdSize} if the device's screen is at least 360 dp wide, otherwise gets a
   * 320x50 {@link AdSize}.
   * NOTE: The AdFormat will claim that the AdSize is 360x50, but this method allows us to support
   * older devices that are 320dp wide - because if a 360x50dp is requested on a 320dp wide device,
   * AdMob won't show the ad when it comes back because it is too big.
   *
   * @return an appropriate {@link AdSize} for the device.
   */
  private AdSize getWidestBannerSize() {
    if (getScreenWidthInDp() >= 360) {
      return new AdSize(360, 50);
    } else {
      return AdSize.BANNER;
    }
  }

  private int getScreenWidthInDp() {
    DisplayMetrics dm = this.getResources().getDisplayMetrics();
    return (int) (dm.widthPixels / dm.density);
  }

  private int getScreenHeightInDp() {
    DisplayMetrics dm = this.getResources().getDisplayMetrics();
    return (int) (dm.heightPixels / dm.density);
  }

  /** Handles the on click events for each button. */
  @Override
  public void onClick(View view) {
    final int id = view.getId();
    switch (id) {
      case R.id.showInterstitial:
        if (this.interstitial != null) {
          AdRequest adRequest = new AdRequest();
          DfpExtras extras = new DfpExtras();
          extras.addExtra("screenWidth", getScreenWidthInDp());
          extras.addExtra("screenHeight", getScreenHeightInDp());
          adRequest.setNetworkExtras(extras);
          this.interstitial.loadAd(adRequest);
        }
        break;
    }
  }

  /** AdListener interface implementation. */
  @Override
  public void onReceiveAd(Ad ad) {
    Log.v(Constants.SHOWCASE, "onReceiveAd");
    Toast.makeText(this, "onReceiveAd", Toast.LENGTH_SHORT).show();
    if (this.interstitial != null && this.interstitial == ad && this.interstitial.isReady()) {
      this.interstitial.show();
    }
  }

  @Override
  public void onFailedToReceiveAd(Ad ad, ErrorCode errorCode) {
    String message = "onFailedToReceiveAd (" + errorCode + ")";
    Log.v(Constants.SHOWCASE, message);
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onPresentScreen(Ad ad) {
    Log.v(Constants.SHOWCASE, "onPresentScreen");
    Toast.makeText(this, "onPresentScreen", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onDismissScreen(Ad ad) {
    Log.v(Constants.SHOWCASE, "onDismissScreen");
    Toast.makeText(this, "onDismissScreen", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onLeaveApplication(Ad ad) {
    Log.v(Constants.SHOWCASE, "onLeaveApplication");
    Toast.makeText(this, "onLeaveApplication", Toast.LENGTH_SHORT).show();
  }
}
