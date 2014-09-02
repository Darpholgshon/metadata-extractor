/*
 * Copyright 2002-2013 Drew Noakes
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * More information about this project is available at:
 *
 *    http://drewnoakes.com/code/exif/
 *    http://code.google.com/p/metadata-extractor/
 */
package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

/**
 * Provides human-readable string representations of tag values stored in a {@link PentaxMakernoteDirectory}.
 * <p/>
 * Some information about this makernote taken from here:
 * http://www.ozhiker.com/electronics/pjmt/jpeg_info/pentax_mn.html
 *
 * @author Drew Noakes http://drewnoakes.com
 */
public class PentaxMakernoteDescriptor extends TagDescriptor<PentaxMakernoteDirectory>
{
    public PentaxMakernoteDescriptor(@NotNull PentaxMakernoteDirectory directory)
    {
        super(directory);
    }

    @Nullable
    public String getDescription(int tagType)
    {
        switch (tagType) {
            case PentaxMakernoteDirectory.TAG_CAPTURE_MODE:
                return getCaptureModeDescription();
            case PentaxMakernoteDirectory.TAG_QUALITY_LEVEL:
                return getQualityLevelDescription();
            case PentaxMakernoteDirectory.TAG_FOCUS_MODE:
                return getFocusModeDescription();
            case PentaxMakernoteDirectory.TAG_FLASH_MODE:
                return getFlashModeDescription();
            case PentaxMakernoteDirectory.TAG_WHITE_BALANCE:
                return getWhiteBalanceDescription();
            case PentaxMakernoteDirectory.TAG_DIGITAL_ZOOM:
                return getDigitalZoomDescription();
            case PentaxMakernoteDirectory.TAG_SHARPNESS:
                return getSharpnessDescription();
            case PentaxMakernoteDirectory.TAG_CONTRAST:
                return getContrastDescription();
            case PentaxMakernoteDirectory.TAG_SATURATION:
                return getSaturationDescription();
            case PentaxMakernoteDirectory.TAG_ISO_SPEED:
                return getIsoSpeedDescription();
            case PentaxMakernoteDirectory.TAG_COLOUR:
                return getColourDescription();
            default:
                return super.getDescription(tagType);
        }
    }

    @Nullable
    public String getColourDescription()
    {
        return getIndexedDescription(PentaxMakernoteDirectory.TAG_COLOUR, 1, "Normal", "Black & White", "Sepia");
    }

    @Nullable
    public String getIsoSpeedDescription()
    {
        Integer value = _directory.getInteger(PentaxMakernoteDirectory.TAG_ISO_SPEED);
        if (value == null)
            return null;
        switch (value) {
            // TODO there must be other values which aren't catered for here
            case 10: return "ISO 100";
            case 16: return "ISO 200";
            case 100: return "ISO 100";
            case 200: return "ISO 200";
            default: return "Unknown (" + value + ")";
        }
    }

    @Nullable
    public String getSaturationDescription()
    {
        return getIndexedDescription(PentaxMakernoteDirectory.TAG_SATURATION, "Normal", "Low", "High");
    }

    @Nullable
    public String getContrastDescription()
    {
        return getIndexedDescription(PentaxMakernoteDirectory.TAG_CONTRAST, "Normal", "Low", "High");
    }

    @Nullable
    public String getSharpnessDescription()
    {
        return getIndexedDescription(PentaxMakernoteDirectory.TAG_SHARPNESS, "Normal", "Soft", "Hard");
    }

    @Nullable
    public String getDigitalZoomDescription()
    {
        Float value = _directory.getFloatObject(PentaxMakernoteDirectory.TAG_DIGITAL_ZOOM);
        if (value == null)
            return null;
        if (value == 0)
            return "Off";
        return Float.toString(value);
    }

    @Nullable
    public String getWhiteBalanceDescription()
    {
        return getIndexedDescription(PentaxMakernoteDirectory.TAG_WHITE_BALANCE,
            "Auto", "Daylight", "Shade", "Tungsten", "Fluorescent", "Manual");
    }

    @Nullable
    public String getFlashModeDescription()
    {
        return getIndexedDescription(PentaxMakernoteDirectory.TAG_FLASH_MODE,
            1, "Auto", "Flash On", null, "Flash Off", null, "Red-eye Reduction");
    }

    @Nullable
    public String getFocusModeDescription()
    {
        return getIndexedDescription(PentaxMakernoteDirectory.TAG_FOCUS_MODE, 2, "Custom", "Auto");
    }

    @Nullable
    public String getQualityLevelDescription()
    {
        return getIndexedDescription(PentaxMakernoteDirectory.TAG_QUALITY_LEVEL, "Good", "Better", "Best");
    }

    @Nullable
    public String getCaptureModeDescription()
    {
        return getIndexedDescription(PentaxMakernoteDirectory.TAG_CAPTURE_MODE,
            "Auto", "Night-scene", "Manual", null, "Multiple");
    }
}