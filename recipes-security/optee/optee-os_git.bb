# Copyright (C) 2017 NXP

SUMMARY = "OPTEE OS"
DESCRIPTION = "OPTEE OS"
HOMEPAGE = "http://www.optee.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=69663ab153298557a59c67a60a743e5b"

inherit deploy pythonnative autotools
DEPENDS = "python-pycrypto-native python-wand-native"
SRC_URI = "git://sw-stash.freescale.net/scm/imx/imx-optee-os.git;branch=imx_2.5.y;protocol=http"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"
python () {
	machine = d.getVar("MACHINE", True)
	if machine == "imx6qsabresd":
		d.setVar("CURRENT_ARCH", "arm32")
		d.setVar("EXTRA_OEMAKE",
		("PLATFORM=imx-mx6qsabresd" +
		" ARCH=arm" +
		" CFG_PAGEABLE_ADDR=0" +
		" CFG_NS_ENTRY_ADDR=0x12000000" +
		" CFG_DT=y" +
		" CFG_PSCI_ARM32=y" +
		" DEBUG=y" +
		" CFG_TEE_CORE_LOG_LEVEL=0" +
		" CFG_BOOT_SYNC_CPU=n" +
		" CFG_BOOT_SECONDARY_REQUEST=y" +
		" CFG_MX6=y" +
		" CFG_TEE_CORE_NB_CORE=4" +
		" CFG_TZC380=y"))
	elif machine == "imx6qpsabresd":
		d.setVar("CURRENT_ARCH", "arm32")
		d.setVar("EXTRA_OEMAKE",
		("PLATFORM=imx-mx6qpsabresd" +
		" ARCH=arm" +
		" CFG_PAGEABLE_ADDR=0" +
		" CFG_NS_ENTRY_ADDR=0x12000000" +
		" CFG_DT=y" +
		" CFG_PSCI_ARM32=y" +
		" DEBUG=y" +
		" CFG_TEE_CORE_LOG_LEVEL=0" +
		" CFG_BOOT_SYNC_CPU=n" +
		" CFG_BOOT_SECONDARY_REQUEST=y" +
		" CFG_MX6=y" +
		" CFG_TEE_CORE_NB_CORE=4"))
	elif machine == "imx6dlsabresd":
		d.setVar("CURRENT_ARCH", "arm32")
		d.setVar("EXTRA_OEMAKE",
		("PLATFORM=imx-mx6dlsabresd" +
		" ARCH=arm" +
		" CFG_PAGEABLE_ADDR=0" +
		" CFG_NS_ENTRY_ADDR=0x12000000" +
		" CFG_DT=y" +
		" CFG_PSCI_ARM32=y" +
		" DEBUG=y" +
		" CFG_TEE_CORE_LOG_LEVEL=0" +
		" CFG_BOOT_SYNC_CPU=n" +
		" CFG_BOOT_SECONDARY_REQUEST=y" +
		" CFG_TZC380=y"))
	elif machine == "imx6ulevk":
		d.setVar("CURRENT_ARCH", "arm32")
		d.setVar("EXTRA_OEMAKE",
		("PLATFORM=imx-mx6ulevk" +
		" ARCH=arm" +
		" CFG_PAGEABLE_ADDR=0" +
		" CFG_NS_ENTRY_ADDR=0x80800000" +
		" CFG_DT_ADDR=0x83000000" +
		" CFG_DT=y" +
		" CFG_PSCI_ARM32=y" +
		" CFG_TEE_CORE_LOG_LEVEL=0" +
		" DEBUG=y" +
		" CFG_BOOT_SYNC_CPU=n" +
		" CFG_MX6=y" +
		" CFG_MX6UL=y" +
		" CFG_TZC380=y" +
		" CFG_TEE_CORE_NB_CORE=1" +
		" CFG_IMX_CAAM=y" +
		" CFG_TEE_TA_LOG_LEVEL=0"))
	elif machine == "imx6ull14x14evk":
		d.setVar("CURRENT_ARCH", "arm32")
		d.setVar("EXTRA_OEMAKE",
		("PLATFORM=imx-mx6ullevk" +
		" ARCH=arm" +
		" CFG_PAGEABLE_ADDR=0" +
		" CFG_NS_ENTRY_ADDR=0x80800000" +
		" CFG_DT_ADDR=0x83000000" +
		" CFG_DT=y" +
		" CFG_PSCI_ARM32=y" +
		" CFG_TEE_CORE_LOG_LEVEL=0" +
		" DEBUG=y" +
		" CFG_BOOT_SYNC_CPU=n" +
		" CFG_MX6=y" +
		" CFG_MX6UL=y" +
		" CFG_TZC380=y" +
		" CFG_TEE_CORE_NB_CORE=1" +
		" CFG_IMX_CAAM=y" +
		" CFG_TEE_TA_LOG_LEVEL=0"))
	elif machine == "imx7dsabresd":
		d.setVar("CURRENT_ARCH", "arm32")
		d.setVar("EXTRA_OEMAKE",
        	(" PLATFORM=imx-mx7dsabresd" +
		" CFG_NS_ENTRY_ADDR=0x80800000" +
		" CFG_DT=y" +
		" CFG_TEE_CORE_LOG_LEVEL=0" +
		" CFG_PSCI_ARM32=y" +
		" CFG_MX7=y" +
		" CFG_DDR_SIZE=0x40000000" +
		" CFG_TEE_CORE_LOG_LEVEL=0" +
		" DEBUG=y" +
		" CFG_BOOT_SYNC_CPU=n" +
		" CFG_BOOT_SECONDARY_REQUEST=y" +
		" CFG_TEE_CORE_NB_CORE=2" +
		" CFG_TZC380=y" +
		" CFG_CORE_UNWIND=y" +
		" CFG_IMX_CAAM=y"))
	elif machine == "imx7ulpevk":
		d.setVar("CURRENT_ARCH", "arm32")
		d.setVar("EXTRA_OEMAKE",
        	(" PLATFORM=imx-mx7ulpevk" +
		" CFG_NS_ENTRY_ADDR=0x60800000" +
		" CFG_DT=y" +
		" CFG_TEE_CORE_LOG_LEVEL=0" +
		" CFG_PSCI_ARM32=y" +
		" CFG_MX7ULP=y" +
		" CFG_DDR_SIZE=0x40000000" +
		" DEBUG=y" +
		" CFG_CORE_UNWIND=y"))
	elif machine == "imx8mqevk":
		d.setVar("CURRENT_ARCH", "arm64")
		d.setVar("EXTRA_OEMAKE",
        	("PLATFORM=imx-mx8mqevk" +
		" CROSS_COMPILE64=aarch64-poky-linux-" +
		" ARCH=arm" +
		" DEBUG=1" +
		" CFG_TEE_CORE_LOG_LEVEL=1"))
	else:
        	bb.fatal("optee-os doesn't recognize this MACHINE")
}

do_compile () {
    unset LDFLAGS
    export CFLAGS="${CFLAGS} --sysroot=${STAGING_DIR_HOST}"
    ${HOST_PREFIX}gcc --version
    oe_runmake -C ${S} all
}

do_mkimage () {
    if [ ${MACHINE} = "imx6qsabresd" ]; then
        ${S}/mkimage -A arm -O linux -C none -a 0x4dffffe4 -e 0x4e000000 -d ${S}/out/arm-plat-imx/core/tee.bin uTee-6q
    elif [ ${MACHINE} = "imx6qpsabresd" ]; then
        ${S}/mkimage -A arm -O linux -C none -a 0x4dffffe4 -e 0x4e000000 -d ${S}/out/arm-plat-imx/core/tee.bin uTee-6qp
    elif [ ${MACHINE} = "imx6dlsabresd" ]; then
        ${S}/mkimage -A arm -O linux -C none -a 0x4dffffe4 -e 0x4e000000 -d ${S}/out/arm-plat-imx/core/tee.bin uTee-6dl
    elif [ ${MACHINE} = "imx6ulevk" ]; then
        ${S}/mkimage -A arm -O linux -C none -a 0x9dffffe4 -e 0x9e000000 -d ${S}/out/arm-plat-imx/core/tee.bin uTee-6ul
    elif [ ${MACHINE} = "imx6ull14x14evk" ]; then
        ${S}/mkimage -A arm -O linux -C none -a 0x9dffffe4 -e 0x9e000000 -d ${S}/out/arm-plat-imx/core/tee.bin uTee-6ull
    elif [ ${MACHINE} = "imx7dsabresd" ]; then
        ${S}/mkimage -A arm -O linux -C none -a 0xbdffffe4 -e 0xbe000000 -d ${S}/out/arm-plat-imx/core/tee.bin uTee-7d
    elif [ ${MACHINE} = "imx7ulpevk" ]; then
        ${S}/mkimage -A arm -O linux -C none -a 0x9dffffe4 -e 0x9e000000 -d ${S}/out/arm-plat-imx/core/tee.bin uTee-7ulp
    elif [ ${MACHINE} = "imx8mqevk" ]; then
        aarch64-poky-linux-objcopy -O binary ${S}/out/arm-plat-imx/core/tee.elf tee.bin
    else
        echo "optee-os doesn't recognize this MACHINE"
    fi
}

addtask mkimage after do_compile before do_install

do_install () {
    install -d ${D}/lib/firmware/
    install -m 644 ${S}/out/arm-plat-imx/core/*.bin ${D}/lib/firmware/

    # Install the TA devkit
    install -d ${D}/usr/include/optee/export-user_ta_${CURRENT_ARCH}/
#TODO:
   for f in ${S}/out/arm-plat-imx/export-ta_${CURRENT_ARCH}/*; do
#    for f in ${S}/out/arm-plat-imx/export-ta_arm32/*; do
        cp -aR $f ${D}/usr/include/optee/export-user_ta_${CURRENT_ARCH}/
    done
}

FILES_${PN} = "/lib/firmware/"
FILES_${PN}-dev = "/usr/include/optee"
INSANE_SKIP_${PN}-dev = "staticdev"
