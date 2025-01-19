#!/bin/bash

get_latest_version() {
    local module_name=$1
    find ~/.m2/repository/org/openjfx -name "${module_name}-*.jar" | grep -vE "javadoc|sources" | sort -Vr | head -n1
}

case "$OSTYPE" in
  darwin*)  javafx_platform="mac" ;; 
  linux*)   javafx_platform="linux" ;;
  *)        javafx_platform="linux" ;;
esac

FX_BASE_PATH=$(get_latest_version "javafx-base")
FX_CONTROLS_PATH=$(get_latest_version "javafx-controls")
FX_FXML_PATH=$(get_latest_version "javafx-fxml")
FX_GRAPHICS_PATH=$(get_latest_version "javafx-graphics")

FX_PATH="${FX_BASE_PATH}:${FX_CONTROLS_PATH}:${FX_FXML_PATH}:${FX_GRAPHICS_PATH}"

if [[ -z "$FX_PATH" ]]; then
    echo "No se puede encontrar el módulo JavaFX en el repositorio Maven local."
    exit 1
fi

# Configuración de MAVEN_OPTS
export MAVEN_OPTS="--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED -Dfile.encoding=UTF8 --module-path $FX_PATH --add-modules javafx.controls,javafx.fxml,javafx.graphics"

if [[ "$OSTYPE" == "darwin"* ]]; then
    export MAVEN_OPTS="$MAVEN_OPTS -Xdock:icon=./target/classes/icons/iconOSX.png"
fi

# Definir la clase principal y la acción
mainClass="com.biblioteca.Main"  # Clase principal de tu proyecto
action=$1
verbose=0

# Comprobar si el parámetro "verbose" está presente
for arg in "$@"; do
    if [[ "$arg" == "-X" ]]; then
        verbose=1
    fi
done

if [[ "$verbose" -eq 1 ]]; then
    mavenLogLevel="-X" # Modo detallado
else
    mavenLogLevel="-q" # Modo silencioso
fi

if [[ "$action" == "build" ]]; then
    echo "Generando archivo JAR con todas las dependencias..."
    mvn clean package -Dmaven.test.skip=true
    echo "JAR generado en el directorio target."
    if [ -f target/tu-proyecto.jar ]; then
        echo "JAR generado exitosamente: target/tu-proyecto.jar"
    else
        echo "Error al generar el JAR."
        exit 1
    fi
else
    # Ejecutar comando Maven con el perfil y clase principal como argumentos
    execArg="-PrunMain -Dexec.mainClass=$mainClass -Djavafx.platform=$javafx_platform $mavenLogLevel"
    mvn clean test-compile exec:java $execArg
fi
