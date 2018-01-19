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
    Optional parameters: [-b build-dir] [-r release] [-m machine] [-h]"
    echo "
    * [-b build-dir]: Build directory, if unspecified, script uses 'build-optee' as the output directory
    * [-m machine]: Machine to build, default imx7dsabresd
    * [-r release]: Select internal or external release 
    * [-h]: help
"
}

optee_cleanup()
{
    echo -e "Cleaning up variables"
    unset BUILD_DIR OPTEEDISTRO RELEASE MACHINE
    unset nxp_setup_help nxp_setup_error nxp_setup_flag
    unset optee_usage optee_cleanup optee_exit_message
}

# get command line options
OLD_OPTIND=$OPTIND

OPTIND=1

echo -e "Reading command line parameters"
# Read command line parameters
while getopts ":b:m:r:h" nxp_setup_flag
do
    case $nxp_setup_flag in
        b) BUILD_DIR="$OPTARG";
           echo -e "\n Build directory is " $BUILD_DIR
           ;;
        m) MACHINE="$OPTARG";
            echo -e "\n Machine is " $MACHINE
            ;;
        r) RELEASE="$OPTARG";
            echo -e "\n Release is " $RELEASE
            ;;
        h) nxp_setup_help='true';
           ;;
        ?) nxp_setup_error='true';
           ;;
    esac
done

OPTIND=$OLD_OPTIND

if [ "$RELEASE" = "external" ]; then
    RELEASEPROGNAME="./fsl-setup-release.sh"
else
    RELEASEPROGNAME="./fsl-setup-internal-build.sh"
fi

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

source ../sources/meta-imx-optee/tools/hook-in-optee.sh

optee_exit_message
optee_cleanup
