_generate_boot_image_append() {
	# add tee image
	bbnote "Adding TEE OS to boot image"
        for UTEE_FILE_PATH in `find ${DEPLOY_DIR_IMAGE} -maxdepth 1 -type f -name 'uTee-*' -print -quit`; do
                UTEE_FILE=`basename ${UTEE_FILE_PATH}`
		bbnote "${UTEE_FILE}"
                mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/${UTEE_FILE} ::/${UTEE_FILE}
        done

}

