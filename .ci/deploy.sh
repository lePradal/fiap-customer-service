#!/bin/bash

PROFILE="postech-cluster"
PROJECT_DIR="/c/Users/lepra/OneDrive/Documentos/Projetos/PosTech/projeto-tech-challenge/tech-challenge"
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
kubectl apply -f k8s/

tentativas=10
for i in $(seq 1 $tentativas); do
  echo "⏳ Tentativa $i/$tentativas: verificando pods..."
  if kubectl get pods | grep -v NAME | grep -v Running | grep -v Completed; then
    sleep 3
  else
    echo "✅ Todos os pods estão prontos!"
    break
  fi
done

echo "✅ Deploy completo!"