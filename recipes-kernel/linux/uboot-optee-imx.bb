# Copyright (C) 2017 NXP

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"
SRC_URI = "git://sw-stash.freescale.net/scm/imx/uboot-imx-testbuild.git;branch=imx_v2017.03_optee;protocol=http"
SRCREV = "${AUTOREV}"

DESCRIPTION = "Optee U-boot"
S = "${WORKDIR}/git"
inherit deploy pythonnative autotools
DEPENDS += "python"
EXTRA_OEMAKE = 'ARCH=arm CROSS_COMPILE=${TARGET_PREFIX} CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" V=1'
EXTRA_OEMAKE += 'HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}"'

python () {
    machine = d.getVar("MACHINE", True)

    if machine == "imx7dsabresd":
        d.setVar("fsl_uboot_defconfig", "mx7dsabresd_optee_defconfig")
    elif machine == "imx6qsabresd":
        d.setVar("fsl_uboot_defconfig", "mx6qsabresd_optee_defconfig")
    elif machine == "imx8mqevk":
        d.setVar("fsl_uboot_defconfig", "imx8mq_evk_optee_defconfig")
    else:
        bb.err("Optee kernel cannot be built for current MACHINE")
}

do_compile () {
    oe_runmake -C ${S} ${fsl_uboot_defconfig}
    oe_runmake -C ${S}
}

do_install () {
    echo "installing"
}

do_deploy () {
    install -d ${STAGING_DIR}/boot
    if [ "${MACHINE}" = "imx7dsabresd" ] || [ "${MACHINE}" = "imx6qsabresd" ]; then
        install -m 0644 ${S}/u-boot-dtb.imx ${STAGING_DIR}/boot/u-boot-optee.imx
    else
        install -m 0644 ${S}/spl/u-boot-spl.bin ${STAGING_DIR}/boot/
        install -m 0644 ${S}/u-boot.bin ${STAGING_DIR}/boot/
    fi
}

addtask deploy before do_build after do_install
#FILES_${PN}="/boot/u-boot*"
