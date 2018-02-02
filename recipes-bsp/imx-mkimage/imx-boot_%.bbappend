DEPENDS += "optee-os"

do_compile_prepend () {
    if [ "${SOC_TARGET}" = "iMX8M" ]; then
        cp ${DEPLOY_DIR_IMAGE}/tee.bin             ${S}/${SOC_TARGET}/
    fi
}

do_deploy_append () {
    if [ "${SOC_TARGET}" = "iMX8M" ]; then
        install -m 0644 ${DEPLOY_DIR_IMAGE}/tee.bin ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
    fi
}
