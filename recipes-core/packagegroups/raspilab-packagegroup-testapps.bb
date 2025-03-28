DESCRIPTION = "RaspiLab test application packagegroup"
SUMMARY = "RaspiLab packagegroup - tools/testapps"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = " \
    ethtool \
    evtest \
    fbset \
    i2c-tools \
    memtester \
"
