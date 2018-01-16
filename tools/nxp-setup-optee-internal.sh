#!/bin/sh
#
# NXP Build Enviroment Setup Script
#
# Copyright (C) 2015-2016 Freescale Semiconductor
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

echo -e "\n----------------\n"
optee_exit_message()
{
   echo -e "Optee setup complete"
}

optee_usage()
{
    echo -e "\nDescription: nxp-setup-optee.sh will setup the bblayers and local.conf for an Optee build."
    echo -e "\nUsage: source nxp-setup-optee.sh
    Optional parameters: [-b build-dir] [-h]"
    echo "
    * [-b build-dir]: Build directory, if unspecified, script uses 'build-optee' as the output directory
    * [-h]: help
"
}

optee_cleanup()
{
    echo -e "Cleaning up variables"
    unset BUILD_DIR OPTEEDISTRO
    unset nxp_setup_help nxp_setup_error nxp_setup_flag
    unset optee_usage optee_cleanup optee_exit_message
}

# get command line options
OLD_OPTIND=$OPTIND

OPTIND=1

echo -e "Reading command line parameters"
# Read command line parameters
while getopts ":b:h" nxp_setup_flag
do
    case $nxp_setup_flag in
        b) BUILD_DIR="$OPTARG";
           echo -e "\n Build directory is " $BUILD_DIR
           ;;
        h) nxp_setup_help='true';
           ;;
        ?) nxp_setup_error='true';
           ;;
    esac
done

RELEASEPROGNAME="./fsl-setup-release.sh"

OPTIND=$OLD_OPTIND


if [ -z "${BUILD_DIR}" ]; then
	BUILD_DIR=build-optee
fi

if [ -z "${MACHINE}" ]; then
	MACHINE="imx7dsabresd"
fi

if [ -z "${OPTEEDISTRO}" ]; then
        OPTEEDISTRO="fsl-imx-x11"
fi


echo EULA=1 DISTRO=$OPTEEDISTRO MACHINE=$MACHINE source $RELEASEPROGNAME -b $BUILD_DIR
EULA=1 DISTRO=$OPTEEDISTRO MACHINE=$MACHINE source $RELEASEPROGNAME -b $BUILD_DIR

echo "Enable the Optee layer "
echo "# For OPTEE" >> conf/bblayers.conf
echo "BBLAYERS += \" \${BSPDIR}/sources/meta-imx-optee \"" >> conf/bblayers.conf

echo "ALLOW_EMPTY_uboot-optee-imx = \"1\"" >> conf/local.conf
echo "INSANE_SKIP_optee-test = \"dev-deps\"" >> conf/local.conf
echo "FSL_USE_GIT = \"\"" >> conf/local.conf
echo "KERNEL_DEVICETREE_append = \" \${IMX_KERNEL_DEVICETREE_BASE}-optee.dtb \"" >> conf/local.conf
echo "UBOOT_CONFIG = \"sd-optee\"" >> conf/local.conf
echo "UBOOT_CONFIG[sd-optee] = \"\${IMX_UBOOT_CONFIG_BASE}_optee_config,sdcard\"" >> conf/local.conf
echo "DISTRO_FEATURES_append = \" optee \"" >> conf/local.conf

echo >> conf/bblayers.conf

echo "# Internal Build servers" >> conf/local.conf
echo "FSL_ARM_GIT_SERVER           = \"\${FSL_ARM_GIT_SERVER_PRIMARY}\"" >> conf/local.conf
echo "FSL_ARM_GIT_SERVER_PRIMARY   = \"bitbucket.sw.nxp.com\"" >> conf/local.conf
echo "FSL_ARM_GIT_SERVER_MIRROR_US = \"bitm-us-cdc01.sw.nxp.com:7999/bitbucket\"" >> conf/local.conf
echo "BITBUCKET_URL = \"git://\${FSL_ARM_GIT_SERVER}\"" >> conf/local.conf
echo "KERNEL_SRC = \"\${BITBUCKET_URL}/imx/linux-imx.git;protocol=ssh\"" >> conf/local.conf
echo "UBOOT_SRC = \"\${BITBUCKET_URL}/imx/uboot-imx.git;protocol=ssh\"" >> conf/local.conf
echo "IMXLIB_SRC = \"\${BITBUCKET_URL}/imx/imx-lib.git;protocol=ssh\"" >> conf/local.conf
echo "IMXTEST_SRC = \"\${BITBUCKET_URL}/imx/linux-test.git;protocol=ssh\"" >> conf/local.conf
echo "IMX_FIRMWARE_SRC = \"\${BITBUCKET_URL}/imx/imx-firmware.git;protocol=ssh\"" >> conf/local.conf
echo "IMX_MKIMAGE_SRC = \"\${BITBUCKET_URL}/imx/imx-mkimage.git;protocol=ssh\"" >> conf/local.conf
echo "ATF_SRC = \"\${BITBUCKET_URL}/imx/arm-trusted-firmware.git;protocol=ssh\"" >> conf/local.conf
echo "XF86_VIDEO_IMX_VIVANTE_SRC = \"\${BITBUCKET_URL}/gtec/linux-x-server-viv.git;protocol=ssh\""  >> conf/local.conf
echo "GPU_SDK_SRC = \"\${BITBUCKET_URL}/gtec/demo-framework.git;protocol=ssh\"" >> conf/local.conf
echo "# For SDK release builds" >> conf/local.conf
echo "GPU_SDK_SRC_BRANCH = \"release/github/master\"" >> conf/local.conf
echo "# For SDK release candidate builds" >> conf/local.conf
echo "#GPU_SDK_SRC_BRANCH = \"release/github/RC\"" >> conf/local.conf
echo "# For builds prior to first SDK release candidate" >> conf/local.conf
echo "#GPU_SDK_SRC_BRANCH = \"release/5.0.0\"" >> conf/local.conf
echo "IMXGST_SRC = \"\${BITBUCKET_URL}/mmcsh/gst1.0-plugins-fsl.git;protocol=ssh\"" >> conf/local.conf
echo "IMXALSA_SRC = \"\${BITBUCKET_URL}/mmcsh/fsl-alsa-plugins.git;protocol=ssh\"" >> conf/local.conf
echo "SIMG2IMG_SRC = \"\${BITBUCKET_URL}/imx/simg2img.git;protocol=ssh\"" >> conf/local.conf
echo "APITRACE_SRC = \"\${BITBUCKET_URL}/gtec/apitrace-imx.git;protocol=ssh\"" >> conf/local.conf
echo "GPUTOP_SRC = \"\${BITBUCKET_URL}/gtec/gputop.git;protocol=ssh\"" >> conf/local.conf
echo "WESTON_SRC = \"\${BITBUCKET_URL}/gtec/weston-imx.git;protocol=ssh\"" >> conf/local.conf
echo "IMX_LIBDRM_SRC = \"\${BITBUCKET_URL}/gtec/libdrm-imx.git;protocol=ssh\"" >> conf/local.conf
# Internal GST fork
echo "BITBUCKET_SM_URL = \"gitsm://\${FSL_ARM_GIT_SERVER}\"" >> conf/local.conf
echo "GST1.0_SRC = \"\${BITBUCKET_SM_URL}/mmcsh/gstreamer.git;protocol=ssh\"" >> conf/local.conf
echo "GST1.0-LIBAV_SRC = \"\${BITBUCKET_SM_URL}/mmcsh/gst-libav.git;protocol=ssh\"" >> conf/local.conf
echo "GST1.0-PLUGINS-BAD_SRC = \"\${BITBUCKET_SM_URL}/mmcsh/gst-plugins-bad.git;protocol=ssh\"" >> conf/local.conf
echo "GST1.0-PLUGINS-BASE_SRC = \"\${BITBUCKET_SM_URL}/mmcsh/gst-plugins-base.git;protocol=ssh\"" >> conf/local.conf
echo "GST1.0-PLUGINS-GOOD_SRC = \"\${BITBUCKET_SM_URL}/mmcsh/gst-plugins-good.git;protocol=ssh\"" >> conf/local.conf
echo "GST1.0-PLUGINS-UGLY_SRC = \"\${BITBUCKET_SM_URL}/mmcsh/gst-plugins-ugly.git;protocol=ssh\"" >> conf/local.conf

echo "NXP_REPO_MIRROR = \"\"" >> conf/local.conf



optee_exit_message
optee_cleanup
