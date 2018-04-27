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

package com.example.android.watchface.watchfacekotlin.service

import android.graphics.Color
import com.example.android.watchface.watchfacekotlin.model.AnalogWatchFaceStyle
import com.example.android.watchface.watchfacekotlin.model.WatchFaceBackgroundImage
import com.example.android.watchface.watchfacekotlin.model.WatchFaceDimensions
import com.example.android.watchface.watchfacekotlin.model.WatchFaceColors

/**
 * Creates watch face style DSL so developers can easily customize watch faces without learning the
 * underlying complex implementation.
 */
@DslMarker
annotation class WatchFaceStyleDSL

@WatchFaceStyleDSL
class WatchFaceColorsBuilder {

    // Initializes defaults for fields. Check [AnalogWatchFaceStyle] for detailed explanation of
    // each field.
    private val attributesMap: MutableMap<String, Any?> = mutableMapOf(
            "main" to Color.WHITE,
            "highlight" to Color.RED,
            "background" to Color.DKGRAY,
            "shadow" to Color.BLACK
    )

    var main:Int by attributesMap
    var highlight:Int by attributesMap
    var background:Int by attributesMap
    var shadow:Int by attributesMap

    fun build(): WatchFaceColors {
        return WatchFaceColors(
                main, highlight, background, shadow
        )
    }
}

@WatchFaceStyleDSL
class WatchFaceDimensionsBuilder {

    // Initializes defaults for fields. Non-ratio fields represent pixels, as watch faces are
    // painted from a Canvas object. Check [AnalogWatchFaceStyle] for detailed explanation of
    // each field.
    private val attributesMap: MutableMap<String, Any?> = mutableMapOf(
            "hourHandRadiusRatio" to 0.5f,
            "minuteHandRadiusRatio" to 0.75f,
            "secondHandRadiusRatio" to 0.875f,
            "hourHandWidth" to 5f,
            "minuteHandWidth" to 3f,
            "secondHandWidth" to 2f,
            "shadowRadius" to 2f,
            "innerCircleRadius" to 4f,
            "innerCircleToArmsDistance" to 5f
    )

    var hourHandRadiusRatio:Float by attributesMap
    var minuteHandRadiusRatio:Float by attributesMap
    var secondHandRadiusRatio:Float by attributesMap
    var hourHandWidth:Float by attributesMap
    var minuteHandWidth:Float by attributesMap
    var secondHandWidth:Float by attributesMap
    var shadowRadius:Float by attributesMap
    var innerCircleRadius:Float by attributesMap
    var innerCircleToArmsDistance:Float by attributesMap

    fun build(): WatchFaceDimensions {
        return WatchFaceDimensions(
                hourHandRadiusRatio,
                minuteHandRadiusRatio,
                secondHandRadiusRatio,
                hourHandWidth,
                minuteHandWidth,
                secondHandWidth,
                shadowRadius,
                innerCircleRadius,
                innerCircleToArmsDistance
        )
    }
}

@WatchFaceStyleDSL
class WatchFaceBackgroundImageBuilder {

    // A background image isn't required for a watch face, so if it isn't defined in the DSL,
    // it gets an empty image resource value which means it won't be rendered.
    private val attributesMap: MutableMap<String, Any?> = mutableMapOf(
            "backgroundImageResource" to WatchFaceBackgroundImage.EMPTY_IMAGE_RESOURCE
    )

    var backgroundImageResource:Int by attributesMap

    fun build(): WatchFaceBackgroundImage {
        return WatchFaceBackgroundImage(backgroundImageResource)
    }
}

@WatchFaceStyleDSL
class AnalogWatchFaceStyleBuilder {

    private var watchFaceColors: WatchFaceColors? = null
    private var watchFaceDimensions: WatchFaceDimensions? = null
    private var watchFaceBackgroundImage: WatchFaceBackgroundImage =
        WatchFaceBackgroundImageBuilder().build()

    fun watchFaceColors(setup: WatchFaceColorsBuilder.() -> Unit) {
        val watchFaceColorsBuilder = WatchFaceColorsBuilder()
        watchFaceColorsBuilder.setup()
        watchFaceColors = watchFaceColorsBuilder.build()
    }

    fun watchFaceDimensions(setup: WatchFaceDimensionsBuilder.() -> Unit) {
        val analogWatchFaceDimensionsBuilder = WatchFaceDimensionsBuilder()
        analogWatchFaceDimensionsBuilder.setup()
        watchFaceDimensions = analogWatchFaceDimensionsBuilder.build()
    }

    fun watchFaceBackgroundImage(setup: WatchFaceBackgroundImageBuilder.() -> Unit) {
        val analogWatchFaceBackgroundImageBuilder = WatchFaceBackgroundImageBuilder()
        analogWatchFaceBackgroundImageBuilder.setup()
        watchFaceBackgroundImage = analogWatchFaceBackgroundImageBuilder.build()
    }


    fun build(): AnalogWatchFaceStyle {

        return AnalogWatchFaceStyle(
                watchFaceColors ?:
                    throw InstantiationException("Must define watch face styles in DSL."),
                watchFaceDimensions ?:
                    throw InstantiationException("Must define watch face dimensions in DSL."),
                watchFaceBackgroundImage
        )
    }

    /**
     * This method shadows the [analogWatchFaceStyle] method when inside the scope
     * of a [AnalogWatchFaceStyleBuilder], so that watch faces can't be nested.
     */
    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR, message = "WatchFaceStyles can't be nested.")
    fun analogWatchFaceStyle(param: () -> Unit = {}) {
    }
}

@WatchFaceStyleDSL
fun analogWatchFaceStyle (setup: AnalogWatchFaceStyleBuilder.() -> Unit): AnalogWatchFaceStyle {
    val analogWatchFaceStyleBuilder = AnalogWatchFaceStyleBuilder()
    analogWatchFaceStyleBuilder.setup()
    return analogWatchFaceStyleBuilder.build()
}
