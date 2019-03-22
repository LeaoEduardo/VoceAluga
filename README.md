# VoceAluga

## Conectando Java com o MYSQL
- É necessário instalar na máquina um driver que você pode baixar em https://dev.mysql.com/downloads/connector/j/
- É possível também baixar diretamente o código fonte do 'connector' e compilá-lo com alguma configuração prévia desejada (não fizemos isso)
- É necessário incluir o pacote .jar (compilado ou baixado) na variável CLASSPATH do sistema
- No Ubuntu: export CLASSPATH=$CLASSPATH:path/mysql-connector-java-version.jar
- No Windows usa-se o Painel de controle do sistema para acessar as variáveis do sistema
- Em vez de incluir na variável do sistema, é possível também adicionar o pacote .jar na hora de executar o programa com 
algo semelhante a isso (rodando a classe main <code>Exemplo</code>, incluida neste rep):<br>
<code>java -cp mysql-connector-java-version/mysql-connector-java-version.jar:. Exemplo</code>
