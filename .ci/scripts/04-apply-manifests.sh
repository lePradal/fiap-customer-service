echo "📦 Aplicando manifestos Kubernetes..."
kubectl apply -f k8s/db/
kubectl apply -f k8s/kafka/
kubectl apply -f k8s/app/
if [ $? -ne 0 ]; then
  echo "❌ Erro ao aplicar manifestos Kubernetes."
  exit 1
fi