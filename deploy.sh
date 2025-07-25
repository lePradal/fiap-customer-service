#!/bin/bash

# Nome do perfil do Minikube
PROFILE="postech-cluster"

# Caminho do seu projeto com o Dockerfile e os YAMLs
PROJECT_DIR="/c/Users/lepra/OneDrive/Documentos/Projetos/PosTech/projeto-tech-challenge/tech-challenge"

# Nome da imagem
IMAGE_NAME="tech-challenge_app:latest"

echo "➡️ Ativando ambiente Docker do Minikube ($PROFILE)..."
eval $(minikube docker-env --profile "$PROFILE")

echo "➡️ Acessando diretório do projeto..."
cd "$PROJECT_DIR" || { echo "❌ Projeto não encontrado em $PROJECT_DIR"; exit 1; }

echo "🐳 Buildando imagem Docker: $IMAGE_NAME"
docker build -t "$IMAGE_NAME" .

if [ $? -ne 0 ]; then
  echo "❌ Erro ao buildar imagem."
  exit 1
fi

echo "📦 Aplicando manifestos Kubernetes..."
kubectl apply -f .ci/

echo "✅ Deploy completo!"
