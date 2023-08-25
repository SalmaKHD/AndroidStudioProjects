package com.salmakhd.android.forpractice

/*
The general structure that is used for mapping virtual addresses to physical addresses typically is composed of the following
building blocks:
- pages in the virtual address space of a process
- physical frame numbers (PFNs)
- Translation Lookaside Buffers (TLBs)
- page tables
To better understand this design, we may roughly define the steps that need to be taken to translate a virtual address into
a physical one lying in main memory:
1. a process requesting to access or write to an address in its address space
2. the OS looking for the corresponding page table among other page directory entries (i.e., PDEs) with respect to the process segment
encoded in the virtual address (the common format of each virtual address would be *seg-VPN-offset*).
3. finding the page frame number of the virtual address by looking up the page it belongs to in the respective page table.
4. adding the offset to the base register's value to calculate the exact address.
There are some points to ponder when it comes to translating addresses including the precedure the OS has to undergo to allow a process to actually make
changes or read from the supplied address. It's worth noting that this mechanism is particularly known as multi-level (two-leve) page tables, and it is among the most complex designs we studied this week.
A simpler approach could be to have one big page table constituted of entries of pages currently in memory and make use of TLBs for faster retrieval of
data using virtual addresses. Bits stored in TLBs would be composed of *VPN-PEN-otherBits* to identify the target row in the page table. We would have to use the base address and the offset once again to
complete the translation.
One last mechanism is to insert al; PFNs into a table and map them to virtual pages associated with each process, but introducing multiple levels of tables
enhances flexibility by allowing us to utilize more factors for grouping these tables such as process segments mentioned earlier.
If I were to devise my own mechanism for these mappings, I would

3.
 */


/*
Exploring the File System on the Linux Operating System
Navigating through our files on our working machine is one of the most common operations we carry out when
operating them. This clearly explains why being cognizant of most, if not all, of the methods that have been made
available for Linux OS users to work with file systems and detect their exact types effectively and easily is an important step toward
diagnosing potential problems with mounting filesystems as well as harnessing their features. This paper aims at expounding two of
the best methods that users can employ to identify the filesystem of various device nodes on the Linux operating system with the
help of some screenshots.
The first method requires us to issue the Isblk -f command in our terminal to see  a list of all the devices and storage
devices along with the filesystem associated with each. The -e7 option can be used for eliminating loop devices for achieving a neater
output. As shown in the screenshot below, there is a single device on mounted on my computer which has 3 parttions. The filesystem type of the the two
last partitions re vfat and ext4 respectively. The subsequnet columns represent the label set to every partition or deevice, the Universally Unique Id for
identifying each partition uniquely the amount of free space available, the percentage of the used space and the absolute path to the directory to which
the node is mounted.
The second program we can run to find out the filesystem of each node is the well-known mount command. Since the output of this command is way
more than we need for casual reasons, it is better to use the "grep" utility to filter the output and find the filesyatem type of
specific devices or partitions. For instance, if we would like to know the filesyatem type of a partition named "/dev/sda3", we can
enter the folowing command in our terminal: mount | grep "/dev/sda3". The screenshot below depicts the output of this command:

One interesting thing that I noticed is that there are 2 nodes with the same exact name on my computer with different mount points. One more interesting point
is that they happen to have the same filesystem type. This may be mere coincidence, or these two nodes may be closely related to one another. A quick search
online shows that the first node is while the second one is

This paper investigated two important command-line tools for identifying the filesystem types of all the currently mounted (i.e., node and filesystem ready to be used) devices
and partitions on the Linux operating system. This task may seem trivial now, but when encountering problems such as not having access to the filesystem of a
connected device among other issues, we will soon appreciate what these tools are actually capable of doing.

 */


class DiscussionForum {
}