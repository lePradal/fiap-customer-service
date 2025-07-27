#!/bin/bash
set -e

echo "🚀 Iniciando o deploy do Tech Challenge..."

source ./.ci/scripts/00-get-global-variables.sh
source ./.ci/scripts/00-detect-env.sh
source ./.ci/scripts/01-start-docker.sh
source ./.ci/scripts/02-start-minikube.sh
source ./.ci/scripts/03-build-image.sh
source ./.ci/scripts/04-apply-manifests.sh
source ./.ci/scripts/05-wait-pods.sh
source ./.ci/scripts/06-get-k8s-resources.sh
source ./.ci/scripts/07-wait-external-ip.sh

echo "✅ Deploy concluído com sucesso!"
