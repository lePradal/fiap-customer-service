#!/bin/bash

echo "🔍 Detectando sistema operacional..."
OS_TYPE="unknown"
case "$(uname -s)" in
  Linux*)   OS_TYPE="linux" ;;
  Darwin*)  OS_TYPE="macos" ;;
  CYGWIN*|MSYS*|MINGW*) OS_TYPE="windows" ;;
esac
echo "Sistema operacional detectado: $OS_TYPE"

echo "🔍 Detectando terminal..."
if [ -n "$BASH_VERSION" ]; then
  if [[ "$MSYSTEM" == MINGW* ]]; then
    SHELL_TYPE="git-bash"
  else
    SHELL_TYPE="bash"
  fi
elif [ -n "$ZSH_VERSION" ]; then
  SHELL_TYPE="zsh"
elif [ -n "$PSModulePath" ]; then
  SHELL_TYPE="powershell"
else
  SHELL_TYPE="desconhecido"
fi
echo "Terminal detectado: $SHELL_TYPE"

export OS_TYPE SHELL_TYPE
