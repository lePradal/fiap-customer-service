#!/bin/bash
set -e

clear

echo "🚀 Iniciando o stop do Tech Challenge..."

source ./.ci/scripts/00-get-global-variables.sh

minikube stop --profile postech-cluster
echo "✅ Minikube parado com sucesso!"
