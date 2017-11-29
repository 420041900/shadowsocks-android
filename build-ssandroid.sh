#!/bin/bash
# setup ANDROID_HOME for sbt-android plugin
export ANDROID_HOME=/home/daniel/android/tools/sdk

# ANDROID_NDK_HOME can be automatically setup by android sdk if this is empty
#ANDROID_NDK_HOME to /path/to/android-ndk

#compile go source require a working go tool chain. and require to setup GOROOT_BOOTSTRAP point to go install home
export GOROOT_BOOTSTRAP=/usr/lib/golang

cp ../local.properties mobile/

git submodule init
# stick to the locking commit for submodule. do not use update --remote and --recursive.
# as --remote will update submodule to latest of upstream and trying to checkout
# (could failed because no setup tracking upstream branch).
# --recursive will trying to update submodule in each immediate submodule. in case of a submodule
# lies in more than one level directory below, the .gitmodules is not checkout yet. so update is uesless
git submodule update

# mobile/src/main/jni/shadowsocks-libev submodule include submodules: libipset,libbloom and libcork,
# after checkout mobile/src/main/jni/shadowsocks-libev itself, we should checkout its submodules
cd mobile/src/main/jni/shadowsocks-libev
git submodule init
git submodule update

# Build the App
sbt clean go-build android:package-release
