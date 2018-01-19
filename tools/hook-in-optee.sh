

# Enable OPTEE Layer
echo >> conf/bblayers.conf
echo "Enable the Optee layer "
echo "# For OPTEE" >> conf/bblayers.conf
echo "BBLAYERS += \" \${BSPDIR}/sources/meta-imx-optee \"" >> conf/bblayers.conf
echo >> conf/bblayers.conf
####################

# Configs for OPTEE build

echo "INSANE_SKIP_optee-test = \"dev-deps\"" >> conf/local.conf
echo "FSL_USE_GIT = \"\"" >> conf/local.conf

if [ "${MACHINE}" != "imx8mqevk" ]; then
    if [ "${MACHINE}" != "imx7ulpevk" ]; then
        echo "KERNEL_DEVICETREE_append = \" \${IMX_KERNEL_DEVICETREE_BASE}-optee.dtb \"" >> conf/local.conf
    fi
    echo "UBOOT_CONFIG = \"sd-optee\"" >> conf/local.conf
    echo "UBOOT_CONFIG[sd-optee] = \"\${IMX_UBOOT_CONFIG_BASE}_optee_config,sdcard\"" >> conf/local.conf
fi
echo "DISTRO_FEATURES_append = \" optee \"" >> conf/local.conf


