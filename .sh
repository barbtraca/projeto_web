
set -euf
[ "${MVNW_VERBOSE-}" != debug ] || set -x

native_path() { printf %s\\n "$1"; }

case "$(uname)" in
  CYGWIN* | MINGW*)
    [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
    native_path() { cygpath --path --windows "$1"; }
    ;;
esac

set_java_home() {
  if [ -n "${JAVA_HOME-}" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
      JAVACMD="$JAVA_HOME/jre/sh/java"
      JAVACCMD="$JAVA_HOME/jre/sh/javac"
    else
      JAVACMD="$JAVA_HOME/bin/java"
      JAVACCMD="$JAVA_HOME/bin/javac"
    fi
    [ -x "$JAVACMD" ] && [ -x "$JAVACCMD" ] || return 1
  else
    JAVACMD=$(command -v java || :) 
    JAVACCMD=$(command -v javac || :)
    [ -x "${JAVACMD-}" ] && [ -x "${JAVACCMD-}" ] || return 1
  fi
}

hash_string() {
  str="${1:-}" h=0
  while [ -n "$str" ]; do
    char="${str%"${str#?}"}"
    h=$(((h * 31 + $(LC_CTYPE=C printf %d "'$char")) % 4294967296))
    str="${str#?}"
  done
  printf %x\\n $h
}

die() { printf %s\\n "$1" >&2; exit 1; }
trim() { printf "%s" "${1}" | tr -d '[:space:]'; }

while IFS="=" read -r key value; do
  case "${key-}" in
    distributionUrl) distributionUrl=$(trim "${value-}") ;;
    distributionSha256Sum) distributionSha256Sum=$(trim "${value-}") ;;
  esac
done <"${0%/*}/.mvn/wrapper/maven-wrapper.properties"

[ -n "${distributionUrl-}" ] || die "Missing distributionUrl in maven-wrapper.properties"

case "${distributionUrl##*/}" in
  maven-mvnd-*bin.*) MVN_CMD=mvnd.sh ;;
  *) MVN_CMD="mvn${0##*/mvnw}" ;;
esac

distributionUrlName="${distributionUrl##*/}"
distributionUrlNameMain="${distributionUrlName%.*}"
MAVEN_HOME="${HOME}/.m2/wrapper/dists/${distributionUrlNameMain}/$(hash_string "$distributionUrl")"

exec_maven() {
  unset MVNW_VERBOSE MVNW_USERNAME MVNW_PASSWORD MVNW_REPOURL || :
  exec "$MAVEN_HOME/bin/$MVN_CMD" "$@" || die "Cannot execute $MAVEN_HOME/bin/$MVN_CMD"
}

[ -d "$MAVEN_HOME" ] && exec_maven "$@"

TMP_DOWNLOAD_DIR=$(mktemp -d) || die "Cannot create temp dir"
trap 'rm -rf "$TMP_DOWNLOAD_DIR"' EXIT

mkdir -p -- "${MAVEN_HOME%/*}"
verbose() { :; }
[ "${MVNW_VERBOSE-}" != true ] || verbose() { printf %s\\n "$1"; }

verbose "Downloading Maven from $distributionUrl"
curl -fLo "$TMP_DOWNLOAD_DIR/$distributionUrlName" "$distributionUrl" || die "Failed to download Maven"

if [ -n "${distributionSha256Sum-}" ]; then
  echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | sha256sum -c >/dev/null || die "SHA-256 validation failed"
fi

unzip -q "$TMP_DOWNLOAD_DIR/$distributionUrlName" -d "$TMP_DOWNLOAD_DIR" || die "Failed to unzip Maven"
mv -- "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain" "$MAVEN_HOME" || die "Failed to move Maven home"

exec_maven "$@"
