DEPENDS_append = " ${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'optee-os', '', d)}"

OPTEE_COMPILE_DEPENDS = "${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'optee-os:do_deploy', '', d)}"

do_compile[depends] += "${OPTEE_COMPILE_DEPENDS}"

OPTEE_ENABLE = "${@bb.utils.contains('COMBINED_FEATURES', 'optee', 'true', 'false', d)}"

do_compile_prepend () {
    if ${OPTEE_ENABLE}; then
        cp ${DEPLOY_DIR_IMAGE}/tee.bin             ${S}/${SOC_TARGET}/
    fi
}

do_deploy_append () {
    if ${OPTEE_ENABLE}; then
        install -m 0644 ${DEPLOY_DIR_IMAGE}/tee.bin ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
    fi
}
