SUMMARY = "RaspiLab development image"

inherit core-image
inherit populate_sdk_qt6
require raspilab-image.bb

EXTRA_IMAGE_FEATURES:append = " tools-debug dbg-pkgs"
EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=Debug"
EXTRA_QMAKEVARS_PRE += "CONFIG+=debug"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
DEBUG_BUILD = "1"
