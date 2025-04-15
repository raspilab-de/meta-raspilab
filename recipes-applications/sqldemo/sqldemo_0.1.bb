SUMMARY = "SQL Demo"
DESCRIPTION = "This is a SQL Demo with Qt6."
LICENSE = "CLOSED"

SRC_URI = " \
    file://sqldemo.pro \
    file://main.cpp \
    file://mainwindow.cpp \
    file://mainwindow.h \
    file://mainwindow.ui \
    file://odbc.ini.append \
"

S = "${WORKDIR}"

DEPENDS += "qtbase wayland"
RDEPENDS:${PN} += "qtwayland freetds"

inherit qt6-qmake

do_install:append() {
    # Template-Dateien installieren (nur für postinst)
    install -d ${D}${datadir}/sqldemo
    install -m 0644 ${WORKDIR}/odbc.ini.append ${D}${datadir}/sqldemo/odbc.ini.append

    install -d ${D}${bindir}
    install -m 0775 sqldemo ${D}${bindir}
}

pkg_postinst_ontarget:${PN}() {
    echo "Appending ODBC config at runtime..."

    if [ -f /etc/odbc.ini ]; then
        cat ${datadir}/sqldemo/odbc.ini.append >> /etc/odbc.ini
    else
        cp ${datadir}/sqldemo/odbc.ini.append /etc/odbc.ini
    fi
}

FILES:${PN} += " \
    ${bindir}/sqldemo \
    ${datadir}/sqldemo \
    ${sysconfdir}/odbc.ini \
"
