#Begin---Makefile
KERNELDIR=/lib/modules/3.11.0-26-generic/build
PWD:=$(shell pwd)
INSTALLDIR=/home/tiny.hui/workspace/driver_test/install
obj-m:= hello.o
modules:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) modules
modules_install:
	cp hello.ko $(INSTALLDIR)
clean:
	rm -rf *.o *~ core .depend .*.cmd *.ko *.mod.c .tmp_versions
.PHONY: modules modules_install clean
#End---Makefile
