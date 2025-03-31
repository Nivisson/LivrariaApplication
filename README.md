                                                    📚 API Livraria (Controle de Autores e Livros)
  📖 Sobre o Projeto
Esta API permite o cadastro, consulta, atualização e remoção de autores e livros. Ela utiliza diferentes estratégias de busca: example para autores e specification para livros. O projeto conta com segurança via Spring Security e uma interface de login gerada com Thymeleaf.

  🚀 Tecnologias Utilizadas
•	Java + Spring Boot (Desenvolvimento da API)
•	PostgreSQL via Docker Toolbox (Banco de Dados)
•	Lombok (Redução de código repetitivo)
•	MapStruct (Mapeamento de DTOs)
•	Hypersistence (Conversão de array de string para array de varchar)
•	JPA (Mapeamento de entidades)
•	Spring Security (Autenticação com formulário de login)
•	Thymeleaf (Geração de tela de login)
  
  ⚙️ Instalação e Configuração
Pré-requisitos
•	Docker Toolbox instalado
•	Java 17+ e Maven instalados
  
  Configuração do Banco de Dados
1.	Suba o container do PostgreSQL com Docker:
2.	docker run --name postgres-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=livraria -p 5432:5432 -d postgres
3.	Configure o application.properties ou application.yml com as credenciais do banco.

  🔍 Endpoints Disponíveis
A API disponibiliza endpoints para manipulação de autores e livros:
📌 Autores
•	GET /autores - Lista todos os autores
•	POST /autores - Cadastra um novo autor
•	PUT /autores/{id} - Atualiza um autor existente
•	DELETE /autores/{id} - Remove um autor
📌 Livros
•	GET /livros - Lista todos os livros
•	POST /livros - Cadastra um novo livro
•	PUT /livros/{id} - Atualiza um livro existente
•	DELETE /livros/{id} - Remove um livro
📌 Usuarios
•	POST /usuarios - Cadastra um novo usuário

  🔑 Segurança e Autenticação
•	A API implementa Spring Security com autenticação via formulário de login.
•	Para acessar determinadas rotas, é necessário estar autenticado.
  💡 Contribuição
Contribuições são bem-vindas! Para contribuir:
  👥 Autor
Desenvolvido por Nivisson Oliveira.
