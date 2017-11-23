FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://0001-TEE-249-PL310-unlock-ways-during-initialization.patch"
