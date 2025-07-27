echo "❗ Atenção: execute o seguinte comando em um **outro terminal** com privilégios de administrador:"
echo ""
echo "    minikube tunnel --profile $CLUSTER_PROFILE"
echo ""
read -p "⏳ Pressione ENTER quando o tunnel estiver ativo para continuar..."

echo "⏳ Aguardando EXTERNAL-IP ser atribuído ao serviço '$SERVICE_NAME'..."

EXTERNAL_IP=""
for i in {1..20}; do
  EXTERNAL_IP=$(kubectl get svc "$SERVICE_NAME" -o jsonpath='{.status.loadBalancer.ingress[0].ip}' 2>/dev/null)
  if [[ -n "$EXTERNAL_IP" ]]; then
    break
  fi
  sleep 2
done

if [[ -z "$EXTERNAL_IP" ]]; then
  echo "❌ EXTERNAL-IP não foi atribuído ao serviço após 40 segundos. Verifique se o tunnel está realmente ativo."
  exit 1
fi

echo "✅ EXTERNAL-IP atribuído: $EXTERNAL_IP"
echo "➡️ Acesse o serviço via: http://$EXTERNAL_IP:8080"