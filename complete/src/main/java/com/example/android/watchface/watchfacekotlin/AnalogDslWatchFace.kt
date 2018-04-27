/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.watchface.watchfacekotlin

import android.graphics.Color
import com.example.android.watchface.watchfacekotlin.model.AnalogWatchFaceStyle
import com.example.android.watchface.watchfacekotlin.service.AbstractKotlinWatchFace
import com.example.android.watchface.watchfacekotlin.service.analogWatchFaceStyle

/**
 * Renders watch face via data object created by DSL.
 */
class AnalogDslWatchFace : AbstractKotlinWatchFace() {

    override fun getWatchFaceStyle(): AnalogWatchFaceStyle {

        /**
         * Initializes colors and dimensions of watch face. Review [AnalogWatchFaceStyle] for
         * detailed explanation of each field.
         */
        return analogWatchFaceStyle {
            watchFaceColors {
                main = Color.CYAN
                highlight = Color.parseColor("#ffa500")
                background = Color.WHITE
            }
            watchFaceDimensions {
                hourHandRadiusRatio = 0.2f
                minuteHandRadiusRatio = 0.5f
                secondHandRadiusRatio = 0.9f
            }
            watchFaceBackgroundImage {
                backgroundImageResource = R.drawable.background_image
            }
        }
    }
}
