# Copyright (C) 2017 NXP

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"
SRC_URI = "git://sw-stash.freescale.net/scm/imx/linux-2.6-testbuild.git;branch=imx_4.9.y_optee;protocol=http"
SRCREV = "${AUTOREV}"

DESCRIPTION = "Optee kernel"

S = "${WORKDIR}/git"
inherit deploy pythonnative autotools

python () {
    machine = d.getVar("MACHINE", True)

    if (machine == "imx7dsabresd") or (machine == "imx6qsabresd"):
        d.setVar("fsl_defconfig", "imx_v7_defconfig")
        d.setVar("ARCH", "arm")
    elif machine == "imx8mqevk":
        d.setVar("fsl_defconfig", "defconfig")
        d.setVar("ARCH", "arm64")
    else:
        bb.err("Optee kernel cannot be built for current MACHINE")
}

do_compile () {
    unset LDFLAGS
    oe_runmake ARCH=${ARCH} -C ${S} CROSS_COMPILE=${HOST_PREFIX} ${fsl_defconfig}
    oe_runmake ARCH=${ARCH} -C ${S} CROSS_COMPILE=${HOST_PREFIX}
    oe_runmake ARCH=${ARCH} -C ${S} CROSS_COMPILE=${HOST_PREFIX} INSTALL_PATH=${S}/arch/${ARCH}/boot install
}

do_install () {
    install -d ${D}/lib/modules
    cp -rf ${S}/drivers/tee/optee/optee.ko ${D}/lib/modules
}

do_deploy () {
    install -d ${DEPLOY_DIR_IMAGE}/boot/
    if [ "${MACHINE}" = "imx8mqevk" ]; then
        cp -rf ${S}/arch/${ARCH}/boot/Image ${DEPLOY_DIR_IMAGE}/boot/zImage-optee
        cp -rf ${S}/arch/${ARCH}/boot/dts/freescale/fsl-imx8mq-evk.dtb ${DEPLOY_DIR_IMAGE}/boot/
    else
        cp -rf ${S}/arch/${ARCH}/boot/zImage ${DEPLOY_DIR_IMAGE}/boot/zImage-optee
    fi
}
addtask deploy before do_build after do_install

FILES_${PN}="/lib/modules/*"

