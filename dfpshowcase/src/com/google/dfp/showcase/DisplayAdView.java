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
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * This activity showcases the ad type that the user selected.
 *
 * @author api.eleichtenschl@gmail.com (Eric Leichtenschlag)
 */
public class DisplayAdView extends Activity implements OnClickListener, AdListener {
  private AdView adView;
  private InterstitialAd interstitial;
  private String adType;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Remove title and status bar.
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                              WindowManager.LayoutParams.FLAG_FULLSCREEN);

    this.adView = null;
    this.interstitial = null;
    this.adType = getIntent().getStringExtra("adType");
    if (adType.matches(Constants.AdTypes.BANNER)) {
      this.adView = new AdView(this, AdSize.BANNER, Constants.AdUnits.BANNER);
    } else if (adType.matches(Constants.AdTypes.EXPANDABLE)) {
      this.adView = new AdView(this, AdSize.BANNER, Constants.AdUnits.EXPANDABLE);
    } else if (adType.matches(Constants.AdTypes.ADMOB_BACKFILL)) {
      this.adView = new AdView(this, AdSize.BANNER, Constants.AdUnits.ADMOB_BACKFILL);
    } else if (adType.matches(Constants.AdTypes.RICH_MEDIA_INTERSTITIAL)) {
      this.interstitial = new InterstitialAd(this, Constants.AdUnits.RICH_MEDIA_INTERSTITIAL);
    } else if (adType.matches(Constants.AdTypes.IMAGE_ANIMATION)) {
      this.adView = new AdView(this,
          new AdSize(Constants.IMAGE_ANIMATION_WIDTH, Constants.IMAGE_ANIMATION_HEIGHT),
          Constants.AdUnits.IMAGE_ANIMATION);
    } else if (adType.matches(Constants.AdTypes.CLICK_TO_DOWNLOAD)) {
      this.adView = new AdView(this, AdSize.BANNER, Constants.AdUnits.CLICK_TO_DOWNLOAD);
    } else if (adType.matches(Constants.AdTypes.CLICK_TO_CALL)) {
      this.adView = new AdView(this, AdSize.BANNER, Constants.AdUnits.CLICK_TO_CALL);
    } else if (adType.matches(Constants.AdTypes.CLICK_TO_MAP)) {
      this.adView = new AdView(this, AdSize.BANNER, Constants.AdUnits.CLICK_TO_MAP);
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
            RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,
                                        LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layout.addView(adView);
        this.adView.setLayoutParams(params);
      }
      AdRequest request = this.buildRequest();
      this.adView.loadAd(request);
    }
  }

  /** Handles the on click events for each button. */
  @Override
  public void onClick(View view) {
    final int id = view.getId();
    switch (id) {
      case R.id.showInterstitial:
        AdRequest request = this.buildRequest();
        if (this.interstitial != null) {
          this.interstitial.loadAd(request);
        }
        break;
    }
  }

  /**
   * Builds a request with the proper keyword based on the adType.
   * @return An AdRequest object.
   */
  private AdRequest buildRequest() {
    AdRequest request = new AdRequest();
    if (this.adType.matches(Constants.AdTypes.BANNER)) {
      request.addExtra("kw", "banner");
    } else if (this.adType.matches(Constants.AdTypes.EXPANDABLE)) {
      request.addExtra("kw", "expandable");
    } else if (this.adType.matches(Constants.AdTypes.ADMOB_BACKFILL)) {
      request.addExtra("kw", "admob");
    } else if (this.adType.matches(Constants.AdTypes.RICH_MEDIA_INTERSTITIAL)) {
      request.addExtra("kw", "interstitial1");
    } else if (this.adType.matches(Constants.AdTypes.IMAGE_ANIMATION)) {
      request.addExtra("kw", "imageanimation");
    } else if (this.adType.matches(Constants.AdTypes.CLICK_TO_DOWNLOAD)) {
      request.addExtra("kw", "appdownload");
    } else if (this.adType.matches(Constants.AdTypes.CLICK_TO_CALL)) {
      request.addExtra("kw", "clicktocall");
    } else if (this.adType.matches(Constants.AdTypes.CLICK_TO_MAP)) {
      request.addExtra("kw", "clicktomap");
    }
    return request;
  }

  @Override
  public void onDismissScreen(Ad ad) {
    Log.v(Constants.SHOWCASE, "onDismissScreen");
  }

  @Override
  public void onLeaveApplication(Ad ad) {
    Log.v(Constants.SHOWCASE, "onLeaveApplication");
  }

  @Override
  public void onPresentScreen(Ad ad) {
    Log.v(Constants.SHOWCASE, "onPresentScreen");
  }

  @Override
  public void onReceiveAd(Ad ad) {
    Log.v(Constants.SHOWCASE, "Did Receive Ad");
    if (this.interstitial != null && this.interstitial == ad && this.interstitial.isReady()) {
      this.interstitial.show();
    }
  }

  @Override
  public void onFailedToReceiveAd(Ad ad, ErrorCode errorCode) {
    Log.v(Constants.SHOWCASE, "Failed to receive ad (" + errorCode + ")");
  }
}
