SUMMARY = "GNU libiconv"
DESCRIPTION = "Character set conversion library"
HOMEPAGE = "https://www.gnu.org/software/libiconv/"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "https://ftp.gnu.org/pub/gnu/libiconv/libiconv-${PV}.tar.gz"

SRC_URI[sha256sum] = "8f74213b56238c85a50a5329f77e06198771e70dd9a739779f4c02f65d971313"

inherit autotools gettext

EXTRA_OECONF += "--enable-static --enable-shared"

do_install:append() {
    # Entferne iconv.h, um Konflikt mit musl/glibc zu vermeiden
    rm -f ${D}${includedir}/iconv.h
}

# Nur die Bibliothek soll ins sysroot, nicht die Header
SYSROOT_DIRS:remove = "${includedir}"
