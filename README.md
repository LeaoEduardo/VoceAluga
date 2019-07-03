# VoceAluga

## Conectando Java com o MYSQL
- É necessário instalar na máquina um driver que você pode baixar em https://dev.mysql.com/downloads/connector/j/
- É possível também baixar diretamente o código fonte do 'connector' e compilá-lo com alguma configuração prévia desejada (não fizemos isso)
- É necessário incluir o pacote .jar (compilado ou baixado) na variável CLASSPATH do sistema
- No Ubuntu: export CLASSPATH=$CLASSPATH:path/mysql-connector-java-version.jar
- No Windows usa-se o Painel de controle do sistema para acessar as variáveis do sistema
- Em vez de incluir na variável do sistema, é possível 'linkar' o arquivo .jar do connector pelo eclipse em Properties(do projeto VoceAluga)->Java Build Path->Libraries->Add External Jars...


## JavaFX no eclipse
- O pacote JavaFx está incluso na versão 1.8+ do Java Standard Edition
- O Openjdk 8 **Não** tem o pacote do JavaFX embutido.  
- Download do Java Standard Edition 8: https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html.
- (Caso não tenha/queira fazer uma conta Oracle: https://github.com/frekele/oracle-java/releases )
- Clique com o botão direito no projeto java do eclipse e em Build Path -> Add Libraries... -> JRE System Library -> Alternate JRE -> Adicionar a pasta que acabou de baixar como a JRE.


## Integração do SceneBuilder no eclipse
- Baixar JavaFX Scene Builder em https://www.oracle.com/technetwork/java/javafxscenebuilder-1x-archive-2199384.html#javafx-scenebuilder-2.0-oth-JPR
- No eclipse: Help -> Install New Software...
- Em Work With colocar a opção com o seu eclipse+ http://download.eclipse.org/releases/  , por exemplo: Neon-http://download.eclipse.org/releases/neon
- Filtrar texto com "e(fx)clipse"
- Selecionar e(fx)clipse - IDE e baixar
- Após extrair, ir no eclipse: Window -> Preferences -> JavaFX, e colocar o executável do Scene Builder que está dentro da pasta que baixou.
