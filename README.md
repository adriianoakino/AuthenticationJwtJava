# Sistema de autenticação e criptografia da senha do banco de dados utilizando o Jasypt feito em Spring Boot;
- Jasypt;
- application developers, criptografia senha do banco de dados;
- Jwt;
- BCryptPasswordEncoder;
- Hibernate;

# Criptografia Jasypt, criação e configuração do Application-dev.properties
## Gerando hash com o Jasypt
- Vá até a pasta Jasypt e extraia os zip.
- dentro da pasta utilize o CMD, e execute os seguintes comandos para criptograr a sua senha do banco de dados;
```
java -cp jasypt-1.9.0.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="Senha do banco" password=SenhaParaJasypt algorithm=PBEWithMD5AndDES
```
- Este comando irá gerar no CMD o seguintes texto:
```
----ENVIRONMENT-----------------

Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 25.212-b10



----ARGUMENTS-------------------

algorithm: PBEWithMD5AndDES
input: Senha do seu banco
password: SimplePassword



----OUTPUT----------------------

u9Q+NP31hsvFd0qSk1P4-VMH9JhmmMo2faLQod
```

- para descriptografar sua senha utilize o comando:
```
java -cp jasypt-1.9.0.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input="senha criptografada" password=SenhaDoJasypt algorithm=PBEWithMD5AndDES
```
## Configurando o Application-dev para executar como principal no Spring Security
- Vá até seu pom.xml e coloque as seguintes dependencias.
```
    <dependency>
        	<groupId>com.github.ulisesbocchio</groupId>
        	<artifactId>jasypt-spring-boot</artifactId>
        	<version>2.1.2</version>
		</dependency>
    
		<dependency>
			<groupId>com.github.ulisesbocchio</groupId>
			<artifactId>jasypt-spring-boot-starter</artifactId>
			<version>2.0.0</version>
		</dependency>
```
- Vá até a classe de excução principal do seu código e coloque a seguinte anotação
```
@EncryptablePropertySource(name = "EncryptedPropertiesDev", value = "classpath:application-dev.properties")
```
Onde o name = representa o nome para o application, e o value  = o nome do application que criou

A classe deve ficar desse modo: 
```
@SpringBootApplication
@EncryptablePropertySource(name = "EncryptedPropertiesDev", value = "classpath:application-dev.properties")
public class ProjetoSistemasInformacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSistemasInformacoesApplication.class, args);
	}

}

```
- Já no seu application properties criado vamos utilizar a senha gerada pelo Jasypt, basta utilizar o ENC('senha gerada'):
`
spring.datasource.password 			= 	ENC(u9Q+NPhsvFd0qSk1P4VH9JhmmMofLQod)
`


# Postman Rotas
  # Registro
    http://localhost:3000/auth/signin
    # Objeto
      {
        "username": "username",
        "senha": "123456",
        "perfil": "USER"
      }
  # Login
    http://localhost:3000/auth/signup
    # Objeto
      {
        "username": "username",
        "senha": "123456",
      }
