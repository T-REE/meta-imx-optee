# Copyright (C) 2017 NXP

SUMMARY = "OPTEE Client libs"
HOMEPAGE = "http://www.optee.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=69663ab153298557a59c67a60a743e5b"

#DEPENDS = "python-pycrypto-native"
SRC_URI = "git://sw-stash.freescale.net/scm/imx/imx-optee-client.git;branch=imx_2.5.y;protocol=http"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

do_compile () {
    if [ ${DEFAULTTUNE} = "aarch64" ]; then
        oe_runmake -C ${S} ARCH=arm64
    else
        oe_runmake -C ${S} ARCH=arm
    fi
}

do_install () {
    install -d ${D}/usr/lib
    install ${S}/out/export/lib/* ${D}/usr/lib/
    install -d ${D}/usr/bin
    install ${S}/out/export/bin/tee-supplicant ${D}/usr/bin/
    install -d ${D}/usr/include
    install ${S}/out/export/include/* ${D}/usr/include/
}

PACKAGES += "tee-supplicant"
FILES_${PN} += "${libdir}/* ${includedir}/*"
FILES_tee-supplicant += "${bindir}/tee-supplicant"

INSANE_SKIP_${PN} = "ldflags dev-elf"
INSANE_SKIP_${PN}-dev = "ldflags dev-elf"
INSANE_SKIP_tee-supplicant = "ldflags"
