# Copyright 2018 NXP

#Currently only i.MX 8M is supporting OP-TEE addition.
EXTRA_OEMAKE_mx8mq += "${@bb.utils.contains('DISTRO_FEATURES', 'optee', 'SPD=opteed', '', d)}"
