SUMMARY = "RaspiLab production image"

IMAGE_FSTYPES = "ext4.xz rpi-sdimg"
SDIMG_ROOTFS_TYPE = "ext4.xz"

IMAGE_FEATURES += "splash"

LICENSE = "MIT"

inherit core-image

GLIBC_GENERATE_LOCALES="de_DE.UTF-8 en_US.UTF-8"
IMAGE_LINGUAS = "de-de"
ENABLE_BINARY_LOCALE_GENERATION = "1"

CORE_OS = "\
    openssh \
    openssh-keygen \
    openssh-sftp-server \
    packagegroup-core-boot \
    tzdata \
    kbd-keymaps \
    kbdlayout \
    glibc-utils \
    localedef \
    raspilab-locales \
"

EXTRA_TOOLS = " \
    nano \
    curl \
    grep \
    i2c-tools \
    wget \
"

QT = " \
    packagegroup-qt6-essentials \
"

APPS = " \
    hellowidget \
"

FONTS = "\
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-sans-condensed \
    ttf-dejavu-serif \
    ttf-dejavu-serif-condensed \
    ttf-dejavu-common \
"

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${EXTRA_TOOLS} \
    ${QT} \
    ${APPS} \
    ${FONTS} \
"
