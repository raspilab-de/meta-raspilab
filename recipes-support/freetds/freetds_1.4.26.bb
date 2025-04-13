DESCRIPTION = "Free implementation of Microsoft/Sybase wire protocol for databases"
HOMEPAGE = "http://www.freetds.org"
SECTION = "console/network"
LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING_LIB.txt;md5=5f30f0716dfdd0d91eb439ebec522ec2"
DEPENDS = "gnutls libgcrypt unixodbc"

BBCLASSEXTEND = "native"

SRC_URI = " \
	http://ftp.freetds.org/pub/freetds/stable/freetds-${PV}.tar.bz2;sha256sum=74641a66cc2bfae302c2a64a4b268a3db8fb0cc7364dc7975c44c57d65cd8d1c \
	file://odbcinst.ini.append \
"

inherit autotools pkgconfig

export LIBS="-lgcrypt"

do_install:append() {
    # Template-Dateien installieren (nur für postinst)
    install -d ${D}${datadir}/freetds
    install -m 0644 ${WORKDIR}/odbcinst.ini.append ${D}${datadir}/freetds/odbcinst.ini.append
}

pkg_postinst_ontarget:${PN}() {
    echo "Appending ODBC Driver at runtime..."

    if [ -f /etc/odbcinst.ini ]; then
        cat ${datadir}/freetds/odbcinst.ini.append >> /etc/odbcinst.ini
    else
        cp ${datadir}/freetds/odbcinst.ini.append /etc/odbcinst.ini
    fi
}


EXTRA_OECONF = " \
	--enable-sspi \
	--enable-msdblib \
	--enable-sybase-compat \
	--with-tdsver=auto \
	--with-gnutls \
	--disable-libiconv \
"

FILES:${PN} += " \
	${datadir}/freetds \
	${sysconfdir}/odbcinst.ini \
"
