echo "🚀 Iniciando o Docker se necessário..."

if ! docker info > /dev/null 2>&1; then
  if [ "$OS_TYPE" = "linux" ]; then
    echo "🔄 Tentando iniciar Docker no Linux via systemctl..."
    sudo systemctl start docker
  elif [ "$OS_TYPE" = "windows" ] && [ "$SHELL_TYPE" = "git-bash" ]; then
    echo "🔄 Tentando iniciar Docker Desktop no Windows (via Git Bash)..."
    echo "❗ Por favor, certifique-se que o Docker Desktop está instalado e no PATH, ou inicie-o manualmente."
    start "" "C:\Program Files\Docker\Docker\Docker Desktop.exe"
  else
    echo "❗ Docker não detectado e não há forma automática de iniciá-lo para seu SO/Terminal."
    echo "Por favor, inicie o Docker manualmente e execute o script novamente."
    exit 1
  fi

  echo "⏳ Aguardando Docker iniciar..."
  for i in {1..20}; do
    sleep 3
    if docker info > /dev/null 2>&1; then
      echo "✅ Docker iniciado!"
      break
    fi
    if [ "$i" -eq 20 ]; then
      echo "❌ Docker não iniciou após 60 segundos."
      exit 1
    fi
  done
fi

echo "✅ Docker está rodando e pronto para uso!"