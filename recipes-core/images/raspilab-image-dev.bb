SUMMARY = "RaspiLab development image"

inherit core-image
inherit populate_sdk_qt6
require raspilab-image.bb

IMAGE_FEATURES += "dev-pkgs"
