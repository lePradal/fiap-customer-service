echo "➡️ Verificando status do Minikube ($CLUSTER_PROFILE)..."

check_minikube_status() {
  STATUS=$(minikube status --profile "$CLUSTER_PROFILE" 2>/dev/null)
  if echo "$STATUS" | grep -q "host: Running" && \
     echo "$STATUS" | grep -q "kubelet: Running" && \
     echo "$STATUS" | grep -q "apiserver: Running" && \
     echo "$STATUS" | grep -q "kubeconfig: Configured"; then
    return 0
  fi
  return 1
}

if ! check_minikube_status; then
  echo "🔄 Minikube não está rodando ou não está saudável. Tentando iniciar..."
  if ! minikube start --profile "$CLUSTER_PROFILE" --cpus=2 --memory=2g; then
    echo "❌ Erro ao iniciar Minikube. Verifique instalação e recursos do sistema."
    exit 1
  fi

  echo "✅ Minikube iniciado. Aguardando API Kubernetes..."
  MAX_ATTEMPTS=20
  WAIT_TIME=5

  for i in $(seq 1 $MAX_ATTEMPTS); do
    if check_minikube_status; then
      echo "✅ API do Kubernetes está respondendo após $i tentativas."
      break
    else
      echo "⏳ Tentativa $i/$MAX_ATTEMPTS: API não pronta. Aguardando $WAIT_TIME s..."
      sleep "$WAIT_TIME"
    fi

    if [ "$i" -eq "$MAX_ATTEMPTS" ]; then
      echo "❌ Minikube iniciou, mas API Kubernetes não ficou pronta após $MAX_ATTEMPTS tentativas."
      echo "Por favor, verifique 'minikube status --profile $CLUSTER_PROFILE' e os logs do Minikube para mais detalhes."
      exit 1
    fi
  done
else
  echo "✅ Minikube já está rodando e saudável."
fi

echo "➡️ Configurando kubectl para perfil Minikube..."
minikube update-context --profile "$CLUSTER_PROFILE" || { echo "❌ Falha ao configurar contexto kubectl."; exit 1; }

echo "➡️ Ativando ambiente Docker do Minikube ($CLUSTER_PROFILE)..."
if [ "$SHELL_TYPE" = "powershell" ]; then
  echo "⚠️ Script não suporta ativação automática do Docker do Minikube no PowerShell."
  echo "Execute manualmente: minikube docker-env --profile $CLUSTER_PROFILE | Invoke-Expression"
else
  eval "$(minikube docker-env --profile "$CLUSTER_PROFILE" 2>/dev/null)"
fi

export CLUSTER_PROFILE