# VoceAluga

## Conectando Java com o MYSQL
- É necessário instalar na máquina um driver que você pode baixar em https://dev.mysql.com/downloads/connector/j/
- É possível também baixar diretamente o código fonte do 'connector' e compilá-lo com alguma configuração prévia desejada (não fizemos isso)
- É necessário incluir o pacote .jar (compilado ou baixado) na variável CLASSPATH do sistema
- No Ubuntu: export CLASSPATH=$CLASSPATH:path/mysql-connector-java-version.jar
- No Windows usa-se o Painel de controle do sistema para acessar as variáveis do sistema
- Em vez de incluir na variável do sistema, é possível também adicionar o pacote .jar na hora de executar o programa com 
algo semelhante a isso (rodando a classe main <code>ExemploSQL</code>, incluida neste rep):<br>
<code>java -cp mysql-connector-java-version/mysql-connector-java-version.jar:. ExemploSQL</code>


## JavaFX no eclipse
- No eclipse: Help -> Install New Software...
- Em Work With colocar a opção com o seu eclipse+ http://download.eclipse.org/releases/  , por exemplo: Neon-http://download.eclipse.org/releases/neon
- Filtrar texto com "e(fx)clipse"
- Selecionar e(fx)clipse - IDE e baixar

- Baixar JavaFX Scene Builder em https://www.oracle.com/technetwork/java/javafxscenebuilder-1x-archive-2199384.html#javafx-scenebuilder-2.0-oth-JPR
- Após extrair, ir no eclipse: Window -> Preferences -> JavaFX, e colocar o executável do Scene Builder que está dentro da pasta que baixou.

- Se no código o eclipse ainda não estiver aceitando o javafx, clica com o direito no projeto com javafx e em Build Path -> Add Libraries... -> JRE System Library -> Alternate JRE.
- Caso n tenha outra opção em JRE, baixe https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html.
- Após extrair a pasta, de volta a janela de Add Library escolher a opção Installed JREs... -> Search e seleciona a pasta que acabou de baixar.
