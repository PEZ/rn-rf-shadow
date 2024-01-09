#!/usr/bin/env bash

# If the OS is MacOS, install Java so that the shadow-cljs build succeeds
if [[ $OSTYPE == "darwin"* ]]; then
  brew install openjdk@11
  sudo ln -sfn /opt/homebrew/opt/openjdk@11/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-11.jdk
  echo 'export PATH="/opt/homebrew/opt/openjdk@11/bin:$PATH"' >>~/.zshrc
  export CPPFLAGS="-I/opt/homebrew/opt/openjdk@11/include"
fi
