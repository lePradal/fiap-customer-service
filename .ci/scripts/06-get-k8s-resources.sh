echo "🔍 Obtendo recursos do Kubernetes..."
DEPLOYMENT_NAME=$(kubectl get deployment -n "$KUBERNETES_NAMESPACE" -l app="$APP_LABEL" -o jsonpath="{.items[0].metadata.name}" 2>/dev/null)

if [ -z "$DEPLOYMENT_NAME" ]; then
  echo "❌ Nenhum deployment encontrado com label app=$APP_LABEL"
  exit 1
fi
echo "✅ Deployment encontrado: $DEPLOYMENT_NAME"

SERVICE_NAME=$(kubectl get svc -n "$KUBERNETES_NAMESPACE" -l app="$APP_LABEL" -o jsonpath="{.items[0].metadata.name}" 2>/dev/null)

if [ -z "$SERVICE_NAME" ]; then
  echo "❌ Nenhum service encontrado com label app=$APP_LABEL. Verifique se o service foi criado corretamente."
  exit 1
fi

echo "✅ Service encontrado: $SERVICE_NAME"

export DEPLOYMENT_NAME SERVICE_NAME