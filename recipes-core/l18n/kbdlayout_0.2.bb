SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Setup a default (german) keyboard-layout for the console only"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

PV = "0.2"
PACKAGE_ARCH = "raspberrypi4_64"
SRCREV = "HEAD"

SRC_URI = "file://kbdlayout.service"

do_install () {
    install -d ${D}/${base_libdir}/systemd/system
    install -m 0644 ${WORKDIR}/kbdlayout.service ${D}/${base_libdir}/systemd/system/
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "kbdlayout.service"

FILES:${PN} += "${base_libdir}/systemd"
