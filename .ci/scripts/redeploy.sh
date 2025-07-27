#!/bin/bash
set -e

clear

echo "🚀 Iniciando o redeploy do Tech Challenge..."

source ./.ci/scripts/00-get-global-variables.sh

echo "⏳ Excluindo o Minikube..."
minikube delete --profile "$CLUSTER_PROFILE"
echo "✅ Minikube excluído com sucesso!"

source ./.ci/scripts/deploy.sh
