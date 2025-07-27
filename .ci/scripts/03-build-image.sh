echo "🚀 Iniciando o build da imagem Docker do Tech Challenge..."

PROJECT_DIR="$(cd "$(dirname "$0")/../.." && pwd)"
echo "➡️ Acessando diretório do projeto..."
echo "📂 Caminho do projeto: $PROJECT_DIR"
cd "$PROJECT_DIR" || { echo "❌ Projeto não encontrado em $PROJECT_DIR"; exit 1; }

IMAGE_NAME="$APP_LABEL:latest"
echo "🐳 Buildando imagem Docker: $IMAGE_NAME"
docker build -t "$IMAGE_NAME" .
if [ $? -ne 0 ]; then
  echo "❌ Erro ao buildar imagem."
  exit 1
fi

export APP_LABEL