# Copyright 2017 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Add extra packages for optee build"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690\
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup

IMX_OPTEE_INSTALL = "${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'optee-client optee-os optee-test', '', d)}"

RDEPENDS_${PN} += " \
	${IMX_OPTEE_INSTALL} \
"
