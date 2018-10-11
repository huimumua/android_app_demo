//Begin---hello.c
#include</usr/src/linux-headers-3.11.0-26/include/linux/init.h>
#include</usr/src/linux-headers-3.11.0-26/include/linux/module.h>
MODULE_LICENSE("GPL");
//printk(KERN_ALERT "Begin\n");
static int hello_init(void)
{
printk(KERN_ALERT "Hello World!\n");
return 0;
}
static void hello_exit(void)
{
printk(KERN_ALERT "Good bye, ubuntu\n");
// return 0;
}
module_init(hello_init);
module_exit(hello_exit);
//End---hello.c
