echo "⏳ Aguardando pods ficarem prontos (timeout 5min)..."
if ! kubectl wait --for=condition=Ready pods --all --timeout=300s; then
  echo "❌ Nem todos os pods ficaram prontos no tempo esperado."
  exit 1
fi
echo "✅ Todos os pods estão prontos!"