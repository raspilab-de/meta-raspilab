SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Copy scripts to profile.d"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://locale.sh"

do_install () {
        install -d ${D}/etc/profile.d
        install -m755 ${WORKDIR}/locale.sh ${D}/etc/profile.d
}
