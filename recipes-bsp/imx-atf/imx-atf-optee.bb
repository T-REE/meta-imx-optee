# Copyright 2017 NXP

DESCRIPTION = "i.MX ARM Trusted Firmware"
SECTION = "BSP"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit autotools pkgconfig

ATF_SRC ?= "git://git.freescale.com/imx/arm-trusted-firmware.git;protocol=git"
ATF_BRANCH = "imx_1.4.y_optee"

SRC_URI = "${ATF_SRC};branch=${ATF_BRANCH}"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"

SOC_ATF = "imx8mq"

do_compile () {
   export CROSS_COMPILE="${TARGET_PREFIX}"
   cd ${S}
   # Clear LDFLAGS to avoid the option -Wl recognize issue
   unset LDFLAGS

   oe_runmake clean PLAT=${SOC_ATF}
   oe_runmake PLAT=${SOC_ATF} SPD=opteed bl31

   unset CROSS_COMPILE
}

do_install () {
    install -d ${D}/boot
    install -m 0644 ${S}/build/${SOC_ATF}/release/bl31.bin ${D}/boot/bl31-${SOC_ATF}.bin

    install -d ${STAGING_DIR}/boot
    install -m 0644 ${S}/build/${SOC_ATF}/release/bl31.bin ${STAGING_DIR}/boot/bl31-${SOC_ATF}.bin
}


FILES_${PN} = "/boot"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(mx8)"
