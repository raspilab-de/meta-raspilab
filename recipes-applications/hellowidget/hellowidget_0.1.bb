SUMMARY = "Sample Qt6 Application"
DESCRIPTION = "This is a sample Qt6 application."
LICENSE = "CLOSED"

SRC_URI = " \
            file://hellowidget.pro \
            file://main.cpp \
            file://mainwindow.cpp \
            file://mainwindow.h \
            file://mainwindow.ui \
"

S = "${WORKDIR}"

DEPENDS += "qtbase wayland"
RDEPENDS:${PN} += "qtwayland"

do_install:append() {
    install -d ${D}${bindir}
    install -m 0775 hellowidget ${D}${bindir}
}

FILES:${PN} += "${bindir}/hellowidget"

inherit qt6-qmake
