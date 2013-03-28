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
  /** Logtag used throughout the entire application. */
  public static final String SHOWCASE = "Showcase";

  /** Ad size string indicating full screen. */
  public static final String FULL_SCREEN = "Full Screen";

  /** Represents the ad format key passed as an extra to an Intent. */
  public static final String AD_FORMAT_KEY = "adFormat";

  /** Private constructor so that the class cannot be instantiated. */
  private Constants() {
    // This will never be called.
  }
}
