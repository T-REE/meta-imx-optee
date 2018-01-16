FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://0001-TEE-249-PL310-unlock-ways-during-initialization.patch \
		 file://0002-OP-TEE-imx8-add-support-for-OP-TEE.patch \
		"

